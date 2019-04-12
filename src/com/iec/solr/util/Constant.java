package com.iec.solr.util;

/**
 * @author ex_kjkfb_lvrz
 * @Date 2014-9-19 下午04:44:30
 * @description 常量类
 * @Version V1.0
 */
public class Constant {

	/**
	 * 成功状态码
	 */
	public static String SUCCESS_CODE = "000000";

	/**
	 * 错误状态码
	 */
	public static String ERROR_CODE = "111111";
	
	public static String SHOP_GOODS_SORT = "2";
	
	public static Integer SHOP_GOODS_PAGESIZE = 3;

	/**
	 * 资管计划索引字段
	 */
	public static String[] PRODUCT_VISIBLES = { "goods_id", "product_id",
			"shop_id", "org_id", "order_name", "shop_cat_id", "cat_id","modifytime",
			"goods_type", "title", "status", "audit_flag", "createtime", "hot",
			"recommend", "expected_earnings_rate", "prod_name", "min_pchs_m",
			"risk_coler", "company_info", "sort", "view_count", "sell_count",
			"year_income_rate", "prod_risk_level", "prod_type", "dead_line",
			"prod_lifecycle", "nav", "total_nav", "main_pic", "fund_type","fundcorporationcode",
			"inverest_scale", "fav_count", "total_quota", "total_quota2","locked",
			"subs_end_time", "subs_end_date", "min_subs_m", "between_days","shop_name",
			"bank_prod_code" };

	/**
	 * 实物金索引字段
	 */
	public static String[] PRODUCT_GOLD_VISIBLES = { "goods_id", "product_id",
			"shop_id", "org_id", "order_name", "shop_cat_id", "cat_id","modifytime",
			"goods_type", "title", "status", "audit_flag", "createtime", "hot",
			"recommend", "expected_earnings_rate", "prod_name", "prod_weight",
			"price", "sort", "view_count", "sell_count", "main_pic",
			"prod_category", "fav_count","shop_name","locked" };

	/**
	 * 机构索引字段
	 */
	public static String[] SHOP_VISIBLES = { "shop_id", "org_id", "shop_name",
			"description", "province", "city", "area", "address", "logo",
			"key_word", "zipcode", "buss_range", "fav_count", "createtime","locked",
			"s_level", "connect_man", "connect_tel", "service_tel", "prepare_1" };

	/**
	 * 数据库查找添加字段
	 */
	public static String[] SHOP_FIND = { "shop_id", "org_id", "shop_name",
			"description", "province", "city", "area", "address", "logo",
			"key_word", "zipcode", "buss_range", "fav_count", "createtime",
			"s_level", "connect_man", "connect_tel", "service_tel","locked",
			"prepare_1", "modifytime" };

	/**
	 * 机构搜索是需要的商品字段
	 */
	public static String[] PRODUCT_GOODS_FIND = { "shop_id", "price_market",
			"goods_id", "goods_type","product_id", "title", "risk_level", "dead_line",
			"goods_gold_weight", "modifytime", "recommend" };

}
