package com.iec.solr.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iec.solr.module.SolrImpl;
import com.iec.solr.module.product.ProductAddTimer;
import com.iec.solr.module.productgold.ProductGoldAddTimer;
import com.iec.solr.module.shop.ShopAddTimer;
import com.iec.solr.module.shopgoods.ShopGoodsAddTimer;
import com.iec.solr.util.PropsConfig;

/**
 * 
 * 项目名称：SolrService 类名称：AdminController 类描述： 创建人：ex_kjkfb_xuek 创建时间：2013年3月14日
 * 上午10:59:53 修改人：ex_kjkfb_xuek 修改时间：2013年3月14日 上午10:59:53 修改备注：
 * 
 * @version
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger log = Logger.getLogger(AdminController.class);

	private static final String url_product = PropsConfig.getPropValue(
			"url_product", null);

	@Autowired
	private SolrImpl solrimpl;

	/**
	 * login(管理员用户登录，用户名密码自己在配置文件中配置)
	 * 
	 * @return String
	 * @Exception 异常对象
	 */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.equals(PropsConfig.getPropValue("username", null))
				&& password.equals(PropsConfig.getPropValue("password", null))) {
			request.getSession().setAttribute("username", username);
			return "admin/admin";
		} else
			return "redirect:/index.jsp";

	}

	/**
	 * reIndex(管理员删除索引,【目前只有一个core】)
	 * 
	 * @return String
	 * @Exception 异常对象
	 */
	@RequestMapping(value = "/reIndex")
	public String reIndex(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = null;
		try{
			username = request.getSession().getAttribute("username").toString();
		}catch(Exception e){
			return "redirect:/index.jsp";
		}
		String type = request.getParameter("type");
		if (PropsConfig.getPropValue("username", null).equals(username)) {
			if ("url_product".equals(type)) {
				solrimpl.init(ProductAddTimer.url_product);
				solrimpl.deleteAll("goods_id:*");
				ProductAddTimer.t_product = "10001010 00:00:00";
			} else if ("url_product_gold".equals(type)) {
				solrimpl.init(ProductGoldAddTimer.url_product_gold);
				solrimpl.deleteAll("goods_id:*");
				ProductGoldAddTimer.t_product_gold = "10001010 00:00:00";
			} else if ("url_shop".equals(type)) {
				solrimpl.init(ShopAddTimer.url_shop);
				solrimpl.deleteAll("shop_id:*");
				ShopAddTimer.t_shop = "10001010 00:00:00";
			} else if ("url_shop_goods".equals(type)) {
				solrimpl.init(ShopGoodsAddTimer.url_shop_goods);
				solrimpl.deleteAll("goods_id:*");
				ShopGoodsAddTimer.t_shop_goods = "10001010 00:00:00";
			}else if ("all".equals(type)) {
				solrimpl.init(ProductAddTimer.url_product);
				solrimpl.deleteAll("goods_id:*");
				ProductAddTimer.t_product = "10001010 00:00:00";
				solrimpl.init(ProductGoldAddTimer.url_product_gold);
				solrimpl.deleteAll("goods_id:*");
				ProductGoldAddTimer.t_product_gold = "10001010 00:00:00";
				solrimpl.init(ShopAddTimer.url_shop);
				solrimpl.deleteAll("shop_id:*");
				ShopAddTimer.t_shop = "10001010 00:00:00";
				solrimpl.init(ShopGoodsAddTimer.url_shop_goods);
				solrimpl.deleteAll("goods_id:*");
				ShopGoodsAddTimer.t_shop_goods = "10001010 00:00:00";
			}
			model.addAttribute("username",
					request.getSession().getAttribute("username"));
			return "admin/admin";
		} else {
			return "redirect:/index.jsp";
		}
	}

	/*
	 * @RequestMapping(value = "/reAppIndex") public void reAppIndex(ModelMap
	 * model, HttpServletRequest request, HttpServletResponse response) throws
	 * IOException { response.setCharacterEncoding("UTF-8"); PrintWriter out =
	 * response.getWriter(); String type = request.getParameter("type");
	 * Map<String, Object> map = new HashMap<String, Object>(); try { if
	 * ("t_bill".equals(type)) { solrimpl.init(url_bill);
	 * solrimpl.deleteAll("goods_id:*"); BillAddTimer.t_bill =
	 * "10001010 00:00:00"; } else if ("t_finance".equals(type)) {
	 * solrimpl.init(url_finance); solrimpl.deleteAll("goods_id:*");
	 * FinanceAddTimer.t_finance = "10001010 00:00:00"; } else if
	 * ("all".equals(type)) { solrimpl.init(url_bill);
	 * solrimpl.deleteAll("goods_id:*"); solrimpl.init(url_finance);
	 * solrimpl.deleteAll("goods_id:*"); BillAddTimer.t_bill =
	 * "10001010 00:00:00"; FinanceAddTimer.t_finance = "10001010 00:00:00"; }
	 * map.put("desc", "recreate index is succsessed."); } catch (Exception e) {
	 * map.put("desc", "recreate index is fault !"); } out.write(new
	 * Gson().toJson(map)); }
	 */
}
