package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.entity.Articles;
import com.example.englishhub.mapper.ArticlesMapper;
import com.example.englishhub.service.ArticlesService;
import org.springframework.stereotype.Service;

/**
 * 文章资源表(Articles)表服务实现类
 *
 * @author makejava
 * @since 2024-04-11 16:10:53
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService {

}

