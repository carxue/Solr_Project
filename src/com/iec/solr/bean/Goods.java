package com.iec.solr.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class Goods implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商品id
	 */
	@Field
	private Long goods_id;
	/**
	 * 产品id
	 */
	@Field
	private String product_id;
	/**
	 * 机构ID
	 */
	private Long org_id;
	/**
	 * 机构名称
	 */
	@Field
	private String order_name;
	/**
	 * 店铺id
	 */
	@Field
	private Long shop_id;
	/**
	 * 店铺分类id
	 */
	@Field
	private Long shop_cat_id;
	/**
	 * 商品类目ID
	 */
	@Field
	private Long cat_id;
	/**
	 * 商品类型1:理财2:信托3:资管4：基金 5：实物金
	 */
	@Field
	private Integer goods_type;
	/**
	 * 标题
	 */
	@Field
	private String title;
	/**
	 * 市场价
	 */
	private Double price_market;
	/**
	 * 货币单位
	 */
	private String price_unit;
	/**
	 * ems运费
	 */
	private Double transit_ems;
	/**
	 * 快递运费
	 */
	private Double transit_exp;
	/**
	 * 平邮运费
	 */
	private Double transit_post;
	/**
	 * 支付方式在线支付，线下支付
	 */
	private Integer pay_types;
	/**
	 * 库存
	 */
	private Long stock;
	/**
	 * 库存商品对应的单位
	 */
	private String unit;
	/**
	 * 商品状态0=下架1=上架
	 */
	private Integer status;
	/**
	 * 审核标志0=待审，1=通过，2=拒绝，3=修改待审，9=删除
	 */
	private Integer audit_flag;
	/**
	 * 拒绝理由
	 */
	private String refusedesc;
	/**
	 * 标志集0=新品，1=精品，2=促销，3=特价
	 */
	private Integer flags;
	/**
	 * 排序值越小越靠前
	 */
	@Field
	private Integer sort;
	/**
	 * 收藏人气
	 */
	@Field
	private Integer fav_count;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 修改时间
	 */
	private Date modifytime;
	/**
	 * 是否锁定0：未锁定 1：已锁定
	 */
	private Integer locked;
	/**
	 * 浏览次数
	 */
	@Field
	private Long view_count;
	/**
	 * 运费承担方0-买家承担1-卖家承担
	 */
	private Integer isfreight;
	/**
	 * 销售数量
	 */
	@Field
	private Long sell_count;
	/**
	 * 审核时间
	 */
	private Date authtime;
	/**
	 * 是否热销(1是，0否)
	 */
	@Field
	private Integer hot;
	/**
	 * 商品发送类型0：普通需要物流的商品1：自动发货商品2：虚拟商品
	 */
	private Integer goodsend_type;
	/**
	 * 是否推荐0：否；1是
	 */
	private Integer recommend;
	/**
	 * 预期收益率
	 */
	@Field
	private String expected_earnings_rate;
	/**
	 * 收益计量单位
	 */
	private String earnings_unit;
	/**
	 * 投资期限
	 */
	@Field
	private Integer dead_line;
	/**
	 * 风险等级
	 */
	private Integer risk_level;
	/**
	 * 上架时间
	 */
	private Date shelves_date;
	/**
	 * 上架天数从上架开始计算到下架的天数
	 */
	private Integer shelves_dates;
	/**
	 * 营销语
	 */
	private String marketing;
	/**
	 * 下架状态 0：手动下架 ，1：自动下架 缺省值是0
	 */
	private Integer undercarriage_status;
	/**
	 * 上架状态 0：手动上架 ，1：自动上架 缺省值是0
	 */
	private Integer upcarriage_status;
	/**
	 * 上架预设时间
	 */
	private Date preset_date;
	/**
	 * 下架时间
	 */
	private Date undercarriage_date;
	/**
	 * 上架期限
	 */
	private Integer max_shelves_days;
	/**
	 * 编辑时间
	 */
	private Date update_date;

	/**
	 * 商品主图
	 */
	@Field
	private String main_pic;
	/**
	 * 实物金的重量
	 */
	private Double goods_gold_weight;
	
	/**
	 * 商品名称
	 */
	@Field
	private String shop_name;
	
	
	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public Double getGoods_gold_weight() {
		return goods_gold_weight;
	}

	public void setGoods_gold_weight(Double goods_gold_weight) {
		this.goods_gold_weight = goods_gold_weight;
	}

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

	public Long getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}

	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public Long getShop_cat_id() {
		return shop_cat_id;
	}

	public void setShop_cat_id(Long shop_cat_id) {
		this.shop_cat_id = shop_cat_id;
	}

	public Long getCat_id() {
		return cat_id;
	}

	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
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

	public String getPrice_unit() {
		return price_unit;
	}

	public void setPrice_unit(String price_unit) {
		this.price_unit = price_unit;
	}

	public Double getTransit_ems() {
		return transit_ems;
	}

	public void setTransit_ems(Double transit_ems) {
		this.transit_ems = transit_ems;
	}

	public Double getTransit_exp() {
		return transit_exp;
	}

	public void setTransit_exp(Double transit_exp) {
		this.transit_exp = transit_exp;
	}

	public Double getTransit_post() {
		return transit_post;
	}

	public void setTransit_post(Double transit_post) {
		this.transit_post = transit_post;
	}

	public Integer getPay_types() {
		return pay_types;
	}

	public void setPay_types(Integer pay_types) {
		this.pay_types = pay_types;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAudit_flag() {
		return audit_flag;
	}

	public void setAudit_flag(Integer audit_flag) {
		this.audit_flag = audit_flag;
	}

	public String getRefusedesc() {
		return refusedesc;
	}

	public void setRefusedesc(String refusedesc) {
		this.refusedesc = refusedesc;
	}

	public Integer getFlags() {
		return flags;
	}

	public void setFlags(Integer flags) {
		this.flags = flags;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getFav_count() {
		return fav_count;
	}

	public void setFav_count(Integer fav_count) {
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

	public Long getView_count() {
		return view_count;
	}

	public void setView_count(Long view_count) {
		this.view_count = view_count;
	}

	public Integer getIsfreight() {
		return isfreight;
	}

	public void setIsfreight(Integer isfreight) {
		this.isfreight = isfreight;
	}

	public Long getSell_count() {
		return sell_count;
	}

	public void setSell_count(Long sell_count) {
		this.sell_count = sell_count;
	}

	public Date getAuthtime() {
		return authtime;
	}

	public void setAuthtime(Date authtime) {
		this.authtime = authtime;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getGoodsend_type() {
		return goodsend_type;
	}

	public void setGoodsend_type(Integer goodsend_type) {
		this.goodsend_type = goodsend_type;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getExpected_earnings_rate() {
		return expected_earnings_rate;
	}

	public void setExpected_earnings_rate(String expected_earnings_rate) {
		this.expected_earnings_rate = expected_earnings_rate;
	}

	public String getEarnings_unit() {
		return earnings_unit;
	}

	public void setEarnings_unit(String earnings_unit) {
		this.earnings_unit = earnings_unit;
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

	public Date getShelves_date() {
		return shelves_date;
	}

	public void setShelves_date(Date shelves_date) {
		this.shelves_date = shelves_date;
	}

	public Integer getShelves_dates() {
		return shelves_dates;
	}

	public void setShelves_dates(Integer shelves_dates) {
		this.shelves_dates = shelves_dates;
	}

	public String getMarketing() {
		return marketing;
	}

	public void setMarketing(String marketing) {
		this.marketing = marketing;
	}

	public Integer getUndercarriage_status() {
		return undercarriage_status;
	}

	public void setUndercarriage_status(Integer undercarriage_status) {
		this.undercarriage_status = undercarriage_status;
	}

	public Integer getUpcarriage_status() {
		return upcarriage_status;
	}

	public void setUpcarriage_status(Integer upcarriage_status) {
		this.upcarriage_status = upcarriage_status;
	}

	public Date getPreset_date() {
		return preset_date;
	}

	public void setPreset_date(Date preset_date) {
		this.preset_date = preset_date;
	}

	public Date getUndercarriage_date() {
		return undercarriage_date;
	}

	public void setUndercarriage_date(Date undercarriage_date) {
		this.undercarriage_date = undercarriage_date;
	}

	public Integer getMax_shelves_days() {
		return max_shelves_days;
	}

	public void setMax_shelves_days(Integer max_shelves_days) {
		this.max_shelves_days = max_shelves_days;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getMain_pic() {
		return main_pic;
	}

	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}

	
}
