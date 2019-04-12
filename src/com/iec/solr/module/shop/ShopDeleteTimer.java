package com.iec.solr.module.shop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iec.solr.module.SolrImpl;
import com.iec.solr.module.goods.GoodsService;
import com.iec.solr.schedule.AbsJobScheduler;
import com.iec.solr.util.PropsConfig;
import com.iec.solr.util.SolrConfig;

/**    
 *     
 * 项目名称：SolrService    
 * 类名称：FinanceDeleteTimer    
 * 类描述：    删除下架理财定时器
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月14日 下午3:28:57    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月14日 下午3:28:57    
 * 修改备注：    
 * @version     
 *     
 */
@Component("shopDeleteTimer")
public class ShopDeleteTimer extends AbsJobScheduler{
	
	private static final Logger log = Logger.getLogger(ShopDeleteTimer.class);
	
	private static final String url_shop=PropsConfig.getPropValue("url_shop",null);


	//保存删除的最新时间
	private static String t_shop_delete = null;

	static {
		t_shop_delete = SolrConfig.getPropValue("t_shop_delete");;
	}
	
	/*private static boolean lock = false;*/

	@Autowired
	private GoodsService goodsService;

	/**    
	 * execute(删除理财定时器)    
	 * @return void   
	 * @Exception 异常对象   
	*/
	//@Scheduled(cron = "0/12 * * * * ?")
	public synchronized void schedule() {
		/*if(lock == true)
			return;*/
		//初始化solr
		SolrImpl billSolr = new SolrImpl();
		billSolr.init(url_shop);
		//分页查询理财
		String sqlinner = "SELECT * FROM T_SHOP TF WHERE TF.STATUS!=1 AND TF.MODIFYTIME>=TO_DATE('"+t_shop_delete+"','yyyy/mm/dd hh24:mi:ss')  ORDER BY TF.MODIFYTIME";
		List<Map<String,Object>> goodsList = null;
		List<String> goods_ids=new ArrayList<String>();
		int currentpage = 0;
		/*lock = true;*/
		while (true) {
			currentpage++;
			try
			{
				// 获取下架的理财的product_id
				goodsList = goodsService.findGoods(sqlinner, null, currentpage, 500);
				int length=goodsList.size();
				if (goodsList != null&&length!=0) {
					for(int i=0;i<goodsList.size();i++){
						goods_ids.add(goodsList.get(i).get("shop_id").toString());
					}
					// 批量删除索引
					billSolr.deleteBatch(goods_ids);
					goods_ids.clear();
					
					//更新理财的时间
					t_shop_delete = (new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(goodsList.get(length - 1).get("modifytime")));
					goodsList.clear();
				}else  //没有数据是这跳出
				   break;
			}
			catch(Exception e) {
				log.error("ShopDeleteTimer delete : ",e);
				break;
			}
		}
		goods_ids=null;
		/*lock = false;//解锁*/		
//		AnalyXML.updateNewTime(t_shop_delete, "t_shop_delete");
		SolrConfig.setPropValue("t_shop_delete", t_shop_delete);
	}

}
