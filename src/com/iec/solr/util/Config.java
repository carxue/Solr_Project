package com.iec.solr.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**    
 *  [已弃用]   
 * 项目名称：SolrService    
 * 类名称：Config    
 * 类描述：    配置文件读取类
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月14日 下午3:55:17    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月14日 下午3:55:17    
 * 修改备注：    
 * @version     
 *     
 */
public class Config {
	
	private static final Logger log = Logger.getLogger(Config.class);

	/**    
	 * getValue(获取数据库配置的信息)    
	 * @return String   
	 * @Exception 异常对象   
	*/
	public static String getValue(String key) {
		Properties props = new Properties();
		InputStream is = null;
		try {
			is = Config.class.getResourceAsStream("/dbconfig.properties");
			props.load(is);
			return props.getProperty(key);
		}
		catch (IOException e) {
			log.error("Config getValue:", e);
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				log.error("Config getValue:", e);
			}
		}
		return null;
	}
	
	/**    
	 * getConfig(获取服务相关的配置文件)    
	 * @return String   
	 * @Exception 异常对象   
	*/
	public static String getConfig(String key) {
		Properties props = new Properties();
		InputStream is = null;
		try {
			is = Config.class.getResourceAsStream("/config.properties");
			props.load(is);
			return props.getProperty(key);
		}
		catch (IOException e) {
			log.error("Config getConfig:", e);
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				log.error("Config getConfig:", e);
				e.printStackTrace();
			}
		}
		return null;
	}
}
