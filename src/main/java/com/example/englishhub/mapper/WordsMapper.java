package com.example.englishhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.Words;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 单词表 Mapper 接口
 * </p>
 *
 * @author hahaha
 */
@Mapper
public interface WordsMapper extends BaseMapper<Words> {

}
