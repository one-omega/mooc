package com.eo.mooc.service.edu.feign.fallback;

import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OssFileServiceFallBack.java
 * @Description TODO
 * @createTime 2022年10月08日 16:53
 */
@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public R removeFile(String url) {
        log.info("熔断处理");
        return R.error();
    }
}
