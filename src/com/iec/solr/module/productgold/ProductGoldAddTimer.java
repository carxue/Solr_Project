package com.iec.solr.module.productgold;

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
 * @description 资管计划添加定时任务
 * @Version V1.0
 */
@Component("productGoldAddTimer")
public class ProductGoldAddTimer extends AbsJobScheduler{

	private static Logger logger = Logger.getLogger(ProductGoldAddTimer.class);
	public static  String url_product_gold=PropsConfig.getPropValue("url_product_gold",null);
	
	//保存更新数据的时闄1�7
	public static String t_product_gold = null;

	static {
		t_product_gold = SolrConfig.getPropValue("t_product_gold");
	}
	@Autowired
	private ProductGoldService productService;
	
	@Override
	protected synchronized void schedule() {
		// TODO Auto-generated method stub
		 String sqlinner = "SELECT S.SHOP_NAME,S.LOCKED,TF.GOODS_ID,TF.PRODUCT_ID,TF.SHOP_ID,TF.ORG_ID,TF.ORDER_NAME,TF.SHOP_CAT_ID,TF.CAT_ID,TF.GOODS_TYPE,"+
				 "TF.TITLE,TF.STATUS,TF.AUDIT_FLAG,TF.CREATETIME,TF.MODIFYTIME,TF.HOT,TF.RECOMMEND,TF.EXPECTED_EARNINGS_RATE,TF.SORT,TF.VIEW_COUNT,"+
				 "TF.MAIN_PIC,TP.PROD_NAME,TP.PROD_WEIGHT,TF.PRICE_MARKET PRICE,TP.PROD_CATEGORY,"+
				 "(CASE WHEN FA.FAVTYPE!=4 THEN (SELECT COUNT(FA.OBJ_ID) FROM T_FAVORITE FA"+
				 " WHERE FA.OBJ_ID = TF.GOODS_ID AND FA.FAVTYPE!=4) ELSE (SELECT COUNT(FA.OBJ_ID)FROM T_FAVORITE FA"+
				 " WHERE FA.OBJ_ID = TF.GOODS_ID AND FA.FAVTYPE=4) END) fav_count,"+
				 "(SELECT SUM(OG.AMOUNT)FROM T_ORDER_GOODS OG WHERE OG.GOODS_ID = TF.GOODS_ID) sell_count"+
				 " FROM T_GOODS TF, T_PRODUCT_GOLD TP,T_FAVORITE FA ,T_SHOP S"+
				 " WHERE TF.STATUS = 1 AND TF.MODIFYTIME >TO_DATE('"+t_product_gold+"', 'yyyy/mm/dd hh24:mi:ss')"+
				 " AND TF.PRODUCT_ID = TP.PROD_CODE AND TF.GOODS_ID=FA.OBJ_ID(+) AND TF.SHOP_ID=S.SHOP_ID(+) ORDER BY TF.MODIFYTIME";
		 /*"SELECT * FROM T_GOODS TF,T_PRODUCT_GOLD TP WHERE TF.STATUS = 1 AND TF.MODIFYTIME>TO_DATE('"+t_product_gold+"','yyyy/mm/dd hh24:mi:ss') AND TF.PRODUCT_ID=TP.PROD_CODE ORDER BY TF.MODIFYTIME";*/
		 
		 
		 AtomicInteger aInteger = new AtomicInteger(0);//原子方式更新的1�7 int 倄1�7
		 while(true){
			 try {
				if(!productService.addAssetManageIndex(url_product_gold,sqlinner, aInteger.incrementAndGet(),300,Constant.PRODUCT_GOLD_VISIBLES))
				{
					break;
				}
			} catch (Exception e) {
				logger.error("实物金索引添加异常:",e);
				break;
			}
		 }

	}

}
