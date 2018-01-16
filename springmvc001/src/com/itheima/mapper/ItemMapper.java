package com.itheima.mapper;

import java.util.List;

import com.itheima.pojo.Item;

public interface ItemMapper {
	public Item queryItemById(Integer id);
	
	public List<Item> queryItems();
}
