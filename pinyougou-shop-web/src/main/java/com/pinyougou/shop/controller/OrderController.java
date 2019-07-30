package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.entity.ItemMoney;
import com.entity.OrderItems;
import com.entity.Result;
import com.github.pagehelper.PageInfo;
//import com.pinyougou.OrderOne.service.OrderService;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.sellergoods.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	@Reference
	private OrderService orderService;

	//指定时间段统计各商品销售额
//	@RequestMapping("/findGoodsSeller")
//	public List findGoodsSeller(){
//
//		List goodsSellerList=orderService.findGoodsSellerPayment();
//		return goodsSellerList;
//	}

	//查询所有订单
	@RequestMapping("/findOrderItems")
	@ResponseBody
	public List<OrderItems> findOrderItems(){
		List<OrderItems> orderItems = orderService.findOrderItems();

		System.out.println(orderItems);
		return orderItems;

	}
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbOrder> findAll(){			
		return orderService.findAll();
	}
	
	
	
	@RequestMapping("/findPage")
    public PageInfo<TbOrder> findPage(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize) {
        return orderService.findPage(pageNo, pageSize);
    }
	
	/**
	 * 增加
	 * @param order
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbOrder order){
		try {
			orderService.add(order);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param order
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbOrder order){
		try {
			orderService.update(order);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne/{id}")
	public TbOrder findOne(@PathVariable(value = "id") Long id){
		return orderService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(@RequestBody Long[] ids){
		try {
			orderService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	

	@RequestMapping("/search")
    public PageInfo<TbOrder> findPage(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
                                      @RequestBody TbOrder order) {
        return orderService.findPage(pageNo, pageSize, order);
    }

	@RequestMapping("/findItemMoney/{startTime}/{endTime}")
    public List<ItemMoney> findItemMoney(@PathVariable(value = "startTime")Long startTime, @PathVariable(value = "endTime") Long endTime) {

		try {
			return orderService.findItemMoney(startTime,endTime);
		} catch (Exception e) {
			throw new RuntimeException("查询失败");
		}
	}
}
