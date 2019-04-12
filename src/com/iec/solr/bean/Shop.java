package com.iec.solr.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 机构实体类
 * 
 * @author ex_kjkfb_xuek
 * 
 */
public class Shop implements Serializable {

	private static final long serialVersionUID = -2664057391092756629L;
	/**
	 * 店铺ID
	 */
	@Field
	private Long shop_id;
	/**
	 * 机构ID（对应B2B中的银行）
	 */
	@Field
	private Long org_id;
	/**
	 * 店铺名称
	 */
	@Field
	private String shop_name;
	/**
	 * 店铺简介
	 */
	@Field
	private String description;
	/**
	 * 省
	 */
	@Field
	private Integer province;
	/**
	 * 市
	 */
	@Field
	private Integer city;
	/**
	 * 区
	 */
	@Field
	private Integer area;
	/**
	 * 详细地址
	 */
	@Field
	private String address;
	/**
	 * 店铺LOGO
	 */
	@Field
	private String logo;
	/**
	 * 关键字
	 */
	@Field
	private String key_word;
	/**
	 * 邮编
	 */
	@Field
	private String zipcode;
	/**
	 * 经营范围
	 */
	@Field
	private String buss_range;
	/**
	 * 收藏人气
	 */
	@Field
	private Long fav_count;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 修改时间
	 */
	private Date modifytime;
	/**
	 * LOCKED：0：否，1是
	 */
	private Integer locked;
	/**
	 * 店铺级别
	 */
	@Field
	private Integer s_level;
	/**
	 * 审核状态：0-待审，1-通过，2-拒绝，9-删除
	 */
	private Integer status;
	/**
	 * 联系人
	 */
	@Field
	private String connect_man;
	/**
	 * 联系电话
	 */
	@Field
	private String connect_tel;
	/**
	 * 客服电话
	 */
	@Field
	private String service_tel;
	/**
	 * 机构所对应的商品推荐列表最多3个
	 */
	private List<ShopGoods> goodsList;
/*
	*//**
	 * 机构所对应的商品推荐列表最多3个-保存到solr
	 *//*
	private String recommendGoods;*/

	/**
	 * 该机构的商品总数
	 */
	@Field
	private Integer goodsNum;
	
	@Field
	private Double price_market;
	
	/**
	 * 店铺的省市区
	 */
	@Field
	private String prepare_1;
	
	public Double getPrice_market() {
		return price_market;
	}

	public void setPrice_market(Double price_market) {
		this.price_market = price_market;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public Long getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getKey_word() {
		return key_word;
	}

	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getBuss_range() {
		return buss_range;
	}

	public void setBuss_range(String buss_range) {
		this.buss_range = buss_range;
	}

	public Long getFav_count() {
		return fav_count;
	}

	public void setFav_count(Long fav_count) {
		this.fav_count = fav_count;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getS_level() {
		return s_level;
	}

	public void setS_level(Integer s_level) {
		this.s_level = s_level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getConnect_man() {
		return connect_man;
	}

	public void setConnect_man(String connect_man) {
		this.connect_man = connect_man;
	}

	public String getConnect_tel() {
		return connect_tel;
	}

	public void setConnect_tel(String connect_tel) {
		this.connect_tel = connect_tel;
	}

	public String getService_tel() {
		return service_tel;
	}

	public void setService_tel(String service_tel) {
		this.service_tel = service_tel;
	}

	public List<ShopGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<ShopGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getPrepare_1() {
		return prepare_1;
	}

	public void setPrepare_1(String prepare_1) {
		this.prepare_1 = prepare_1;
	}
	
}
