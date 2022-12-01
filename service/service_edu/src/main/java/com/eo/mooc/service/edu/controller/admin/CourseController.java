package com.eo.mooc.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.edu.entity.form.CourseInfoForm;
import com.eo.mooc.service.edu.entity.vo.CoursePublishVo;
import com.eo.mooc.service.edu.entity.vo.CourseQueryVo;
import com.eo.mooc.service.edu.entity.vo.CourseVo;
import com.eo.mooc.service.edu.service.CourseService;
import com.eo.mooc.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
//@CrossOrigin
@Api(description = "课程管理")
@RestController
@RequestMapping("/admin/edu/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private VideoService videoService;

    @ApiOperation("保存课程信息")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId", courseId).message("保存成功");
    }

    @ApiOperation("根据课程id查询课程信息")
    @GetMapping("course-info/{id}")
    public R getCourseInfoById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable("id") String id) {
        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(id);
        if (courseInfoForm != null) {
            return R.ok().data("item", courseInfoForm);
        } else {
            return R.error().message("课程信息更新失败");
        }
    }

    @ApiOperation("更新课程信息")
    @PutMapping("update-course-info")
    public R updateCourseInfoById(
            @ApiParam(value = "课程信息表单", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {
        courseService.updateCourseInfoById(courseInfoForm);
        return R.ok().message("更新课程信息成功");
    }

    @ApiOperation("分页查询课程列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(value = "查询对象")
            CourseQueryVo courseQueryVo) {
        IPage<CourseVo> pageModel = courseService.selectPage(page, limit, courseQueryVo);
        List<CourseVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("课程删除")
    @DeleteMapping("remove/{id}")
    public R removeCourseById(@ApiParam("课程id")
                                  @PathVariable("id") String id) {
        //在此处调用vod中的删除视频文件的接口
        videoService.removeMediaVideoByCourseId(id);

        //删除封面
//        courseService.removeCoverById(id);

        boolean b = courseService.removeCourseById(id);
        if (b) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("根据ID获取课程发布信息")
    @GetMapping("course-publish/{id}")
    public R getCoursePublishVoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        if (coursePublishVo != null) {
            return R.ok().data("item", coursePublishVo);
        } else {
            return R.error().message("数据不存在");
        }
    }


    @ApiOperation("根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = courseService.publishCourseById(id);
        if (result) {
            return R.ok().message("发布成功");
        } else {
            return R.error().message("数据不存在");
        }
    }
}

