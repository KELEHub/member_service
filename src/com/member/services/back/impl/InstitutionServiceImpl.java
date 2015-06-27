package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlInstitution;
import com.member.dao.InstitutionDao;
import com.member.entity.BankRechargeHistory;
import com.member.entity.BankService;
import com.member.entity.EditeHistory;
import com.member.entity.Information;
import com.member.entity.Institution;
import com.member.form.front.MemberOperForm;
import com.member.form.front.MemberUpdateForm;
import com.member.services.back.InstitutionService;
@Service("InstitutionServiceImpl")
public class InstitutionServiceImpl implements InstitutionService{
	
	@Resource(name = "InstitutionDaoImpl")
    InstitutionDao institutionDao;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Institution getInstitutionInfo() {
		List<Institution> dataPermissionCodes=(List<Institution>) institutionDao.queryByHql(HqlInstitution.getInstitutionInfo);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes.get(0);
        }
        return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean setInstitution(Institution institution) {
		
		try {
			institutionDao.saveOrUpdate(institution);
			
	        return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean savaOrUpdate(Object ob) {
		
		try {
			institutionDao.saveOrUpdate(ob);
			
	        return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<EditeHistory> getEditeHistoryListByUserId(Integer userId) {
		String hql="select mr from EditeHistory mr where userId=?";
		List arguments = new ArrayList();
		arguments.add(userId);
		List<EditeHistory> result = (List<EditeHistory>)institutionDao.queryByHql(hql,arguments);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<EditeHistory> getEditeHistoryList() {
		String hql="select mr from EditeHistory mr";
		List<EditeHistory> result = (List<EditeHistory>)institutionDao.queryByHql(hql);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Information getNnmuserByName(String number) {
		String hql="from Information mr where mr.number=?";
		List arguments = new ArrayList();
		arguments.add(number);
		List<Information> result = (List<Information>)institutionDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<BankService> getBankServiceInfo() {
		String hql="from BankService mr";
		List<BankService> result = (List<BankService>)institutionDao.queryByHql(hql);
		if(result!=null && result.size()>0){
			return result;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public BankService getBankServiceInfoByName(String name) {
		String hql="from BankService mr where mr.bankName=?";
		List arguments = new ArrayList();
		arguments.add(name);
		List<BankService> result = (List<BankService>)institutionDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteData(Object ob) {
		try {
			institutionDao.delete(ob);
		} catch (Exception e) {
			
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<BankRechargeHistory> getBankRechargeHistoryList() {
		String hql="select mr from BankRechargeHistory mr";
		List<BankRechargeHistory> result = (List<BankRechargeHistory>)institutionDao.queryByHql(hql);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<BankRechargeHistory> getBankRechargeHistoryListByUserId(
			Integer userId) {
		String hql="select mr from BankRechargeHistory mr where userId=?";
		List arguments = new ArrayList();
		arguments.add(userId);
		List<BankRechargeHistory> result = (List<BankRechargeHistory>)institutionDao.queryByHql(hql,arguments);
		return result;
	}

	@Override
	@Transactional
	public void update1Password(MemberOperForm form) {
		Information ifm = getInformationById(form.getUserId());
		if(ifm!=null){
			ifm.setPassword(form.getOnelevelpassword());
			institutionDao.update(ifm);
		}
	}

	@Override
	@Transactional
	public void update2Password(MemberOperForm form) {
		Information ifm = getInformationById(form.getUserId());
		if(ifm!=null){
			ifm.setProtectPassword(form.getTwolevelpassword());
			institutionDao.update(ifm);
		}
	}

	public Information getInformationById(Integer id) {
		String hql="from Information mr where mr.id=?";
		List<Information> listresult = (List<Information>)institutionDao.queryByHql(hql,id);
		if(listresult!=null && listresult.size()>0){
			return listresult.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void updateMemberInfo(MemberUpdateForm form) {
		Information ifm = getInformationById(form.getId());
		if(ifm!=null){
			/**邮箱 */
			ifm.setEmail(form.getEmail());

			/**电话号码 */
			ifm.setPhoneNumber(form.getPhoneNumber());
			
			/**银行名称 */
			ifm.setBankName(form.getBankName());
			
			/**银行省份 */
			ifm.setBankProvince(form.getBankProvince());
			
			/**银行市级*/
			ifm.setBankCity(form.getBankCity());
			
			/**银行区县 */
			ifm.setBankCounty(form.getBankCounty());
			
			/**银行支行地址 */
			ifm.setBankAddress(form.getBankAddress());
			
			/**银行卡号 */
			ifm.setBankCard(form.getBankCard());
			
			/**邮编*/
			ifm.setPostNumber(form.getPostNumber());
			
			/**联系人省份 */
			ifm.setLinkProvince(form.getLinkProvince());
			
			/**联系人市级 */
			ifm.setLinkCity(form.getLinkCity());

			/**联系人区县 */
			ifm.setLinkCounty(form.getLinkCounty());
			
			/**联系人地址 */
			ifm.setLinkAddress(form.getLinkAddress());
			
			institutionDao.update(ifm);
		}
	}
}
