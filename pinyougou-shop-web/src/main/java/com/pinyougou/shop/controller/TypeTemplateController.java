package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * search   update   add
 */

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference
	private TypeTemplateService typeTemplateService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbTypeTemplate> findAll(){			
		return typeTemplateService.findAll();
	}
	
	
	
	@RequestMapping("/findPage")
    public PageInfo<TbTypeTemplate> findPage(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize) {
        return typeTemplateService.findPage(pageNo, pageSize);
    }
	
	/**
	 * 增加
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplateService.add(typeTemplate);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param template
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbTypeTemplate template){
		System.out.println(template);
		try {
			typeTemplateService.update(template);
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
	public TbTypeTemplate findOne(@PathVariable(value = "id") Long id){
		System.out.println(id);
		return typeTemplateService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(@RequestBody Long[] ids){
		try {
			typeTemplateService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	

	@RequestMapping("/search")
    public PageInfo<TbTypeTemplate> findPage(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
                                      @RequestBody TbTypeTemplate typeTemplate) {
        return typeTemplateService.findPage(pageNo, pageSize, typeTemplate);
    }

    @RequestMapping("/findSpecList/{id}")
	public List<Map> findSpecList(@PathVariable(value = "id") Long id) {
		return typeTemplateService.findSpecList(id);
	}

	/**
	 * 返回全部列表
	 * @return
	 */



//=========================================



	/**
	 * 增加
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/addOne")
	public Result addOne(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplate.setStatus("0");
			typeTemplate.setSellerId(SecurityContextHolder.getContext().getAuthentication().getName());
			typeTemplateService.add(typeTemplate);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/updateOne")
	public Result updateOne(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplate.setStatus("0");
			typeTemplateService.update(typeTemplate);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}









	/**
	 *
	 * @param pageNo
	 * @param pageSize
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/searchOne")
	public PageInfo<TbTypeTemplate> findPageOne(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
											 @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
											 @RequestBody TbTypeTemplate typeTemplate) {
		return typeTemplateService.oneFindPage(pageNo, pageSize, typeTemplate);
	}



	@RequestMapping("/searchApply")
	public PageInfo<TbTypeTemplate> findPageApply(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
												  @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
												  @RequestBody TbTypeTemplate typeTemplate) {
		typeTemplate.setSellerId(SecurityContextHolder.getContext().getAuthentication().getName());
		return typeTemplateService.oneFindPage(pageNo, pageSize, typeTemplate);
	}

	@RequestMapping("/searchListAll")
	public PageInfo<TbTypeTemplate> searchListAll(@RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
												  @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
												  @RequestBody TbTypeTemplate typeTemplate) {
		return typeTemplateService.oneFindPage(pageNo, pageSize, typeTemplate);
	}
}