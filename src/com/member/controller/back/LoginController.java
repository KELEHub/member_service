package com.member.controller.back;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.member.common.config.FrameConfig;
import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.entity.NmsRolePermission;
import com.member.entity.NmsUser;
import com.member.helper.controller.BaseController;
import com.member.services.back.PermissionService;
import com.member.util.FrameObjectUtil;

 @Controller
 @RequestMapping(value = "/LoginController")
 @SuppressWarnings("unchecked") 
 public class LoginController extends BaseController {
	
	@Resource(name = "PermissionServiceImpl")
	public PermissionService permissionService;
	
	@Resource(name = "NmsUserDaoImpl")
	NmsUserDao nmsUserDao;
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, String userName, String password,String code,String loginLanguage)
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
			if(!randCode.equals(code)){// 验证码输入不正确
					request.getSession().setAttribute("loginReturnWarnMsg", "验证码输入错误");
					   mv.setViewName("redirect:/login.jsp");
					return mv;
			}
			List users = nmsUserDao.queryByHql(
					HqlUserRole.getUserByName, userName);
			String loginUril="/./login.jsp";
			
			  if(loginLanguage!=null&&!loginLanguage.toString().replaceAll(" ", "").equals(""))
			    {
				  loginUril=loginUril+"?locale="+loginLanguage;
			    }
			if (users == null || users.size() == 0) {
		
				request.getSession().setAttribute("loginReturnWarnMsg", "用户名错误");
				   mv.setViewName("redirect:/login.jsp");
				return mv;
			}
			NmsUser user = (NmsUser) users.get(0);
			if (!user.getUserPassword().equals(password)) {
				request.getSession().setAttribute("loginReturnWarnMsg",  "密码错误");
				   mv.setViewName("redirect:/login.jsp");
			    createUserLoginLog(request,user.getId(),true,false);

				return mv;
			}
			  // 将用户状态该为已登陆
			   user.setUserStatus(FrameConfig.userStatus_Online);
			   nmsUserDao.saveOrUpdate(user);
			   //将系统语�?��改为  用户 登录�?设置的语�?
			    Cookie cookie = new Cookie("userName", user.getUserName());
			    response.addCookie(cookie);
			    cookie = new Cookie("userPassword", user.getUserPassword());
			    response.addCookie(cookie);
			    HttpSession session = request.getSession();
			    if(session.getAttribute("loginReturnWarnMsg")!=null)
			    {
			    	session.removeAttribute("loginReturnWarnMsg");
			    }
			    Map<String,Object> userMap= FrameObjectUtil.convertObjctToMap(user);
			    userMap.remove("userPassword");
			    List<NmsRolePermission> rolePermissions = permissionService.getPermissionsByUserName(user.getUserName());
				for(NmsRolePermission rolePermission:rolePermissions)
				{
					//权限设置
					userMap.put("getGoldCode", rolePermission.getGetGoldCode());
				}
				
				userMap.put(FrameConfig.userLastHeartbeatTime, new Date());
			    session.setAttribute("logonUser", userMap);
			    
			    System.out.println(cookie.getValue());
			    mv.setViewName("redirect:/index.jsp");
		              
				//response.sendRedirect(basePath+"main.jsp");
			    createUserLoginLog(request,user.getId(),true,true);
			    return mv;
		} catch (Exception e) {
			  e.printStackTrace();
			  try {
					response.sendRedirect(basePath+"login.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;

	}
	
	private void createUserLoginLog(HttpServletRequest request,Integer userId,Boolean login,Boolean success) {
	  
		
	}

	@RequestMapping(value = "/logout")
	@ResponseBody
	protected Map<String,Object> logout(HttpServletRequest request,
			HttpServletResponse response, String userName, String password)
			 {
		Map<String,Object> returnMap = new  HashMap<String,Object>();
		returnMap.put("success", true);
		try {
		
			 Object userO = request.getSession().getAttribute("logonUser");
			 if(userO!=null)
			 {
				Map<String,Object> userMap = (Map<String, Object>) userO;
				Object userId=userMap.get("id");
				if(userId!=null)
				{
			     createUserLoginLog(request,Integer.parseInt(userId.toString()),false,true);
				}
			 }
			request.getSession().invalidate();
			
		
		} catch (Exception e) {
		     e.printStackTrace();
		     returnMap.put("success", false);	
		     returnMap.put("returnMsg", "session�?��异常.session为："+request.getSession());	
		}
		return returnMap;
             
	}
	
	
	


	
	@RequestMapping(value = "/userHeartbeat")
	@ResponseBody
	protected Map<String,Object> userHeartbeat(HttpServletRequest request)
			 {
		Map<String,Object> returnMap = new  HashMap<String,Object>();
		returnMap.put("success", true);
		try {
			Object logonUserO = request.getSession().getAttribute("logonUser");
			if (logonUserO != null) {
				Map<String,Object> logonUser=(Map)logonUserO;
				@SuppressWarnings("unused")
				Object userLastHeartbeatTimeOld=logonUser.get(FrameConfig.userLastHeartbeatTime);
				logonUser.put(FrameConfig.userLastHeartbeatTime, new Date());
				//log.debug("收到用户心跳,用户名为�?+logonUser.get("userName")+" 上次心跳时间�?+userLastHeartbeatTimeOld);

			}
		} catch (Exception e) {
		     e.printStackTrace();
		     returnMap.put("success", false);	
		 
		}
		return returnMap;
             
	}
}