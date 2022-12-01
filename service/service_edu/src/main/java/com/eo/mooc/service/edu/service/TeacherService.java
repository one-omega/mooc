package com.eo.mooc.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eo.mooc.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eo.mooc.service.edu.entity.vo.TeacherQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
public interface TeacherService extends IService<Teacher> {

    IPage<Teacher> selectPage(Integer page, Integer limit, TeacherQueryVo teacherQueryVo);

    List<Map<String, Object>> selectNameListByKey(String key);

    boolean removeAvatarById(String id);

    Map<String, Object> getTeacherInfoById(String id);

    List<Teacher> selectHotTeacher();
}
