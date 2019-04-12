package com.iec.solr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SolrConfig {
	
	private static final Logger log = Logger.getLogger(Config.class);
	
	public static String getPropValue(String key) {
		return getProperties().getProperty(key);
	}

	public static String fileNamePath = null;
	static {
		fileNamePath = System.getProperty("solr.solr.home") + File.separator
				+ "solr_settings.properties";
	}

	public static void setPropValue(String key, String value) {
		Properties props = getProperties();
		FileOutputStream fos = null;
		try {
			props.put(key, value);

			fos = new FileOutputStream(fileNamePath);
			props.store(fos, "store query solr core");

		} catch (IOException e) {
			log.error("读取solr时间配置文件异常：",e);
		}finally{
			if(null!=fos)
				try {
					fos.close();
				} catch (IOException e) {
					log.error(e);
				}
		}

	}

	public static Properties getProperties(){
		Properties props = new Properties();
		File cfgFile = null;
		InputStream is  = null;
		try {
			cfgFile = new File(fileNamePath);
			is = new FileInputStream(cfgFile);
			props.load(is);

		} catch (FileNotFoundException e) {
			log.error("BaseSolr FileNotFoundException", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("BaseSolr IOException", e);
		}finally{
			if(null!=is)
				try {
					is.close();
				} catch (IOException e) {
					log.error(e);
				}
		}
		return props;
	}

}
