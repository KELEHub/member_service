package com.member.controller.back;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.SystemParameter;
import com.member.form.back.ParameterForm;
import com.member.helper.BaseResult;
import com.member.services.back.ParameterService;

@Controller
@RequestMapping(value = "/ParameterController")
public class ParameterController {

	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model) {
		try {
			SystemParameter systemParameter = parameterService
					.getSystemParameter();
			if (systemParameter != null) {
				model.addAttribute("bean", systemParameter);
				return "back/systemset/systemParameter";
			} else {
				return "back/systemset/systemParameter";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "back/systemset/systemParameter";
		}

	}

	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody ParameterForm form, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			SystemParameter systemParameter = parameterService
					.getSystemParameter();
			if (systemParameter == null) {
				systemParameter = new SystemParameter();
				systemParameter.setSystemData("system");
				systemParameter.setCreateTime(new Date());
			}
			systemParameter.setDayCount(Integer.valueOf(form.getDayCount()));
			systemParameter.setGoldFlg(form.getGoldFlg());
			systemParameter.setGoldMax(getValue(form.getGoldMax()));
			systemParameter.setGoldMin(getValue(form.getGoldMin()));
			systemParameter.setGoldTake(getValue(form.getGoldTake()));
			systemParameter.setScoreMax(getValue(form.getScoreMax()));
			systemParameter.setScoreMin(getValue(form.getScoreMin()));
			systemParameter.setScoreTake(getValue(form.getScoreTake()));
			systemParameter.setGlbMin(getValue(form.getGlbMin()));
			systemParameter.setGlbTake(getValue(form.getGlbTake()));
			systemParameter.setScoreInTake(getValue(form.getScoreInTake()));
			systemParameter.setScoreInMin(getValue(form.getScoreInMin()));

			parameterService.setSystemParameter(systemParameter);
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
	
	private BigDecimal getValue(String s){
		 	BigDecimal b = new BigDecimal(s); 
		 	return b.setScale(2, BigDecimal.ROUND_DOWN);
	}

}
