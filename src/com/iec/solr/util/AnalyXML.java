package com.iec.solr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**    
 *     
 * 项目名称：SolrService    
 * 类名称：AnalyXML    
 * 类描述： 读写xml文件
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2013年3月14日 下午3:50:54    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2013年3月14日 下午3:50:54    
 * 修改备注：    
 * @version     
 *     
 */
public class AnalyXML {
	
	private static  final Logger log = Logger.getLogger(AnalyXML.class);
	
	private static final String XMLPATH = PropsConfig.getPropValue("timexml", "");


	/**
	 * updateNewTime(更新最新表的时间，用于下次更新索引时使用)
	 * @return void
	 * @Exception 异常对象
	 */
	public static synchronized void updateNewTime(String date, String tableName) {
        //读取日期文件的路径
//		String xmlPath = PropsConfig.getPropValue("timexml", "");
		
		XMLWriter writer = null;
		InputStream is = null;
		try {
			SAXReader saxReader = new SAXReader();
//			Document document = saxReader.read(new File(XMLPATH));//linux环境下报to many files open
			is = new FileInputStream(XMLPATH);
			Document document = saxReader.read(is);
			
			Element root = document.getRootElement();
			Element tabletime = (Element) root.selectSingleNode("/tables/table[@name='" + tableName+ "']");
			tabletime.setText(date);

			org.dom4j.io.OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");// 这就是已经设置了输出格式或编码集
			format.setSuppressDeclaration(true);
			format.setIndent(true);// 是否缩进
			format.setIndent("     ");// 以空格方式缩进
			format.setNewlines(true);// 设置是否换行
			// 实例化MLWriter对象
			writer = new XMLWriter(new FileOutputStream(XMLPATH), format);
			writer.write(document);
			writer.flush();
		} catch (Exception e) {
			log.error("AnalyXML updateNewTime: ", e);
		} finally {
			try {
				if(null!=writer)
					writer.close();
				if(null != is)
					is.close();
			} catch (IOException e) {
				log.error("AnalyXML updateNewTime: ", e);
			}
		}

	}

	/**
	 * queryTime(得到上次更新的时间)
	 * @return String
	 * @Exception 异常对象
	 */
	public static synchronized String queryTime(String tableName) {
		String time = null;
		//读取日期文件的路径
//		String xmlPath = PropsConfig.getPropValue("timexml", null);
        InputStream is = null;
		try {
			SAXReader saxReader = new SAXReader();
//			Document document = saxReader.read(new File(XMLPATH));//linux环境下报to many files open
			is = new FileInputStream(XMLPATH);
			Document document = saxReader.read(is);
			
			Element root = document.getRootElement();
			Element tabletime = (Element) root.selectSingleNode("/tables/table[@name='" + tableName+ "']");
			time = tabletime.getText();
		} catch (Exception e) {
			log.error("AnalyXML queryTime: ", e);
		}finally{
				try {
					if(null != is)
						is.close();
				} catch (IOException e) {
					log.error("AnalyXML queryTime.close: ", e);
				}
		}
		return time;
	}
	
	
	public static void main(String[] args){
		String xmlPath = AnalyXML.class.getClassLoader().getResource("").toString();
		System.out.println(xmlPath);
	}
}
