package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.BankService;
import com.member.form.back.BankForm;
import com.member.helper.BaseResult;
import com.member.services.back.InstitutionService;


@Controller
@RequestMapping(value = "/BankController")
public class BankController {
	
	
	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;
	
	@RequestMapping(value = "/show")
	public String show(Model model)
			 {
		try {
			 List<BankService> bankServiceList = institutionService.getBankServiceInfo();
			 if (bankServiceList != null) {
					model.addAttribute("result", bankServiceList);
					return "back/systeminfo/bankService";
				} else {
					return "back/systeminfo/bankService";
				}

		} catch (Exception e) {
			  e.printStackTrace();
				return "back/systeminfo/bankService";
		}

	}
	
	
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody BankForm form, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {

			if (form.getBankName() == null
					|| "".equals(form.getBankName())) {
				result.setMsg("银行名字不能为空");
				result.setSuccess(true);
				return result;
			}
			BankService bs = institutionService.getBankServiceInfoByName(form.getBankName());
			if(bs != null){
				result.setMsg("该银行已经存在");
				result.setSuccess(true);
				return result;
			}
			BankService bankServer = new BankService();
			bankServer.setBankName(form.getBankName());
			institutionService.savaOrUpdate(bankServer);
			result.setMsg("添加银行成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("添加银行失败，请重新尝试");
			result.setSuccess(true);
			return result;
		}

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> delete(@RequestBody BankForm form, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			BankService bs = institutionService.getBankServiceInfoByName(form.getBankName());
			if(bs!=null){
				institutionService.deleteData(bs);
			}
			result.setMsg("删除用户成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("删除用户失败，请重新尝试");
			result.setSuccess(true);
			return result;
		}

	}
	
	

}
