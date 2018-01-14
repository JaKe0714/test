package com.ithiema.solr.test;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class QueryTest {

	@Test
	public void test() throws Exception {
		SolrQuery query = new SolrQuery();
		
		//q
		query.setQuery("product_name:蜡笔小新");
		
		//fq
		query.addFilterQuery("product_catalog_name:时尚卫浴");
		query.addFilterQuery("product_price:[5 TO *]");
		
		//sort
		query.addSort("product_price", ORDER.asc);
		
		//分页
		query.setStart(0);
		query.setRows(10);
		
		//fl
		query.setFields("id","product_name","product_catalog_name","product_price");
		
		//默认搜索
		//query.set("df", "product_name");
		
		//高亮
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		
		
		HttpSolrServer server = new HttpSolrServer("http://localhost:8088/solr");
		
		QueryResponse response = server.query(query);
		
		SolrDocumentList results = response.getResults();
		
		System.out.println("----总记录数-----：" + results.getNumFound());
		
		for (SolrDocument solrDocument : results) {
			System.out.println("id-----" + solrDocument.get("id"));
			
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			Map<String, List<String>> map = highlighting.get(solrDocument.get("id"));
			List<String> list = map.get("product_name");
			if(list != null){
				System.out.println("product_name---高亮------" + list.get(0));
			}else{
				System.out.println("product_name---------" + solrDocument.get("product_name"));
			}
			System.out.println(map);
			System.out.println(highlighting);
			
			System.out.println("product_price-----" + solrDocument.get("product_price"));
			System.out.println("product_catalog_name-----" + solrDocument.get("product_catalog_name"));
			System.out.println("---------------------------------------------");
		}
	}

}
