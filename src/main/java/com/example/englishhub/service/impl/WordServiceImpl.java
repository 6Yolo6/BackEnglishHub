package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Word;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.entity.WordReviewVO;
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
        System.out.println("单词列表" + wordList);
        return transform.listToPage(wordList, pageNum, pageSize);
    }

    @Override
    public List<WordReviewVO> searchByName(String word) {
        // 模糊查询15条
        return wordMapper.searchByName(word);
    }

    @Override
    public List<WordReviewVO> findWordsByNames(List<String> words) {
        return wordMapper.findWordsByNames(words);
    }

    @Override
    public WordReviewVO getDetail(Integer wordId) {
        return wordMapper.getDetail(wordId);
    }
}
