package com.eo.mooc.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eo.mooc.service.base.dto.CourseDto;
import com.eo.mooc.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eo.mooc.service.edu.entity.vo.CoursePublishVo;
import com.eo.mooc.service.edu.entity.vo.CourseQueryVo;
import com.eo.mooc.service.edu.entity.vo.CourseVo;
import com.eo.mooc.service.edu.entity.vo.WebCourseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {

    IPage<CourseVo> selectPageByCourseQueryVo(Page<CourseVo> pageParam,
                                              @Param(Constants.WRAPPER) QueryWrapper<CourseQueryVo> queryWrapper);

    CoursePublishVo selectCoursePublishVoById(String id);

    WebCourseVo selectWebCourseVoById(String id);

    CourseDto selectCourseDtoById(@Param("id") String courseId);
}
