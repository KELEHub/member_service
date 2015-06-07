package com.member.controller.back;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.entity.LimitDeclaration;
import com.member.form.back.DeclarationForm;
import com.member.helper.BaseResult;
import com.member.services.back.LimitDeclarationService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/LimitDeclarationController")
public class LimitDeclarationController {
	
	@Resource(name = "LimitDeclarationServiceImpl")
	public LimitDeclarationService limitDeclarationService;
	
	
	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model) {
		try {

			
			int week = CommonUtil.getWeek();
			Date bfore = getDate(week);
			LimitDeclaration limitDeclaration = limitDeclarationService
					.getLimitDeclarationInfo();
		   int count= limitDeclarationService.getCountThisWeek(bfore,ProjectEnum.servicepointsforone);
		   int countAll = limitDeclarationService.getCountAll(ProjectEnum.servicepointsforone);
			if (limitDeclaration != null) {
				float num= (float)count/limitDeclaration.getMaxDeclaration();
				float result = num*100;
				DeclarationForm form = new DeclarationForm();
				NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		        format.setMinimumFractionDigits(2);// 设置小数位
		        form.setDeclarationIndex(format.format(result/100.00));
				form.setMaxDeclaration(String.valueOf(limitDeclaration.getMaxDeclaration()));
				form.setDeclarationAll(String.valueOf(countAll));
				model.addAttribute("bean", form);
				return "back/systemset/limitDeclaration";
			} else {
				return "back/systemset/limitDeclaration";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "back/systemset/limitDeclaration";
		}

	}
	
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody DeclarationForm form, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			LimitDeclaration limitDeclaration = limitDeclarationService
			.getLimitDeclarationInfo();
			if (limitDeclaration == null) {
				limitDeclaration = new LimitDeclaration();
				limitDeclaration.setSystemData("system");
				limitDeclaration.setCreateTime(new Date());
			}
			limitDeclaration.setMaxDeclaration(Integer.valueOf(form.getMaxDeclaration()));

			limitDeclarationService.saveOrUpdate(limitDeclaration);
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
	
	
	
	
	private Date getDate(int week){
		if(week==1){
			week=8;
		}
		int day = 1-week;
		Date dNow = new Date();   //褰撳墠鏃堕棿
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //寰楀埌鏃ュ巻
		calendar.setTime(dNow);//鎶婂綋鍓嶆椂闂磋祴缁欐棩鍘�
		calendar.add(Calendar.DAY_OF_MONTH, day);  //璁剧疆涓哄墠涓�ぉ
		dBefore = calendar.getTime();   //寰楀埌鍓嶄竴澶╃殑鏃堕棿
		return dBefore;
	}
	

}
