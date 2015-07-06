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
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/ParameterController")
public class ParameterController {

	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model) {
		try {
			SystemParameter form = parameterService.getSystemParameter();
			if (form!=null){
				ParameterForm systemParameter = new ParameterForm();
//			systemParameter.setDayCount(form.getDayCount().toString());
				systemParameter.setGoldFlg(form.getGoldFlg());
				systemParameter.setGoldMax(CommonUtil.insertComma(form.getGoldMax().toString(),2));
				systemParameter.setGoldMin(CommonUtil.insertComma(form.getGoldMin().toString(),2));
				systemParameter.setGoldTake(CommonUtil.insertComma(form.getGoldTake().toString(),2));
				systemParameter.setScoreMax(CommonUtil.insertComma(form.getScoreMax().toString(),2));
				systemParameter.setScoreMin(CommonUtil.insertComma(form.getScoreMin().toString(),2));
				systemParameter.setScoreTake(CommonUtil.insertComma(form.getScoreTake().toString(),2));
				systemParameter.setGlbMin(CommonUtil.insertComma(form.getGlbMin().toString(),2));
				systemParameter.setGlbTake(CommonUtil.insertComma(form.getGlbTake().toString(),2));
				systemParameter.setScoreInTake(CommonUtil.insertComma(form.getScoreInTake().toString(),2));
				systemParameter.setScoreInMin(CommonUtil.insertComma(form.getScoreInMin().toString(),2));
				model.addAttribute("bean", systemParameter);
				return "back/systemset/systemParameter";
			}else{
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
//			systemParameter.setDayCount(Integer.valueOf(form.getDayCount()));
			systemParameter.setGoldFlg(form.getGoldFlg());
			systemParameter.setGoldMax(getValue(form.getGoldMax().replace(",", "")));
			systemParameter.setGoldMin(getValue(form.getGoldMin().replace(",", "")));
			systemParameter.setGoldTake(getValue(form.getGoldTake().replace(",", "")));
			systemParameter.setScoreMax(getValue(form.getScoreMax().replace(",", "")));
			systemParameter.setScoreMin(getValue(form.getScoreMin().replace(",", "")));
			systemParameter.setScoreTake(getValue(form.getScoreTake().replace(",", "")));
			systemParameter.setGlbMin(getValue(form.getGlbMin().replace(",", "")));
			systemParameter.setGlbTake(getValue(form.getGlbTake().replace(",", "")));
			systemParameter.setScoreInTake(getValue(form.getScoreInTake().replace(",", "")));
			systemParameter.setScoreInMin(getValue(form.getScoreInMin().replace(",", "")));

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
