package com.iec.solr.module.shop;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iec.app.utils.GsonUtils;
import com.iec.solr.util.DataUtil;

/**    
 *     
 * 项目名称：SolrService    
 * 类名称：FinanceController    
 * 类描述：    理财控制器
 * 创建人：ex_kjkfb_xuek    
 * 创建时间：2014年3月14日 下午3:24:10    
 * 修改人：ex_kjkfb_xuek    
 * 修改时间：2014年3月14日 下午3:24:10    
 * 修改备注：    
 * @version     
 *     
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
	
	private static final Logger log = Logger.getLogger(ShopController.class);

	
	@Autowired
	private ShopService shopService;
   
	/**@param拼接字符串条件查询
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryShops")
	public void solrfinances(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String params=request.getParameter("params");
		String  column=request.getParameter("orderfield");
		String  sort=request.getParameter("ordertype");
		Integer current=DataUtil.toIngeger(request.getParameter("current"));
		Integer pagesize=DataUtil.toIngeger(request.getParameter("pagesize"));
		
		Map<String,Object> bmap=shopService.queryShops(params,column,sort,current,pagesize);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(GsonUtils.toJson(bmap));
	}
	

	/** 【已弃用、暂时保存】     
	 * queryFinances(查询理财方法)    
	 * @return ModelAndView   
	 * @throws IOException 
	 * @Exception 异常对象   
	*/
}
