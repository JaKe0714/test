package com.ithiema.solr.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ithiema.solr.dao.ProductDao;
import com.ithiema.solr.pojo.ResultModel;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public ResultModel queryProduct(String queryString, String catalog_name, String price, String sort, Integer page) throws Exception {
		SolrQuery query = new SolrQuery();
		
		query.set("df", "product_name");
		if(queryString != null && !queryString.equals("")){
			query.setQuery(queryString);
		}else{
			query.setQuery("*:*");
		}
		
		if(catalog_name != null && !"".equals(catalog_name)){
			query.addFilterQuery("product_catalog_name:" + catalog_name);
		}
		
		if(price != null && !"".equals(price)){
			String[] split = price.split("-");
			query.addFilterQuery("product_price:[" + split[0] + " TO " + split[1] + "]");
		}
		
		if("1".equals(sort)){
			query.setSort("product_price", ORDER.desc);
		}else{
			query.setSort("product_price", ORDER.asc);
		}
		
		if(page == null){
			page = 1;
		}
		
		int start = (page -1) * 12;
		query.setStart(start);
		query.setRows(12);
		
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<span style=\"color:red\">");
		query.setHighlightSimplePost("</span>");
		
		
		
		ResultModel model = productDao.queryProduct(query);
		
		model.setCurPage(page);
		int pageCount = 0;
		if((model.getRecordCount() % 12) > 0){
			pageCount = (int) ((model.getRecordCount() / 12) + 1);
		}else{
			pageCount = (int) (model.getRecordCount() /12);
		}
		
		model.setPageCount(pageCount);
		
		return model;
	}

}
