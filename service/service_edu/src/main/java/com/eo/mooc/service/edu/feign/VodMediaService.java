package com.eo.mooc.service.edu.feign;

import com.eo.mooc.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName VodMediaService.java
 * @Description TODO
 * @createTime 2022年10月23日 21:38
 */
@Service
@FeignClient(value = "service-vod")
public interface VodMediaService {

    @DeleteMapping("/admin/vod/media/remove/{vodId}")
    R removeVideo(@PathVariable String vodId);

    @DeleteMapping("/admin/vod/media/remove")
    R removeVideoByIdList(@RequestBody List<String> vodIdList);
}
