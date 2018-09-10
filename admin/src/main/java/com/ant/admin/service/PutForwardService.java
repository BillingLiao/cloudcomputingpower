package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.PutForward;
import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:30
 */
public interface PutForwardService extends IService<PutForward> {

    PageUtils queryPage(Map<String, Object> params);

    Page<PutForward> queryPage(Map<String, Object> params, Wrapper<PutForward> wrapper);

    //void add(User user, Integer productId, BigDecimal amount, BigDecimal actualReceipts);

}