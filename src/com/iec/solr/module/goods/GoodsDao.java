package com.iec.solr.module.goods;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iec.app.base.BaseDao;
import com.iec.app.exception.ServiceException;

/**    
 *     
 * 项目名称：SolrService    
 * 类名称：GoodsDao    
 * 类描述：    商票类
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月14日 下午3:48:01    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月14日 下午3:48:01    
 * 修改备注：    
 * @version     
 *     
 */
@Repository
public class GoodsDao extends BaseDao  {
	
	private static final Logger log = Logger.getLogger(GoodsDao.class);
	
	/**    
	 * findBlack(分页查询查询票据信息,用于票据索引的添加和删除)    
	 * @return List<Map<String,Object>>   
	 * @throws ServiceException 
	 * @Exception 异常对象   
	*/
	public List<Map<String, Object>> findGoods(String sql, Object[] params, int currentpage, int pagesize) throws ServiceException {
		return basePageOrder(sql, params, currentpage, pagesize);
	}
	
	/**    
	 * findById(根据id查找票据信息)    
	 * @return List<Map<String,Object>>   
	 * @Exception 异常对象   
	*/
	public List<Map<String, Object>> findById(String sql){
		log.info(sql);
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	
}
