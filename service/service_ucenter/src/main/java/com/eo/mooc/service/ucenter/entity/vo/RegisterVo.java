package com.eo.mooc.service.ucenter.entity.vo;

import lombok.Data;

/**
 * @author omeOmega
 * @ClassName RegisterVo.java
 * @Description TODO
 * @createTime 2022年11月04日
 */
@Data
public class RegisterVo {
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String mobile;
    private String password;
    private String code;
}
