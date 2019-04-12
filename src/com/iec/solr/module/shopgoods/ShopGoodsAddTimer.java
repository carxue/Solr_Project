package com.iec.solr.module.shopgoods;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iec.solr.schedule.AbsJobScheduler;
import com.iec.solr.util.Constant;
import com.iec.solr.util.PropsConfig;
import com.iec.solr.util.SolrConfig;

/**
 * @author ex_kjkfb_lvrz
 * @Date 2014-9-17 上午09:10:17
 * @description 机构商品列表索引
 * @Version V1.0
 */
@Component("shopGoodsAddTimer")
public class ShopGoodsAddTimer extends AbsJobScheduler{

	private static Logger logger = Logger.getLogger(ShopGoodsAddTimer.class);
	public static  String url_shop_goods=PropsConfig.getPropValue("url_shop_goods",null);
	
	//保存更新数据的时闄1�7
	public static String t_shop_goods = null;

	static {
		t_shop_goods = SolrConfig.getPropValue("t_shop_goods");;
	}
	@Autowired
	private ShopGoodsService productService;
	
	@Override
	protected synchronized void schedule() {
		// TODO Auto-generated method stub
		 String sqlinner =  "select g.goods_id,g.shop_id,g.product_id,g.title, g.goods_type, g.modifytime,g.recommend, p.min_subs_m, pg.prod_weight,"+
       "(CASE WHEN p.prod_code is not null THEN p.between_days end) dead_line,"+
       "(CASE WHEN p.prod_code is not null THEN p.prod_risk_level end) risk_level,"+
       "(CASE WHEN pg.prod_code is not null THEN pg.prod_weight end) goods_gold_weight,"+
       "(CASE WHEN p.prod_code is not null THEN to_char(p.min_subs_m) ELSE to_char(pg.price) end) price_market"+
       " from t_goods g, t_product p, t_product_gold pg where g.status = 1 and g.product_id = p.prod_code(+) and g.product_id = pg.prod_code(+)"+
       " and g.modifytime > to_date('"+t_shop_goods+"', 'yyyy/mm/dd hh24:mi:ss')order by g.modifytime";
		 
		 
		 AtomicInteger aInteger = new AtomicInteger(0);//原子方式更新的
		 while(true){
			 try {
				if(!productService.addShopGoodsIndex(url_shop_goods,sqlinner, aInteger.incrementAndGet(),300,Constant.PRODUCT_GOODS_FIND))
				{
					break;
				}
			} catch (Exception e) {
				logger.error("ShopGoodsAddTimer.schedule机构商户添加异常:",e);
				break;
			}
		 }

	}

}
