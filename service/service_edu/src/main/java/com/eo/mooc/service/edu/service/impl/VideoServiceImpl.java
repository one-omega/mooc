package com.eo.mooc.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eo.mooc.service.edu.entity.Video;
import com.eo.mooc.service.edu.feign.VodMediaService;
import com.eo.mooc.service.edu.mapper.VideoMapper;
import com.eo.mooc.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodMediaService vodMediaService;

    @Override
    public void removeMediaVideoById(String id) {
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        vodMediaService.removeVideo(videoSourceId);
    }

    @Override
    public void removeMediaVideoByChapterId(String chapterId) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Video::getVideoSourceId);
        queryWrapper.eq(Video::getChapterId, chapterId);
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        List<String> vodIdList = getVodIdList(maps);
        vodMediaService.removeVideoByIdList(vodIdList);
    }

    @Override
    public void removeMediaVideoByCourseId(String courseId) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Video::getVideoSourceId);
        queryWrapper.eq(Video::getCourseId, courseId);
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        List<String> vodIdList = getVodIdList(maps);
        vodMediaService.removeVideoByIdList(vodIdList);
    }

    /**
     * @title getVodIdList
     * @description 根据键值对列表获取vodId列表
     * @params [maps]
     * @return java.util.List<java.lang.String>
     */
    private List<String> getVodIdList(List<Map<String, Object>> maps) {
        List<String> vodIdList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            String vodId = (String) map.get("video_source_id");
            vodIdList.add(vodId);
        }
        return vodIdList;
    }
}
