package com.eo.mooc.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CourseVo.java
 * @Description 输出的查询结果对象
 * @createTime 2022年10月13日 16:18
 */
@Data
public class CourseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private Integer lessonNum;
    private String price;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    private String gmtCreate;
}
