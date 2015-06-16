package com.member.controller.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.member.beans.back.MenuBean;
import com.member.common.config.FrameConfig;
import com.member.entity.Information;
import com.member.entity.NmsUser;
import com.member.services.back.InstitutionService;
import com.member.services.back.NmUserService;
import com.member.util.FrameObjectUtil;

@Controller
@RequestMapping(value = "/MemberLoginController")
@SuppressWarnings("unchecked") 
public class MemberLoginController {
	
	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;
	
	@Resource(name = "NmUserServiceImpl")
	private NmUserService nmUserService;
	
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
			if (!info.getPassword().equals(password)) {
				request.getSession().setAttribute("loginReturnWarnMsg",  "密码错误");
				   mv.setViewName("redirect:/member_login.jsp");
				return mv;
			}
			  // 将用户状态该为已登陆
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
			    Map<String,Object> userMap= FrameObjectUtil.convertObjctToMap(info);
			    userMap.remove("userPassword");
				
				userMap.put(FrameConfig.userLastHeartbeatTime, new Date());
			    session.setAttribute("logonUser", userMap);
			    
			    mv.setViewName("front/index");
		              
			    //根据当前用户的编号，取得用户的角色和菜单信息。
			    model.addAttribute("menuList", getUserRoleMenu(info));
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
