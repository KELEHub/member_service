package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.back.OrderListBean;
import com.member.entity.OrderList;
import com.member.form.OrderListOperForm;
import com.member.helper.BaseResult;
import com.member.services.back.OrderListService;

@Controller
@RequestMapping(value = "/orderlist")
public class OrderListController {

	@Resource(name = "OrderListServiceImpl")
	private OrderListService orderListService;
	
	@RequestMapping(value = "/getOrderList",method = RequestMethod.POST)
	public String getOrderList(Model model){
		OrderListOperForm form = new OrderListOperForm();
		List<OrderListBean> result = orderListService.getOrderListList(form);
		model.addAttribute("result", result);
		return "back/orderlist/showorderlist";
	}

	@RequestMapping(value = "/getOrderListRecord",method = RequestMethod.POST)
	public String getOrderListRecord(Model model){
		OrderListOperForm form = new OrderListOperForm();
		List<OrderListBean> result = orderListService.getOrderListList(form);
		model.addAttribute("result", result);
		return "back/orderlist/showorderlistrecord";
	}
	
	@RequestMapping(value = "/searchOrderList",method = RequestMethod.POST)
	public String searchActivationMember(@RequestBody OrderListOperForm form,Model model) {
		List<OrderListBean> result = orderListService.getOrderListList(form);
		model.addAttribute("form",form);
		model.addAttribute("result",result);
		return "back/orderlist/showorderlist";
	}
	
	@RequestMapping(value = "/searchOrderListRecord",method = RequestMethod.POST)
	public String searchOrderListRecord(@RequestBody OrderListOperForm form,Model model) {
		List<OrderListBean> result = orderListService.getOrderListList(form);
		model.addAttribute("form",form);
		model.addAttribute("result",result);
		return "back/orderlist/showorderlistrecord";
	}
	
	@RequestMapping(value = "/getOrderListDetail",method = RequestMethod.POST)
	public String getProduDetail(@RequestBody OrderListOperForm form,Model model){
		OrderList result = orderListService.getOrderListDetailById(form.getId());
		model.addAttribute("result", result);
		return "back/orderlist/showorderlistdetail";
	}
	
	@RequestMapping(value = "/deliveryOrder",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> updateProduct(@RequestBody OrderListOperForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		orderListService.updateOrderList(form);
		result.setMsg("发货成功.");
		result.setSuccess(true);
		return result;
	}
	
}
