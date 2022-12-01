package com.eo.mooc.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName VideoService.java
 * @Description TODO
 * @createTime 2022年10月23日 15:44
 */
public interface VideoService {
    String uploadVideo(InputStream inputStream, String originalFilename);

    void removeVideo(String vodId) throws ClientException;

    void removeVideoByIdList(List<String> vodIdList) throws ClientException;

    String getPlayAuth(String videoSourceId) throws ClientException;
}
