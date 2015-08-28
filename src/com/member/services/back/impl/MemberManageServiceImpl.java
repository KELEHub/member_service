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
	public List<Information> getActiveMembers(MemberSearchForm form,String number,String recommendNumber, String serviceNumber,
			int pageSize,int pageNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String hqlQuery = " from Information s where s.isActivate=1 ";
		if (number != null && !"".equals(number)) {
			hqlQuery+=" and number=:number";
			arguments.put("number", number);
		}
		if (recommendNumber != null && !"".equals(recommendNumber)) {
			hqlQuery+=" and recommendNumber=:recommendNumber";
			arguments.put("recommendNumber", recommendNumber);
		}
		if (serviceNumber != null
				&& !"".equals(serviceNumber)) {
			hqlQuery+=" and activateNumber=:serviceNumber";
			arguments.put("serviceNumber", serviceNumber);
		}
		hqlQuery = hqlQuery +"  order by activeDate desc";
		List result = informationDao.queryByHql(hqlQuery,pageNumber,pageSize, arguments);
		return result;
	}

	@Override
	public int countActiveMembersData(MemberSearchForm form,String number,String recommendNumber, String serviceNumber,Integer isActivate) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String hqlQuery = " from Information s where s.isActivate=:isActivate";
		if (number != null && !"".equals(number)) {
			hqlQuery+=" and number=:number";
			arguments.put("number", number);
		}
		if (recommendNumber != null && !"".equals(recommendNumber)) {
			hqlQuery+=" and recommendNumber=:recommendNumber";
			arguments.put("recommendNumber", recommendNumber);
		}
		if (serviceNumber != null
				&& !"".equals(serviceNumber)) {
			hqlQuery+=" and activateNumber=:serviceNumber";
			arguments.put("serviceNumber", serviceNumber);
		}
		arguments.put("isActivate", isActivate);
		List result = informationDao.queryByHql(hqlQuery,arguments);
		if(result!=null){
			return result.size();
		}
		return 0;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Information> getNotActiveMembers(MemberSearchForm form,String customerPar,
			int pageSize,int pageNumber) {
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
			hqlQuery+=" and activateNumber=:serviceNumber";
			arguments.put("serviceNumber", form.getServiceNumber());
		}
		hqlQuery = hqlQuery +"  order by registerDate desc";
		List result = informationDao.queryByHql(hqlQuery, pageNumber,pageSize,arguments);
		return result;
	}

	@Override
	public int countNotActiveMembersData(MemberSearchForm form) {
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
			hqlQuery+=" and activateNumber=:serviceNumber";
			arguments.put("serviceNumber", form.getServiceNumber());
		}
		hqlQuery = hqlQuery +"  order by registerDate desc";
		List result = informationDao.queryByHql(hqlQuery,arguments);
		if(result!=null){
			return result.size();
		}
		return 0;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateMemberLockFlag(Integer id) {
		Information member = getMemberById(id);
		if(1==member.getIsLock()){
			member.setIsLock(0);
		}else{
			member.setIsLock(1);
		}
		informationDao.update(member);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateMemberPassword(Integer id) {
		Information member = getMemberById(id);
		member.setPassword("123456");
		member.setProtectPassword("123456");
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
		member.setName(form.getUsername());
		
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
	public List<AccountDetails> getAccountDetailsByProjectAndUserId(ProjectEnum project,Integer childId) {
		String hqlQuery = "from AccountDetails where project=? and childId=?";
		List<Object> list = new ArrayList<Object>();
		list.add(project);
		list.add(childId);
		return (List<AccountDetails>) informationDao.queryByHql(
				hqlQuery, list);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Information> getMemberInfoByNumber(String number) {
		String hqlQuery = "from Information where number=?";
		return (List<Information>) informationDao.queryByHql(
				hqlQuery, number);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteActiveMember(Integer id,String number,Integer isService,Integer recommendId,String activateNumber,AccountDetails serviceAD,AccountDetails memberAD,BigDecimal sum,
			BigDecimal shoppingMoneySurplus,BigDecimal repeatedMoneySurplus) {
		//删除以所删除会员为上级报单中心的信息（报单中心）
		String hql1 = "update Information set leaderServiceId=null,leaderServiceNumber=null where leaderServiceNumber=?";
		informationDao.executeHqlUpdate(hql1, number);
		//删除以所删除会员为推荐人的信息，包括未激活的会员（报单中心，普通会员）
		String hql2 = "update Information set recommendId=null,recommendNumber=null where recommendNumber=?";
		informationDao.executeHqlUpdate(hql2, number);
//		if (isService==1 && leaderServiceId!=null){
			//删除该会员的激活人的积分和服务积分各50
			String hql3 = "update Information set shoppingMoney=shoppingMoney-50,repeatedMoney=repeatedMoney-50 where number=?";
			informationDao.executeHqlUpdate(hql3, activateNumber);
			//在账户明细表里记录
			informationDao.saveOrUpdate(serviceAD);
//		}
			
//		if (recommendId!=null){
//			//删除该会员的推荐人的相应礼包（报单中心，普通会员）
//			String hql4 = "update Information set shoppingMoney=?,repeatedMoney=? where id=?";
//			List<Object> list = new ArrayList<Object>();
//			list.add(shoppingMoneySurplus);
//			list.add(repeatedMoneySurplus);
//			list.add(recommendId);
//			informationDao.executeHqlUpdate(hql4,list);
//			//在账户明细表里记录
//			informationDao.saveOrUpdate(memberAD);
//		}
		//删除留言未回复、提现申请、充值申请、报单中心申请的相关信息
		String hql5="delete Tickling where memberNumber=?";//删除留言
		String hql6="delete Withdrawals where number=? and status='0'";//删除提现申请
		String hql7="delete Charge where number=?";//删除充值申请
		String hql8="delete ApplyService where applyNumber=?";//删除申请人为要删除的会员的报单中心申请
		String hql11 = "delete RepeatedMoneyStatistics where declarationNumber=?";//删除RepeatedMoneyStatistics（极差积分统计表）对应激活人的极差积分统计信息
		informationDao.executeHqlUpdate(hql5,number);
		informationDao.executeHqlUpdate(hql6,number);
		informationDao.executeHqlUpdate(hql7,number);
		informationDao.executeHqlUpdate(hql8,number);
		informationDao.executeHqlUpdate(hql11,number);
		
		//删除提交人为要删除的会员的报单中心申请字段信息
		String hql9 = "update ApplyService set submitId=null,submitNumber=null where submitNumber=?";
		informationDao.executeHqlUpdate(hql9, number);
		
		//删除information表记录
		String hql10="update Information set isActivate=2 where number=?";
		informationDao.executeHqlUpdate(hql10,number);
	}
}
