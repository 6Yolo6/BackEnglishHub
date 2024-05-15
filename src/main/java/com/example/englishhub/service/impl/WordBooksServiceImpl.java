package com.example.englishhub.service.impl;

import com.example.englishhub.entity.WordBooks;
import com.example.englishhub.entity.WordBooksVO;
import com.example.englishhub.mapper.WordBooksMapper;
import com.example.englishhub.service.WordBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 单词库表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-12
 */
@Service
public class WordBooksServiceImpl extends ServiceImpl<WordBooksMapper, WordBooks> implements WordBooksService {

    @Autowired
    private WordBooksMapper wordBooksMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public WordBooksVO getByUserId() {
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);
        return wordBooksMapper.getByUserId(Integer.parseInt(userId));
    }
}
