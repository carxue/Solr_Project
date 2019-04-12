package com.iec.solr.module;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;

import com.iec.app.exception.ServiceException;
import com.iec.solr.bean.AutoComplete;
import com.iec.solr.inter.ISolr;
import com.iec.solr.util.CommonsHttpSolrServer;

/**
 * 
 * 项目名称：SolrService 类名称：SolrImpl 类描述： solr实现类 创建人：ex_kjkfb_xuek 创建时间：2013年3月14日
 * 下午3:40:05 修改人：ex_kjkfb_xuek 修改时间：2013年3月14日 下午3:40:05 修改备注：
 * 
 * @version
 * 
 */
@Repository
public class SolrImpl implements ISolr {

	private static final Logger log = Logger.getLogger(SolrImpl.class);

	// private HttpSolrServer server;//部署到was上会失败，tomcat上可以
	private CommonsHttpSolrServer server;

	/**
	 * init(初始化solr相关信息)
	 * 
	 * @param url查询服务的路径
	 * @return void
	 * @Exception 异常对象
	 */
	public void init(String url) {
		try {
			// server = new HttpSolrServer(url);
			server = new CommonsHttpSolrServer(url);
			server.setSoTimeout(5000);
			server.setConnectionTimeout(10000);// 连接超时时间
			server.setDefaultMaxConnectionsPerHost(100);
			server.setMaxTotalConnections(1000);
			server.setFollowRedirects(false);
			server.setAllowCompression(true);// 允许压缩
			server.setMaxRetries(1);// 是否恢复
			server.setParser(new XMLResponseParser());// binary parser is userd
														// by
		} catch (Exception e) {
			log.error("SolrImpl init:", e);
		}
	}

	/**
	 * (批量添加或更新)
	 * 
	 * @see com.iec.web.inter.ISolr#updateT(java.util.List, java.util.List)
	 */
	@Override
	public <T> void updateT(List<T> list, String[] visibles) {
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		for (int i = 0; i < list.size(); i++) {
			Class<?> clazz = list.get(i).getClass();
			SolrInputDocument doc = new SolrInputDocument();
			for (int j = 0; j < visibles.length; j++) {// 创建列数
				String column = visibles[j];
				Field field = null;
				try {
					try {
						field = clazz.getDeclaredField(column);
					} catch (Exception e) {
						field = clazz.getSuperclass().getDeclaredField(column);
					}
					field.setAccessible(true);
					doc.addField(column, field.get(list.get(i)));
				} catch (Exception e) {
					log.error("SolrImpl updateT:", e);
				}

			}
			
			log.info("需要添加的数据updateT:"+doc.toString());
			docs.add(doc);
		}
		try {
			server.add(docs);// 批量添加
			server.commit();// 提交
			log.info("【SolrImpl.updateT 添加成功的新的数据列表，长度为{ "+docs.size()+" },数据为：】"+docs.toString());
		} catch (Exception e) {
			log.error("SolrImpl updateT:", e);
		}
	}

	public boolean addOrUpdate(String solrUrl,
			List<Map<String, Object>> objMaplist, String[] visibles)
			throws SolrServerException, IOException {
		this.init(solrUrl);
		int size = objMaplist.size();
		List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>(size);
		Map<String, Object> objMap = null;
		SolrInputDocument sidoc = null;
		for (int i = 0; i < size; i++) {
			objMap = objMaplist.get(i);
			sidoc = new SolrInputDocument();
			for (int j = 0; j < visibles.length; j++) {
				sidoc.addField(visibles[j], objMap.get(visibles[j]));
			}
			
			log.info("需要添加的数据addOrUpdate:"+sidoc.toString());
			
			docs.add(sidoc);
		}
		// 先测试状态
		UpdateResponse updateResponse1 = server.add(docs);
		log.info("索引添加返回状态==============>" + updateResponse1.getStatus());
		server.optimize();// 对索引进行优化
		UpdateResponse updateResponse2 = server.commit();
		log.info("索引添加提交返回状态==============>" + updateResponse2.getStatus());
		log.info("【SolrImpl.addOrUpdate 添加成功的新的数据列表，长度为{ "+docs.size()+" },数据为：】"+docs.toString());
		
		return updateResponse1.getStatus() == 0
				&& updateResponse2.getStatus() == 0;
	}

