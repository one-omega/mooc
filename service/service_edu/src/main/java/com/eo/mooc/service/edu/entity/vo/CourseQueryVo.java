package com.eo.mooc.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CourseQueryVo.java
 * @Description 输入的课程查询条件
 * @createTime 2022年10月13日 16:16
 */
@Data
public class CourseQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}
