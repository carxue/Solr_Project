package com.iec.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

public class Product extends Goods {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品代码
	 */
	private String prod_code;
	/**
	 * 内部产品代码
	 */
	@Field
	private String bank_prod_code;
	/**
	 * 产品风险等级
	 */
	@Field
	private String prod_risk_level;
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
	 * 起息日
	 */
	private String value_date;
	/**
	 * 不代收不代付标识
	 */
	/* private String cp_flag; */
	/**
	 * 专属产品标识
	 */
	/* private String onwer_prod; */
	/**
	 * 投资领域
	 */
	@Field
	private String inverest_scale;
	/**
	 * 清算方式
	 */
	/* private String liqu_type; */
	/**
	 * 产品收市时间
	 */
	private String trans_close_time;
	/**
	 * 风险控制
	 */
	@Field
	private String risk_coler;
	/**
	 * 产品销售状态
	 */
	private String prod_sale_status;
	/**
	 * 供应商
	 */
	@Field
	private String company_info;
	/**
	 * 预期年化收益率
	 */
	@Field
	private Double year_income_rate;
	/**
	 * 产品存续期
	 */
	@Field
	private Double between_days;
	/**
	 * 产品周期
	 */
	@Field
	private Double prod_lifecycle;
	/**
	 * 开放结束日期
	 */
	private String open_end_date;
	/**
	 * 开放起始时间
	 */
	private String open_begin_time;
	/**
	 * 开放起始日期
	 */
	private String open_begin_date;
	/**
	 * 认购起始日期
	 */
	private String subs_begin_date;
	/**
	 * 认购起始时间
	 */
	private String subs_begin_time;
	/**
	 * 认购结束日期
	 */
	@Field
	private String subs_end_date;
	/**
	 * 认购结束时间
	 */
	@Field
	private String subs_end_time;
	/**
	 * 周期类型
	 */
	private String period_type;
	/**
	 * 产品币种
	 */
	private String prod_currency;
	/**
	 * 产品类型
	 */
	@Field
	private String prod_type;
	/**
	 * 产品大类
	 */
	private String prod_big_type;
	/**
	 * 单笔申购起点金额
	 */
	@Field
	private Double min_pchs_m;
	/**
	 * 单笔申购最高金额
	 */
	private String max_pchs_m;
	/**
	 * 单笔申购递增金额
	 */
	private String step_pchs_m;
	/**
	 * 发行规模下限
	 */
	private String min_size;
	/**
	 * 单笔认购起点金额
	 */
	@Field
	private Double min_subs_m;
	/**
	 * 单笔认购最高金额
	 */
	private String max_subs_m;
	/**
	 * 单笔认递增金额
	 */
	private String step_subs_m;
	/**
	 * 最低持有份额
	 */
	private String min_hold_m;
	/**
	 * 单笔赎回最低份额
	 */
	private String min_redeem_m;
	/**
	 * 累计购买金额上限
	 */
	private String max_buy_m;
	/**
	 * 成立日
	 */
	private String establish_date;
	/**
	 * 清盘日
	 */
	private String winding_date;
	/**
	 * 归集户编号
	 */
	/* private String acct_serno; */
	/**
	 * 归集账户
	 */
	/* private String acct_no; */
	/**
	 * 归集账户名称
	 */
	/* private String acct_nm; */
	/**
	 * 开户行
	 */
	/* private String exchange_code; */
	/**
	 * 开户银行名称
	 */
	/* private String open_bank_name; */
	/**
	 * 开户省份
	 */
	/* private String open_province; */
	/**
	 * 开户城市
	 */
	/* private String open_city; */
	/**
	 * 开户行所在地区
	 */
	/* private String open_area; */
	/**
	 * 产品总额度
	 */
	@Field
	private String total_quota;
	/**
	 * 客户总额度
	 */
	@Field
	private String total_quota2;

	/**
	 * 最新净值
	 */
	@Field
	private Double nav;
	/**
	 * 累计净值
	 */
	@Field
	private Double total_nav;

	/**
	 * 基金类型
	 */
	@Field
	private String fund_type;
	
	/**
	 * 基金代码
	 */
	@Field
	private String fundcorporationcode;

	
	public String getFundcorporationcode() {
		return fundcorporationcode;
	}

	public void setFundcorporationcode(String fundcorporationcode) {
		this.fundcorporationcode = fundcorporationcode;
	}

	public String getProd_code() {
		return prod_code;
	}

	public void setProd_code(String prod_code) {
		this.prod_code = prod_code;
	}

	public String getBank_prod_code() {
		return bank_prod_code;
	}

	public void setBank_prod_code(String bank_prod_code) {
		this.bank_prod_code = bank_prod_code;
	}

	public String getProd_risk_level() {
		return prod_risk_level;
	}

	public void setProd_risk_level(String prod_risk_level) {
		this.prod_risk_level = prod_risk_level;
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

	public String getValue_date() {
		return value_date;
	}

	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}

	public String getInverest_scale() {
		return inverest_scale;
	}

	public void setInverest_scale(String inverest_scale) {
		this.inverest_scale = inverest_scale;
	}

	public String getTrans_close_time() {
		return trans_close_time;
	}

	public void setTrans_close_time(String trans_close_time) {
		this.trans_close_time = trans_close_time;
	}

	public String getRisk_coler() {
		return risk_coler;
	}

	public void setRisk_coler(String risk_coler) {
		this.risk_coler = risk_coler;
	}

