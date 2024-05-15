package com.example.englishhub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Voiceovers;
import com.example.englishhub.entity.VoiceoversVO;
import com.example.englishhub.service.VoiceoversService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 每日一句配音表(Voiceovers)表控制层
 *
 * @author makejava
 * @since 2024-05-11 16:46:09
 */

@RestController
@RequestMapping("voiceovers")
@Tag(name = "每日一句配音管理")
public class VoiceoversController {
    /**
     * 服务对象
     */
    @Autowired
    private VoiceoversService voiceoversService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @GetMapping("/getPage")
    @Operation(summary = "分页查询所有每日一句配音")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<VoiceoversVO> voiceoversPage = voiceoversService.getAllVoiceovers(pageNum, pageSize);
        result.setData(voiceoversPage);
        result.success("查询所有每日一句配音成功");
        return result;
    }


    @GetMapping("/getPageBySentenceId")
    @Operation(summary = "通过句子ID查询每日一句配音")
    public Result getPageBySentenceId(Integer sentenceId, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<VoiceoversVO> voiceoversPage = voiceoversService.getAllVoiceoversBySentenceId(sentenceId, pageNum, pageSize);
        result.setData(voiceoversPage);
        result.success("通过句子ID查询每日一句配音成功");
        return result;
    }


    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/getPageByUserId")
    @Operation(summary = "通过用户ID查询每日一句配音")
    public Result getPageByUserId(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<VoiceoversVO> voiceoversPage = voiceoversService.getAllVoiceoversByUserId(pageNum, pageSize);
        result.setData(voiceoversPage);
        result.success("通过用户ID查询每日一句配音成功");
        return result;
    }


    /**
     * 新增配音点赞
     *
     * @param voiceoverId 配音ID
     * @return 新增结果
     */
    @PostMapping("/like")
    @Operation(summary = "新增配音点赞")
    public Result like(@RequestParam Integer voiceoverId) {
        Result result = new Result();
        if (voiceoversService.like(voiceoverId)) {
            result.success("新增配音点赞成功");
        } else {
            result.fail("新增配音点赞失败");
        }
        return result;
    }

    /**
     * 修改数据
     *
     * @param voiceovers 实体对象
     * @return 修改结果
     */


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

