package com.iec.solr.module.goods;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iec.app.exception.ServiceException;

/**    
 *     
 * 项目名称：SolrService    
 * 类名称：GoodsService    
 * 类描述：    商票服务类
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月14日 下午3:49:36    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月14日 下午3:49:36    
 * 修改备注：    
 * @version     
 *     
 */
@Repository
public class GoodsService {
	
	private static final Logger log = Logger.getLogger(GoodsDao.class);
	
	@Autowired
	private GoodsDao goodsDao;
	
	/**    
	 * findGoods(查询商票信息列表)    
	 * @return List<Map<String,Object>>   
	 * @throws ServiceException 
	 * @Exception 异常对象   
	*/
	public List<Map<String,Object>> findGoods(String sql, Object[] params, int currentpage, int pagesize) throws ServiceException{
		return goodsDao.findGoods(sql,params, currentpage,pagesize);
	}

}
