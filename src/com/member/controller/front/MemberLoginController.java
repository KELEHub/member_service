package com.member.controller.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.member.beans.back.MenuBean;
import com.member.common.config.FrameConfig;
import com.member.entity.BankService;
import com.member.entity.Information;
import com.member.entity.Notice;
import com.member.form.front.MemberOperForm;
import com.member.form.front.MemberUpdateForm;
import com.member.helper.BaseResult;
import com.member.services.back.InformationService;
import com.member.services.back.InstitutionService;
import com.member.services.back.NmUserService;
import com.member.services.front.LatestNewsService;

@Controller
@RequestMapping(value = "/MemberLoginController")
@SuppressWarnings("unchecked") 
public class MemberLoginController {
	
	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;
	
	@Resource(name = "NmUserServiceImpl")
	private NmUserService nmUserService;
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@Resource(name = "LatestNewsServiceImpl")
	public LatestNewsService latestNewsService;
	
	@RequestMapping(value = "/memberlogin")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, String userName, String password,String code,String loginLanguage,Model model)
			 {
		String randCode = (String)request.getSession().getAttribute("randCode");
		// 带参数重定向  view.setViewName("redirect:/index{id}");
		String basePath="/";
		//System.out.println(a);
		ModelAndView mv=new ModelAndView();
	    if(loginLanguage!=null&&!loginLanguage.toString().replaceAll(" ", "").equals(""))
	    {
	       mv.addObject("locale",loginLanguage); 
	    }
	    Object loginLanguageO = request.getSession().getAttribute("loginLanguage");
	    if(loginLanguageO!=null)
	    {
	    	loginLanguage=loginLanguageO.toString();
	    }
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String path = request.getContextPath();
			 basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			 Information info = institutionService.getNnmuserByName(userName);

			if (info == null) {
		
				request.getSession().setAttribute("loginReturnWarnMsg", "用户名错误");
				   mv.setViewName("redirect:/member_login.jsp");
				return mv;
			}
			
			if (info.getIsActivate() == 0) {
				
				request.getSession().setAttribute("loginReturnWarnMsg", "该账户未激活");
				   mv.setViewName("redirect:/member_login.jsp");
				return mv;
			}
           if (info.getIsActivate() == 2) {
				
				request.getSession().setAttribute("loginReturnWarnMsg", "该账户已被删除");
				   mv.setViewName("redirect:/member_login.jsp");
				return mv;
			}
            if (info.getIsLock() == 1) {
				
				request.getSession().setAttribute("loginReturnWarnMsg", "该账户已被锁定");
				   mv.setViewName("redirect:/member_login.jsp");
				return mv;
			}
			
			
			if (!info.getPassword().equals(password)) {
				request.getSession().setAttribute("loginReturnWarnMsg",  "密码错误");
				   mv.setViewName("redirect:/member_login.jsp");
				return mv;
			}
			  // 将用户状态该为已登录
//			   user.setUserStatus(FrameConfig.userStatus_Online);
//			   nmsUserDao.saveOrUpdate(user);
			   //将系统语�?��改为  用户 登录�?设置的语�?
			    Cookie cookie = new Cookie("userName",userName);
			    response.addCookie(cookie);
			    cookie = new Cookie("userPassword", password);
			    response.addCookie(cookie);
			    HttpSession session = request.getSession();
			    if(session.getAttribute("loginReturnWarnMsg")!=null)
			    {
			    	session.removeAttribute("loginReturnWarnMsg");
			    }
			    Map<String,Object> userMap= new HashMap<String, Object>();
			    userMap.remove("userPassword");
			    userMap.put("username", userName);
			    userMap.put("userId", info.getId());
				userMap.put(FrameConfig.userLastHeartbeatTime, new Date());
			    session.setAttribute("logonUser", userMap);
			    
			    mv.setViewName("front/index");
		              
			    //根据当前用户的编号，取得用户的角色和菜单信息。
			    model.addAttribute("menuList", getUserRoleMenu(info));
			    
			    List<Notice> result = latestNewsService.getLatestNewsList();
			    List<Notice> list = new ArrayList<Notice>();
			    int count =0;
			    if(result!=null && result.size()>0){
			    	if(result.size()>10){
			    		count=10;
			    	}else{
			    		count=result.size();
			    	}
			    }
			    for(int i=0;i<count;i++){
			    	Notice nc = result.get(i);
			    	list.add(nc);
			    }
				model.addAttribute("result", list);
			    model.addAttribute("username", userName);
			    return mv;
		} catch (Exception e) {
			  e.printStackTrace();
			  try {
					response.sendRedirect(basePath+"member_login.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;

	}
	
	@RequestMapping(value = "/showmemberinfo",method = RequestMethod.POST)
	public String showmemberinfo(HttpServletRequest request,Model model){
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userId = logonUserMap.get("userId");
		Information result = institutionService.getInformationById((Integer)userId);
		
		// 取得银行卡的信息
		List<BankService> bankServiceList = institutionService
				.getBankServiceInfo();
		model.addAttribute("bank", bankServiceList);
		model.addAttribute("result", result);
		return "front/memberinfo/showmemberinfo";
	}
	
	@RequestMapping(value = "/changepassword",method = RequestMethod.POST)
	public String showChangePassword(HttpServletRequest request,Model model){
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userId = logonUserMap.get("userId");
		model.addAttribute("userid",userId);
		return "front/memberinfo/showchangepasswordpage";
	}
	@RequestMapping(value = "/change1password",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> change1password(@RequestBody MemberOperForm form,Model model,HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		Information info =  informationService.getInformationByNumber(userNaemO);
		BaseResult<Void> result = new BaseResult<Void>();
		if(info!=null){
			if(info.getProtectPassword().equals(form.getSecond1Password())){
				institutionService.update1Password(form);
				result.setMsg("修改登录密码成功.");
				result.setSuccess(true);
				return result;
			}
		}
		result.setMsg("二级密码不正确");
		result.setSuccess(false);
		return result;
		
		
	}
	
	@RequestMapping(value = "/change2password",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> change2password(@RequestBody MemberOperForm form,Model model,HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		Information info =  informationService.getInformationByNumber(userNaemO);
		BaseResult<Void> result = new BaseResult<Void>();
		if(info != null){
			if(info.getProtectPassword().equals(form.getSecond2Password())){
				institutionService.update2Password(form);
				result.setMsg("修改二级密码成功.");
				result.setSuccess(true);
				return result;
			}
		}
		result.setMsg("原二级密码不正确");
		result.setSuccess(false);
		return result;
		
	}
	
	@RequestMapping(value = "/updateMemberInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> updateMemberInfo(@RequestBody MemberUpdateForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		Information info = informationService.getInformationByNumber(form.getNumber());
		if(!info.getBankCard().equals(form.getBankCard())){
			if(informationService.countBankCard(form.getBankCard())>=2){
				result.setMsg("系统已存在两张相同的银行卡，请换卡");
				result.setSuccess(true);
				return result;
			}
		}
		institutionService.updateMemberInfo(form);
		result.setMsg("更新个人信息成功.");
		result.setSuccess(true);
		return result;
		
	}
	
	private List<MenuBean> getUserRoleMenu(Information user){
		List<MenuBean> menuList = null;
		 if(user.getIsService()==1){
			 menuList = nmUserService.getFrontMenuAll();
		   }else{
			   menuList = nmUserService.getFrontMenuBySome();
		   }
		 
		List<MenuBean> menuBeanList = new ArrayList<MenuBean>();
		for(int i=0;i<menuList.size();i++){
			MenuBean mm = menuList.get(i);
			if(mm.getPid()!=0){
				//
			}else{
				//当前菜单是父菜单的时候
				MenuBean pMenuBean = new MenuBean();
				pMenuBean.setId(mm.getId());
				pMenuBean.setMenunm(mm.getMenunm());
				pMenuBean.setPid(mm.getPid());
				pMenuBean.setMenuurl(mm.getMenuurl());
				getChildMenu(menuList, mm.getId(), pMenuBean);
				menuBeanList.add(pMenuBean);
			}
		}
		return menuBeanList;
	}
	
	private void getChildMenu(List<MenuBean> menuList,Integer parMenuId,MenuBean pMenuBean){
		List<MenuBean> childMenu = new ArrayList<MenuBean>();
		for(MenuBean mm : menuList){
			if(mm.getPid().equals(parMenuId)){//当前菜单是指定菜单的子菜单
				MenuBean cMenuBean = new MenuBean();
				cMenuBean.setId(mm.getId());
				cMenuBean.setMenunm(mm.getMenunm());
				cMenuBean.setPid(mm.getPid());
				cMenuBean.setMenuurl(mm.getMenuurl());
				childMenu.add(cMenuBean);
				pMenuBean.setChildMenu(childMenu);
				getChildMenu(menuList, mm.getId(),cMenuBean);
			}
		}
	}
	
	@RequestMapping(value = "/logout")
	@ResponseBody
	protected ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response, String userName, String password)
			 {
		       ModelAndView mv=new ModelAndView();
		try {
		
			mv.setViewName("redirect:/member_login.jsp");
		
		} catch (Exception e) {
		     e.printStackTrace();
		     mv.setViewName("redirect:/member_login.jsp");
		}
		return mv;
             
	}

}
