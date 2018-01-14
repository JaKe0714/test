package com.ithiema.solr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ithiema.solr.pojo.ProductModel;
import com.ithiema.solr.pojo.ResultModel;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SolrServer solrServer;
	
	@Override
	public ResultModel queryProduct(SolrQuery query) throws Exception {
		
		QueryResponse response = solrServer.query(query);
		
		SolrDocumentList results = response.getResults();
		
		System.out.println("总记录数----" + results.getNumFound());
		
		ArrayList<ProductModel> products = new ArrayList<ProductModel>();
		for (SolrDocument solrDocument : results) {
			
			ProductModel model = new ProductModel();
			
			model.setPid((String) solrDocument.get("id"));
			
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
			
			if(list != null){
				model.setName(list.get(0));
			}else{
				model.setName((String) solrDocument.get("product_name"));
			}
			
			model.setPicture((String) solrDocument.get("product_picture"));
			model.setCatalog_name((String) solrDocument.get("product_catalog_name"));
			model.setPrice((float) solrDocument.get("product_price"));
		
			products.add(model);
		}
		
		ResultModel resultModel = new ResultModel();
		resultModel.setProductList(products);
		resultModel.setRecordCount(results.getNumFound());
		
		
		return resultModel;
	}

}
