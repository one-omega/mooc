package com.eo.mooc.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.eo.mooc.service.edu.entity.Subject;
import com.eo.mooc.service.edu.entity.excel.ExcelSubjectData;
import com.eo.mooc.service.edu.entity.vo.SubjectVo;
import com.eo.mooc.service.edu.listener.ExcelSubjectDataListener;
import com.eo.mooc.service.edu.mapper.SubjectMapper;
import com.eo.mooc.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void batchImport(InputStream inputStream) {
        // 这里需要指定读用哪个class去读，然后读取sheet文件流完毕会自动关闭
        EasyExcel.read(inputStream, ExcelSubjectData.class,
                new ExcelSubjectDataListener(baseMapper))
                .excelType(ExcelTypeEnum.XLS).sheet().doRead();
    }

    @Override
    public List<SubjectVo> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }
}
