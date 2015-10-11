package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlServiceManager;
import com.member.dao.ServiceManagerDao;
import com.member.entity.ApplyService;
import com.member.entity.ForbidForm;
import com.member.entity.Information;
import com.member.entity.Product;
import com.member.entity.StockDistribute;
import com.member.form.back.InformationForm;
import com.member.form.back.MemberSearchForm;
import com.member.services.back.ProductService;
import com.member.services.back.ServiceManagerService;

@SuppressWarnings("unchecked")
@Service("ServiceManagerServiceImpl")
public class ServiceManagerServiceImpl implements ServiceManagerService{

	@Resource(name = "ServiceManagerDaoImpl")
    ServiceManagerDao serviceManagerDao;
	
	@Resource(name = "ProductServiceImpl")
	private ProductService productService;
	
	@Override
	@Transactional(readOnly=true)
	public List<Information> getServiceByIsService(Integer isService,String customerPar,
			int pageSize,int pageNumber,String number) {
		
		Map<String, Object> arguments = new HashMap<String, Object>();
		String hql = "from Information where (isService=1 OR (isService=0 AND bdUse=1)) ";
		if (number != null && !"".equals(number)) {
			hql+=" and number=:number";
			arguments.put("number", number);
		}
		hql = hql + " order by activeDate desc";
		return (List<Information>) serviceManagerDao.queryByHql(
				hql,pageNumber,pageSize, arguments);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Information getServiceByNumber(String number) {
		List<Information> result = (List<Information>) serviceManagerDao.queryByHql(
				HqlServiceManager.getServiceByNumber, number);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public ForbidForm getForbidForm() {
		List<ForbidForm> result = (List<ForbidForm>) serviceManagerDao.queryByHql(
				HqlServiceManager.getForbidForm);
		if(result!=null && result.size()>0){
			return (ForbidForm) result.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ApplyService> getApplyService(String customerPar,
			int pageSize,int pageNumber) {
		return (List<ApplyService>) serviceManagerDao.queryByHql(
				HqlServiceManager.getApproveService,pageNumber,pageSize);
	}
	
	@Override
	public int countApproveServiceData(String customerPar) {
		List result = serviceManagerDao.queryByHql(HqlServiceManager.getApproveService);
		if(result!=null){
			return result.size();
		}
		return 0;
	}
	
	@Override
	public int countServiceManagerData(String customerPar,Integer isService,String number) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String hql = "from Information where (isService=1 OR (isService=0 AND bdUse=1)) ";
		if (number != null && !"".equals(number)) {
			hql+=" and number=:number";
			arguments.put("number", number);
		}
		hql = hql + " order by activeDate desc";
		
		List result = serviceManagerDao.queryByHql(hql,arguments);
		if(result!=null){
			return result.size();
		}
		return 0;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateInfo(InformationForm msf) {
		String hql = "update Information set number=?,crmMoney=?,crmAccumulative=?,shoppingMoney=?,shoppingAccumulative=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(msf.getNumber());
		list.add(msf.getCrmMoney());
		list.add(msf.getCrmAccumulative());
		list.add(msf.getShoppingMoney());
		list.add(msf.getShoppingAccumulative());
		list.add(msf.getId());
		serviceManagerDao.executeHqlUpdate(hql, list);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void forbiddenService(Integer isService,Integer id) {
		String hql = "update Information set isService=?,bdUse=?,isFrim=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(isService);
		list.add(1);
		list.add(0);
		list.add(id);
		serviceManagerDao.executeHqlUpdate(hql, list);
		//关闭相关服务站信息
		closeFirmService(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateApplyState(Integer state,Integer id,String failureCause) {
		String hql = "update ApplyService set state=?,failureCause=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(state);
		list.add(failureCause);
		list.add(id);
		serviceManagerDao.executeHqlUpdate(hql, list);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateForbidForm(Integer ifForbid) {
		String hql = "update ForbidForm set ifForbid=?";
		List<Object> list = new ArrayList<Object>();
		list.add(ifForbid);
		serviceManagerDao.executeHqlUpdate(hql, list);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(Object obj) {
		serviceManagerDao.saveOrUpdate(obj);
	}

	@Override
	public Information getServiceById(Integer id) {
		List<Information> result = (List<Information>) serviceManagerDao.queryByHql(
				HqlServiceManager.getServiceById, id);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void openbiddenService(Integer isService, Integer id) {
		String hql = "update Information set isService=?,bdUse=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(isService);
		list.add(0);
		list.add(id);
		serviceManagerDao.executeHqlUpdate(hql, list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void closeFirmService(Integer id) {
		String hql = "from Information where id=?";
		List<Information> result = (List<Information>) serviceManagerDao.queryByHql(hql, id);
		if(result!=null && result.size()>0){
			Information info = result.get(0);
			info.setIsFrim(0);
			serviceManagerDao.saveOrUpdate(info);
			List<StockDistribute> list = productService.getStockDistributeByServerNumber(info.getNumber());
			if(list!=null && list.size()>0){
				for(StockDistribute st : list){
					Product pt = productService.getProductInfoByProductNumber(st.getProductNumber());
					pt.setFirmStock(pt.getFirmStock()+st.getServerFirm());
					serviceManagerDao.saveOrUpdate(pt);
					serviceManagerDao.delete(st);
				}
			}
			
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void openFirmService(Integer id) {
		String hql = "from Information where id=?";
		List<Information> result = (List<Information>) serviceManagerDao.queryByHql(hql, id);
		if(result!=null && result.size()>0){
			Information info = result.get(0);
			info.setIsFrim(1);
			serviceManagerDao.saveOrUpdate(info);
			List<Product> list = productService.getAllProductList();
			if(list!=null && list.size()>0){
				for(Product product : list){
					StockDistribute sb = new StockDistribute();
					sb.setProductId(product.getId());
					sb.setProductNumber(product.getProductNumber());
					sb.setProductName(product.getProductName());
					sb.setServerId(info.getId());
					sb.setServerNumber(info.getNumber());
					sb.setOperDate(new Date());
					sb.setServerFirm(0);
					serviceManagerDao.saveOrUpdate(sb);
				}
			}
			
		}
		
	}
}
