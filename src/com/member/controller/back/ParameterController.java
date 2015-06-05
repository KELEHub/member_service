package com.member.controller.back;

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
	
	
	@RequestMapping(value = "/show",method = RequestMethod.POST)
	public String show(Model model)
			 {
		try {
			 SystemParameter systemParameter = parameterService.getSystemParameter();
			 if(systemParameter!=null){
				 model.addAttribute("bean", systemParameter);
				 return "back/parameter/systemParameter";
			}else{
				return "back/parameter/systemParameter";
			}
		
		} catch (Exception e) {
			  e.printStackTrace();
			  return "back/parameter/systemParameter";
		}

	}
	
	@RequestMapping(value = "/set",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody ParameterForm form,Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			SystemParameter systemParameter = parameterService
					.getSystemParameter();
			if(systemParameter == null){
				systemParameter = new SystemParameter();
				systemParameter.setSystemData("system");
				systemParameter.setCreateTime(new Date());
			}
			systemParameter.setDayCount(Integer.valueOf(form.getDayCount()));
			systemParameter.setGoldFlg(form.getGoldFlg());
			systemParameter.setGoldMax(Integer.valueOf(form.getGoldMax()));
			systemParameter.setGoldMin(Integer.valueOf(form.getGoldMin()));
			systemParameter.setGoldTake(Integer.valueOf(form.getGoldTake()));
			systemParameter.setScoreMax(Integer.valueOf(form.getScoreMax()));
			systemParameter.setScoreMin(Integer.valueOf(form.getScoreMin()));
			systemParameter.setScoreTake(Integer.valueOf(form.getScoreTake()));
			systemParameter.setGlbMin(Integer.valueOf(form.getGlbMin()));
			systemParameter.setGlbTake(Integer.valueOf(form.getGlbTake()));
			systemParameter.setScoreInTake(Integer.valueOf(form.getScoreInTake()));
			systemParameter.setScoreInMin(Integer.valueOf(form.getScoreInMin()));
			
			parameterService.setSystemParameter(systemParameter);
			result.setMsg("设置成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("设置失败");
			result.setSuccess(false);
			return result;
		}

	}
	

}
