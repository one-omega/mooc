package com.eo.mooc.service.oss.controller.admin;

import com.eo.mooc.common.base.result.R;
import com.eo.mooc.common.base.result.ResultCodeEnum;
import com.eo.mooc.common.base.util.ExceptionUtils;
import com.eo.mooc.service.base.exception.GuliException;
import com.eo.mooc.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName FileController.java
 * @Description 阿里云文件管理
 * @createTime 2022年10月02日 15:44
 */
@Api("阿里云文件管理")
//@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {
    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(@ApiParam(value = "文件", required = true)
                    @RequestParam("file") MultipartFile file,
                    @ApiParam(value = "模块名", required = true)
                    @RequestParam("module") String module) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String url = fileService.upload(inputStream, module, originalFilename);

            return R.ok().message("文件上传成功").data("url", url);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation("删除文件")
    @DeleteMapping("remove")
    public R removeFile(@ApiParam(value = "删除文件的路径", required = true)
                               @RequestBody String url) {
        fileService.removeFile(url);
        return R.ok().message("删除文件成功");
    }
}
