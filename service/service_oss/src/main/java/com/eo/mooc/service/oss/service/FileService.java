package com.eo.mooc.service.oss.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

public interface FileService {
    void removeFile(String url);

    /**
     * @title upload
     * @description 文件上传至阿里云
     * @params [inputStream, module, originalFilename]
     * @updateTime 2022/10/2 14:45
     * @return java.lang.String
     */
    String upload(InputStream inputStream, String module, String originalFilename);
}
