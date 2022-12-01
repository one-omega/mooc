package com.eo.mooc.service.edu.feign;

import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.edu.feign.fallback.OssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OssFileService.java
 * @Description 远程调用oss微服务
 * @createTime 2022年10月07日 17:27
 */
@Service
@FeignClient(value = "service-oss", fallback = OssFileServiceFallBack.class)
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(String url);
}
