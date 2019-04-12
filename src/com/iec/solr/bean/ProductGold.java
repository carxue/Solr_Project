package com.iec.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

public class ProductGold extends Goods{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品代码
	 */
	private String prod_code;
	/**
	 * 产品名称
	 */
	@Field
	private String prod_name;
	/**
	 * 产品简称
	 */
	private String prod_short_name;
	/**
	 * 产品质地
	 */
	@Field
	private String prod_category;
	/**
	 * 产品规格
	 */
	private String prod_spec;
	/**
	 * 重量单位000-克 001-盎司
	 */
	private String weight_unit;
	/**
	 * 产品重量
	 */
	@Field
	private Double prod_weight;
	/**
	 * 图片总数
	 */
	private String prod_pic_sum;
	/**
	 * 价格
	 */
	@Field
	private Double price;
	/**
	 * 基准价
	 */
	private String baseprice;

	public String getProd_code() {
		return prod_code;
	}

	public void setProd_code(String prod_code) {
		this.prod_code = prod_code;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_short_name() {
		return prod_short_name;
	}

	public void setProd_short_name(String prod_short_name) {
		this.prod_short_name = prod_short_name;
	}

	public String getProd_category() {
		return prod_category;
	}

	public void setProd_category(String prod_category) {
		this.prod_category = prod_category;
	}

	public String getProd_spec() {
		return prod_spec;
	}

	public void setProd_spec(String prod_spec) {
		this.prod_spec = prod_spec;
	}

	public String getWeight_unit() {
		return weight_unit;
	}

	public void setWeight_unit(String weight_unit) {
		this.weight_unit = weight_unit;
	}

	public Double getProd_weight() {
		return prod_weight;
	}

	public void setProd_weight(Double prod_weight) {
		this.prod_weight = prod_weight;
	}

	public String getProd_pic_sum() {
		return prod_pic_sum;
	}

	public void setProd_pic_sum(String prod_pic_sum) {
		this.prod_pic_sum = prod_pic_sum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(String baseprice) {
		this.baseprice = baseprice;
	}

}
