package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.MenuBean;
import com.member.beans.back.OrderListBean;
import com.member.dao.OrderListDao;
import com.member.entity.OrderList;
import com.member.form.OrderListOperForm;
import com.member.helper.dao.impl.BaseDaoImpl;
import com.member.services.back.OrderListService;

@Service("OrderListServiceImpl")
@Transactional
public class OrderListServiceImpl extends BaseDaoImpl implements OrderListService{


	@Resource(name = "OrderListDaoImpl")
	private OrderListDao orderListDao;
	
	@Override
	public List<OrderListBean> getOrderListList(OrderListOperForm form) {
		String sql="select "
				+ " ol.id,"
				+ " ol.memeberNumber,"
				+ " ol.tradeNo,"
				+ " ol.orderDate,"
				+ " ol.serviceNumber,"
				+ " ol.productId,"
				+ " ol.status,"
				+ " ol.linkName,"
				+ " ol.linkProvince,"
				+ " ol.linkCity,"
				+ " ol.linkCounty,"
				+ " ol.linkAddress,"
				+ " ol.postNumber,"
				+ " ol.phoneNumber,"
				+ " tp.productName "
				+ " from t_order_list ol "
				+ " inner join t_product tp"
				+ " on ol.productId=tp.id ";
		if(form.getMemeberNumber()!=null && !"".equals(form.getMemeberNumber())){//查询条件不为空
			sql+=" where ol.memeberNumber=?";
		}
		List<Object> arguments =null;
		if(form.getMemeberNumber()!=null && !"".equals(form.getMemeberNumber())){//查询条件不为空
			arguments = new ArrayList<Object>();
			arguments.add(form.getMemeberNumber());
		}
		
		Session session = getCurrentSession();
		Query q = session.createSQLQuery(sql)
				.addScalar("id", org.hibernate.type.IntegerType.INSTANCE)
				.addScalar("memeberNumber", org.hibernate.type.StringType.INSTANCE)
				.addScalar("tradeNo", org.hibernate.type.StringType.INSTANCE)
				.addScalar("orderDate", org.hibernate.type.DateType.INSTANCE)
				.addScalar("serviceNumber", org.hibernate.type.StringType.INSTANCE)
				.addScalar("productId", org.hibernate.type.IntegerType.INSTANCE)
				.addScalar("status", org.hibernate.type.IntegerType.INSTANCE)
				.addScalar("linkName", org.hibernate.type.StringType.INSTANCE)
				.addScalar("linkProvince", org.hibernate.type.StringType.INSTANCE)
				.addScalar("linkCity", org.hibernate.type.StringType.INSTANCE)
				.addScalar("linkCounty", org.hibernate.type.StringType.INSTANCE)
				.addScalar("linkAddress", org.hibernate.type.StringType.INSTANCE)
				.addScalar("postNumber", org.hibernate.type.StringType.INSTANCE)
				.addScalar("phoneNumber", org.hibernate.type.StringType.INSTANCE)
				.addScalar("productName", org.hibernate.type.StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(OrderListBean.class));
		if (arguments != null) {
			int len = arguments.size();
			for (int i = 0; i < len; i++) {
				q.setParameter(i, arguments.get(i));

			}
		}
		List<OrderListBean> list = q.list();
		return list;
	}

	@Override
	public OrderList getOrderListDetailById(Integer id) {
		String hql = "from OrderList ol where ol.id=?";
		List result = orderListDao.queryByHql(hql,id);
		if(result!=null){
			return (OrderList) result.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void updateOrderList(OrderListOperForm form) {
		OrderList result = getOrderListDetailById(form.getId());
		result.setStatus(1);//已发货
		orderListDao.update(result);
	}

	@Override
	public void deleteOrderList(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveOrderList(OrderListOperForm form) {
		// TODO Auto-generated method stub
		
	}

}
