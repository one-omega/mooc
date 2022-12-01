package com.eo.mooc.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eo.mooc.service.base.dto.CourseDto;
import com.eo.mooc.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eo.mooc.service.edu.entity.form.CourseInfoForm;
import com.eo.mooc.service.edu.entity.vo.*;
import com.eo.mooc.service.edu.entity.vo.*;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo);

    WebCourseVo selectWebCourseVoById(String courseId);

    List<Course> selectHotCourse();

    CourseDto getCourseDtoById(String courseId);
}
