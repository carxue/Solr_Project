package com.iec.solr.module.product;

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
@RequestMapping("/product")
public class ProductController {

	private static Logger logger = Logger.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/queryProducts")
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
	
	@RequestMapping(value = "/testquery")
	public void testQuerysolrAssetManage(HttpServletRequest request,HttpServletResponse response){
		String params=request.getParameter("params");
		String  column=request.getParameter("orderfield");
		String  sort=request.getParameter("ordertype");
		Integer current=DataUtil.toIngeger(request.getParameter("current"));
		Integer pagesize=DataUtil.toIngeger(request.getParameter("pagesize"));
		Map<String,Object> bmap = null;
		PrintWriter out = null;
		List<AutoComplete> autoCompleteList = null;
		try {
			response.setContentType("text/json;charset=utf-8");  
			out =  response.getWriter();
			 autoCompleteList = productService.testQuery("南", 10,new String[]{"title","product_name"});
			out.print(GsonUtils.toJson(autoCompleteList));
		} catch (Exception e) {
			logger.error("资管计划查询异常：",e);
			bmap=new HashMap<String, Object>();
			bmap.put("code",Constant.ERROR_CODE);
			out.print(GsonUtils.toJson(autoCompleteList));
		}
	}
	/**
	 * spring rest风格
	 * @param appcode
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryProduct")
	public ResponseEntity<String> addApply(String appcode,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		InputStream inputStream = request.getInputStream();
		String requeString = IOUtils.toString(inputStream, "utf-8");
		logger.info("requeststring:" + requeString);
		Map<String,Object> bmap = null;
		Gson gson = new Gson();
		Map<String,Map<String,String>> jsonMap = null;
		try {
			jsonMap = gson.fromJson(requeString, new TypeToken<Map<String,Map<String,String>>>(){}.getType());
		} catch (Exception e) {
			logger.error("产品查询异常 参数有误:"+requeString);
			bmap=new HashMap<String, Object>();
			bmap.put("code", Constant.ERROR_CODE);
			bmap.put("desc", "参数错误!");
			return SpringRestUtils.getReturnData(bmap);
			
		}
		Map<String,String> params = jsonMap.get("parmas");//参数集合
		Map<String,String>  sort =jsonMap.get("sort");//排序集合
		Map<String,String> page = jsonMap.get("page");//分页
		try {
			bmap = productService.queryAssetManage(params,sort,page);
		} catch (Exception e) {
			logger.error("产品查询异常：",e);
			bmap=new HashMap<String, Object>();
			bmap.put("code", Constant.ERROR_CODE);
		}
		gson = null;
		return SpringRestUtils.getReturnData(bmap);
	}
}
