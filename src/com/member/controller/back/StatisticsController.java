package com.member.controller.back;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.form.back.StatisticsForm;
import com.member.services.front.AccountDetailsService;

@Controller
@RequestMapping(value = "/StatisticsController")
public class StatisticsController {

	@Resource(name = "AccountDetailsServiceImpl")
	AccountDetailsService accountDetailsService;

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model) {
		try {

			List<StatisticsForm> list = accountDetailsService
					.getAccountDetailsGroupBy(ProjectEnum.servicepointsforone,
							ProjectEnum.recharge, ProjectEnum.fromgifts,
							ProjectEnum.pointcash);
			
			Map<String, Integer> countAll=accountDetailsService.getCountAll(ProjectEnum.servicepointsforone,
					ProjectEnum.recharge, ProjectEnum.fromgifts,
					ProjectEnum.pointcash);
			
			
			int countServiceNumber = accountDetailsService.countService();
			model.addAttribute("countBill", countServiceNumber);
			model.addAttribute("countRecharge", countAll.get("countRecharge"));
			model.addAttribute("countFromgifts", countAll.get("countFromgifts"));
			model.addAttribute("countPointcash", countAll.get("countPointcash"));
			model.addAttribute("countXfMoney", countAll.get("countXfMoney"));
			model.addAttribute("result", list);
			return "back/systeminfo/statistics";

		} catch (Exception e) {
			e.printStackTrace();
			return "back/systeminfo/statistics";
		}

	}

}
