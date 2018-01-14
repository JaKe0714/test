package com.ithiema.solr.service;

import com.ithiema.solr.pojo.ResultModel;

public interface ProductService {
	public ResultModel queryProduct(String queryString, String catalog_name, String price, String sort, Integer page) throws Exception;
}