	/**
	 * (根据产品id批量删除)
	 * 
	 * @see com.iec.web.inter.ISolr#deleteBatch(java.util.List)
	 */
	@Override
	public void deleteBatch(List<String> list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			server.deleteById(list.get(i));
			log.info("【SolrImpl.deleteBatch 成功删除下架的商品id：】" + list.get(i));
		}
		server.commit();
	}

	public boolean deleteBatchIndex(List<Map<String, Object>> list,
			String solrUrl,String id) throws SolrServerException, IOException {
		this.init(solrUrl);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			server.deleteById(list.get(i).get(id).toString());
			log.info("【SolrImpl.deleteBatchIndex 成功删除下架的商品id：】" + list.get(i));
		}
		return server.commit().getStatus() == 0;
	}

	/**
	 * (根据条件删除索引)
	 * 
	 * @see com.iec.web.inter.ISolr#deleteAll(java.lang.String)
	 */
	@Override
	public void deleteAll(String condition) {
		try {
			server.deleteByQuery(condition);
			server.optimize();
			server.commit();
		} catch (Exception e) {
			log.error("SolrImpl deleteAll:", e);
		}
	}

	/**
	 * (分页查找)
	 * 
	 * @see com.iec.web.inter.ISolr#queryPage(java.lang.String, int, int)
	 */
	@Override
	public SolrDocumentList queryPage(List<String> clist, int current,
			int pagesize) throws Exception {
		/**
		 * 其他相关方法 过滤:query.addFilterQuery("text_mmseg:是否 ")对查询的结果进行再过滤
		 * 排除:query.setQuery("title:薛奎 -士大夫");包含薛奎，但是不包含士大夫的中间有一个空格然后是一个“-”减号
		 * 权重问题:query.setQuery("title:士大夫^4  OR text_mmseg:士大夫");
		 * 权重问题:query.setQuery("title:士大夫^4 薛奎");
		 * */
		SolrQuery query = new SolrQuery();
		query.setQuery(clist.get(0));
		for (int i = 1; i < clist.size(); i++)
			query.addFilterQuery(clist.get(i));
		query.setStart((current - 1) * pagesize);
		query.setRows(pagesize);
		SolrDocumentList docs = new SolrDocumentList();
		try {
			QueryResponse rsp = server.query(query);
			docs = rsp.getResults();
		} catch (SolrServerException e1) {
			log.error("SolrImpl queryPage:", e1);
		}
		return docs;
	}

	/**
	 * 所有查询的方法
	 * 
	 * @param carray
	 * @param column
	 * @param sort
	 * @param current
	 * @param pagesize
	 * @return
	 */
	public SolrDocumentList solrQuery(String[] carray, String column,
			String sort, int current, int pagesize) {
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		if (carray != null) {
			for (int i = 0; i < carray.length; i++)
				query.addFilterQuery(carray[i]);
		}
		query.setStart((current - 1) * pagesize);
		query.setRows(pagesize);
		if ("1".equals(sort))
			query.addSortField(column, ORDER.asc);
		else if ("2".equals(sort) || null != sort)
			query.addSortField(column, ORDER.desc);

		SolrDocumentList docs = new SolrDocumentList();
		try {
			QueryResponse rsp = server.query(query);
			docs = rsp.getResults();
		} catch (Exception e1) {
			log.error("SolrImpl queryPage:", e1);
		}
		return docs;
	}

	/**
	 * 多值排序
	 * 
	 * @param carray
	 * @param column
	 * @param sort
	 * @param current
	 * @param pagesize
	 * @return
	 * @throws ServiceException
	 */
	public SolrDocumentList solrSortQuery(String[] carray, String[] column,
			String sort, int current, int pagesize) throws ServiceException {
		SolrQuery query = new SolrQuery();
		SolrDocumentList docs = new SolrDocumentList();
		query.setQuery("*:*");
		if (carray != null) {
			for (int i = 0; i < carray.length; i++)
				query.addFilterQuery(carray[i]);
		}
		query.setStart((current - 1) * pagesize);
		query.setRows(pagesize);
		if (null != column) {
			for (int i = 0; i < column.length; i++) {// 多字段排序
				if ("1".equals(sort) && null != column[i])
					query.addSortField(column[i], ORDER.asc);
				else if ("2".equals(sort) && null != column[i])
					query.addSortField(column[i], ORDER.desc);
			}
		}
		try {
			QueryResponse rsp = server.query(query);
			docs = rsp.getResults();
		} catch (SolrServerException e1) {
			log.error("SolrImpl queryPage:", e1);
			throw new ServiceException("solr查询异常");
		}
		return docs;
	}

	/**
	 * 多值排序 直接返回对象
	 * 
	 * @param carray
	 * @param column
	 * @param sort
	 * @param current
	 * @param pagesize
	 * @return
	 * @throws ServiceException
	 */
	public QueryResponse solrSortQueryList(Map<String,String> parmasMap, Map<String,String> sort,
			 int current, int pagesize) throws ServiceException {
		SolrQuery query = new SolrQuery("*:*");
		
			if(parmasMap!=null)
			{
				Set<Entry<String, String>> set = parmasMap.entrySet();
				for (Entry<String, String> entry : set) {
					query.addFilterQuery(entry.getValue());
				}
			}
		query.setStart((current - 1) * pagesize);
		query.setRows(pagesize);
		//排序
		if(sort!=null)
		{
			Set<Entry<String, String>> sortSet = sort.entrySet();
			for (Entry<String, String> entry : sortSet) {
				 if("1".equals(entry.getValue())){
					 query.addSortField(entry.getKey(),ORDER.asc);
				 }else if("2".equals(entry.getValue()))
				 {
					 query.addSortField(entry.getKey(),ORDER.desc);
				 }
			}
		}
		try {
			QueryResponse rsp = server.query(query);
			return rsp;
		} catch (SolrServerException e1) {
			log.error("SolrImpl queryPage:", e1);
			throw new ServiceException("solr查询异常");
		}
	}

	// prefix为前缀，min为最大返回结果数

	public List<AutoComplete> autoComplete(String prefix, int min,String[] params) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		QueryResponse rsp = new QueryResponse();
		// Facet为solr中的层次分类查询
		query.setFacet(true);
		query.setQuery("*:*");
		query.setFacetPrefix(prefix);
		query.setFacetLimit(min);