	public String getProd_sale_status() {
		return prod_sale_status;
	}

	public void setProd_sale_status(String prod_sale_status) {
		this.prod_sale_status = prod_sale_status;
	}

	public String getCompany_info() {
		return company_info;
	}

	public void setCompany_info(String company_info) {
		this.company_info = company_info;
	}

	public Double getYear_income_rate() {
		return year_income_rate;
	}

	public void setYear_income_rate(Double year_income_rate) {
		this.year_income_rate = year_income_rate;
	}

	public Double getBetween_days() {
		return between_days;
	}

	public void setBetween_days(Double between_days) {
		this.between_days = between_days;
	}

	public Double getProd_lifecycle() {
		return prod_lifecycle;
	}

	public void setProd_lifecycle(Double prod_lifecycle) {
		this.prod_lifecycle = prod_lifecycle;
	}

	public String getOpen_end_date() {
		return open_end_date;
	}

	public void setOpen_end_date(String open_end_date) {
		this.open_end_date = open_end_date;
	}

	public String getOpen_begin_time() {
		return open_begin_time;
	}

	public void setOpen_begin_time(String open_begin_time) {
		this.open_begin_time = open_begin_time;
	}

	public String getOpen_begin_date() {
		return open_begin_date;
	}

	public void setOpen_begin_date(String open_begin_date) {
		this.open_begin_date = open_begin_date;
	}

	public String getSubs_begin_date() {
		return subs_begin_date;
	}

	public void setSubs_begin_date(String subs_begin_date) {
		this.subs_begin_date = subs_begin_date;
	}

	public String getSubs_begin_time() {
		return subs_begin_time;
	}

	public void setSubs_begin_time(String subs_begin_time) {
		this.subs_begin_time = subs_begin_time;
	}

	public String getSubs_end_date() {
		return subs_end_date;
	}

	public void setSubs_end_date(String subs_end_date) {
		this.subs_end_date = subs_end_date;
	}

	public String getSubs_end_time() {
		return subs_end_time;
	}

	public void setSubs_end_time(String subs_end_time) {
		this.subs_end_time = subs_end_time;
	}

	public String getPeriod_type() {
		return period_type;
	}

	public void setPeriod_type(String period_type) {
		this.period_type = period_type;
	}

	public String getProd_currency() {
		return prod_currency;
	}

	public void setProd_currency(String prod_currency) {
		this.prod_currency = prod_currency;
	}

	public String getProd_type() {
		return prod_type;
	}

	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}

	public String getProd_big_type() {
		return prod_big_type;
	}

	public void setProd_big_type(String prod_big_type) {
		this.prod_big_type = prod_big_type;
	}

	public Double getMin_pchs_m() {
		return min_pchs_m;
	}

	public void setMin_pchs_m(Double min_pchs_m) {
		this.min_pchs_m = min_pchs_m;
	}

	public String getMax_pchs_m() {
		return max_pchs_m;
	}

	public void setMax_pchs_m(String max_pchs_m) {
		this.max_pchs_m = max_pchs_m;
	}

	public String getStep_pchs_m() {
		return step_pchs_m;
	}

	public void setStep_pchs_m(String step_pchs_m) {
		this.step_pchs_m = step_pchs_m;
	}

	public String getMin_size() {
		return min_size;
	}

	public void setMin_size(String min_size) {
		this.min_size = min_size;
	}

	public Double getMin_subs_m() {
		return min_subs_m;
	}

	public void setMin_subs_m(Double min_subs_m) {
		this.min_subs_m = min_subs_m;
	}

	public String getMax_subs_m() {
		return max_subs_m;
	}

	public void setMax_subs_m(String max_subs_m) {
		this.max_subs_m = max_subs_m;
	}

	public String getStep_subs_m() {
		return step_subs_m;
	}

	public void setStep_subs_m(String step_subs_m) {
		this.step_subs_m = step_subs_m;
	}

	public String getMin_hold_m() {
		return min_hold_m;
	}

	public void setMin_hold_m(String min_hold_m) {
		this.min_hold_m = min_hold_m;
	}

	public String getMin_redeem_m() {
		return min_redeem_m;
	}

	public void setMin_redeem_m(String min_redeem_m) {
		this.min_redeem_m = min_redeem_m;
	}

	public String getMax_buy_m() {
		return max_buy_m;
	}

	public void setMax_buy_m(String max_buy_m) {
		this.max_buy_m = max_buy_m;
	}

	public String getEstablish_date() {
		return establish_date;
	}

	public void setEstablish_date(String establish_date) {
		this.establish_date = establish_date;
	}

	public String getWinding_date() {
		return winding_date;
	}

	public void setWinding_date(String winding_date) {
		this.winding_date = winding_date;
	}

	public String getTotal_quota() {
		return total_quota;
	}

	public void setTotal_quota(String total_quota) {
		this.total_quota = total_quota;
	}

	public String getTotal_quota2() {
		return total_quota2;
	}

	public void setTotal_quota2(String total_quota2) {
		this.total_quota2 = total_quota2;
	}

	public Double getNav() {
		return nav;
	}

	public void setNav(Double nav) {
		this.nav = nav;
	}

	public Double getTotal_nav() {
		return total_nav;
	}

	public void setTotal_nav(Double total_nav) {
		this.total_nav = total_nav;
	}

	public String getFund_type() {
		return fund_type;
	}

	public void setFund_type(String fund_type) {
		this.fund_type = fund_type;
	}

}
