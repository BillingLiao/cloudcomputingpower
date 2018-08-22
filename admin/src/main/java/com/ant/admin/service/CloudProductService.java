package com.ant.admin.service;

import com.ant.admin.dto.CloudProduct;
import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface CloudProductService extends IService<CloudProduct> {

    CloudProduct infoCloud(Integer productId);

    void insertCloud(CloudProduct cloudProduct);

    Page<CloudProduct> queryPage(Map<String,Object> params, Wrapper<Product> wrapper);
}
