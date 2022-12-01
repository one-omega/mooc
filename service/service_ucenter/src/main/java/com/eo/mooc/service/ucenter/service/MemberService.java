package com.eo.mooc.service.ucenter.service;

import com.eo.mooc.service.base.dto.MemberDto;
import com.eo.mooc.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eo.mooc.service.ucenter.entity.vo.LoginVo;
import com.eo.mooc.service.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author eo
 * @since 2022-11-04
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    MemberDto getMemberDtoByMemberId(String memberId);
}
