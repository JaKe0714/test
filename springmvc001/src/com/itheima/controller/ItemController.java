package com.itheima.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.pojo.Item;
import com.itheima.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
/*	@RequestMapping("list")
	public ModelAndView list(){
		List<Item> list = new ArrayList<>();
		list.add(new Item(1, "1华为 荣耀8", 2399f, new Date(), "质量好！1"));
		list.add(new Item(2, "2华为 荣耀8", 2399f, new Date(), "质量好！2"));
		list.add(new Item(3, "3华为 荣耀8", 2399f, new Date(), "质量好！3"));
		list.add(new Item(4, "4华为 荣耀8", 2399f, new Date(), "质量好！4"));
		list.add(new Item(5, "5华为 荣耀8", 2399f, new Date(), "质量好！5"));
		list.add(new Item(6, "6华为 荣耀8", 2399f, new Date(), "质量好！6"));
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("itemList", list);
		mv.setViewName("itemList");
		
		return mv;
	}*/
	
	@RequestMapping("itemEdit")
	public ModelAndView itemEdit(HttpServletRequest request){
		String id = request.getParameter("id");
		Item item = itemService.queryItemById(Integer.parseInt(id));
		ModelAndView mv = new ModelAndView();
		mv.addObject("item", item);
		mv.setViewName("editItem");
		return mv;
	}
	
	@RequestMapping("itemList")
	public ModelAndView queryItems(){
		List<Item> list = itemService.queryItems();
		ModelAndView mv = new ModelAndView();
		mv.addObject("itemList", list);
		mv.setViewName("itemList");
		return mv;
	}
}
