package com.member.services.back;

import java.util.List;

import com.member.beans.back.OrderListBean;
import com.member.entity.OrderList;
import com.member.form.OrderListOperForm;

public interface OrderListService {

	public List<OrderListBean> getOrderListList(OrderListOperForm form);
	
	public OrderList getOrderListDetailById(Integer id);
	
	public void saveOrderList(OrderListOperForm form);
	
	public void updateOrderList(OrderListOperForm form);
	
	public void deleteOrderList(Integer id);
}
