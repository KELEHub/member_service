package com.member.services.back.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.InformationDao;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.form.back.MemberSaveForm;
import com.member.form.back.MemberSearchForm;
import com.member.services.back.MemberManageService;

@SuppressWarnings("unchecked")
@Service("MemberManageServiceImpl")
public class MemberManageServiceImpl implements MemberManageService {

	@Resource(name = "InformationDaoImpl")
	private InformationDao informationDao;

	@Override
	@Transactional(readOnly=true)
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

	@Override
	@Transactional(readOnly=true)
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateMemberLockFlag(Integer id) {
		Information member = getMemberById(id);
		member.setIsLock(1);
		informationDao.update(member);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateMemberPassword(Integer id) {
		Information member = getMemberById(id);
		member.setPassword("123456");
		informationDao.update(member);
	}

	@Override
	@Transactional(readOnly=true)
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteMember(MemberSaveForm form) {
		Information member = getMemberById(form.getId());
		informationDao.delete(member);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
	
	@Override
	@Transactional(readOnly=true)
	public List<AccountDetails> getAccountDetailsByProjectAndUserId(ProjectEnum project,Integer userId) {
		String hqlQuery = "from AccountDetails where project=? and userId=?";
		List<Object> list = new ArrayList<Object>();
		list.add(project);
		list.add(userId);
		return (List<AccountDetails>) informationDao.queryByHql(
				hqlQuery, list);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Information> getMemberInfoById(Integer id) {
		String hqlQuery = "from Information where id=?";
		return (List<Information>) informationDao.queryByHql(
				hqlQuery, id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteActiveMember(Integer id,String number,Integer isService,Integer recommendId,Integer leaderServiceId,AccountDetails serviceAD,AccountDetails memberAD,BigDecimal sum,
			BigDecimal shoppingMoneySurplus,BigDecimal repeatedMoneySurplus) {
		//删除以所删除会员为上级报单中心的信息（报单中心）
		String hql1 = "update Information set leaderServiceId=null,leaderServiceNumber=null where leaderServiceId=?";
		informationDao.executeHqlUpdate(hql1, id);
		//删除以所删除会员为推荐人的信息，包括未激活的会员（报单中心，普通会员）
		String hql2 = "update Information set recommendId=null,recommendNumber=null where recommendId=?";
		informationDao.executeHqlUpdate(hql2, id);
		if (isService==1){
			//删除该会员的上级报单中心积分和服务积分各50（报单中心）
			String hql3 = "update Information set shoppingMoney=shoppingMoney-50,repeatedMoney=repeatedMoney-50 where id=?";
			informationDao.executeHqlUpdate(hql3, leaderServiceId);
			//在账户明细表里记录
			informationDao.saveOrUpdate(serviceAD);
		}
		if (recommendId!=null){
			//删除该会员的推荐人的相应礼包（报单中心，普通会员）
			String hql4 = "update Information set shoppingMoney=?,repeatedMoney=? where id=?";
			List<Object> list = new ArrayList<Object>();
			list.add(shoppingMoneySurplus);
			list.add(repeatedMoneySurplus);
			list.add(recommendId);
			informationDao.executeHqlUpdate(hql4,list);
			//在账户明细表里记录
			informationDao.saveOrUpdate(memberAD);
		}
		//删除留言未回复、提现申请、充值申请、报单中心申请的相关信息
		String hql5="delete Tickling where memberId=?";//删除留言
		String hql6="delete Withdrawals where number=? and status='0'";//删除提现申请
		String hql7="delete Charge where number=?";//删除充值申请
		String hql8="delete ApplyService where applyId=?";//删除申请人为要删除的会员的报单中心申请
		informationDao.executeHqlUpdate(hql5,id);
		informationDao.executeHqlUpdate(hql6,number);
		informationDao.executeHqlUpdate(hql7,number);
		informationDao.executeHqlUpdate(hql8,id);
		
		//删除提交人为要删除的会员的报单中心申请字段信息
		String hql9 = "update ApplyService set submitId=null,submitNumber=null where submitId=?";
		informationDao.executeHqlUpdate(hql9, id);
		
		//删除information表记录
		String hql10="delete Information where id=?";
		informationDao.executeHqlUpdate(hql10,id);
	}
}
