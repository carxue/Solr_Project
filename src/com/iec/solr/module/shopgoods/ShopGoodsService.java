package com.iec.solr.module.shopgoods;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iec.app.exception.ServiceException;
import com.iec.solr.bean.ShopGoods;
import com.iec.solr.module.SolrImpl;
import com.iec.solr.util.PropsConfig;
import com.iec.solr.util.SolrConfig;

/**
 * @author ex_kjkfb_lvrz
 * @Date 2014-9-17 上午09:49:05
 * @description
 * @Version V1.0
 */
@Service
public class ShopGoodsService {
	
	private static Logger logger = Logger.getLogger(ShopGoodsService.class);

	
	public static  String url_shop_goods=PropsConfig.getPropValue("url_shop_goods",null);
	
	private @Autowired ShopGoodsDao shopGoodsDao;
	
	private @Autowired SolrImpl solrimpl;
	
	/**
	 * 批量添加资管计划索引 是否成功
	 * @param sql
	 * @param currentpage
	 * @param pagesize
	 * @param visibles
	 * @throws ServiceException 
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public boolean addShopGoodsIndex(String solrUrl,String sql,int currentpage,int pagesize,String[] visibles) throws ServiceException, SolrServerException, IOException{
		List<Map<String, Object>> listMap = shopGoodsDao.queryGoods(sql, null, currentpage, pagesize);
		logger.info("机构商品查找数据长度:"+listMap.size());
		if(listMap!=null&&listMap.size()>0){
		    if(solrimpl.addOrUpdate(solrUrl,listMap, visibles)){
		    	String date = (new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(listMap.get(listMap.size()-1).get("modifytime")));
//				AnalyXML.updateNewTime(date, "t_shop_goods");
		    	SolrConfig.setPropValue("t_shop_goods", date);
				ShopGoodsAddTimer.t_shop_goods = date;
				logger.info("机构商品成功添加索引长度:"+listMap.size());
		    	return true;
		    }else{
		       return false;
		    }
		}else {
			return false;
		}
	}
	
	/**
	 * 删除资管计划索引
	 * @param solrUrl
	 * @param sql
	 * @param currentpage
	 * @param pagesize
	 * @return
	 * @throws ServiceException 
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public boolean deleteShopGoodsIndex(String solrUrl,String sql,int currentpage,int pagesize) throws ServiceException, SolrServerException, IOException{
		List<Map<String,Object>> goodsList = shopGoodsDao.queryGoods(sql, null, currentpage, pagesize);
		logger.info("机构商品查询将要删除数据的长度:"+goodsList.size());
		if(goodsList!=null&&goodsList.size()>0){
			if(solrimpl.deleteBatchIndex(goodsList,solrUrl,"goods_id")){
				String date = (new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(goodsList.get(goodsList.size()-1).get("modifytime")));
//				AnalyXML.updateNewTime(date, "t_shop_goods_delete");
				SolrConfig.setPropValue("t_shop_goods_delete", date);
				ShopGoodsDeleteTimer.t_shop_goods_delete=date;
				logger.info("机构商品成功删除索引的长度:"+goodsList.size());
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public Map<String,Object> queryShopGoodsList(String params, String column,String sort,Integer current,Integer pagesize){
		List<ShopGoods> list=null;
		Map<String,Object> fmap = new HashMap<String,Object>();//返回给用户Map的对象
		solrimpl.init(url_shop_goods); //solr初始化
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
		try {
			QueryResponse qs = solrimpl.solrSortQueryNew(condition, sortColumns, sort, current, pagesize);
			list = qs.getBeans(ShopGoods.class);
			totalRecord =  qs.getResults().getNumFound();
		}catch(Exception e){
			logger.error("机构商品查询异常ShopGoodsService.queryProductList: ",e);
		}
		fmap.put("goodNum", totalRecord);
		fmap.put("list", list);
		return fmap;
	}
}
