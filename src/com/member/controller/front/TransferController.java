package com.member.controller.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Information;
import com.member.form.front.Transform;
import com.member.helper.BaseResult;
import com.member.services.back.InformationService;

@Controller
@RequestMapping(value = "/TransferController")
public class TransferController {
	
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model) {
		try {
				return "front/transfer/transfer";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/transfer/transfer";
		}

	}
	
	@RequestMapping(value = "/transferData", method = RequestMethod.POST)
	public String transferData(@RequestBody Transform form,Model model) {
		try {
				return "front/transfer/transfer";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/transfer/transfer";
		}

	}

	
	@RequestMapping(value = "/select",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> select(@RequestBody Transform form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		
		Information ad = informationService.getInformationByName(form.getToUserNumber());
		if(ad != null){
			result.setMsg(ad.getName());
		}else{
			result.setMsg("此用户不存在");
		}
		result.setSuccess(true);
		return result;
	}
	

}
