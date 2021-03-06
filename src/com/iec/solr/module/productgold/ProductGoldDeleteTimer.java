package com.iec.solr.module.productgold;

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
@Component("productGoldDeleteTimer")
public class ProductGoldDeleteTimer extends AbsJobScheduler{
       private static Logger logger = Logger.getLogger(ProductGoldDeleteTimer.class);
       private static  String url_product_gold=PropsConfig.getPropValue("url_product_gold",null);
   	
   	//保存更新数据的时闄1�7
   	public static String t_product_gold_delete = null;
    @Autowired
   	private ProductGoldService assetManageService;
   	static {
   		t_product_gold_delete = SolrConfig.getPropValue("t_product_gold_delete");;
   	}
	@Override
	protected synchronized void schedule() {
		  
		AtomicInteger aInteger = new AtomicInteger();
		//查询下架或�1�7�删除的商品
		String sqlinner = "SELECT * FROM T_GOODS TF,T_PRODUCT_GOLD TP WHERE TF.STATUS != 1 AND TF.MODIFYTIME>TO_DATE('"+t_product_gold_delete+"','yyyy/mm/dd hh24:mi:ss') AND TF.PRODUCT_ID=TP.PROD_CODE ORDER BY TF.MODIFYTIME";
		while(true){
			try {
				if(!assetManageService.deleteAssetManageIndex(url_product_gold, sqlinner, aInteger.incrementAndGet(), 300)){
					break;
				}
			} catch (Exception e) {
				logger.error("删除资管计划索引异常:",e);
				break;
			}
		}
	}
}
