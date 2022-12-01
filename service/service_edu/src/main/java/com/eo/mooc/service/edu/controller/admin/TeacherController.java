package com.eo.mooc.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.edu.entity.Teacher;
import com.eo.mooc.service.edu.entity.vo.TeacherQueryVo;
import com.eo.mooc.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */

@Api(description = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("list")
    public R listAll() {
        return R.ok().data("items", teacherService.list()).message("获取讲师列表成功");
    }

    @ApiOperation("根据 id 删除讲师")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "讲师id", required = true)
                            @PathVariable String id) {
        teacherService.removeAvatarById(id);

        boolean b = teacherService.removeById(id);
        if (b) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("根据 id 批量删除讲师")
    @DeleteMapping("batch-remove")
    public R removeByIdList(@ApiParam(value = "删除的讲师ID列表", required = true)
                                @RequestBody List<String> idList) {
        boolean b = teacherService.removeByIds(idList);
        if (b) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("分页查询")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Integer limit,
                      @ApiParam("讲师列表查询对象")TeacherQueryVo teacherQueryVo) {
        IPage<Teacher> pageModel = teacherService.selectPage(page, limit, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records).message("获取列表指定页成功");
    }

    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam(value = "讲师对象", required = true) @RequestBody Teacher teacher) {
        boolean b = teacherService.save(teacher);
        if (b) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id修改讲师")
    @PutMapping("update")
    public R updateById(@ApiParam(value = "讲师对象", required = true) @RequestBody Teacher teacher) {
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return R.ok().message("修改成功");
        } else {
            return R.error().message("修改失败");
        }
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("get/{id}")
    public R getById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return R.ok().data("item", teacher).message("根据id查询成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据关键字模糊查询讲师")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(@ApiParam(value = "关键字", required = true)
                                     @PathVariable String key) {
        List<Map<String, Object>> nameList = teacherService.selectNameListByKey(key);
        return R.ok().data("nameList", nameList);
    }
}

