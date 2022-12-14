package com.eo.mooc.service.edu.controller.admin;


import com.eo.mooc.common.base.result.R;
import com.eo.mooc.common.base.result.ResultCodeEnum;
import com.eo.mooc.common.base.util.ExceptionUtils;
import com.eo.mooc.service.base.exception.GuliException;
import com.eo.mooc.service.edu.entity.vo.SubjectVo;
import com.eo.mooc.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
//@CrossOrigin
@Api(description = "课程类别管理")
@RestController
@RequestMapping("/admin/edu/subject")
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("根据excel文件批量导入课程")
    @PostMapping("import")
    public R batchImport(@ApiParam(value = "excel文件", required = true)
                             @RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            subjectService.batchImport(inputStream);
            return R.ok().message("批量导入成功");
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }


    @ApiOperation("返回嵌套数据列表")
    @GetMapping("nested-list")
    public R nestedList() {
        List<SubjectVo> subjectVoList = subjectService.nestedList();
        return R.ok().data("items", subjectVoList);
    }

}

