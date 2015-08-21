package com.member.controller.back;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.back.enumData.BatchNoEnum;
import com.member.beans.back.enumData.GiftEnum;
import com.member.common.config.FrameConfig;
import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.entity.AccountDetails;
import com.member.entity.GiftsDetails;
import com.member.entity.GiftsHistory;
import com.member.entity.Information;
import com.member.entity.ManageRole;
import com.member.entity.NmsUser;
import com.member.entity.SendGiftsDetails;
import com.member.form.back.GiftsForm;
import com.member.form.back.GiftsHistoryForm;
import com.member.form.back.IntegralHistoryForm;
import com.member.helper.BaseResult;
import com.member.services.back.GiftsDetailsService;
import com.member.services.back.InformationService;
import com.member.services.back.InstitutionService;
import com.member.services.back.IntegralManagerService;
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

	@Resource(name = "IntegralManagerServiceImpl")
	public IntegralManagerService integralManagerService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getGiftsDetailsPage")
	@ResponseBody
	public Map getGiftsDetailsPage(HttpServletRequest request,Model model) {
		String number = request.getParameter("number");
		String iDisplayLength = request.getParameter("iDisplayLength");	
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		int iTotalRecords = parameterService.countGiftsDetails(number);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<GiftsDetails> list = parameterService.getGiftsDetails(number,Integer.parseInt(iDisplayLength),
				pageNumber);
		List<GiftsForm> gfList = new ArrayList<GiftsForm>();
		if (list != null && list.size() > 0) {
			for (GiftsDetails gd : list) {
				Information info = null;
				if(gd.getChildId() == null){
					info = null; 
				}else{
				    info = informationService.getInformationById(gd
							.getChildId());
				}
				
				GiftsForm form = new GiftsForm();
				form.setCreateTime(gd.getCreateTime().toString());
				if(info==null){
					if(gd.getName()!=null&& !"".equals(gd.getName())){
						form.setName(gd.getName());
					}else{
						form.setName("礼包_"+"未知");
					}
				}else{
					form.setName("礼包_"+info.getNumber());
				}
				form.setId(gd.getId().toString());
				int last = 0;
				if (gd.getGiftEnum().equals(GiftEnum.FIVE)) {
					last = 5 - gd.getPointNumber() + 1;
				} else {
					last = 10 - gd.getPointNumber() + 1;
				}
				form.setRemaind("积分领取剩余次数：" + last);
				form.setNumber(gd.getNumber());
				gfList.add(form);
			}
		}
		
		model.addAttribute("result", gfList);
		Map map = new HashMap();
		map.put("aaData", gfList);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
	@RequestMapping(value = "/show",method = RequestMethod.POST)
	public String show(Model model) {
		return "back/systeminfo/gifts";
	}

	@RequestMapping(value = "/showDialog", method = RequestMethod.POST)
	public String showDialog(@RequestBody GiftsForm form, Model model) {
		GiftsDetails giftsDetails = parameterService
				.getGiftsDetailsById(Integer.valueOf(form.getId()));
		Information info = null;
		if(giftsDetails.getChildId() == null){
			info = null; 
		}else{
		    info = informationService.getInformationById(giftsDetails
					.getChildId());
		}
		GiftsForm gf = new GiftsForm();
		gf.setNumber(giftsDetails.getNumber());
		gf.setId(giftsDetails.getId().toString());
		String typeGift = "";
		if (giftsDetails.getGiftEnum().equals(GiftEnum.FIVE)) {
			typeGift = "五期次礼包类型";
		} else {
			typeGift = "十期次礼包类型";
		}
		gf.setTypeGift(typeGift);
		if(info==null){
			if(giftsDetails.getName()!=null&&!"".equals(giftsDetails.getName())){
				gf.setName(giftsDetails.getName());
			}else{
				gf.setName("礼包_"+"未知");
			}
		}else{
			gf.setName("礼包_"+info.getNumber());
		}
		gf.setPointNumber(giftsDetails.getPointNumber().toString());
		model.addAttribute("bean", gf);
		return "back/systeminfo/editeGifts";
	}

//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public String search(@RequestBody GiftsForm giftsForm, Model model) {
//		try {
//			List<GiftsDetails> list = null;
//			if (giftsForm.getNumber() == null
//					|| "".equals(giftsForm.getNumber())) {
//				list = parameterService.getGiftsDetails();
//			} else {
//				list = parameterService.getGiftsDetailsByNumber(giftsForm
//						.getNumber());
//			}
//
//			List<GiftsForm> gfList = new ArrayList<GiftsForm>();
//			if (list != null && list.size() > 0) {
//				for (GiftsDetails gd : list) {
//					Information info = null;
//					if(gd.getChildId() == null){
//						info = null; 
//					}else{
//					    info = informationService.getInformationById(gd
//								.getChildId());
//					}
//					GiftsForm form = new GiftsForm();
//					form.setCreateTime(gd.getCreateTime().toString());
//					if(info==null){
//						form.setName("礼包_"+"未知");
//					}else{
//						form.setName("礼包_"+info.getNumber());
//					}
//					form.setId(gd.getId().toString());
//					int last = 0;
//					if (gd.getGiftEnum().equals(GiftEnum.FIVE)) {
//						last = 5 - gd.getPointNumber() + 1;
//					} else {
//						last = 10 - gd.getPointNumber() + 1;
//					}
//					form.setRemaind("积分领取剩余次数：" + last);
//					form.setNumber(gd.getNumber());
//					gfList.add(form);
//				}
//				model.addAttribute("result", gfList);
//			}
//			return "back/systeminfo/gifts";
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "back/systeminfo/gifts";
//		}
//	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody GiftsForm form, Model model,
			HttpSession sesison) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
			String userNaemO = (String) logonUserMap.get("userName");
			List<NmsUser> users = (List<NmsUser>) nmsUserDao.queryByHql(
					HqlUserRole.getUserByName, userNaemO);
			NmsUser user = users.get(0);
			GiftsDetails giftsDetails = parameterService
					.getGiftsDetailsById(Integer.valueOf(form.getId()));
			Information infoEdite = informationService.getInformationByNumber(form.getNumber());
			if(infoEdite==null){
				result.setSuccess(false);
				result.setMsg("会员不存在");
				return result;
			}
			if (Integer.valueOf(form.getPointNumber()) == 0) {
				result.setSuccess(false);
				result.setMsg("期次号不能等于0");
				return result;
			}
			if (giftsDetails.getGiftEnum().equals(GiftEnum.FIVE)) {
				if (Integer.valueOf(form.getPointNumber()) > 5) {
					result.setSuccess(false);
					result.setMsg("期次号必须小于5");
					return result;
				}
			} else {
				if (Integer.valueOf(form.getPointNumber()) > 10) {
					result.setSuccess(false);
					result.setMsg("期次号必须小于10");
					return result;
				}
			}
			if (Integer.valueOf(form.getPointNumber()) < giftsDetails
					.getPointNumber()) {
				result.setSuccess(false);
				result.setMsg("期次号不能小于当前期次号");
				return result;
			}
			if (Integer.valueOf(form.getPointNumber()) == giftsDetails
					.getPointNumber()&& giftsDetails.getNumber().equals(form.getNumber())) {
				result.setSuccess(false);
				result.setMsg("期次号未修改");
				return result;
			}
			Information info = null;
			if(giftsDetails.getChildId() == null){
				info = null; 
			}else{
			    info = informationService.getInformationById(giftsDetails
						.getChildId());
			}
			String remaind = "修改前的账号："+giftsDetails.getNumber()+",期次：" + giftsDetails.getPointNumber() + ";"
					+ "修改后的账号："+form.getNumber()+",期次：" + form.getPointNumber();
			giftsDetails.setPointNumber(Integer.valueOf(form.getPointNumber()));
            Boolean fig = false;
			if(!giftsDetails.getNumber().equals(form.getNumber())){
				fig=true;
            	giftsDetails.setNumber(form.getNumber());
            	giftsDetails.setUserId(infoEdite.getId());
            }
			GiftsHistory giftsHistory = new GiftsHistory();
			giftsHistory.setCreateTime(new Date());
			giftsHistory.setNumeber(giftsDetails.getNumber());
			giftsHistory.setUserId(user.getId());
			giftsHistory.setRemaind(remaind);
			if(info==null){
				if(giftsDetails.getName()!=null&&!"".equals(giftsDetails.getName())){
					form.setName(giftsDetails.getName());
				}else{
					form.setName("礼包_"+"未知");
				}
				
			}else{
				form.setName("礼包_"+info.getNumber());
			}
			parameterService.saveOrUpdate(giftsDetails, giftsHistory, Integer
					.valueOf(form.getPointNumber()),fig);
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
			if (list != null && list.size() > 0) {
				for (GiftsHistory eh : list) {
					List<NmsUser> oprations = (List<NmsUser>) nmsUserDao
							.queryByHql(HqlUserRole.getUserById, eh.getUserId());
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
            //判断上一批次的处理是否结束
			BatchNoEnum beforBatchNoEnum=getBeforBatchNoEnum(batchNo);
			int beforDay = CommonUtil.getDay();
			int beformouth=CommonUtil.getMounthNumber();
			int beforYear= CommonUtil.getYearNumber();
			int beforCountNumber=CommonUtil.getCountNumber();
			if(BatchNoEnum.FIRST.equals(batchNo)){
				if(beforDay!=31){
					if(beformouth==1){
						beformouth=12;
						beforYear=beforYear-1;
					}else{
						beformouth=beformouth-1;
					}
				}
			}
			beforCountNumber=getNextCountNumber(String.valueOf(beforYear),
					String.valueOf(beformouth));
			
			String beforGold=giftsDetailsService.getCountGoldAll(beforCountNumber,
					beforBatchNoEnum);
			if(!"0".equals(beforGold)){
				String dayFrom = "";
				String dayTo = "";
				if (BatchNoEnum.FIRST.equals(beforBatchNoEnum)) {
					dayFrom = "1(31)";
					dayTo = "10";
				} else if (BatchNoEnum.SECOND.equals(beforBatchNoEnum)) {
					dayFrom = "11";
					dayTo = "20";
				} else if (BatchNoEnum.THREE.equals(beforBatchNoEnum)) {
					dayFrom = "21";
					dayTo = "30";
				}
				int year = beforYear;
				int mouth = beformouth;
				int nextMoth = 0;
				int nextYear = 0;
				String nextDayFrom = "";
				String nextDayTo = "";
				BatchNoEnum nextBatchNo = null;
				int nextCountNumber = 0;

				int threeMoth = 0;
				int threeYear = 0;
				String threeDayFrom = "";
				String threeDayTo = "";
				BatchNoEnum threeBatchNo = null;
				int threeCountNumber = 0;

				if (BatchNoEnum.FIRST.equals(beforBatchNoEnum)) {
					nextBatchNo = BatchNoEnum.SECOND;
					nextYear = year;
					nextMoth = mouth;
					nextDayFrom = "11";
					nextDayTo = "20";

					threeBatchNo = BatchNoEnum.THREE;
					threeYear = year;
					threeMoth = mouth;
					threeDayFrom = "21";
					threeDayTo = "30";
				} else if (BatchNoEnum.SECOND.equals(beforBatchNoEnum)) {
					nextBatchNo = BatchNoEnum.THREE;
					nextYear = year;
					nextMoth = mouth;
					nextDayFrom = "21";
					nextDayTo = "30";

					threeBatchNo = BatchNoEnum.FIRST;
					if (mouth == 12) {
						threeYear = year + 1;
						threeMoth = 1;
					} else {
						threeYear = year;
						threeMoth = mouth + 1;
					}
					threeDayFrom = "1(31)";
					threeDayTo = "10";

				} else if (BatchNoEnum.THREE.equals(beforBatchNoEnum)) {
					countNumber=getNextCountNumber(String.valueOf(beforYear),
							String.valueOf(beformouth));
					nextBatchNo = BatchNoEnum.FIRST;
					if (mouth == 12) {
						nextYear = year + 1;
						nextMoth = 1;
					} else {
						nextYear = year;
						nextMoth = mouth + 1;
					}
					nextDayFrom = "1(31)";
					nextDayTo = "10";

					threeBatchNo = BatchNoEnum.SECOND;
					if (mouth == 12) {
						threeYear = year + 1;
						threeMoth = 1;
					} else {
						threeYear = year;
						threeMoth = mouth + 1;
					}
					threeDayFrom = "11";
					threeDayTo = "20";
				}
				nextCountNumber = getNextCountNumber(String.valueOf(nextYear),
						String.valueOf(nextMoth));
				threeCountNumber = getNextCountNumber(String.valueOf(threeYear),
						String.valueOf(threeMoth));

				// 当前积分发放总金额
				String firstGold = String.valueOf(mouth) + "月" + dayFrom + "-"
						+ dayTo + "应发放积分";
				// 下批次积分发放总金额
				String SecondGold = String.valueOf(nextMoth) + "月" + nextDayFrom
						+ "-" + nextDayTo + "应发放积分";
				;
				// 下下批次积分发放总金额
				String threeGold = String.valueOf(threeMoth) + "月" + threeDayFrom
						+ "-" + threeDayTo + "应发放积分";
				;
				firstGold = firstGold
						+ giftsDetailsService.getCountGoldAll(countNumber, beforBatchNoEnum);
				SecondGold = SecondGold
						+ giftsDetailsService.getCountGoldAll(nextCountNumber,
								nextBatchNo);
				threeGold = threeGold
						+ giftsDetailsService.getCountGoldAll(threeCountNumber,
								threeBatchNo);
				model.addAttribute("firstGold", firstGold);
				model.addAttribute("SecondGold", SecondGold);
				model.addAttribute("threeGold", threeGold);
				
			}else{
				String dayFrom = "";
				String dayTo = "";
				if (BatchNoEnum.FIRST.equals(batchNo)) {
					dayFrom = "1(31)";
					dayTo = "10";
				} else if (BatchNoEnum.SECOND.equals(batchNo)) {
					dayFrom = "11";
					dayTo = "20";
				} else if (BatchNoEnum.THREE.equals(batchNo)) {
					dayFrom = "21";
					dayTo = "30";
				}
				int year = CommonUtil.getYearNumber();
				int mouth = CommonUtil.getMounthNumber();
				int day = CommonUtil.getDay();
				int nextMoth = 0;
				int nextYear = 0;
				String nextDayFrom = "";
				String nextDayTo = "";
				BatchNoEnum nextBatchNo = null;
				int nextCountNumber = 0;

				int threeMoth = 0;
				int threeYear = 0;
				String threeDayFrom = "";
				String threeDayTo = "";
				BatchNoEnum threeBatchNo = null;
				int threeCountNumber = 0;

				if (BatchNoEnum.FIRST.equals(batchNo)) {
					if(day==31){
						if(mouth==12){
							mouth=1;
							year=year+1;
						}else{
							mouth=mouth+1;
						}
						countNumber=getNextCountNumber(String.valueOf(year),
								String.valueOf(mouth));
					}
					nextBatchNo = BatchNoEnum.SECOND;
					nextYear = year;
					nextMoth = mouth;
					nextDayFrom = "11";
					nextDayTo = "20";

					threeBatchNo = BatchNoEnum.THREE;
					threeYear = year;
					threeMoth = mouth;
					threeDayFrom = "21";
					threeDayTo = "30";
				} else if (BatchNoEnum.SECOND.equals(batchNo)) {
					nextBatchNo = BatchNoEnum.THREE;
					nextYear = year;
					nextMoth = mouth;
					nextDayFrom = "21";
					nextDayTo = "30";

					threeBatchNo = BatchNoEnum.FIRST;
					if (mouth == 12) {
						threeYear = year + 1;
						threeMoth = 1;
					} else {
						threeYear = year;
						threeMoth = mouth + 1;
					}
					threeDayFrom = "1(31)";
					threeDayTo = "10";

				} else if (BatchNoEnum.THREE.equals(batchNo)) {
					nextBatchNo = BatchNoEnum.FIRST;
					if (mouth == 12) {
						nextYear = year + 1;
						nextMoth = 1;
					} else {
						nextYear = year;
						nextMoth = mouth + 1;
					}
					nextDayFrom = "1(31)";
					nextDayTo = "10";

					threeBatchNo = BatchNoEnum.SECOND;
					if (mouth == 12) {
						threeYear = year + 1;
						threeMoth = 1;
					} else {
						threeYear = year;
						threeMoth = mouth + 1;
					}
					threeDayFrom = "11";
					threeDayTo = "20";
				}
				nextCountNumber = getNextCountNumber(String.valueOf(nextYear),
						String.valueOf(nextMoth));
				threeCountNumber = getNextCountNumber(String.valueOf(threeYear),
						String.valueOf(threeMoth));

				// 当前积分发放总金额
				String firstGold = String.valueOf(mouth) + "月" + dayFrom + "-"
						+ dayTo + "应发放积分";
				// 下批次积分发放总金额
				String SecondGold = String.valueOf(nextMoth) + "月" + nextDayFrom
						+ "-" + nextDayTo + "应发放积分";
				;
				// 下下批次积分发放总金额
				String threeGold = String.valueOf(threeMoth) + "月" + threeDayFrom
						+ "-" + threeDayTo + "应发放积分";
				;
				firstGold = firstGold
						+ giftsDetailsService.getCountGoldAll(countNumber, batchNo);
				SecondGold = SecondGold
						+ giftsDetailsService.getCountGoldAll(nextCountNumber,
								nextBatchNo);
				threeGold = threeGold
						+ giftsDetailsService.getCountGoldAll(threeCountNumber,
								threeBatchNo);
				model.addAttribute("firstGold", firstGold);
				model.addAttribute("SecondGold", SecondGold);
				model.addAttribute("threeGold", threeGold);
			}
		
			

			

			// List<SendGiftsDetails> giftsList =
			// giftsDetailsService.getGiftsDetailsList(countNumber, batchNo);
			// if(giftsList!=null&&giftsList.size()>0){
			// model.addAttribute("result", giftsList);
			// String goldAll = giftsDetailsService.getCountGoldAll(countNumber,
			// batchNo);
			// model.addAttribute("goldAll", goldAll);
			// }
			return "back/systeminfo/giftsSend";
		} catch (Exception e) {
			e.printStackTrace();
			return "back/systeminfo/giftsSend";
		}

	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getSendGiftsDetailsPage")
	@ResponseBody
	public Map getSendGiftsDetailsPage(HttpServletRequest request,Model model) {
		String number = request.getParameter("number");
		String iDisplayLength = request.getParameter("iDisplayLength");	
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		List<AccountDetails> list = integralManagerService
		.getFromgiftsHistoryPoints(Integer.parseInt(iDisplayLength),pageNumber);
        List<IntegralHistoryForm> result = new ArrayList<IntegralHistoryForm>();
        if (list != null && list.size() > 0) {
	        for (AccountDetails ad : list) {
		         IntegralHistoryForm form = new IntegralHistoryForm();
		         form.setCreateTime(ad.getCreateTime().toString());
		         form.setIncome(ad.getIncome().toString());
		         form.setPay(ad.getPay().toString());
		         form.setPointbalance(ad.getPointbalance().toString());
		         form.setUserNumber(ad.getUserNumber());
		         form.setProject("礼包释放积分");
		         result.add(form);
	}
}
		int iTotalRecords = integralManagerService.countFromgiftsHistoryPoints();
		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
	
	
	

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> send(@RequestBody GiftsForm form, Model model,
			HttpSession sesison) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
			String userNaemO = (String) logonUserMap.get("userName");
			if(userNaemO==null){
				result.setMsg("账号登陆异常，请重新登陆");
				result.setSuccess(true);
				return result;
			}
			Date logonTime=(Date) logonUserMap.get(FrameConfig.userLastHeartbeatTime);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String logonDateStr = format.format(logonTime);
			if(!String.valueOf(CommonUtil.getServerCountNumber()).equals(logonDateStr)){
				result.setMsg("账号登陆异常，请重新登陆");
				result.setSuccess(true);
				return result;
			}
			int countNumber = CommonUtil.getCountNumber();
			BatchNoEnum batchNo = CommonUtil.getBatchNo();
			 //判断上一批次的处理是否结束
			BatchNoEnum beforBatchNoEnum=getBeforBatchNoEnum(batchNo);
			int beforDay = CommonUtil.getDay();
			int beformouth=CommonUtil.getMounthNumber();
			int beforYear= CommonUtil.getYearNumber();
			int beforCountNumber=CommonUtil.getCountNumber();
			if(BatchNoEnum.FIRST.equals(batchNo)){
				if(beforDay!=31){
					if(beformouth==1){
						beformouth=12;
						beforYear=beforYear-1;
					}else{
						beformouth=beformouth-1;
					}
				}
			}
			beforCountNumber=getNextCountNumber(String.valueOf(beforYear),
					String.valueOf(beformouth));
			
			String beforGold=giftsDetailsService.getCountGoldAll(beforCountNumber,
					beforBatchNoEnum);
			
			if(!"0".equals(beforGold)){
				countNumber=beforCountNumber;
				batchNo=beforBatchNoEnum;
			}else{
				int day = CommonUtil.getDay();
				if(day==31){
					int year = CommonUtil.getYearNumber();
					int mouth = CommonUtil.getMounthNumber();
					if(mouth == 12){
						mouth=1;
						year=year+1;
					}else{
						mouth=mouth+1;
					}
					countNumber=getNextCountNumber(String.valueOf(year),
							String.valueOf(mouth));
				}
			}
			List<SendGiftsDetails> giftsList = giftsDetailsService
					.getGiftsDetailsList(countNumber, batchNo);
			if (giftsList != null && giftsList.size() > 0) {
				for (SendGiftsDetails ss : giftsList) {
					giftsDetailsService.senGold(ss);
				}
			} else {
				result.setMsg("没有积分可以释放");
				result.setSuccess(true);
				return result;
			}
			result.setMsg("积分释放成功");
			result.setSuccess(true);

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("积分释放失败，请重新登陆系统释放");
		}
		return result;
	}

	private int getNextCountNumber(String year, String month) {
		if (month.length() < 2) {
			month = "0" + month;
		}
		int result = Integer.valueOf(year + month);
		return result;

	}
	
	
	private BatchNoEnum getBeforBatchNoEnum(BatchNoEnum currentEnum){
		if(BatchNoEnum.FIRST.equals(currentEnum)){
			return BatchNoEnum.THREE;
		}else if(BatchNoEnum.SECOND.equals(currentEnum)){
			return BatchNoEnum.FIRST;
		}else if(BatchNoEnum.THREE.equals(currentEnum)){
			return BatchNoEnum.SECOND;
		}
		return null;
	}

}
