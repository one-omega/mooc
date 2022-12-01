package com.eo.mooc.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author omeOmega
 * @ClassName WebCourseQueryVo.java
 * @Description 查询课程列表的条件
 * @createTime 2022年10月26日
 */

@Data
public class WebCourseQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subjectParentId;
    private String subjectId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;
    private Integer type; //价格正序：1，价格倒序：2
}
