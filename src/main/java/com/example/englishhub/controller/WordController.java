package com.example.englishhub.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Word;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.entity.WordReviewVO;
import com.example.englishhub.service.WordService;
import com.example.englishhub.utils.JwtUtil;
import com.example.englishhub.utils.RedisUtils;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 基本单词表(Word)表控制层
 *
 * @author makejava
 * @since 2024-05-13 22:46:18
 */
@Tag(name = "基本单词管理")
@RestController
@RequestMapping("/word")
public class WordController {
    /**
     * 服务对象
     */
    @Autowired
    private WordService wordService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 根据wordBookId分页查询所有数据
     *
     * @param wordBookId 单词书ID
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @GetMapping("/getPageByWordBookId")
    public Result getPageByWordBookId(Integer wordBookId, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<Word> wordPage = wordService.getPageByWordBookId(wordBookId, pageNum, pageSize);
        System.out.println(wordPage.getRecords());
        result.setData(wordPage);
        result.success("查询所有单词成功");
        return result;
    }


    /**
     * 通过word查询单词列表15条
     *
     * @param word 单词
     * @return 数据
     */
    @Operation(summary = "根据单词名搜索单词15条")
    @GetMapping("/searchByName")
    public Result searchByName(String word) {
        Result result = new Result();
        List<WordReviewVO> wordList = wordService.searchByName(word);

        // 缓存到redis，记录用户搜索历史
        String token = request.getHeader("token");
        if (token == null) {
            result.fail("用户未登录");
            return result;
        }
        String userId = JwtUtil.validateToken(token);
        redisUtils.zAdd("searchHistory:" + userId, word, System.currentTimeMillis());

        result.setData(wordList);
        result.success("查询单词成功");
        return result;
    }

    // 获取用户搜索历史
    @Operation(summary = "获取用户搜索历史")
    @GetMapping("/getSearchHistory")
    public Result getSearchHistory() {
        Result result = new Result();
        String token = request.getHeader("token");
        if (token == null) {
            result.fail("用户未登录");
            return result;
        }
        String userId = JwtUtil.validateToken(token);
        // 为了保证搜索历史最多只有10条，当搜索历史超过10条时，删除最早的搜索历史
        if (redisUtils.zSize("searchHistory:" + userId) > 10) {
            redisUtils.zRemoveRange("searchHistory:" + userId, 0, redisUtils.zSize("searchHistory:" + userId) - 11);
        }

        // zReverseRange是按照分数从大到小的顺序返回指定区间内的元素，0表示第一个元素，-1表示最后一个元素
        List<String> searchHistoryWords = redisUtils.zReverseRange("searchHistory:" + userId, 0, 9)
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        result.setData(wordService.findWordsByNames(searchHistoryWords));
//        result.setData(searchHistoryWords);
        result.success("成功获取用户搜索历史");
        return result;
    }

    // 清空用户搜索历史
    @Operation(summary = "清空用户搜索历史")
    @PostMapping("/clearSearchHistory")
    public Result clearSearchHistory() {
        Result result = new Result();
        String token = request.getHeader("token");
        if (token == null) {
            result.fail("用户未登录");
            return result;
        }
        String userId = JwtUtil.validateToken(token);
        redisUtils.zRemoveRange("searchHistory:" + userId, 0, -1);
        result.success("成功清空用户搜索历史");
        return result;
    }

    /**
     * 根据wordId查询数据
     *
     * @param wordId 单词ID
     * @return 单条数据
     */
    @Operation(summary = "根据wordId查询单词")
    @GetMapping("/getDetail")
    public Result getDetail(Integer wordId) {
        Result result = new Result();
        WordReviewVO word = wordService.getDetail(wordId);
        result.setData(word);
        result.success("查询单词成功");
        return result;
    }

    /**
     * 新增数据
     *
     * @param word 实体对象
     * @return 新增结果
     */


    /**
     * 修改数据
     *
     * @param word 实体对象
     * @return 修改结果
     */


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

