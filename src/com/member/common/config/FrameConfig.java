
package com.member.common.config;

/**
 * 配置文件的配置 从这取
 * @author zj
 *
 */
public class FrameConfig {
	
public final  static String aa="aa";
public final  static String defaultUserName="DefaultUserName";
public final  static String defaultUserPassword="DefaultUserPassword";
public final  static String defaultRoleName="DefaultRoleName";
/**
 * 用户挂机（不进行任何与后台交互的操作）的最长超时时间，超过此时间需要重新登录.  单位分钟
 */
public   static int userLeaveMaxTime=30;

/**
 *  用户心跳超时，必须大于心跳间隔。单位秒
 */
public   static int userHeartbeatTimeout=4000;

/**权限的更新间隔,单位分钟 */
public  final static int initPermissionInterval = 1;

/**  用户最近一次请求的时间(排除心跳) */
public final static String userLastRequestTime="userLastRequestTime";

/**
 * 后台检查用户心跳时间间隔.单位秒
 */
public   static int checkSessionInterval=60;
 

/** frame 模块初始化*/
/*** 用户在线 */
public final  static String userStatus_Online="UserStatus_Online";
/***用户离线 */
public final  static String userStatus_Offline="UserStatus_Offline";
/**  用户最近一次心跳*/
public final static String userLastHeartbeatTime="userLastHeartbeatTime";


	
}

