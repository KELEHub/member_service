package com.member.services.front.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.front.OrderListBean;
import com.member.dao.OrderListDao;
import com.member.dao.ProductDao;
import com.member.entity.OrderList;
import com.member.entity.Product;
import com.member.form.front.FrontOrderListForm;
import com.member.helper.dao.impl.BaseDaoImpl;
import com.member.services.front.FrontOrderListService;

@Service("FrontOrderListServiceImpl")
@Transactional
public class FrontOrderListServiceImpl extends BaseDaoImpl implements FrontOrderListService{

	@Resource(name = "OrderListDaoImpl")
	private OrderListDao orderListDao;
	
	@Resource(name="ProductDaoImpl")
	private ProductDao productDao;
	
	@Override
	public List<OrderList> getFontOrderListByMemberNumber(
			FrontOrderListForm form) {
		String hql = "from OrderList ol where 1=1";
		//参数map
		Map<String, Object> arguments = new HashMap<String, Object>();
		//会员登录名称
		if(form.getMemberNumber()!=null && !"".equals(form.getMemberNumber())){
			hql+=" and memeberNumber=:memeberNumber";
			arguments.put("memeberNumber", form.getMemberNumber());
		}
		
		//状态
		if(form.getStatus()!=null && !"".equals(form.getStatus())){
			hql+=" and status=:status";
			arguments.put("status", form.getStatus());
		}
		
		List<?> result = orderListDao.queryByHql(hql,arguments);
		
		return (List<OrderList>) result;
	}

	@Override
	public List<OrderListBean> getOrderListBeanByMemberNumber(
			FrontOrderListForm form) {
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
				+ " on ol.productId=tp.id "
				+ " where 1=1 ";
//		if(form.getMemberNumber()!=null && !"".equals(form.getMemberNumber())){//查询条件不为空
//			sql+=" where ol.memeberNumber=?";
//		}
//		List<Object> arguments =null;
//		if(form.getMemberNumber()!=null && !"".equals(form.getMemberNumber())){//查询条件不为空
//			arguments = new ArrayList<Object>();
//			arguments.add(form.getMemberNumber());
//		}
		
		// 参数map
		Map<String, Object> arguments = new HashMap<String, Object>();
		// 会员登录名称
		if (form.getMemberNumber() != null
				&& !"".equals(form.getMemberNumber())) {
			sql += " and ol.memeberNumber=:memeberNumber";
			arguments.put("memeberNumber", form.getMemberNumber());
		}

		// 状态
		if (form.getStatus() != null && !"".equals(form.getStatus())) {
			sql += " and ol.status=:status";
			arguments.put("status", form.getStatus());
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

		for (Iterator<Entry<String, Object>> it = arguments.entrySet()
				.iterator(); it.hasNext();) {
			Entry<String, Object> entry = it.next();
			Object value = entry.getValue();
			if (value instanceof Collection<?>) {
				q.setParameterList(entry.getKey(), (Collection<?>) value);
			} else {
				q.setParameter(entry.getKey(), value);
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
	public void updateOrderList(FrontOrderListForm form) {
		OrderList result = getOrderListDetailById(form.getId());
		result.setStatus(2);//已提货
		orderListDao.update(result);
	}

	@Override
	public Product getProductDetailById(Integer id) {
		String hql = "from Product p where p.productStatus=0 and p.id=?";
		List result = productDao.queryByHql(hql,id);
		if(result!=null && result.size()>0){
			return (Product) result.get(0);
		}else{
			return null;
		}
	}
}
