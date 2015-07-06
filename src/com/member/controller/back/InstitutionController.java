package com.member.controller.back;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Institution;
import com.member.form.back.InstitutionForm;
import com.member.helper.BaseResult;
import com.member.services.back.InstitutionService;

@Controller
@RequestMapping(value = "/InstitutionController")
public class InstitutionController {

	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;

	@RequestMapping(value = "/show")
	public String show(Model model) {
		try {
			Institution institution = institutionService.getInstitutionInfo();
			if (institution != null) {
				model.addAttribute("bean", institution);
				return "back/systemset/institution";
			} else {
				return "back/systemset/institution";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "back/systemset/institution";
		}
	}

	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody InstitutionForm form, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			Institution institution = institutionService.getInstitutionInfo();
			if (institution == null) {
				institution = new Institution();
				institution.setSystemData("system");
				institution.setCreateTime(new Date());
			}
			institution.setPreaFirst(Integer.valueOf(form.getPreaFirst()));
			institution.setPreaSecond(Integer.valueOf(form.getPreaSecond()));
			institution.setPreaThree(Integer.valueOf(form.getPreaThree()));
			institution.setPreaFour(Integer.valueOf(form.getPreaFour()));
			institution.setPreaFive(Integer.valueOf(form.getPreaFive()));
			institution
					.setRegisterGold(Integer.valueOf(form.getRegisterGold()));
			// institution.setPreCount(Integer.valueOf(form.getPreCount()));
			// institution.setServiceCash(Integer.valueOf(form.getServiceCash()));
			institutionService.setInstitution(institution);
			result.setMsg("设置成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("设置失败,数值设置不正确");
			result.setSuccess(true);
			return result;
		}
	}

}
