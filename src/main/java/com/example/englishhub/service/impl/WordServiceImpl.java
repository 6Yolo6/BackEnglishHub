package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Word;
import com.example.englishhub.mapper.WordMapper;
import com.example.englishhub.service.WordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.utils.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 基本单词表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-13
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    @Autowired
    private WordMapper wordMapper;


    @Override
    public Page<Word> getPageByWordBookId(Integer wordBookId, Integer pageNum, Integer pageSize) {
        Transform transform = new Transform();
        int offset = (pageNum - 1) * pageSize;
        List<Word> wordList = wordMapper.getPageByWordBookId(wordBookId, offset, pageSize);
        return transform.listToPage(wordList, pageNum, pageSize);
    }
}
