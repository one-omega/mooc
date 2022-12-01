package com.eo.mooc.service.cms.feign.fallback;

import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.cms.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author eo
 * @since 2020/4/17
 */

@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
