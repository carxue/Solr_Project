package com.iec.solr.inter;

import java.util.List;

import org.apache.solr.common.SolrDocumentList;

/**    
 *     
 * 项目名称：SolrWeb    
 * 类名称：ISolr    
 * 类描述：    solr服务接口
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月4日 下午4:37:41    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月4日 下午4:37:41    
 * 修改备注：    
 * @version     
 *     
 */
public interface ISolr {
   
	/**    
	 * updateT(更新索引)    
	 * @return void   
	 * @Exception 异常对象   
	*/
	public <T> void updateT(List<T> list,String[] visibles);
	
	/**    
	 * deleteBatch(批量删除索引)    
	 * @return void   
	 * @Exception 异常对象   
	*/
	public void deleteBatch(List<String> list) throws Exception;
	
	/**    
	 * deleteAll(删除所有符合条件的索引)    
	 * @return void   
	 * @Exception 异常对象   
	*/
	public void deleteAll(String condition);
	
	/**    
	 * addT(批量添加索引)    
	 * @return void   
	 * @Exception 异常对象   
	*/
	public <T> void addT(List<T> list);
	
	/**    
	 * queryPage(查询索引并分页)  
	 * @return List<T>   
	 * @Exception 异常对象   
	*/
	public SolrDocumentList queryPage(List<String> clist, int current, int pagesize)throws Exception ;
}
