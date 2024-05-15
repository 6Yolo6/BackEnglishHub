package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.englishhub.entity.DailySentences;
import com.example.englishhub.mapper.DailySentencesMapper;
import com.example.englishhub.service.DailySentencesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 每日一句表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-10
 */

@Service
public class DailySentencesServiceImpl extends ServiceImpl<DailySentencesMapper, DailySentences> implements DailySentencesService {

    @Override
    public DailySentences getByDate(String date) {
        QueryWrapper<DailySentences> wrapper = new QueryWrapper<>();
        // 实体类属性是LocalDateTime类型，前端传过来的是String类型
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date + " 00:00:00", formatter);

        wrapper.eq("date", dateTime);
        return this.getOne(wrapper);
    }
}
