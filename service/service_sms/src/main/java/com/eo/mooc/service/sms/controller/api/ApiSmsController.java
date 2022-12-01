package com.eo.mooc.service.sms.controller.api;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.eo.mooc.common.base.result.R;
import com.eo.mooc.common.base.result.ResultCodeEnum;
import com.eo.mooc.common.base.util.FormUtils;
import com.eo.mooc.common.base.util.RandomUtils;
import com.eo.mooc.service.base.exception.GuliException;
import com.eo.mooc.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/sms")
@Api(description = "短信管理")
@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SmsService smsService;

    @GetMapping("send/{mobile}")
    public R getCode(@PathVariable("mobile") String mobile) throws ExecutionException, InterruptedException {
        //产生验证码
        if(StringUtils.isBlank(mobile) || !FormUtils.isMobile(mobile)) {
            log.error("请输入正确的手机号码");
            throw new GuliException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }

        String checkCode = RandomUtils.getFourBitRandom();
        //发送验证码
//        smsService.sendCode(mobile, checkCode);

        //存储验证码
        redisTemplate.opsForValue().set(mobile, checkCode, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }
}
