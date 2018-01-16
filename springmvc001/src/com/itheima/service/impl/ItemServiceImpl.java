package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.mapper.ItemMapper;
import com.itheima.pojo.Item;
import com.itheima.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Override
	public Item queryItemById(Integer id) {
		Item item = itemMapper.queryItemById(id);
		return item;
	}
	@Override
	public List<Item> queryItems() {
		// TODO Auto-generated method stub
		List<Item> list = itemMapper.queryItems();
		return list;
	}

}
