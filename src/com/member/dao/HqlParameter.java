package com.member.dao;

public class HqlParameter {

public final static String getSystemParameter=
	"from SystemParameter t where t.systemData='system'";


public final static String getLimitDeclarationInfo=
	"from LimitDeclaration t where t.systemData='system'";



public final static String getThisWeekData=
	"from AccountDetails t where t.createTime>? and t.project=?";

public final static String getDtaALL=
	"from AccountDetails t where t.project=?";



}
