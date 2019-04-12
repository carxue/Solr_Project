package com.iec.solr.module.productgold;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iec.app.utils.GsonUtils;
import com.iec.solr.bean.AutoComplete;
import com.iec.solr.util.Constant;
import com.iec.solr.util.DataUtil;
import com.iec.solr.util.SpringRestUtils;

/**
 * @author ex_kjkfb_lvrz
 * @Date 2014-9-19 上午09:02:41
 * @description 
 * @Version V1.0
 */
@Controller
@RequestMapping("/productgold")
public class ProductGoldController {

	private static Logger logger = Logger.getLogger(ProductGoldController.class);
	
	private @Autowired ProductGoldService productService;
	
	@RequestMapping(value = "/queryProductGolds")
	public void queryProducts(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		String params=request.getParameter("params");
		String  column=request.getParameter("orderfield");
		String  sort=request.getParameter("ordertype");
		Integer current=DataUtil.toIngeger(request.getParameter("current"),1);
		Integer pagesize=DataUtil.toIngeger(request.getParameter("pagesize"),10);
		logger.info("solr商户搜索参数：： "+ params +","+ column +","+ sort);
		Map<String,Object> bmap=productService.queryProductList(params,column,sort,current,pagesize);
		logger.info("solr商户搜索结果：： "+ GsonUtils.toJson(bmap));
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(GsonUtils.toJson(bmap));
	}
	
}
