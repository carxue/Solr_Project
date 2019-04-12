package com.iec.solr.module.productgold;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.iec.app.base.BaseDao;
import com.iec.app.exception.ServiceException;

/**
 * @author ex_kjkfb_lvrz
 * @Date 2014-9-17 上午09:49:08
 * @description
 * @Version V1.0
 */
@Repository
public class ProductGoldDao extends BaseDao{
	
	/**
	 * 分页查找资管计划 以便添加索引
	 * @param sql
	 * @param params
	 * @param currentpage
	 * @param pagesize
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> queryAssetManages(String sql, Object[] params, int currentpage, int pagesize) throws ServiceException{
	   return  super.basePageOrder(sql, params, currentpage, pagesize);
	}
	

}
