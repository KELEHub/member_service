package com.member.controller.back;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Charge;
import com.member.form.ChargeOperForm;
import com.member.form.back.MemberSearchForm;
import com.member.form.back.WithdrawalsSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.ChargeService;

@Controller
@RequestMapping(value = "/charge")
public class ChargeController {

	@Resource(name = "ChargeServiceImpl")
	private ChargeService chargeService;
	

	@RequestMapping(value = "/showChargeRecord")
	public String showChargeRecord(HttpServletRequest request, Model model) {
		return "back/charge/showchargerecord";
	}
	
	@RequestMapping(value = "/getChargezRecordPage")
	@ResponseBody
	public Map getChargezRecordPage(HttpServletRequest request,Model model) {
		String number = request.getParameter("number");
		String iDisplayLength = request.getParameter("iDisplayLength");
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		int iTotalRecords = chargeService.countChargeList(number);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<Charge> result = chargeService.getChargeList(number,
				Integer.parseInt(iDisplayLength),pageNumber);

		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
	
	

	
	@RequestMapping(value = "/dealChargeRecord")
	public String dealChargezRecord(HttpServletRequest request, Model model) {
		return "back/charge/shownotdealchargerecord";
	}
	
	@RequestMapping(value = "/getNoChargezRecordPage")
	@ResponseBody
	public Map getNoChargezRecordPage(HttpServletRequest request,Model model) {
		String number = request.getParameter("number");
		String iDisplayLength = request.getParameter("iDisplayLength");
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		int iTotalRecords = chargeService.countNoChargeList(number);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<Charge> result = chargeService.getNoChargeList(number,
				Integer.parseInt(iDisplayLength),pageNumber);

		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
	
	
	
	@RequestMapping(value = "/showdisagreecharge")
	public String showdisagree(@RequestBody ChargeOperForm form, Model model) {
		model.addAttribute("form", form);
		return "back/charge/disagreeCharge";
	}
	
	@RequestMapping(value = "/agreeCharge")
	@ResponseBody
	public BaseResult<Void> agreeCharge(@RequestBody ChargeOperForm form,HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		
		BaseResult<Void> result = 
				chargeService.agreeCharge(form,(String)userName);
		return result;
	}
	
	@RequestMapping(value = "/disagreeCharge")
	@ResponseBody
	public BaseResult<Void> disAgreeCharge(@RequestBody ChargeOperForm form,HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		BaseResult<Void> result = 
				chargeService.disAgreeCharge(form,(String)userName);
		return result;
	}
	
	@RequestMapping(value = "/showChargeDetail",method = RequestMethod.POST)
	public String showMemberDetails(@RequestBody MemberSearchForm form,Model model){
		Charge result = chargeService.getChargeDetailById(form.getId());
		model.addAttribute("result",result);
		return "back/charge/showsdetail";
	}
	
	@RequestMapping(value = "/showRefuseReason",method = RequestMethod.POST)
	public String showRefuseReason(@RequestBody MemberSearchForm form,Model model){
		Charge result = chargeService.getChargeDetailById(form.getId());
		model.addAttribute("result",result);
		return "back/charge/showsrefusereason";
	}
	
	@RequestMapping(value = "/exportRecords",method = RequestMethod.POST)
	public String exportRecords(WithdrawalsSearchForm form,HttpServletResponse response) throws Exception{
		InputStream is = chargeService.getExportRecords(form.getNumber());
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(("充值记录.xls").getBytes("utf-8"),"ISO8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}
}
