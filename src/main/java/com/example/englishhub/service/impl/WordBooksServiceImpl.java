package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.WordBooks;
import com.example.englishhub.entity.WordBooksVO;
import com.example.englishhub.mapper.WordBooksMapper;
import com.example.englishhub.service.WordBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Page<WordBooks> getByCategoryId(Integer categoryId, Integer pageNum, Integer pageSize) {
        QueryWrapper<WordBooks> wrapper = new QueryWrapper<>();
        // 判断id是否为0，如果为0则查询所有
        if (categoryId != 0) {
            wrapper.eq("category_id", categoryId);
        }
        return this.page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public List<WordBooks> getByCate(Integer categoryId) {
        QueryWrapper<WordBooks> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        return this.list(wrapper);
    }
}
