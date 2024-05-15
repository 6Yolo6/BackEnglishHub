package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.EBooksVO;
import com.example.englishhub.entity.EBooks;

/**
 * 电子书表(EBooks)表服务接口
 *
 * @author makejava
 * @since 2024-04-14 20:51:34
 */
public interface EBooksService extends IService<EBooks> {


    Page<EBooksVO> getAllEBooks(Integer pageNum, Integer pageSize);

    boolean addEBook(EBooks eBooks);

}

