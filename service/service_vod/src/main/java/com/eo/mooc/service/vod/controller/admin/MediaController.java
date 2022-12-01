package com.eo.mooc.service.vod.controller.admin;

import com.aliyuncs.exceptions.ClientException;
import com.eo.mooc.common.base.result.R;
import com.eo.mooc.common.base.result.ResultCodeEnum;
import com.eo.mooc.common.base.util.ExceptionUtils;
import com.eo.mooc.service.base.exception.GuliException;
import com.eo.mooc.service.vod.service.impl.VideoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MediaController.java
 * @Description TODO
 * @createTime 2022年10月23日 17:07
 */
@Api(description="阿里云视频点播")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/vod/media")
@Slf4j
public class MediaController {

    @Autowired
    private VideoServiceImpl videoService;

    @PostMapping("upload")
    public R upload(@ApiParam(value =  "文件", required = true)
                    @RequestParam("file2222") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String videoId = videoService.uploadVideo(inputStream, originalFilename);
            return R.ok().message("上传成功").data("item", videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }

    @DeleteMapping("remove/{vodId}")
    public R removeVideo(
            @ApiParam(value="阿里云视频id", required = true)
            @PathVariable String vodId){

        try {
            videoService.removeVideo(vodId);
            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

    @DeleteMapping("remove")
    public R removeVideoByIdList(@ApiParam(value = "阿里云视频id列表", required = true)
                                 @RequestBody List<String> vodIdList) {
        try {
            videoService.removeVideoByIdList(vodIdList);
            return R.ok().message("视频批量删除成功");
        } catch (ClientException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }
}
