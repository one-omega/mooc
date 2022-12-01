package com.eo.mooc.service.cms.controller.api;

import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.cms.entity.Ad;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(description="广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
public class ApiAdController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("save-test")
    public R saveAd(@RequestBody Ad ad) {
        redisTemplate.opsForValue().set("index::ad", ad);
        return R.ok();
    }

    @GetMapping("get-test/{key}")
    public R getAd(@PathVariable("key") String key) {
        Ad ad = (Ad) redisTemplate.opsForValue().get(key);
        return R.ok().data("ad", ad);
    }

    @DeleteMapping("remove-test/{key}")
    public R removeAd(@PathVariable("key") String key) {
        Boolean delete = redisTemplate.delete(key);
        Boolean aBoolean = redisTemplate.hasKey(key);
        return R.ok().data("is_delete", delete).data("hasKey", aBoolean);
    }
}
