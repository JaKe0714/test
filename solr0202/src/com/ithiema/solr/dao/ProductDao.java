package com.ithiema.solr.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.ithiema.solr.pojo.ResultModel;

public interface ProductDao {
	
	public ResultModel queryProduct(SolrQuery query) throws Exception;
}
