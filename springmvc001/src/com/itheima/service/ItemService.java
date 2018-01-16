package com.itheima.service;

import java.util.List;

import com.itheima.pojo.Item;

public interface ItemService {
	public Item queryItemById(Integer id);
	
	public List<Item> queryItems();
}
