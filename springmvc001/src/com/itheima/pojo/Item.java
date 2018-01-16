package com.itheima.pojo;

import java.util.Date;

public class Item {
	// 商品id
		private int id; 
		// 商品名称
		private String name;
		// 商品价格
		private Float price;
		// 商品创建时间
		private Date createtime;
		// 商品描述
		private String detail;
		
		
		
		public Item(int id, String name, Float price, Date createtime, String detail) {
			super();
			this.id = id;
			this.name = name;
			this.price = price;
			this.createtime = createtime;
			this.detail = detail;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Float getPrice() {
			return price;
		}
		public void setPrice(Float price) {
			this.price = price;
		}
		public Date getCreatetime() {
			return createtime;
		}
		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		@Override
		public String toString() {
			return "Item [id=" + id + ", name=" + name + ", price=" + price + ", createtime=" + createtime + ", detail="
					+ detail + "]";
		}
		
}
