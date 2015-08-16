package com.member.controller.back;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.member.beans.back.WithdrawalsDto;
import com.member.entity.Withdrawals;
import com.member.form.back.MemberSearchForm;
import com.member.form.back.WithdrawalsCheckForm;
import com.member.form.back.WithdrawalsSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.WithdrawalsService;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value = "/withdrawals")
public class WithdrawalsController {

	@Resource(name = "WithdrawalsServiceImpl")
	private WithdrawalsService withdrawalsService;

//	@Resource(name="MemberManageServiceImpl")
//	private MemberManageService memberManageService;
	
	@RequestMapping(value = "/showWithdrawalszRecord")
	public String showWithdrawalszRecord(HttpServletRequest request, Model model) {
		return "back/withdrawals/showwithdrawalsrecord";
	}

	@RequestMapping(value = "/getWithdrawalszRecordPage")
	@ResponseBody
	public Map getWithdrawalszRecordPage(HttpServletRequest request,Model model) {
		String number = request.getParameter("number");
		String iDisplayLength = request.getParameter("iDisplayLength");
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userId = logonUserMap.get("id");
		Object userName = logonUserMap.get("userName");
		int iTotalRecords = withdrawalsService.countWithdrawalsRecordByMemberNumberData(number, (Integer) userId,
				(String) userName);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<Withdrawals> result = withdrawalsService
				.getWithdrawalsRecordByMemberNumber(number, (Integer) userId,(String) userName,
						Integer.parseInt(iDisplayLength),pageNumber);

		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
//	@RequestMapping(value = "/searchWithdrawalszRecord", method = RequestMethod.POST)
//	public String searchActivationMember(
//			@RequestBody WithdrawalsSearchForm form,
//			HttpServletRequest request, Model model) {
//		Object logonUserO = request.getSession().getAttribute("logonUser");
//		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
//		Object userId = logonUserMap.get("id");
//		Object userName = logonUserMap.get("userName");
//		List<Withdrawals> result = withdrawalsService
//				.getWithdrawalsRecordByMemberNumber(form.getNumber(),
//						(Integer) userId, (String) userName);
//		model.addAttribute("result", result);
//		model.addAttribute("form", form);
//		return "back/withdrawals/showwithdrawalsrecord";
//	}
	
	@RequestMapping(value = "/dealWithdrawalszRecord")
	public String dealWithdrawalszRecord(HttpServletRequest request, Model model) {
		return "back/withdrawals/showNotDealwithdrawalsrecord";
	}
	
	@RequestMapping(value = "/getDealWithdrawalszRecordPage")
	@ResponseBody
	public Map getDealWithdrawalszRecordPage(HttpServletRequest request,Model model) {
		String number = request.getParameter("number");
		String iDisplayLength = request.getParameter("iDisplayLength");
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		int iTotalRecords = withdrawalsService.countNotDealWithdrawalsRecordData(number);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<Withdrawals> result = withdrawalsService.getNotDealWithdrawalsRecord(number,
				Integer.parseInt(iDisplayLength),
				pageNumber);
		
		model.addAttribute("result", refactWithdrawals(result));
		Map map = new HashMap();
		map.put("aaData", refactWithdrawals(result));
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
//	@RequestMapping(value = "/searchNotDealWithdrawalszRecord")
//	public String searchNotDealWithdrawalszRecord(@RequestBody WithdrawalsSearchForm form,HttpServletRequest request, Model model) {
//		List<Withdrawals> result = withdrawalsService
//				.getNotDealWithdrawalsRecord(form.getNumber());
//		model.addAttribute("result", result);
//		model.addAttribute("form", form);
//		return "back/withdrawals/showNotDealwithdrawalsrecord";
//	}
	
	@RequestMapping(value = "/agreewithdrawals")
	@ResponseBody
	public BaseResult<Void> agreewithdrawals(@RequestBody WithdrawalsCheckForm form,HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		
		BaseResult<Void> result = 
		withdrawalsService.agreewithdrawals(form.getId(),(String)userName);
		return result;
	}
	
	@RequestMapping(value = "/deletewithdrawals")
	@ResponseBody
	public BaseResult<Void> deletewithdrawals(@RequestBody WithdrawalsCheckForm form,HttpServletRequest request, Model model) {
		BaseResult<Void> result = withdrawalsService.deletewithdrawals(form.getId());
		return result;
	}
	
	@RequestMapping(value = "/showdisagreeWithdrawals")
	public String showdisagree(@RequestBody WithdrawalsCheckForm form, Model model) {
		model.addAttribute("form", form);
		return "back/withdrawals/disagreeWithdrawals";
	}
	
	@RequestMapping(value = "/showdisagreeWithdrawalsppp")
	public String showdisagreeWithdrawalsppp(@RequestBody WithdrawalsCheckForm form, Model model) {
		model.addAttribute("form", form);
		return "back/withdrawals/disagreeWithdrawalsppp";
	}
	
	@RequestMapping(value = "/disagreewithdrawals")
	@ResponseBody
	public BaseResult<Void> disagreewithdrawals(@RequestBody WithdrawalsCheckForm form,HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		
		BaseResult<Void> result = withdrawalsService.disAgreewithdrawals(
				form.getId(), (String) userName, form.getRefuseReason());
		return result;
	}
	
	
	
	@RequestMapping(value = "/disagreewithdrawalsppp")
	@ResponseBody
	public BaseResult<Void> disagreewithdrawalsppp(@RequestBody WithdrawalsCheckForm form,HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		
		BaseResult<Void> result = withdrawalsService.disAgreewithdrawalsppp(
				form.getId(), (String) userName, form.getRefuseReason());
		return result;
	}
	
	
	
	
	
	@RequestMapping(value = "/showWithDrawalsDetail",method = RequestMethod.POST)
	public String showMemberDetails(@RequestBody MemberSearchForm form,Model model){
		Withdrawals reslut = withdrawalsService.getWithdrawalsDetailById(form.getId());
		model.addAttribute("reslut",reslut);
		return "back/withdrawals/showWithDrawalsDetail";
	}
	
	@RequestMapping(value = "/showRefuseReason",method = RequestMethod.POST)
	public String showRefuseReason(@RequestBody MemberSearchForm form,Model model){
		Withdrawals result = withdrawalsService.getWithdrawalsDetailById(form.getId());
		model.addAttribute("result",result);
		return "back/withdrawals/showsrefusereason";
	}
	
	@RequestMapping(value = "/exportRecords",method = RequestMethod.POST)
	public String exportRecords(WithdrawalsSearchForm form,HttpServletResponse response) throws Exception{
		InputStream is = withdrawalsService.getExportRecords(form.getNumber());
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(("提现记录.xls").getBytes("utf-8"),"ISO8859-1"));
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
	
	@RequestMapping(value = "/exportNotDealRecords",method = RequestMethod.POST)
	public String exportNotDealRecords(WithdrawalsSearchForm form,HttpServletResponse response) throws Exception{
		InputStream is = withdrawalsService.getNotDealExportRecords(form.getNumber());
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(("未处理提现记录.xls").getBytes("utf-8"),"ISO8859-1"));
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
	
	
	
	@RequestMapping(value = "/agreewithdrawalswithnopoints")
	@ResponseBody
	public BaseResult<Void> agreewithdrawalswithnopoints(@RequestBody WithdrawalsCheckForm form,HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		
		BaseResult<Void> result = 
		withdrawalsService.agreewithdrawalswithnopoints(form.getId(),(String)userName);
		return result;
	}
	
	
	private List<WithdrawalsDto> refactWithdrawals(List<Withdrawals> chargeList){
		List<WithdrawalsDto> result = new ArrayList<WithdrawalsDto>();
		for(Withdrawals charge : chargeList){
			WithdrawalsDto dto = new WithdrawalsDto();
			String backInfo = charge.getWithdrawalsBackInfo();
			// 分开总的字符串
			String[] arr1 = backInfo.split(" > ");
			// 姓名
			String[] arr2 = arr1[0].split(":");
			// 银行名称
			String[] arr3 = arr1[1].split(":");
			// 银行账号
			String[] arr4 = arr1[2].split(":");
			// 银行地址
			String[] arr5 = arr1[3].split(":");
			//联系电话
			String[] arr6 = arr1[4].split(":");
			dto.setId(charge.getId());;
			
			/**会员登录ID */
			dto.setNumber(charge.getNumber());
			
			dto.setMemberId(charge.getMemberId());
			
			/**交易流水号*/
			dto.setTradeNo(charge.getTradeNo());
			
			/**交易日期*/
			dto.setTradeDate(charge.getTradeDate());
			
			/**充值金额*/
			dto.setTradeAmt(charge.getTradeAmt());
			
			/**实际得到金额*/
			dto.setRealGetAmt(charge.getRealGetAmt());
			
			/**余额*/
			dto.setBalanceAmt(charge.getBalanceAmt());

			/**手续费*/
			dto.setTradeFee(charge.getTradeFee());
			
			/**充值状态 0：未处理，1：已处理*/
			dto.setStatus(charge.getStatus());
			
			
			/**充值银行信息*/
			dto.setWithdrawalsBackInfo(charge.getWithdrawalsBackInfo());
			

			/**审核人*/
			dto.setUserName(charge.getUserName());
			
			/**拒绝理由*/
			dto.setRefuseReason(charge.getRefuseReason());
			
			/**会员姓名*/
			dto.setNumberName(arr2.length > 1 ? arr2[1] : "");
			
			/**会员联系电话*/
			dto.setNumberPhone(arr6.length > 1 ? arr6[1] : "");
			
			/**充值银行名称*/
			dto.setBankName(arr3.length > 1 ? arr3[1] : "");
			
			/**充值银行地址*/
			dto.setBankAddress(arr5.length > 1 ? arr5[1] : "");
			
			/**充值银行卡号*/
			dto.setBankCardNo(arr4.length > 1 ? arr4[1] : "");
			result.add(dto);
		}
		return result;
	}
	
	@RequestMapping(value = "/batchagreewithdrawals")
	@ResponseBody
	public BaseResult<Void> batchagreewithdrawals(@RequestBody WithdrawalsCheckForm form,HttpServletRequest request, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		String idArr = form.getIdArr();
		String[] ids = idArr.split(",");
		Boolean flg = withdrawalsService.batchAgreewithdrawals(ids,(String)userName);
		if(flg){
			result.setMsg("批量提现成功.");
		}else{
			result.setMsg("批量提现失败.");
		}
		result.setSuccess(true);
		return result;
	}
}
