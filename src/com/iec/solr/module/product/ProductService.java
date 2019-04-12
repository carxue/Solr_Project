package com.iec.solr.module.product;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iec.app.exception.ServiceException;
import com.iec.solr.bean.AutoComplete;
import com.iec.solr.bean.Product;
import com.iec.solr.module.SolrImpl;
import com.iec.solr.module.goods.GoodsDao;
import com.iec.solr.util.Constant;
import com.iec.solr.util.PropsConfig;
import com.iec.solr.util.SolrConfig;

/**
 * @author ex_kjkfb_lvrz
 * @Date 2014-9-17 上午09:49:05
 * @description
 * @Version V1.0
 */
@Service
public class ProductService {
	
	private static Logger logger = Logger.getLogger(ProductService.class);

	
	public static  String url_product=PropsConfig.getPropValue("url_product",null);
	
	private @Autowired ProductDao assetManageDao;
	
	private @Autowired SolrImpl solrimpl;
	
	private @Autowired GoodsDao goodsDao;
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
	public boolean addAssetManageIndex(String solrUrl,String sql,int currentpage,int pagesize,String[] visibles) throws ServiceException, SolrServerException, IOException{
		List<Map<String, Object>> listMap = assetManageDao.queryAssetManages(sql, null, currentpage, pagesize);
		logger.info("产品查找数据长度:"+listMap.size());
		if(listMap!=null&&listMap.size()>0){
		    if(solrimpl.addOrUpdate(solrUrl,listMap, visibles)){
		    	String date = (new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(listMap.get(listMap.size()-1).get("modifytime")));
//				AnalyXML.updateNewTime(date, "t_product");
		    	SolrConfig.setPropValue("t_product", date);
				ProductAddTimer.t_product = date;
				logger.info("产品成功添加索引长度:"+listMap.size());
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
	public boolean deleteAssetManageIndex(String solrUrl,String sql,int currentpage,int pagesize) throws ServiceException, SolrServerException, IOException{
		List<Map<String,Object>> goodsList = goodsDao.findGoods(sql, null, currentpage, pagesize);
		logger.info("产品查询将要删除数据的长度:"+goodsList.size());
		if(goodsList!=null&&goodsList.size()>0){
			if(solrimpl.deleteBatchIndex(goodsList,solrUrl,"goods_id")){
				String date = (new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(goodsList.get(goodsList.size()-1).get("modifytime")));
//				AnalyXML.updateNewTime(date, "t_product_delete");
				SolrConfig.setPropValue("t_product_delete", date);
				ProductDeleteTimer.t_product_delete=date;
				logger.info("产品成功删除索引的长度:"+goodsList.size());
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public Map<String,Object> queryProductList(String params, String column,String sort,Integer current,Integer pagesize){
		List<Product> list=null;
		Map<String,Object> fmap = new HashMap<String,Object>();//返回给用户Map的对象
		solrimpl.init(url_product); //solr初始化
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
			list = qs.getBeans(Product.class);
			totalRecord =  qs.getResults().getNumFound();
		}catch(Exception e){
			logger.error("产品查询异常ProductService.queryProductList: ",e);
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
		fmap.put("list", list);
		fmap.put("objectT", objectT);
		return fmap;
	}
	public Map<String,Object> queryAssetManage(Map<String,String> params,Map<String,String> sort,Map<String,String> page) throws ServiceException, IllegalAccessException, InvocationTargetException {
		List<Product> flist = new ArrayList<Product>(); //返回给用户的对象列表
		Map<String,Object> fmap = new HashMap<String,Object>();//返回给用户Map的对象
		solrimpl.init(ProductAddTimer.t_product); //solr初始化
		String current = page.get("current");
		int current1 = StringUtils.isBlank(current)?1:Integer.parseInt(current);
		String pagesize = page.get("pagesize");
		int pagesize1 = StringUtils.isBlank(pagesize)?1:Integer.parseInt(pagesize);
		long totalRecord = 0;// 总记录数
		    QueryResponse qrs = solrimpl.solrSortQueryList(params, sort, current1, pagesize1);
			flist = qrs.getBeans(Product.class);
			if(flist!=null)
			{
				totalRecord = qrs.getResults().getNumFound();
			}
		long totalPage = totalRecord / pagesize1;
		 totalPage = totalRecord % pagesize1==0?totalPage:totalPage+1;
		Map<String, Object> pageObj = new HashMap<String, Object>(); // 拼凑page对象
		pageObj.put("currentPage", current1);
		pageObj.put("pageSize", pagesize1);
		pageObj.put("totalPage", totalPage);
		pageObj.put("totalRecord", totalRecord);

		fmap.put("page", pageObj);
		fmap.put("list", flist);
		fmap.put("objectT", null);
		fmap.put("code",Constant.SUCCESS_CODE);
		return fmap;
	}

	public List<AutoComplete>  testQuery(String prefix, int min,String[] params) throws SolrServerException{
		solrimpl.init(ProductAddTimer.t_product); //solr初始化
		return solrimpl.autoComplete(prefix, min,params);
	}
}
