package com.iec.solr.util;

import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.iec.app.utils.GsonUtils;

public class SpringRestUtils {
	private static final Log log = LogFactory.getLog(SpringRestUtils.class);
	/**
	 * 生成rest的返回对象
	 * @param domain
	 * @return
	 */
	public static ResponseEntity<String> getReturnData(Object domain) {
		HttpHeaders headers = new HttpHeaders();
        MediaType mt = new MediaType("application", "json", Charset.forName("utf-8"));
        headers.setContentType(mt);
        String  result = GsonUtils.toJson(domain);
        log.info("返回值:"+result);
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
	}
	
}
