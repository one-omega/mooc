package com.eo.mooc.service.edu.service.impl;

import com.eo.mooc.service.edu.entity.Comment;
import com.eo.mooc.service.edu.mapper.CommentMapper;
import com.eo.mooc.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author eo
 * @since 2022-09-20
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
