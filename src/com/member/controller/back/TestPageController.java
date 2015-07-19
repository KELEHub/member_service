package com.member.controller.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.TestPageEntity;
import com.member.services.back.TestPageService;


@Controller
@RequestMapping(value = "/testPage")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TestPageController {

	@Resource(name = "TestPageServiceImpl")
	private TestPageService testPageService;
	
	@RequestMapping(value = "/initPage")
	public String showChargezRecord(HttpServletRequest request, Model model) {
		return "back/testpage/initPage";
	}
	
	@RequestMapping(value = "/getPage")
	@ResponseBody
	public Map getPage(HttpServletRequest request,Model model) {
		//limit表示一次查多少条，offset表示从第几条开始查。
//		,String sEcho,String iDisplayStart,String iDisplayLength,
//		  *iDisplayStart 查询开始的索引； *iDisplayLenth分页查询中，第几页的记录数
		String customerPar = request.getParameter("customerPar");
		String iDisplayLength = request.getParameter("iDisplayLength");
		String iDisplayStart = request.getParameter("iDisplayStart");
//		int pageNumber = iDisplayStart/iDisplayLength+1。
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		List<TestPageEntity> result = testPageService.getPageData(customerPar,
				Integer.parseInt(iDisplayLength),
				pageNumber);
		int iTotalRecords = testPageService.countData(customerPar);
		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
//		return "back/testpage/initPage";
	}
}
