package com.eo.mooc.service.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ExcelSubjectData.java
 * @Description 封装 excel 中的 subject 数据
 * @createTime 2022年10月10日 16:32
 */
@Data
public class ExcelSubjectData {

    @ExcelProperty(value = "一级分类")
    private String levelOneTitle;

    @ExcelProperty(value = "二级分类")
    private String levelTwoTitle;
}
