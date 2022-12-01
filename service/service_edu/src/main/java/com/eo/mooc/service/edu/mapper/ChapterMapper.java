package com.eo.mooc.service.edu.mapper;

import com.eo.mooc.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
//@Repository
@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {

}
