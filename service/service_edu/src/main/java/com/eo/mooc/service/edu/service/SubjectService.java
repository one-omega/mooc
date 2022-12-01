package com.eo.mooc.service.edu.service;

import com.eo.mooc.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eo.mooc.service.edu.entity.vo.SubjectVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
public interface SubjectService extends IService<Subject> {

    void batchImport(InputStream inputStream);

    List<SubjectVo> nestedList();
}
