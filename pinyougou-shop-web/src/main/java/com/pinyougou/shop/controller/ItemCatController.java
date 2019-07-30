package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * findByParentId  search update add
 */

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbItemCat> findAll(){			
		return itemCatService.findAll();
	}
	
	
	
	@RequestMapping("/findPage")
    public PageInfo<TbItemCat> findPage(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize) {
        return itemCatService.findPage(pageNo, pageSize);
    }
	
	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.add(itemCat);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.update(itemCat);
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
	public TbItemCat findOne(@PathVariable(value = "id") Long id){
		return itemCatService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(@RequestBody Long[] ids){
		try {
			itemCatService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}

	@RequestMapping("/search")
    public PageInfo<TbItemCat> findPage(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
                                      @RequestBody TbItemCat itemCat) {
        return itemCatService.findPage(pageNo, pageSize, itemCat);
    }

    @RequestMapping("/findByParentId/{parentId}")
	public List<TbItemCat> findByParentId(@PathVariable(value = "parentId") Long parentId) {
		return itemCatService.findByParentId(parentId);
	}

	/**
	 * =============================================
	 */





	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/addOne")
	public Result addOne(@RequestBody TbItemCat itemCat){
		try {
			itemCat.setSellerId(SecurityContextHolder.getContext().getAuthentication().getName());
			itemCat.setStatus("0");
			itemCatService.add(itemCat);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/updateOne")
	public Result updateOne(@RequestBody TbItemCat itemCat){
		try {
			itemCat.setStatus("0");
			itemCatService.update(itemCat);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}







	@RequestMapping("/searchOne")
	public PageInfo<TbItemCat> findPageOne(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
										@RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
										@RequestBody TbItemCat itemCat) {
		return itemCatService.oneFindPage(pageNo, pageSize, itemCat);
	}

	@RequestMapping("/findByParentIdOne/{parentId}")
	public List<TbItemCat> findByParentIdOne(@PathVariable(value = "parentId")Long parentId){
		return itemCatService.oneFindByParentId(parentId);
	}


	@RequestMapping("/searchApply")
	public PageInfo<TbItemCat> findPageApply(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
											 @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
											 @RequestBody TbItemCat itemCat) {
		itemCat.setSellerId(SecurityContextHolder.getContext().getAuthentication().getName());
		return itemCatService.oneFindPage(pageNo, pageSize, itemCat);
	}
}