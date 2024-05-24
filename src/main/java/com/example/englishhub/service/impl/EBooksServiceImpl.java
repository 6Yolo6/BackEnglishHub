package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.entity.EBooksVO;
import com.example.englishhub.entity.EBooks;
import com.example.englishhub.mapper.EBookSeriesMapper;
import com.example.englishhub.mapper.EBooksMapper;
import com.example.englishhub.service.EBooksService;
import com.example.englishhub.utils.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电子书表(EBooks)表服务实现类
 *
 * @author makejava
 * @since 2024-04-14 20:51:34
 */
@Service
public class EBooksServiceImpl extends ServiceImpl<EBooksMapper, EBooks> implements EBooksService {


    @Autowired
    private EBooksMapper eBooksMapper;

    @Autowired
    private EBookSeriesMapper eBookSeriesMapper;

    @Override
    public Page<EBooksVO> getAllEBooks(Integer pageNum, Integer pageSize) {
        List<EBooksVO> eBookVOS = eBooksMapper.getAllEBooks();
        Transform transform = new Transform();
        return transform.listToPage(eBookVOS, pageNum, pageSize);
    }

    @Override
    public boolean addEBook(EBooks eBooks) {
        QueryWrapper<EBooks> eBookQueryWrapper = new QueryWrapper<>();
        // 判断是否有相同的电子书
        eBookQueryWrapper.eq("title", eBooks.getTitle());
        EBooks existingEBook = this.getOne(eBookQueryWrapper);
        if (existingEBook != null) {
            throw new RuntimeException("电子书已存在");
        }
        // 确保为'title'字段赋值
        if (eBooks.getTitle() == null || eBooks.getTitle().isEmpty()) {
            throw new RuntimeException("电子书标题不能为空");
        }
        return save(eBooks);
    }


//    private EBooks createEBooksFromVO(EBookVO eBookVO) {
//        // 通过EBookVO对象里的seriesName查e_book_series表获取seriesId
//        QueryWrapper<EBookSeries> SeriesQueryWrapper = new QueryWrapper<>();
//        SeriesQueryWrapper.eq("name", eBookVO.getSeriesName());
//        EBookSeries eBookSeries = eBookSeriesMapper.selectOne(SeriesQueryWrapper);
//
//        if (eBookSeries == null) {
//            // 如果没有找到对应的系列，返回null
//            return null;
//        }
//
//        // 创建一个新的EBooks对象，并设置数据
//        EBooks eBooks = new EBooks();
//        eBooks.setTitle(eBookVO.getTitle());
//        eBooks.setAuthor(eBookVO.getAuthor());
//        eBooks.setFilePath(eBookVO.getFilePath());
//        eBooks.setFileType(eBookVO.getFileType());
//        eBooks.setSeriesId(eBookSeries.getId()); // 设置系列ID
//
//        return eBooks;
//    }

}

