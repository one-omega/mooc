package com.eo.mooc.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eo.mooc.service.edu.entity.Subject;
import com.eo.mooc.service.edu.entity.excel.ExcelSubjectData;
import com.eo.mooc.service.edu.mapper.SubjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ExcelSubjectDataListener.java
 * @createTime 2022年10月10日 16:36
 */

@Slf4j
@AllArgsConstructor //全参
@NoArgsConstructor //无参
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    private SubjectMapper subjectMapper;

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        String levelOneTitle = excelSubjectData.getLevelOneTitle();
        String levelTwoTitle = excelSubjectData.getLevelTwoTitle();
        Subject subjectLevelOne = getByTitle(levelOneTitle);
        String parentId = null;
        if (subjectLevelOne == null) {
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            subjectMapper.insert(subject);
            parentId = subject.getId();
        } else {
            parentId = subjectLevelOne.getParentId();
        }
        Subject subjectLevelTwo = getSubByTitle(levelTwoTitle, parentId);
        if (subjectLevelTwo == null) {
            Subject subject = new Subject();
            subject.setParentId(parentId);
            subject.setTitle(levelTwoTitle);
            subjectMapper.insert(subject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("解析完成");
    }

    private Subject getByTitle(String title) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title).eq("parent_id", 0);
        return subjectMapper.selectOne(queryWrapper);
    }

    private Subject getSubByTitle(String title, String parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title).eq("parent_id", parentId);
        return subjectMapper.selectOne(queryWrapper);
    }
}