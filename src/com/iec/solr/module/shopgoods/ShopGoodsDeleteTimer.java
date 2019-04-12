package com.iec.solr.module.shopgoods;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iec.solr.schedule.AbsJobScheduler;
import com.iec.solr.util.PropsConfig;
import com.iec.solr.util.SolrConfig;

/**
 * @author ex_kjkfb_lvrz
 * @Date 2014-9-17 上午11:07:37
 * @description 资管计划索引定时删除
 * @Version V1.0
 */
@Component("shopGoodsDeleteTimer")
public class ShopGoodsDeleteTimer extends AbsJobScheduler{
       private static Logger logger = Logger.getLogger(ShopGoodsDeleteTimer.class);
       private static  String url_shop_goods=PropsConfig.getPropValue("url_shop_goods",null);
   	
   	//保存更新数据的时闄1�7
   	public static String t_shop_goods_delete = null;
    @Autowired
   	private ShopGoodsService shopGoodsService;
   	static {
   		t_shop_goods_delete = SolrConfig.getPropValue("t_shop_goods_delete");;
   	}
	@Override
	protected synchronized void schedule() {
		  
		AtomicInteger aInteger = new AtomicInteger();
		//查询下架或�1�7�删除的商品
		String sqlinner = "SELECT TF.GOODS_ID ,TF.MODIFYTIME FROM T_GOODS TF WHERE TF.STATUS != 1 AND TF.MODIFYTIME>TO_DATE('"+t_shop_goods_delete+"','yyyy/mm/dd hh24:mi:ss') ORDER BY TF.MODIFYTIME";
		while(true){
			try {
				if(!shopGoodsService.deleteShopGoodsIndex(url_shop_goods, sqlinner, aInteger.incrementAndGet(), 300)){
					break;
				}
			} catch (Exception e) {
				logger.error("ShopGoodsDeleteTimer.schedule删除机构商品索引异常:",e);
				break;
			}
		}
	}
}
