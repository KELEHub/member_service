//package com.member.controller.back;

//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;

//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;

//import com.member.beans.back.enumData.BatchNoEnum;
//import com.member.beans.back.enumData.GiftEnum;
//import com.member.dao.NmsUserDao;
//import com.member.entity.ApplyserviceOld;
//import com.member.entity.GiftsDetails;
//import com.member.entity.Information;
//import com.member.entity.Institution;
//import com.member.entity.OldInformation;
//import com.member.entity.Participation;
//import com.member.entity.RepeatedMoneyStatistics;
//import com.member.entity.SendGiftsDetails;
//import com.member.services.back.InformationService;
//import com.member.services.back.InstitutionService;
//import com.member.util.CommonUtil;

//@Controller
//@RequestMapping(value = "/ExportController")
//@SuppressWarnings("unchecked") 
//public class ExportController {
	
	
//	@Resource(name = "NmsUserDaoImpl")
	//private NmsUserDao nmsUserDao;
	
	//@Resource(name = "InstitutionServiceImpl")
    //InstitutionService institutionService;
	
//	@Resource(name = "InformationServiceImpl")
  //  InformationService informationService;
	
	//@RequestMapping(value = "/baodan")
	//public ModelAndView baodan(HttpServletRequest request,
		//	HttpServletResponse response,Model model){
		//String sql = "from Information where to_char(activeDate, 'YYYY-MM')='2015-07'";
		//List<?> applyList = nmsUserDao.queryByHql(sql);
		//if(applyList!=null && applyList.size()>0){
		//	List<Information> serviceIPList = (List<Information>)applyList;
		//	for(Information info : serviceIPList){
	///			Information selfInfo = informationService.getInformationByNumber(info.getActivateNumber().trim());
		//		if(selfInfo!=null){
			///		if(!"company".equals(selfInfo.getLeaderServiceNumber().trim())){
				///		RepeatedMoneyStatistics moneyStatistics = new RepeatedMoneyStatistics();
					//	moneyStatistics.setCreateTime(info.getActiveDate());
						//moneyStatistics.setDateNumber(getDateNumber(info.getActiveDate()));
						//moneyStatistics.setDeclarationId(info.getId());
					//	moneyStatistics.setDeclarationNumber(info.getNumber());
			//			moneyStatistics.setDeclarationBenefitId(selfInfo.getLeaderServiceId());
				//		moneyStatistics.setDeclarationBenefitNumber(selfInfo.getLeaderServiceNumber().trim());
					//	moneyStatistics.setSerialNumber(201507);
						//moneyStatistics.setState(0);
					//	nmsUserDao.saveOrUpdate(moneyStatistics);
					//}
				//}else{
					//System.out.println("未找到的号码："+info.getNumber());
				//}
				
			//}
			
		//}
		
		//return null;
	//}
	
