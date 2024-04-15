package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.entity.Words;
import com.example.englishhub.mapper.WordsMapper;
import com.example.englishhub.service.WordsService;
import com.example.englishhub.utils.JwtUtil;
import com.example.englishhub.utils.MD5Util;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 单词表 服务实现类
 * </p>
 *
 * @author hahaha
 */
@Service
public class WordsServiceImpl extends ServiceImpl<WordsMapper, Words> implements WordsService {


    @Override
    public Page<Words> getAllWords(Integer pageNum, Integer pageSize) {
        Page<Words> page = new Page<>(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    public Page<Words> searchByName(String word, Integer pageNum, Integer pageSize) {
        QueryWrapper<Words> wrapper = new QueryWrapper<>();
        Page<Words> page = new Page<>(pageNum, pageSize);
        wrapper.like("word", word);
        return this.page(page, wrapper);
    }

    @Override
    public Words updateWords(Words words) {
        return null;
    }

    @Override
    public void deleteByIds(String ids) {

    }
}
