package com.iec.solr.util;

import org.apache.log4j.Logger;

import com.iec.app.utils.Utils;

/**    
 *     
 * 项目名称：SolrService    
 * 类名称：DataUtil    
 * 类描述：    数据类型转换类
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月14日 下午3:58:27    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月14日 下午3:58:27    
 * 修改备注：    
 * @version     
 *     
 */
public class DataUtil {
	
	private static final Logger log = Logger.getLogger(DataUtil.class);
	
	public static Integer toIngeger(Object object,int num) {
		if(Utils.isEmpty(object)){
			return num;
		}else{
			return toIngeger(object);
		}
	}
	public static Integer toIngeger(Object object) {
		Integer value = null;
		try {
			value = Integer.parseInt(object.toString());
		} catch (Exception e) {
			log.error("DataUtil searchService 数据格式不正确");
		}
		return value;
	}

	public static Double toDouble(Object object) {
		Double value = null;
		try {
			value = Double.parseDouble(object.toString());
		} catch (Exception e) {
			log.error("DataUtil searchService 数据格式不正确");
		}
		return value;
	}

	public static Long toLong(Object object) {
		Long value = null;
		try {
			value = Long.parseLong(object.toString());
		} catch (Exception e) {
			log.error("DataUtil searchService 数据格式不正确");
		}
		return value;
	}
	
	public static String[] toArray(String condition) {
		String[] values= condition.split("-");
		return values;
	}
	
//	public static Date toDate(String date) {
//		int year=DataUtil.toIngeger(date.substring(0,4));log.info(year);
//		int month=DataUtil.toIngeger(date.substring(4, 6));log.info(month);
//		int day=DataUtil.toIngeger(date.substring(6, 8));log.info(day);
//		Date value = new Date(year,03,day);
//		return value;
//	}
}
