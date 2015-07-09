<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	initMemberInfoUpdateSelect();
</script>
<div id="showServiceDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>${member.number}的账户资料.</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"> <i class="icon-th"></i>
				</span>
				<h5>
					会员基本信息 <font style="color: red">注:[以下信息可根据需要进行修改]</font>
				</h5>
			</div>
			<div class="widget-content nopadding">
			<form action="/ServiceManagerController/saveServiceInfoDetail.do" id="saveServiceDetailForm">
			    <input type="hidden" name="id" value="${member.id}">
				<table class="table table-bordered table-striped">
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">会员账号:</td>
						<td><input type="text" name="number" readonly="readonly"  value="${member.number }"></td>
						<td>姓名:</td>
						<td><input type="text" name="name"  value="${member.name }"></td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">身份证号:</td>
						<td><input type="text" name="identity" value="${member.identity }"></td>
						<td>电话号码:</td>
						<td><input type="text" name="phoneNumber"  value="${member.phoneNumber }"></td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">电子邮箱:</td>
						<td><input type="text" name="email" value="${member.email }"></td>
						<td>开户银行:</td>
						<td><input type="text" name="bankName"  value="${member.bankName }"></td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">银行账号:</td>
						<td><input type="text" name="bankCard" value="${member.bankCard }"></td>
						<td>邮政编码:</td>
						<td><input type="text" name="postNumber"  value="${member.postNumber }"></td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">分行名称:</td>
						<td colspan="3">
						<div class="controls controls-row">
						        <input type="hidden" id="bankProvince" value="${member.bankProvince}">
						        <input type="hidden" id="bankCity" value="${member.bankCity}">
						        <input type="hidden" id="bankCounty" value="${member.bankCounty}">
								<select id="select_province" name="bankProvince"></select> 
								<select id="select_city"  name="bankCity"></select>
								<select id="select_area"  name="bankCounty"></select>
								<input type="text" name="bankAddress" value="${member.bankAddress }">
						</div>
						</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">联系地址:</td>
						<td colspan="3">
						<div class="controls controls-row">
						        <input type="hidden" id="linkProvince" value="${member.linkProvince}">
						        <input type="hidden" id="linkCity" value="${member.linkCity}">
						        <input type="hidden" id="linkCounty" value="${member.linkCounty}">
								<select id="select_province1" name="linkProvince"></select>
							    <select id="select_city1" name="linkCity"></select>
								<select id="select_area1" name="linkCounty"></select>
								<input type="text" name="linkAddress" value="${member.linkAddress }">
						</div>
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"> <i class="icon-th"></i>
				</span>
				<h5>
					会员账户信息 <font style="color: red">注:[以下信息仅供查看]</font>
				</h5>
			</div>
			<div class="widget-content nopadding">
				<table class="table table-bordered table-striped">
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">推荐人:</td>
						<td width="70">${member.recommendNumber }</td>
						<td width="120" style="text-align: right;">报单中心:</td>
						<td width="70">${member.activateNumber }</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">是否锁定:</td>
						<td><c:if test="${member.isLock eq 0}">未锁定</c:if> <c:if
								test="${member.isLock eq 1}">锁定</c:if></td>
						<td style="text-align: right;">推荐人数:</td>
						<td>${member.recommendCount }</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">葛粮币:</td>
						<td>${member.crmMoney }</td>
						<td style="text-align: right;">葛粮币累计:</td>
						<td class="center">${member.crmAccumulative }</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">分红:</td>
						<td>${member.shoppingMoney }</td>
						<td style="text-align: right;">分红累计:</td>
						<td class="center">${member.shoppingAccumulative }</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">服务积分累计:</td>
						<td>${member.repeatedAccumulative }</td>
						<td style="text-align: right;">参与分红点数:</td>
						<td class="center">${member.recommendCount }</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">是否报单中心:</td>
						<td><c:if test="${member.isService eq 0}">普通会员</c:if> <c:if
								test="${member.isService eq 1}">报单中心</c:if></td>
						<td style="text-align: right;">推荐报单中心:</td>
						<td class="center">${member.leaderServiceNumber }</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">本月新报单人数统计:</td>
						<td>${member.serviceCount }</td>
						<td style="text-align: right;">历史报单人数统计:</td>
						<td class="center">${member.serviceSum}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="save" type="button" onclick="saveMemberDetail('saveServiceDetailForm')">确认修改</button>
	</div>
</div>