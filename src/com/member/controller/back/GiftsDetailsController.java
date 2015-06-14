package com.member.controller.back;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.back.enumData.BatchNoEnum;
import com.member.beans.back.enumData.GiftEnum;
import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.entity.GiftsDetails;
import com.member.entity.GiftsHistory;
import com.member.entity.Information;
import com.member.entity.ManageRole;
import com.member.entity.NmsUser;
import com.member.entity.SendGiftsDetails;
import com.member.form.back.GiftsForm;
import com.member.form.back.GiftsHistoryForm;
import com.member.helper.BaseResult;
import com.member.services.back.GiftsDetailsService;
import com.member.services.back.InformationService;
import com.member.services.back.InstitutionService;
import com.member.services.back.ParameterService;
import com.member.services.back.RoleManageService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/GiftsDetailsController")
public class GiftsDetailsController {
	
	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@Resource(name = "NmsUserDaoImpl")
	private NmsUserDao nmsUserDao;
	
	@Resource(name = "RoleManageServiceImpl")
	private RoleManageService roleManageService;
	
	
	@Resource(name = "GiftsDetailsServiceImpl")
	private GiftsDetailsService giftsDetailsService;
	
	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;
	
	
	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model) {
		try {
		
			List<GiftsDetails> list = parameterService
			.getGiftsDetails();
			List<GiftsForm> gfList = new ArrayList<GiftsForm>();
			if(list!=null && list.size()>0){
				for(GiftsDetails gd:list){
					Information info =informationService.getInformationById(gd.getChildId());
					GiftsForm form = new GiftsForm();
					form.setCreateTime(gd.getCreateTime().toString());
					form.setName("礼包_"+info.getNumber());
					form.setId(gd.getId().toString());
					int last = 0;
					if(gd.getGiftEnum().equals(GiftEnum.FIVE)){
						last=5-gd.getPointNumber()+1;
					}else{
						last=10-gd.getPointNumber()+1;
					}
					form.setRemaind("积分领取剩余次数："+last);
					form.setNumber(gd.getNumber());
					gfList.add(form);
				}
				model.addAttribute("result", gfList);
			}
				return "back/systeminfo/gifts";

		} catch (Exception e) {
			e.printStackTrace();
			return "back/systeminfo/gifts";
		}

	}
	
	
	@RequestMapping(value = "/showDialog",method = RequestMethod.POST)
	public String showDialog(@RequestBody GiftsForm form,Model model){
		GiftsDetails giftsDetails = parameterService.getGiftsDetailsById(Integer.valueOf(form.getId()));
		Information info =informationService.getInformationById(giftsDetails.getChildId());
		GiftsForm gf =new GiftsForm();
		gf.setNumber(giftsDetails.getNumber());
		gf.setId(giftsDetails.getId().toString());
		String typeGift = "";
		if(giftsDetails.getGiftEnum().equals(GiftEnum.FIVE)){
			typeGift = "五期次礼包类型";
		}else{
			typeGift = "十期次礼包类型";
		}
		gf.setTypeGift(typeGift);
		gf.setName("礼包_"+info.getNumber());
		gf.setPointNumber(giftsDetails.getPointNumber().toString());
		model.addAttribute("bean",gf);
		return "back/systeminfo/editeGifts";
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public String search(@RequestBody GiftsForm giftsForm,Model model) {
		try {
			List<GiftsDetails> list = null;
			if(giftsForm.getNumber()==null || "".equals(giftsForm.getNumber())){
				list = parameterService
				.getGiftsDetails();
			}else{
				 list = parameterService
					.getGiftsDetailsByNumber(giftsForm.getNumber());
			}
			
			List<GiftsForm> gfList = new ArrayList<GiftsForm>();
			if(list!=null && list.size()>0){
				for(GiftsDetails gd:list){
					Information info =informationService.getInformationById(gd.getChildId());
					GiftsForm form = new GiftsForm();
					form.setCreateTime(gd.getCreateTime().toString());
					form.setName("礼包_"+info.getNumber());
					form.setId(gd.getId().toString());
					int last = 0;
					if(gd.getGiftEnum().equals(GiftEnum.FIVE)){
						last=5-gd.getPointNumber()+1;
					}else{
						last=10-gd.getPointNumber()+1;
					}
					form.setRemaind("积分领取剩余次数："+last);
					form.setNumber(gd.getNumber());
					gfList.add(form);
				}
				model.addAttribute("result", gfList);
			}
				return "back/systeminfo/gifts";

		} catch (Exception e) {
			e.printStackTrace();
			return "back/systeminfo/gifts";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/set",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody GiftsForm form,Model model,HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
			String userNaemO = (String) logonUserMap.get("userName");
			List<NmsUser> users = (List<NmsUser>) nmsUserDao.queryByHql(
					HqlUserRole.getUserByName, userNaemO);
			NmsUser user = users.get(0);
			GiftsDetails giftsDetails = parameterService.getGiftsDetailsById(Integer.valueOf(form.getId()));
			if(Integer.valueOf(form.getPointNumber()) == 0){
				result.setSuccess(false);
				result.setMsg("期次号不能等于0");
				return result;
			}
			if(giftsDetails.getGiftEnum().equals(GiftEnum.FIVE)){
				if(Integer.valueOf(form.getPointNumber())>5){
					result.setSuccess(false);
					result.setMsg("期次号必须小于5");
					return result;
				}
			}else{
				if(Integer.valueOf(form.getPointNumber())>10){
					result.setSuccess(false);
					result.setMsg("期次号必须小于10");
					return result;
				}
			}
			if(Integer.valueOf(form.getPointNumber())<giftsDetails.getPointNumber()){
				result.setSuccess(false);
				result.setMsg("期次号不能小于当前期次号");
				return result;
			}
			if(Integer.valueOf(form.getPointNumber())==giftsDetails.getPointNumber()){
				result.setSuccess(false);
				result.setMsg("期次号未修改");
				return result;
			}
			Information info =informationService.getInformationById(giftsDetails.getChildId());
			String remaind = "修改前的期次：" + giftsDetails.getPointNumber()+";"+"修改后的期次："+form.getPointNumber();
			giftsDetails.setPointNumber(Integer.valueOf(form.getPointNumber()));
			GiftsHistory giftsHistory = new GiftsHistory();
			giftsHistory.setCreateTime(new Date());
			giftsHistory.setNumeber(giftsDetails.getNumber());
			giftsHistory.setUserId(user.getId());
			giftsHistory.setRemaind(remaind);
			giftsHistory.setName("礼包_"+info.getNumber());
			parameterService.saveOrUpdate(giftsDetails, giftsHistory,Integer.valueOf(form.getPointNumber()));
			result.setMsg("修改会员详细信息成功.");
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("修改会员详细信息失败，请重新修改.");
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/historyShow", method = RequestMethod.POST)
	public String historyShow(Model model, HttpSession sesison) {

		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("userName");

		List<NmsUser> users = (List<NmsUser>) nmsUserDao.queryByHql(
				HqlUserRole.getUserByName, userNaemO);
		NmsUser user = new NmsUser();
		if (users != null && users.size() > 0) {
			user = users.get(0);
			ManageRole role = roleManageService.getRoleById(user.getId());
			List<GiftsHistory> list = new ArrayList<GiftsHistory>();
			List<GiftsHistoryForm> formList = new ArrayList<GiftsHistoryForm>();
			if (role.getSuperAdmin() != null && role.getSuperAdmin() == 1) {
				list = parameterService.getGiftsHistoryAll();
			} else {
				list = parameterService.getGiftsHistoryByOperationId(user
						.getId());
			}
			if (list!= null && list.size() > 0) {
				for (GiftsHistory eh : list) {
					List<NmsUser> oprations = (List<NmsUser>) nmsUserDao.queryByHql(
							HqlUserRole.getUserById, eh.getUserId());
					NmsUser opration = oprations.get(0);
					GiftsHistoryForm form = new GiftsHistoryForm();
					form.setCreateDate(String.valueOf(eh.getCreateTime()));
					form.setNumeber(eh.getNumeber());
					form.setOprationMan(opration.getUserName());
					form.setRemaind(eh.getRemaind());
					form.setName(eh.getName());
					formList.add(form);
				}
			}
			model.addAttribute("result", formList);

		}
		return "back/systeminfo/giftsHistory";

	}
	
	
	@RequestMapping(value = "/showSend", method = RequestMethod.POST)
	public String showSend(Model model) {
		try {
		
			int countNumber = CommonUtil.getCountNumber();
			BatchNoEnum batchNo = CommonUtil.getBatchNo();
			
			List<SendGiftsDetails> giftsList = giftsDetailsService.getGiftsDetailsList(countNumber, batchNo);
			if(giftsList!=null&&giftsList.size()>0){
				model.addAttribute("result", giftsList);
				String goldAll = giftsDetailsService.getCountGoldAll(countNumber, batchNo);
				model.addAttribute("goldAll", goldAll);
			}
			return "back/systeminfo/giftsSend";
		} catch (Exception e) {
			e.printStackTrace();
			return "back/systeminfo/giftsSend";
		}

	}
	
	
	@RequestMapping(value = "/send",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> send(@RequestBody GiftsForm form,Model model,HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			int countNumber = CommonUtil.getCountNumber();
			BatchNoEnum batchNo = CommonUtil.getBatchNo();
			List<SendGiftsDetails> giftsList = giftsDetailsService.getGiftsDetailsList(countNumber, batchNo);
			if(giftsList!=null&&giftsList.size()>0){
				for(SendGiftsDetails ss:giftsList){
					giftsDetailsService.senGold(ss);
				}
			}
			result.setMsg("积分释放成功");
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("积分释放失败，请重新释放");
		}
		return result;
	}

}
