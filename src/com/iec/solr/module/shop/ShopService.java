package com.iec.solr.module.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.iec.app.exception.ServiceException;
import com.iec.app.utils.Utils;
import com.iec.solr.bean.Shop;
import com.iec.solr.bean.ShopGoods;
import com.iec.solr.module.SolrImpl;
import com.iec.solr.module.goods.GoodsDao;
import com.iec.solr.module.shopgoods.ShopGoodsService;
import com.iec.solr.util.Constant;
import com.iec.solr.util.PropsConfig;

/**    
 *     
 * 项目名称：SolrService    
 * 类名称：FinanceService    
 * 类描述：   理财服务 
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月14日 下午3:33:22    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月14日 下午3:33:22    
 * 修改备注：    
 * @version     
 *     
 */
/**
 * @author ex_kjkfb_xuek
 * 
 */
@Repository
public class ShopService {

	private static final Logger log = Logger.getLogger(ShopService.class);

	private static final String url_shop = PropsConfig.getPropValue(
			"url_shop", null);
	
	private static Gson gson = new Gson();

	private @Autowired ShopDao shopDao;
	
	private @Autowired GoodsDao goodsDao;
	
	private @Autowired ShopGoodsService shopGoodsService;
	
	private @Autowired SolrImpl solrimpl;
	
	public List<Map<String,Object>> findMapShopList(String sql, Object[] params, int currentpage, int pagesize) throws ServiceException{
		return shopDao.findMapShopList(sql,params, currentpage,pagesize);
	}

	/**
	 * findFinances(更加goods的product_id查找理财信息)
	 * 
	 * @return List<Finance>
	 * @Exception 异常对象
	 */
	public List<Shop> findShopList(List<Map<String, Object>> listShop) {
		List<Shop> shopList = new ArrayList<Shop>();
		for (Map<String, Object> maps : listShop) {
			Shop shop = new Shop();
			for(int i=0;i<Constant.SHOP_FIND.length;i++){
				String column = Constant.SHOP_FIND[i];
				try {
					if(!column.equals("create_time")){
						BeanUtils.setProperty(shop, column, maps.get(column));
					}else{
						if(column.equals("create_time")){
							shop.setCreatetime((Date)maps.get(column));;
						}
					}
				} catch (Exception e) {
					log.error("该对象不存在该字段  ShopService.findShopList:"+column,e);
				}
			}
			shopList.add(shop);
		}
		return shopList;
	}

	/**
	 * 理财查询唯一方法
	 * @param params   多条件查询
	 * @param column   多添加排序
	 * @param sort     排序
	 * @param current  
	 * @param pagesize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> queryShops(String params, String column, String sort,Integer current, Integer pagesize) {
		List<Shop> slist = new ArrayList<Shop>(); //返回给用户的对象列表
		Map<String,Object> fmap = new HashMap<String,Object>();//返回给用户Map的对象
		solrimpl.init(url_shop); //solr初始化
		String[] condition = null;   //查询条件
		String[] sortColumns = null; //排序字段
		if (null != params)
			condition = params.split("-");
		if (null != column)
			sortColumns = column.split("-");
		if (null == current || current < 1)
			current = 1;
		if (null == pagesize || pagesize < 1)
			pagesize = 10;
		long totalRecord = 0;// 总记录数
		
		//机构所对应的商品查询添加构造
		String sgCondition = null;
		String sgColumn = "recommend-modifytime";
		if(Utils.isNotEmpty(condition)){
			for(int i=0;i<condition.length;i++){
				if(condition[i].indexOf("buss_range")!=-1){
					sgCondition = condition[i].replaceAll("buss_range", "goods_type");
					break;
				}
			}
		}
		
		try {
			QueryResponse qs = solrimpl.solrSortQueryNew(condition, sortColumns, sort, current, pagesize);
			slist = qs.getBeans(Shop.class);
			//机构所对应的推荐商品排序查询
			for(int i=0;i<slist.size();i++){
				Shop shop = slist.get(i);
				String newCondition = "shop_id:"+shop.getShop_id();
				if(Utils.isNotEmpty(sgCondition))
					newCondition += "-"+sgCondition;
				Map<String,Object> sgMap = shopGoodsService.queryShopGoodsList(newCondition, sgColumn, Constant.SHOP_GOODS_SORT, null, Constant.SHOP_GOODS_PAGESIZE);
				shop.setGoodsList((List<ShopGoods>) sgMap.get("list"));
				shop.setGoodsNum(Integer.parseInt(sgMap.get("goodNum").toString()));
			}
			totalRecord =  qs.getResults().getNumFound();
		}catch(Exception e){
			log.error("机构查询异常ShopService.queryShops: ",e);
		}

		long totalPage = totalRecord / pagesize;
		long model = totalRecord % pagesize;
		if (model != 0){
			totalPage = totalPage + 1;
		}
		Map<String, Object> objectT = new HashMap<String, Object>(); // 拼凑page对象
		Map<String, Object> page = new HashMap<String, Object>(); // 拼凑page对象
		page.put("currentPage", current);
		page.put("pageSize", pagesize);
		page.put("totalPage", totalPage);
		page.put("totalRecord", totalRecord);

		fmap.put("page", page);
		fmap.put("list", slist);
		fmap.put("objectT", objectT);

		return fmap;
	}

}
