package com.member.services.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.member.dao.InformationDao;
import com.member.entity.Information;
import com.member.form.back.MemberSaveForm;
import com.member.form.back.MemberSearchForm;
import com.member.services.back.MemberManageService;

@Service("MemberManageServiceImpl")
public class MemberManageServiceImpl implements MemberManageService {

	@Resource(name = "InformationDaoImpl")
	private InformationDao informationDao;

	public List<Information> getActiveMembers(MemberSearchForm form) {
		Map<String, Object> arguments = new HashMap<String, Object>();

		String hqlQuery = " from Information s where s.isActivate=1 ";
		
		if (form.getNumber() != null && !"".equals(form.getNumber())) {
			hqlQuery+=" and number=:number";
			arguments.put("number", form.getNumber());
		}
		if (form.getRecommendNumber() != null && !"".equals(form.getRecommendNumber())) {
			hqlQuery+=" and recommendNumber=:recommendNumber";
			arguments.put("recommendNumber", form.getRecommendNumber());
		}
		if (form.getServiceNumber() != null
				&& !"".equals(form.getServiceNumber())) {
			hqlQuery+=" and serviceNumber=:serviceNumber";
			arguments.put("serviceNumber", form.getServiceNumber());
		}

		List result = informationDao.queryByHql(hqlQuery, arguments);
		return result;
	}

	public List<Information> getNotActiveMembers(MemberSearchForm form) {
		Map<String, Object> arguments = new HashMap<String, Object>();

		String hqlQuery = "from Information s where s.isActivate=0";
		if (form.getNumber() != null && !"".equals(form.getNumber())) {
			hqlQuery+=" and number=:number";
			arguments.put("number", form.getNumber());
		}
		if (form.getRecommendNumber() != null && !"".equals(form.getRecommendNumber())) {
			hqlQuery+=" and recommendNumber=:recommendNumber";
			arguments.put("recommendNumber", form.getRecommendNumber());
		}
		if (form.getServiceNumber() != null
				&& !"".equals(form.getServiceNumber())) {
			hqlQuery+=" and serviceNumber=:serviceNumber";
			arguments.put("serviceNumber", form.getServiceNumber());
		}
		List result = informationDao.queryByHql(hqlQuery, arguments);
		return result;
	}

	@Override
	public void updateMemberLockFlag(Integer id) {
		Information member = getMemberById(id);
		member.setIsLock(1);
		informationDao.update(member);
	}

	@Override
	public void updateMemberPassword(Integer id) {
		Information member = getMemberById(id);
		member.setPassword("123456");
		informationDao.update(member);
	}

	@Override
	public Information getMemberById(Integer id) {
		String hqlQuery = " from Information s where s.id=? ";
		List result = informationDao.queryByHql(hqlQuery, id);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteMember(MemberSaveForm form) {
		Information member = getMemberById(form.getId());
		informationDao.delete(member);
	}
	
	@Override
	public void updateMemberDetails(MemberSaveForm form) {
		Information member = getMemberById(form.getId());
		member.setNumber(form.getNumber());
		member.setName(form.getName());
		
		/**身份证号码 */
		member.setIdentity(form.getIdentity());
		
		/**邮箱 */
		member.setEmail(form.getEmail());

		/**电话号码 */
		member.setPhoneNumber(form.getPhoneNumber());
		
		/**银行名称 */
		member.setBankName(form.getBankName());
		
		/**银行省份 */
		member.setBankProvince(form.getBankProvince());
		
		/**银行市级*/
		member.setBankCity(form.getBankCity());
		
		/**银行区县 */
		member.setBankCounty(form.getBankCounty());
		
		/**银行支行地址 */
		member.setBankAddress(form.getBankAddress());
		
		/**银行卡号 */
		member.setBankCard(form.getBankCard());
		
		/**邮编*/
		member.setPostNumber(form.getPostNumber());
		
		/**联系人省份 */
		member.setLinkProvince(form.getLinkProvince());
		
		/**联系人市级 */
		member.setLinkCity(form.getLinkCity());

		/**联系人区县 */
		member.setLinkCounty(form.getLinkCounty());
		
		/**联系人地址 */
		member.setLinkAddress(form.getLinkAddress());
		informationDao.update(member);
	}
}
