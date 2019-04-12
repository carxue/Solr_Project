package com.iec.solr.module.shop;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iec.solr.bean.Shop;
import com.iec.solr.module.SolrImpl;
import com.iec.solr.schedule.AbsJobScheduler;
import com.iec.solr.util.Constant;
import com.iec.solr.util.PropsConfig;
import com.iec.solr.util.SolrConfig;

/**
 * 
 * 项目名称：SolrService 类名称：FinanceAddTimer 类描述： 理财添加数据定时器 创建人：ex_kjkfb_xuek
 * 创建时间：2013年3月14日 下午3:19:20 修改人：ex_kjkfb_xuek 修改时间：2013年3月14日 下午3:19:20 修改备注：
 * 
 * @version
 * 
 */
@Component("shopAddTimer")
public class ShopAddTimer extends AbsJobScheduler {

	private static final Logger log = Logger.getLogger(ShopAddTimer.class);

	public static final String url_shop = PropsConfig.getPropValue(
			"url_shop", null);

	// 更新理财的时间
	public static String t_shop = null;

	static {
		t_shop = SolrConfig.getPropValue("t_shop");
	}

	/*private static boolean lock = false;*/

	private @Autowired ShopService shopService;

	/**
	 * execute(定时器)
	 * @return void
	 * @Exception 异常对象
	 */
	// @Scheduled(cron = "0/10 * * * * ?")
	public synchronized void schedule() {
		/*if (lock == true)
			return;*/
		SolrImpl billSolr = new SolrImpl();
		billSolr.init(url_shop);//solr初始化
		// 分页查询理财的部分分页语句
		String sqlinner = "SELECT * FROM T_SHOP TF WHERE TF.STATUS = 1 AND TF.MODIFYTIME>=TO_DATE('"+t_shop+"','yyyy/mm/dd hh24:mi:ss')  ORDER BY TF.MODIFYTIME";
		List<Map<String, Object>> shopList = null;
		List<Shop> reShopList = null;
		int currentpage = 0;
		// 将最新的数据通过分页添加进索引
		/*lock = true;*/
		while (true) {
			currentpage++;
			try {
				// goods和finance关系先查找Goods信息
				shopList = shopService.findMapShopList(sqlinner, null, currentpage, 300);
				if (shopList != null && shopList.size() != 0) {
					reShopList = shopService.findShopList(shopList);
					shopList.clear();
					int length = reShopList.size();
					if (length > 0) {
						// 将数据添加到索引中
						billSolr.updateT(reShopList, Constant.SHOP_VISIBLES);
						// 添加索引成功后将最新时间添加t_finance和持久化]
						String date = (new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(reShopList.get(length - 1).getModifytime()));
//						AnalyXML.updateNewTime(date, "t_shop");
						SolrConfig.setPropValue("t_shop", date);
						t_shop = date;

						shopList.clear();
					}
				} else
					break;// 没有新数据则跳出
			} catch (Exception e) {
				log.error("FinanceAddTimer add 理财定时添加",e);
				break;
			}
		}
		/*lock = false;//解锁使其可访问*/
	}

}
