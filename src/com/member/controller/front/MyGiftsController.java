package com.member.controller.front;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.beans.back.enumData.GiftEnum;
import com.member.entity.GiftsDetails;
import com.member.entity.Information;
import com.member.form.back.GiftsForm;
import com.member.services.back.InformationService;
import com.member.services.back.ParameterService;

@Controller
@RequestMapping(value = "/MyGiftsController")
public class MyGiftsController {
	
	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model, HttpSession sesison) {
		try {
			
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			String userNaemO =(String) logonUserMap.get("username");
			List<GiftsDetails> list = parameterService
			.getGiftsDetailsByNumber(userNaemO);
			List<GiftsForm> gfList = new ArrayList<GiftsForm>();
			if(list!=null && list.size()>0){
				for(GiftsDetails gd:list){
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
						if(gd.getName()!=null && !"".equals(gd.getName())){
							form.setName(gd.getName());
						}else{
							form.setName("礼包_"+"未知");
						}
						
					}else{
						form.setName("礼包_"+info.getNumber());
					}
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
				return "front/mygifts/mygifts";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/mygifts/mygifts";
		}

	}

}