//	@RequestMapping(value = "/export")
//	public ModelAndView export(HttpServletRequest request,
//			HttpServletResponse response,Model model)
//			 {
//		ModelAndView mv=new ModelAndView();
//		String hql = "from OldInformation";
//		//个人信息表转换
//		List<OldInformation> OldInformationList = (List<OldInformation>)nmsUserDao.queryByHql(hql);
//		if(OldInformationList!=null && OldInformationList.size()>0){
//			for(OldInformation old : OldInformationList){
//				Information info = new Information();
//				String ssHql = "from ApplyserviceOld where a_applyi_number = ?";
//				List arguments = new ArrayList();
//				arguments.add(old.getI_number());
//				List<?> applyList = nmsUserDao.queryByHql(ssHql,arguments);
//				if(applyList!=null && applyList.size()>0){
//					List<ApplyserviceOld> serviceIPList = (List<ApplyserviceOld>)applyList;
//					info.setLeaderServiceNumber(serviceIPList.get(0).getA_submiti_number());
//				}
//				info.setNumber(old.getI_number());
//				info.setAge(old.getI_age());
//				info.setPassword(old.getI_password());
//				info.setProtectPassword(old.getI_protectpassword());
//				info.setPhoneNumber(old.getI_phonenumber());
//				info.setBankAddress(old.getI_bankaddress());
//				info.setBankCard(old.getI_bankcard());
//				info.setBankCity(old.getI_bankcity());
//				info.setBankCounty(old.getI_bankcounty());
//				info.setBankName(old.getI_bankname());
//				info.setBankProvince(old.getI_bankprovince().substring(0,old.getI_bankprovince().length()-1));
//				info.setLinkAddress(old.getI_linkaddress());
//				info.setLinkCity(old.getI_linkcity());
//				info.setLinkCounty(old.getI_linkcounty());
//				info.setLinkProvince(old.getI_linkprovince().substring(0,old.getI_linkprovince().length()-1));
//				info.setActiveDate(old.getI_checkeddate());
//				info.setRecommendCount(old.getI_recommendcount().intValue());
//				info.setRecommendNumber(old.getI_recommendnumber());
//				info.setActivateNumber(old.getI_servicename());
//				info.setRegisterDate(old.getI_registerdate());
//				info.setIsActivate(old.getI_isactivate());
//				info.setIdentity(old.getI_identity());
//				info.setIsService(old.getI_isservice());
//				info.setIsLock(old.getI_islock());
//				info.setCrmMoney(old.getI_crmmoney());
//				info.setCrmAccumulative(old.getI_crmaccumulative());
//				info.setShoppingMoney(old.getI_repeatedmoney());
//				info.setShoppingAccumulative(old.getI_repeatedaccumulative());
//				info.setRepeatedMoney(old.getI_bonusmoney());
//				info.setRepeatedAccumulative(old.getI_bonusaccumulative());
//				nmsUserDao.saveOrUpdate(info);
//			}
//		
//		}
//		//礼包导入
//		String participationHql = "from Participation";
//		List<Participation> OldParticipationList = (List<Participation>)nmsUserDao.queryByHql(participationHql);
//		if(OldParticipationList!=null && OldParticipationList.size()>0){
//			for(Participation participation : OldParticipationList){
//				if(participation.getP_count()<10 && participation.getP_state()!=1){
//					GiftsDetails gf = new GiftsDetails();
//					Information info =informationService.getInformationByNumber(participation.getP_inumber());
//					if(info == null){
//						System.out.println("会员号："+participation.getP_inumber());
//						continue;
//					}
//					gf.setUserId(info.getId());
////					gf.setChildId(info.getId());
//					gf.setNumber(info.getNumber());
//					String date =participation.getP_indate().toString().replace("-", "");
//					int month = Integer.valueOf(date.substring(4,6));
//					int day = Integer.valueOf(date.substring(6,8));
//					String dayStr = String.valueOf(day);
//					if(dayStr.length()<2){
//						dayStr="0"+dayStr;
//					}
//					int dayNumber = Integer.valueOf(String.valueOf(month)+dayStr);
//					if(dayNumber>410){
//						gf.setGiftEnum(GiftEnum.TEN);
//					}else{
//						gf.setGiftEnum(GiftEnum.FIVE);
//					}
//					
//					String dateNumber="";
//					if(day<=10){
//						dateNumber="01";
//					}else if(day<=20){
//						dateNumber="02";
//					}else if(day<=30){
//						dateNumber="03";
//					}
//					if(day==31){
//						dateNumber="01";
//					}
//					gf.setCountNumber(Integer.valueOf(date.substring(0,6)));
//					gf.setDateNumber(date.substring(0,6)+dateNumber);
//					BatchNoEnum batch = null;
//					if(day<=10){
//						batch= BatchNoEnum.FIRST;
//					}else if(day<=20){
//						batch= BatchNoEnum.SECOND;
//					}else if(day<=30){
//						batch= BatchNoEnum.THREE;
//					}
//					if(day==31){
//						batch= BatchNoEnum.FIRST;
//					}
//					gf.setBatchNo(batch);
//					gf.setPointNumber(participation.getP_count()+1);
//					gf.setName("礼包"+"_"+participation.getP_number());
//					gf.setCreateTime(participation.getP_indate());
//					nmsUserDao.saveOrUpdate(gf);
//					//详细礼包信息
//					int countGifts = 0;
//					if(gf.getGiftEnum().equals(GiftEnum.FIVE)){
//						countGifts=5;
//					}else{
//						countGifts=10;
//					}
//					Institution inst = institutionService.getInstitutionInfo();
//					for(int i =participation.getP_count()+1;i<=countGifts;i++){
//						SendGiftsDetails sg = new SendGiftsDetails();
//						sg.setUserId(info.getId());
////						sg.setChildId(info.getId());
//						sg.setNumber(info.getNumber());
//						sg.setGiftEnum(gf.getGiftEnum());
//						int countNumber = Integer.valueOf(date.substring(4,6))+i-1;
//						int realNumber = 0;
//						if(countNumber>12){
//							realNumber=(Integer.valueOf(date.substring(0,4))+1)*100+(i-(13-Integer.valueOf(date.substring(4,6))));
//						}else{
//							realNumber=Integer.valueOf(date.substring(0,6))+i-1;
//						}
//						sg.setCountNumber(realNumber);
//						sg.setDateNumber(date.substring(0,6)+dateNumber);
//						sg.setBatchNo(batch);
//						sg.setName("礼包"+"_"+participation.getP_number());
//						sg.setCreateTime(new Date());
//						sg.setPointNumber(i);
//						sg.setGoldMoney(getGoldMoney(inst,i,gf.getGiftEnum()));
//						sg.setGiftsDetailsId(gf.getId());
//						sg.setCreateTime(new Date());
//						nmsUserDao.saveOrUpdate(sg);
//					}
//				}
//			}
//		}
//		
//		
//		
//		
//		mv.setViewName("redirect:/login.jsp");
//		return mv;
//
//	}
//
//	
//	private Integer getGoldMoney(Institution inst,int countNumber,GiftEnum gift){
//		if(gift.equals(GiftEnum.FIVE)){
//			if(countNumber==1){
//				return inst.getPreaFirst();
//			}else if(countNumber==2){
//				return inst.getPreaSecond();
//			}else if(countNumber==3){
//				return inst.getPreaThree();
//			}else if(countNumber==4){
//				return inst.getPreaFour();
//			}else if(countNumber==5){
//				return inst.getPreaFive();
//			}
//		}else{
//			return 1000;
//		}
//		return 1000;
//	}
	
//	private String getDateNumber(Date date){
	//	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	//	String nowDateStr = format.format(date);
	//	int day =Integer.valueOf(nowDateStr.substring(6,8));
	//	String dateNumber="";
	//	if(day<=10){
	//		dateNumber="01";
	//	}else if(day<=20){
	//		dateNumber="02";
	//	}else if(day<=30){
	//		dateNumber="03";
	//	}
	//	if(day==31){
	//		dateNumber="01";
	//	}
	//	return nowDateStr.substring(0,6)+dateNumber;
		
	//}
	
//}
