package com.iec.solr.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 商铺中推荐的商品
 * @author ex_kjkfb_xuek
 *
 */
public class ShopGoods implements Serializable{
	
	private static final long serialVersionUID = 1379705524306681012L;
	/**
	 * 商品id
	 */
	@Field
	private Long goods_id;
	/**
	 * 店铺id
	 */
	@Field
	private Long shop_id;
	/**
	 * 市场价
	 */
	@Field
	private Double price_market;
	/**
	 * 产品id
	 */
	@Field
	private String product_id;
	/**
	 * 标题
	 */
	@Field
	private String title;
	/**
	 * 风险等级
	 */
	@Field
	private Integer risk_level;
	/**
	 * 投资期限
	 */
	@Field
	private Integer dead_line;
	/**
	 * 商品类型1:理财2:信托3:资管4：基金 5：实物金
	 */
	@Field
	private Integer goods_type;
	/**
	 * 修改时间
	 */
	@Field
	private Date modifytime;
	
	/**
	 * 是否推荐0：否；1是
	 */
	private Integer recommend;
	/**
	 * 实物金的重量
	 */
	@Field
	private Double goods_gold_weight;
	
	public Long getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public Long getShop_id() {
		return shop_id;
	}
	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(Integer goods_type) {
		this.goods_type = goods_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice_market() {
		return price_market;
	}
	public void setPrice_market(Double price_market) {
		this.price_market = price_market;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getDead_line() {
		return dead_line;
	}
	public void setDead_line(Integer dead_line) {
		this.dead_line = dead_line;
	}
	public Integer getRisk_level() {
		return risk_level;
	}
	public void setRisk_level(Integer risk_level) {
		this.risk_level = risk_level;
	}
	public Double getGoods_gold_weight() {
		return goods_gold_weight;
	}
	public void setGoods_gold_weight(Double goods_gold_weight) {
		this.goods_gold_weight = goods_gold_weight;
	}
}
