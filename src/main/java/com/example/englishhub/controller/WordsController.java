package com.example.englishhub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Words;
import com.example.englishhub.service.WordsService;
import com.example.englishhub.utils.JwtUtil;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.englishhub.utils.RedisUtils;

@RestController
@RequestMapping("/words")
@Tag(name = "单词管理")
public class WordsController {

    @Autowired
    private WordsService wordsService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取单词列表
     */
    @Operation(summary = "根据获取单词列表")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<Words> wordsList = wordsService.page(new Page<>(pageNum, pageSize));
        result.setData(wordsList);
        return result;
    }

    // 分页搜索单词
//    @Operation(summary = "根据单词名搜索单词")
//    @GetMapping("/searchByName")
//    public Result searchByName(String word, Integer pageNum, Integer pageSize) {
//        Result result = new Result();
//        Page<Words> wordsPage = wordsService.searchByName(word, pageNum, pageSize);
//        result.setData(wordsPage);
//        // 缓存到redis，记录用户搜索历史
//        String token = request.getHeader("token");
//        if (token == null) {
//            return result;
//        }
//        String userId = JwtUtil.validateToken(token);
//        redisUtils.zAdd("searchHistory:" + userId, word, System.currentTimeMillis());
//        return result;
//    }

    // 根据单词名搜索单词
    @Operation(summary = "根据单词名搜索单词")
    @GetMapping("/searchByName")
    public Result searchByName(String word) {
        Result result = new Result();
        result.setData(wordsService.searchByName(word));
        // 缓存到redis，记录用户搜索历史
        String token = request.getHeader("token");
        if (token == null) {
            return result;
        }
        String userId = JwtUtil.validateToken(token);
        redisUtils.zAdd("searchHistory:" + userId, word, System.currentTimeMillis());
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
        result.setData(redisUtils.zReverseRange("searchHistory:" + userId, 0, 9));
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
}
