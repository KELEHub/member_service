package com.member.controller.front;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.dao.ProductDao;
import com.member.entity.Information;
import com.member.entity.OrderDetails;
import com.member.entity.OrderNumber;
import com.member.entity.Product;
import com.member.entity.ShopingCart;
import com.member.entity.StockDistribute;
import com.member.form.front.ShopCartForm;
import com.member.form.front.ShopingForm;
import com.member.helper.BaseResult;
import com.member.services.back.InformationService;
import com.member.services.back.ProductService;


@Controller
@RequestMapping(value = "/shoping")
public class ShopingController {
	
	@Resource(name = "ProductServiceImpl")
	private ProductService productService;
	
	@Resource(name="ProductDaoImpl")
	private ProductDao productDao;
	
	@Resource(name="InformationServiceImpl")
	private InformationService informationService;
	
	@RequestMapping(value = "/show",method = RequestMethod.POST)
	public String show(HttpServletRequest request,Model model){
		List<ShopingForm> result = new ArrayList<ShopingForm>();
		List<Product> list = productService.getAllProductList();
		ShopingForm form = new ShopingForm();
		int count=0;
		if(list!=null && list.size()>0){
			for(Product pt : list){
				count++;
				if(count==1){
					form.setId1(pt.getId());
					form.setFlg1(1);
					form.setProductTarget1(pt.getProductTarget());
					form.setProductName1(pt.getProductNumber());
					form.setProductPrice1(pt.getProductPrice().toString());
				}
				if(count==2){
					form.setId2(pt.getId());
					form.setFlg2(1);
					form.setProductTarget2(pt.getProductTarget());
					form.setProductName2(pt.getProductNumber());
					form.setProductPrice2(pt.getProductPrice().toString());
				}
				if(count==3){
					count=0;
					form.setId3(pt.getId());
					form.setFlg3(1);
					form.setProductTarget3(pt.getProductTarget());
					form.setProductName3(pt.getProductNumber());
					form.setProductPrice3(pt.getProductPrice().toString());
					result.add(form);
					form=new ShopingForm();
				}
			}
			if(form.getFlg1()==1){
				result.add(form);
			}
		}
		model.addAttribute("result",result);
		return "front/buy/shopingPage";
	}
	
	
	@RequestMapping(value = "/showDetails",method = RequestMethod.POST)
	public String showDetails(HttpServletRequest request,Model model,@RequestBody ShopingForm form ){
		Product pt = productService.getProductDetailById(Integer.valueOf(form.getId()));
		String hql = "from Information where isFrim = 1 ";
		List<Information> result = (List<Information>)productDao.queryByHql(hql);
		model.addAttribute("result",result);
		model.addAttribute("pt",pt);
		return "front/buy/shopingDetails";
	}
	
	
	@RequestMapping(value = "/serverChange",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> serverChange(@RequestBody ShopingForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		if(!"space".equals(form.getNumber())){
			Information info = informationService.getInformationById(Integer.valueOf(form.getNumber()));
			String hql = "from StockDistribute where serverId=? and productNumber=? ";
			List arguments = new ArrayList();
			arguments.add(Integer.valueOf(form.getNumber()));
			arguments.add(form.getProductNumber());
			StockDistribute st= new StockDistribute();
			List<StockDistribute> stList = (List<StockDistribute>)productDao.queryByHql(hql,arguments);
			if(stList!=null && stList.size()>0){
				st = stList.get(0);
			}
			result.setAddress(info.getLinkProvince()+info.getLinkCounty()+info.getLinkCity()+info.getLinkAddress());
			result.setPhone(info.getPhoneNumber());
			result.setFirm(st.getServerFirm().toString());
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/addshoping",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> addshoping(@RequestBody ShopingForm form,Model model, HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		String hql = "from ShopingCart where serverId=? and productNumber=? and number=?";
		List arguments = new ArrayList();
		arguments.add(Integer.valueOf(form.getNumber()));
		arguments.add(form.getProductNumber());
		arguments.add(userNaemO);
		List stList = productDao.queryByHql(hql,arguments);
		if("".equals(form.getNumber()) || form.getNumber() == null){
			result.setMsg("请选择服务站");
			 result.setSuccess(false);
			 return result;
		}
		if(stList != null && stList.size()>0){
			 result.setMsg("购物车已经存在此商品");
			 result.setSuccess(false);
			 return result;
		}
		String hqll = "from ShopingCart where number=?";
		List argumentss = new ArrayList();
		argumentss.add(userNaemO);
		List stLists = productDao.queryByHql(hqll,argumentss);
		if(stLists!=null && stLists.size()>=10){
			 result.setMsg("超过购物车上限（商品数不能超过10）");
			 result.setSuccess(false);
			 return result;
		}
		Information info = informationService.getInformationByNumber(userNaemO);
		Product pt = productService.getProductInfoByProductNumber(form.getProductNumber());
		Information serverInfo = informationService.getInformationById(Integer.valueOf(form.getNumber()));
		ShopingCart shopingCart = new ShopingCart();
		shopingCart.setUserId(info.getId());
		shopingCart.setNumber(info.getNumber());
		shopingCart.setProductId(pt.getId());
		shopingCart.setProductName(pt.getProductName());
		shopingCart.setProductNumber(pt.getProductNumber());
		shopingCart.setServerId(serverInfo.getId());
		shopingCart.setServerNumber(serverInfo.getNumber());
		shopingCart.setShopPrice(pt.getProductPrice());
		shopingCart.setProductTarget(pt.getProductTarget());
		productDao.saveOrUpdate(shopingCart);
		result.setMsg("添加购物车成功");
		result.setSuccess(true);
		return result;
	}
	
	
	@RequestMapping(value = "/showShopingCart",method = RequestMethod.POST)
	public String showShopingCart(HttpServletRequest request,Model model, HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		String hql = "from ShopingCart where number=?";
		List arguments = new ArrayList();
		arguments.add(userNaemO);
		List stList = productDao.queryByHql(hql,arguments);
		if(stList!=null && stList.size()>0){
			List<ShopingCart> list = (List<ShopingCart>)stList;
			List<ShopCartForm> formList = new ArrayList<ShopCartForm>();
			BigDecimal count=new BigDecimal(0);
			for(ShopingCart sc : list){
				ShopCartForm scf = new ShopCartForm();
				scf.setNumber(sc.getNumber());
				scf.setId(sc.getId().toString());
				scf.setProductName(sc.getProductName());
				scf.setServerNumber(sc.getServerNumber());
				Product pro = productService.getProductInfoByProductNumber(sc.getProductNumber());
				scf.setShopPrice(pro.getProductPrice().toString());
				scf.setProductNumber(sc.getProductNumber());
				scf.setProductTarget(sc.getProductTarget());
				scf.setShopCount("1");
				scf.setPriceall(pro.getProductPrice().toString());
				Information serverInfo = informationService.getInformationByNumber(sc.getServerNumber());
				scf.setAddress(serverInfo.getLinkProvince()+serverInfo.getLinkCounty()+serverInfo.getLinkCity()+serverInfo.getLinkAddress());
				scf.setPhone(serverInfo.getPhoneNumber());
				count = count.add(pro.getProductPrice());
				formList.add(scf);
			}
			model.addAttribute("result", formList);
			model.addAttribute("count", count);
		}
		return "front/buy/shopcart";
	}

	
	
	
	@RequestMapping(value = "/shopOrder",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> shopOrder(@RequestBody ShopingForm form,Model model, HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		String msg="";
		try {
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			String userNaemO =(String) logonUserMap.get("username");
			Information info = informationService.getInformationByNumber(userNaemO);
			BigDecimal allPrice = new BigDecimal(form.getAllPrice());
			if(info.getCoupon().compareTo(allPrice)==-1){
				result.setSuccess(false);
				result.setMsg("您的消费卷（"+info.getCoupon()+"）余额不足");
				return result;
			}
			if(info.getPhoneNumber().length()!=11){
				result.setSuccess(false);
				result.setMsg("请确认您的电话号码位数正确性");
				return result;
			}
			
			String idStr = form.getIdArr();
			String countStr = form.getCountArr();
			if(idStr!=""){
				String[] idArr = idStr.split(",");
				String[] countArr = countStr.split(",");
				 msg = productService.orderCreate(idArr, countArr, info);
				if(!"true".equals(msg)){
					result.setSuccess(false);
					result.setMsg(msg);
					return result;
				}
			}
			result.setMsg("下单成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			result.setMsg("下单失败");
			result.setSuccess(false);
			return result;
		}
		
	}
	
	
	@RequestMapping(value = "/showNodealOrder",method = RequestMethod.POST)
	public String showNodealOrder(HttpServletRequest request,Model model, HttpSession sesison){
		
		return "front/buy/shownodealorder";
	}
	
	
	@RequestMapping(value = "/showOrderDetails")
	@ResponseBody
	public Map showOrderDetails(HttpServletRequest request,Model model,HttpSession sesison) {
		String tnumber = request.getParameter("tnumber");
		String iDisplayLength = request.getParameter("iDisplayLength");	
		String iDisplayStart = request.getParameter("iDisplayStart");
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		int iTotalRecords = productService.countOrderNumberListByNumber(userNaemO, tnumber, 3);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<OrderNumber> result = productService.getOrderNumberListByNumber(userNaemO,tnumber,3,Integer.parseInt(iDisplayLength),
				pageNumber);
		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
	
	@RequestMapping(value = "/shopDetailsOrder",method = RequestMethod.POST)
	public String shopDetailsOrder(@RequestBody ShopingForm form,Model model, HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		OrderNumber order = productService.getOrderNumberById(Integer.valueOf(form.getId()));
		List<OrderDetails> result = productService.getOrderDetailsListByNumber(userNaemO,order.getTnumber(),3);
		List<ShopCartForm> list = new ArrayList<ShopCartForm>();
		for(OrderDetails sc : result){
			ShopCartForm scf = new ShopCartForm();
			scf.setNumber(sc.getNumber());
			scf.setId(sc.getId().toString());
			scf.setProductName(sc.getProductName());
			scf.setServerNumber(sc.getServerNumber());
			scf.setShopPrice(sc.getShopPrice().toString());
			scf.setProductNumber(sc.getProductNumber());
			scf.setProductTarget(sc.getProductTarget());
			scf.setShopCount(sc.getShopCount().toString());
			scf.setPriceall(sc.getAllPrice().toString());
			Information serverInfo = informationService.getInformationByNumber(sc.getServerNumber());
			scf.setAddress(serverInfo.getLinkProvince()+serverInfo.getLinkCounty()+serverInfo.getLinkCity()+serverInfo.getLinkAddress());
			scf.setPhone(serverInfo.getPhoneNumber());
			scf.setStauts("未取货");
			list.add(scf);
		}
		model.addAttribute("list", list);
		return "front/buy/showOrderDetails";
	}
	
	
	
	@RequestMapping(value = "/showdealOrder",method = RequestMethod.POST)
	public String showdealOrder(HttpServletRequest request,Model model, HttpSession sesison){
		
		return "front/buy/showdealorder";
	}
	
	
	@RequestMapping(value = "/showDealOrderDetails")
	@ResponseBody
	public Map showDealOrderDetails(HttpServletRequest request,Model model,HttpSession sesison) {
		String tnumber = request.getParameter("tnumber");
		String iDisplayLength = request.getParameter("iDisplayLength");	
		String iDisplayStart = request.getParameter("iDisplayStart");
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		int iTotalRecords = productService.countOrderNumberListByNumber(userNaemO, tnumber, 2);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<OrderNumber> result = productService.getOrderNumberListByNumber(userNaemO,tnumber,2,Integer.parseInt(iDisplayLength),
				pageNumber);
		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
	@RequestMapping(value = "/shopDealDetailsOrder",method = RequestMethod.POST)
	public String shopDealDetailsOrder(@RequestBody ShopingForm form,Model model, HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		OrderNumber order = productService.getOrderNumberById(Integer.valueOf(form.getId()));
		List<OrderDetails> result = productService.getOrderDetailsListByNumber(userNaemO,order.getTnumber(),2);
		List<ShopCartForm> list = new ArrayList<ShopCartForm>();
		for(OrderDetails sc : result){
			ShopCartForm scf = new ShopCartForm();
			scf.setNumber(sc.getNumber());
			scf.setId(sc.getId().toString());
			scf.setProductName(sc.getProductName());
			scf.setServerNumber(sc.getServerNumber());
			scf.setShopPrice(sc.getShopPrice().toString());
			scf.setProductNumber(sc.getProductNumber());
			scf.setProductTarget(sc.getProductTarget());
			scf.setShopCount(sc.getShopCount().toString());
			scf.setPriceall(sc.getAllPrice().toString());
			Information serverInfo = informationService.getInformationByNumber(sc.getServerNumber());
			scf.setAddress(serverInfo.getLinkProvince()+serverInfo.getLinkCounty()+serverInfo.getLinkCity()+serverInfo.getLinkAddress());
			scf.setPhone(serverInfo.getPhoneNumber());
			scf.setStauts("已完成取货");
			list.add(scf);
		}
		model.addAttribute("list", list);
		return "front/buy/showOrderDetails";
	}
	
	
	@RequestMapping(value = "/deleteShopCart",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteShopCart(@RequestBody ShopCartForm form,Model model, HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		String hql = " from ShopingCart where id =?";
		List<ShopingCart> shop = (List<ShopingCart>)productDao.queryByHql(hql,Integer.valueOf(form.getId()));
		if(shop!=null && shop.size()>0){
			productDao.delete(shop.get(0));
			result.setSuccess(true);
			result.setMsg("删除商品成功！");
			return result;
		}
		return result;
	}

}