//		query.addFacetField("title");
		query.addFacetField(params);
        query.setFacetMinCount(1);//统计数量
		rsp = server.query(query);
		List<AutoComplete> autoCompleteList = null;
		if (null != rsp) {
			List<Count> countList = null;
			List<FacetField> ffList = rsp.getFacetFields();
			autoCompleteList = new ArrayList<AutoComplete>();
			int size=0;
			Count count = null;
			AutoComplete autoComplete = null;
			if(ffList!=null)
			{
				for (FacetField facetField : ffList) {
					countList = facetField.getValues();
					autoComplete = new AutoComplete();
					if(countList!=null){
						size = countList.size();
						for(int i=0;i<size;i++)
						{
							count = countList.get(i);
							autoComplete.setCount(count.getCount());
							autoComplete.setValue(count.getName());
						}
					}
					autoCompleteList.add(autoComplete);
				}
			}
		}
		return autoCompleteList;
	}

	/**
	 * 排序查询
	 * @param carray
	 * @param column
	 * @param sort
	 * @param current
	 * @param pagesize
	 * @return
	 * @throws ServiceException
	 */
	public QueryResponse solrSortQueryNew(String[] carray, String[] column,
			String sort, int current, int pagesize) throws ServiceException {
		SolrQuery query = new SolrQuery();
		SolrDocumentList docs = new SolrDocumentList();
		query.setQuery("*:*");
		if (carray != null) {
			for (int i = 0; i < carray.length; i++)
				query.addFilterQuery(carray[i]);
		}
		query.setStart((current - 1) * pagesize);
		query.setRows(pagesize);
		if (null != column) {
			for (int i = 0; i < column.length; i++) {// 多字段排序
				if ("1".equals(sort) && null != column[i])
					query.addSortField(column[i], ORDER.asc);
				else if ("2".equals(sort) && null != column[i])
					query.addSortField(column[i], ORDER.desc);
			}
		}
		try {
			return server.query(query);
		} catch (SolrServerException e1) {
			log.error("SolrImpl queryPage:", e1);
			throw new ServiceException("solr查询异常");
		}
	}
	/**
	 * (使用updateT方法，该方法效率比较低，已废弃)
	 * 
	 * @see com.iec.web.inter.ISolr#addT(java.util.List, java.util.List)
	 */
	@Override
	public <T> void addT(List<T> list) {
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
			docs.add(binder.toSolrInputDocument(list.get(i)));
		}
		try {
			server.add(docs);
			server.commit();
		} catch (Exception e) {
			log.error("SolrImpl addT:", e);
		}
	}
}
