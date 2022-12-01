package com.eo.mooc.service.edu.feign.fallback;

import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.edu.feign.VodMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName VodMediaServiceFallBack.java
 * @Description TODO
 * @createTime 2022年10月23日 21:40
 */
@Service
@Slf4j
public class VodMediaServiceFallBack implements VodMediaService {
    @Override
    public R removeVideo(String vodId) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }

    @Override
    public R removeVideoByIdList(List<String> vodIdList) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
