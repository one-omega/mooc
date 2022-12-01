package com.eo.mooc.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.edu.entity.Course;
import com.eo.mooc.service.edu.entity.Teacher;
import com.eo.mooc.service.edu.entity.vo.TeacherQueryVo;
import com.eo.mooc.service.edu.feign.OssFileService;
import com.eo.mooc.service.edu.mapper.CourseMapper;
import com.eo.mooc.service.edu.mapper.TeacherMapper;
import com.eo.mooc.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private OssFileService ossFileService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public IPage<Teacher> selectPage(Integer page, Integer limit, TeacherQueryVo teacherQueryVo) {
        Page<Teacher> teacherPage = new Page<>(page, limit);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQueryVo == null) {
            return baseMapper.selectPage(teacherPage, queryWrapper);
        }

        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getJoinDateBegin();
        String end = teacherQueryVo.getJoinDateEnd();

        if (!StringUtils.isEmpty(name)) {
            //左%会使B+树索引失效
            queryWrapper.likeRight("name", name);
        }
        if (level != null) {
            queryWrapper.eq("level", level);
        }
        //join_data在[begin,end]之间
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("join_date", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("join_date", end);
        }

        return baseMapper.selectPage(teacherPage, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectNameListByKey(String key) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name").likeRight("name", key);
        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean removeAvatarById(String id) {
        Teacher teacher = baseMapper.selectById(id);
        if (teacher != null) {
            String avatar = teacher.getAvatar();
            if (!StringUtils.isBlank(avatar)) {
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> getTeacherInfoById(String id) {
        Teacher teacher = baseMapper.selectById(id);
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getTeacherId, id);
        List<Course> courseList = courseMapper.selectList(queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);
        map.put("courseList", courseList);

        return map;
    }

    @Cacheable(value = "index", key = "'selectHotTeacher'")
    @Override
    public List<Teacher> selectHotTeacher() {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.last("limit 4");
        return baseMapper.selectList(queryWrapper);
    }
}
