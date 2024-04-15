package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.User;
import com.example.englishhub.entity.Words;

import java.util.Map;
public interface WordsService extends IService<Words> {
    Page<Words> getAllWords(Integer pageNum, Integer pageSize);

    Page<Words> searchByName(String word, Integer pageNum, Integer pageSize);

    Words updateWords(Words words);

    void deleteByIds(String ids);
}
