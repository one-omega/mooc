package com.eo.mooc.service.edu.service;

import com.eo.mooc.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eo.mooc.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
