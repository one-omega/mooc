package com.eo.mooc.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CoursePublishVo.java
 * @Description TODO
 * @createTime 2022年10月14日 19:34
 */
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private String price;//只用于显示
}
