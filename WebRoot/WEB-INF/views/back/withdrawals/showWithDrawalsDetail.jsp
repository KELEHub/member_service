<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="showWithDrawalsDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>${member.name}的提现账户资料.</h3>
	</div>
	<div class="modal-body">
<!-- 		<div class="widget-box"> -->
<!-- 			<div class="widget-title"> -->
<!-- 				<span class="icon"> <i class="icon-th"></i> -->
<!-- 				</span> -->
<!-- 				<h5> -->
<!-- 					<font style="color: red">提现会员信息</font> -->
<!-- 				</h5> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="widget-box">
			<div class="widget-content nopadding">
			<font style="color: red;font-size: 20px;">
			提现会员信息:${member.name} » ${member.bankName} » ${member.bankCard} » ${member.bankProvince}${member.bankCity}${member.bankCounty}${member.bankAddress} » ${member.phoneNumber}
			</font>
			</div>
		</div>
	</div>
	<div class="modal-footer">
<!-- 		<button data-dismiss="modal" class="save" type="button" onclick="saveMemberDetail('saveMemberDetailForm')">确认修改</button> -->
	</div>
</div>