package com.iec.solr.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.iec.solr.module.SolrImpl;
import com.iec.solr.module.product.ProductAddTimer;
import com.iec.solr.module.productgold.ProductGoldAddTimer;
import com.iec.solr.module.shop.ShopAddTimer;
import com.iec.solr.module.shopgoods.ShopGoodsAddTimer;
import com.iec.solr.schedule.AbsJobScheduler;
import com.iec.solr.util.PropsConfig;

/**    
 *     
 * 项目名称：SolrService    
 * 类名称：SolrRecreate    
 * 类描述：   每天早上1点钟重建索引
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月14日 下午3:28:57    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月14日 下午3:28:57    
 * 修改备注：    
 * @version     
 *     
 */
@Controller
@Component("solrRecreate")
public class SolrRecreate extends AbsJobScheduler{
	
	private static final Logger log = Logger.getLogger(SolrRecreate.class);
	
	public static  String url_product=PropsConfig.getPropValue("url_product",null);
	
	public static  String url_product_gold=PropsConfig.getPropValue("url_product_gold",null);
	
	public static  String url_shop=PropsConfig.getPropValue("url_shop",null);
	
	public static  String url_shop_goods=PropsConfig.getPropValue("url_shop_goods",null);
	
	@Autowired
	private SolrImpl solrimpl;
	
	/**    
	 * execute(定时重建索引)    
	 * @return void   
	 * @Exception 异常对象   
	*/
	public void schedule() {
		solrimpl.init(url_product);
		solrimpl.deleteAll("goods_id:*");
		ProductAddTimer.t_product="10001010 00:00:00";
		solrimpl.init(url_product_gold);
		solrimpl.deleteAll("goods_id:*");
		ProductGoldAddTimer.t_product_gold="10001010 00:00:00";
		solrimpl.init(url_shop);
		solrimpl.deleteAll("shop_id:*");
		ShopAddTimer.t_shop = "10001010 00:00:00";
		solrimpl.init(url_shop_goods);
		solrimpl.deleteAll("goods_id:*");
		ShopGoodsAddTimer.t_shop_goods = "10001010 00:00:00";
		log.info("-------------SolrRecreate:  Solr creates indexed is finished !-----------");
	}

}
