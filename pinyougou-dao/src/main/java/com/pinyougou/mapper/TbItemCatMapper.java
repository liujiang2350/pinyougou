package com.pinyougou.mapper;


import com.pinyougou.pojo.TbItemCat;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbItemCatMapper extends Mapper<TbItemCat> {

    List<TbItemCat> selectByParentId(Long parentId);
}