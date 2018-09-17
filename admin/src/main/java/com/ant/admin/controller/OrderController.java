package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.dao.OrderRecordDao;
import com.ant.admin.service.CloudProductService;
import com.ant.admin.service.FinancialProductService;
import com.ant.admin.service.ProductService;
import com.ant.entity.*;
import com.ant.admin.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 订单controller
 * @author Billing
 * @date 2018/8/15 11:54
 */
@RestController
@RequestMapping("/order")
public class OrderController extends  AbstractController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRecordDao orderRecordDao;

    @Autowired private ProductService productService;

    /**
     * 列表
     * 产品订单
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:list")
    public Result list(@RequestParam Map<String,Object> params){
        PageUtils page=orderService.queryPage(params);
/*        EntityWrapper<Order> wrapper = new EntityWrapper<Order>();
        wrapper.like("c.category_name", order.getCategoryName());
        PageUtils page = new PageUtils(orderService.queryPage(params, wrapper));*/
        return Result.ok().put("page", page);
    }

    /**
     * 更新订单状态
     */
    @RequestMapping("/status")
    @RequiresPermissions("order:status:update")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@RequestBody Order order) throws ParseException {
        //校验类型
        ValidatorUtils.validateEntity(order);
        Date createTime = new Date();
        //更新付款时间
        if(order.getOrderStatus() == 2){
            order.setPaymentTime(createTime);
        }else if(order.getOrderStatus() == 5){ //更新完成时间
            order.setCompletionTime(createTime);
        }
        orderService.updateAllColumnById(order);
        OrderRecord orderRecord = new OrderRecord(order.getOrderId(),order.getOrderStatus(),createTime);
        orderRecordDao.insert(orderRecord);
        return Result.ok("更新成功");
    }

    /**
     * 根据id查询单条记录
     * @param orderId
     * @return
     */
    @RequestMapping("/info/{orderId}")
    @RequiresPermissions("order:info")
    public Result info(@PathVariable("orderId") Integer orderId){
        Order order =  orderService.selectById(orderId);
        return Result.ok().put("order",order);
    }
}
