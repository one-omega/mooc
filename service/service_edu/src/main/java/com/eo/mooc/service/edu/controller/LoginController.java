package com.eo.mooc.service.edu.controller;

import com.eo.mooc.common.base.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description 模拟登录接口
 * @createTime 2022年10月09日 19:14
 */
//@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    /**
     * 登录
     * @return
     */
    @PostMapping("login")
    public R login(){
        return R.ok().data("token", "admin");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("info")
    public R info(){
        return R.ok()
                .data("name", "admin")
                .data("roles", "[admin]")
                .data("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}

