package com.member.controller.back;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.entity.Information;
import com.member.form.back.UserEditeForm;
import com.member.services.back.InstitutionService;

@Controller
@RequestMapping(value = "/BankRechargeController")
public class BankRechargeController {
	
	
	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;
	
	
	@RequestMapping(value = "/show")
	public String show(Model model){
		
		return "back/systeminfo/bankRecharge";

	}
	
	@RequestMapping(value = "/serch", method = RequestMethod.POST)
	public String serch(@RequestBody UserEditeForm form, Model model) {
		try {
			// List<NmsUser> users = (List<NmsUser>)nmsUserDao.queryByHql(
			// HqlUserRole.getUserByName, userNaemO);

			Information info = institutionService.getNnmuserByName(form
					.getNumber());
			if(info == null){
				form.setError("该用户不存在");
				model.addAttribute("bean", form);
				return "back/systeminfo/bankRecharge";
			}
			if (info.getIsActivate() == 0) {
				form.setError("该用户未激活");
				model.addAttribute("bean", form);
				return "back/systeminfo/bankRecharge";
			}
			UserEditeForm uform = new UserEditeForm();
			if (info != null) {
				uform.setNumber(info.getNumber());
				uform.setUserNumber(info.getNumber());
				uform.setUserName(info.getName());
				if(info.getCrmMoney()!=null&&!"".equals(info.getCrmMoney())){
					uform.setCrmMoney(info.getCrmMoney().toString());
				}
				model.addAttribute("bean", uform);
			}

			return "back/systeminfo/bankRecharge";
		} catch (Exception e) {
			e.printStackTrace();
			return "back/systeminfo/bankRecharge";
		}

	}
	

}
