package com.member.controller.front;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.front.OrderListBean;
import com.member.entity.Product;
import com.member.form.front.FrontOrderListForm;
import com.member.helper.BaseResult;
import com.member.services.front.FrontOrderListService;

@Controller
@RequestMapping(value = "/frontorderlist")
public class FrontOrderListController {

	@Resource(name = "FrontOrderListServiceImpl")
	private FrontOrderListService frontOrderListService;
	
	@RequestMapping(value = "/viewMyOrder",method = RequestMethod.POST)
	public String viewMyOrder(HttpServletRequest request, Model model){
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("username");
		
		FrontOrderListForm form = new FrontOrderListForm();
		form.setMemberNumber((String)userName);
		
		List<OrderListBean> result = frontOrderListService.getOrderListBeanByMemberNumber(form);
		model.addAttribute("result", result);
		
		return "front/orderlist/showorderlist";
	}
	

	@RequestMapping(value = "/viewProduDetail",method = RequestMethod.POST)
	public String getProduDetail(@RequestBody FrontOrderListForm form,Model model){
		Product result = frontOrderListService.getProductDetailById(form.getId());
		model.addAttribute("result", result);
		return "front/orderlist/showproductdetail";
	}
	
	@RequestMapping(value = "/getProduct",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> getProduct(@RequestBody FrontOrderListForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		frontOrderListService.updateOrderList(form);
		result.setMsg("提货成功.");
		result.setSuccess(true);
		return result;
		
	}
}
