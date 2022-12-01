package com.eo.mooc.service.edu.controller.api;

import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.edu.entity.Teacher;
import com.eo.mooc.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author omeOmega
 * @ClassName ApiTeacherController.java
 * @Description 用户层讲师控制器
 * @createTime 2022年10月25日
 */
//@CrossOrigin
@Api(description="讲师")
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value="所有讲师列表")
    @GetMapping("list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items", list).message("获取讲师列表成功");
    }

    @ApiOperation(value = "根据讲师id获取讲师详情")
    @GetMapping("get/{id}")
    public R getTeacherInfoById(@PathVariable("id") String id) {
        Map<String, Object> map = teacherService.getTeacherInfoById(id);
        return R.ok().data("items", map);
    }
}
