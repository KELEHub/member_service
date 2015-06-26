package com.member.services.front;

import java.util.List;

import com.member.beans.front.OrderListBean;
import com.member.entity.OrderList;
import com.member.entity.Product;
import com.member.form.front.FrontOrderListForm;

public interface FrontOrderListService {

	public List<OrderListBean> getOrderListBeanByMemberNumber(FrontOrderListForm form);
	
	public List<OrderList> getFontOrderListByMemberNumber(FrontOrderListForm form);
	
	public OrderList getOrderListDetailById(Integer id);
	
	public void updateOrderList(FrontOrderListForm form);
	
	public Product getProductDetailById(Integer id);
}
