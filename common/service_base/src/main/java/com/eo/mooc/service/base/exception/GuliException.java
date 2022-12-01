package com.eo.mooc.service.base.exception;

import com.eo.mooc.common.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName GuliException.java
 * @Description 通用型异常
 * @createTime 2022年10月04日 21:43
 */
@Data
public class GuliException extends RuntimeException {
    private Integer code;

    public GuliException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public GuliException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
