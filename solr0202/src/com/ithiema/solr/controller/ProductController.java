package com.ithiema.solr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ithiema.solr.pojo.ResultModel;
import com.ithiema.solr.service.ProductService;
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("list")
	public String queryProduct(String queryString, String catalog_name, String price, String sort, Integer page, Model model) throws Exception{
		
		ResultModel resultModel = productService.queryProduct(queryString, catalog_name, price, sort, page);
		
		model.addAttribute("result", resultModel);
		//参数回显
		model.addAttribute("queryString", queryString);
		model.addAttribute("catalog_name", catalog_name);
		model.addAttribute("price", price);
		model.addAttribute("sort", sort);
		model.addAttribute("page", page);
		
		return "product_list";
		
		
	}
	
	@RequestMapping("success")
	public String success(){
		return "success";
	}
}
