package com.example.englishhub.service.impl;

import com.example.englishhub.entity.VocabularyBook;
import com.example.englishhub.entity.WordReviewVO;
import com.example.englishhub.mapper.VocabularyBookMapper;
import com.example.englishhub.service.VocabularyBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 生词本表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-17
 */
@Service
public class VocabularyBookServiceImpl extends ServiceImpl<VocabularyBookMapper, VocabularyBook> implements VocabularyBookService {

    @Autowired
    private VocabularyBookMapper vocabularyBookMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public VocabularyBook add(Integer wordId) {
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);

        VocabularyBook vocabularyBook = new VocabularyBook();
        vocabularyBook.setUserId(Integer.parseInt(userId));
        vocabularyBook.setWordId(wordId);
        this.save(vocabularyBook);

        return vocabularyBook;
    }

    @Override
    public List<WordReviewVO> getByUser() {
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);
        return vocabularyBookMapper.getByUser(Integer.parseInt(userId));
    }
}
