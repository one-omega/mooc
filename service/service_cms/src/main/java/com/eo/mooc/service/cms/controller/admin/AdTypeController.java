package com.eo.mooc.service.cms.controller.admin;

import com.eo.mooc.common.base.result.R;
import com.eo.mooc.service.cms.entity.Ad;
import com.eo.mooc.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 推荐位 前端控制器
 * </p>
 *
 * @author eo
 * @since 2022-10-27
 */
@CrossOrigin //解决跨域问题
@Api(description = "推荐位管理")
@RestController
@RequestMapping("/admin/cms/ad-type")
@Slf4j
public class AdTypeController {

    @Autowired
    private AdService adService;

    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("list/{adTypeId}")
    public R listByAdTypeId(@ApiParam(value = "推荐位id", required = true)
                            @PathVariable("adTypeId") String adTypeId) {
        List<Ad> ads = adService.selectByAdTypeId(adTypeId);
        return R.ok().data("items", ads);
    }
}
