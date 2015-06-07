<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content-header">
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i
			class="icon-home"></i> Home</a> <a href="#" class="current">Buttons
			&amp; icons</a>
	</div>
	<h1>
		未激活会员资料
	</h1>
	<form action="/membermanage/searchNotActivationMember.do" method="POST"
							id="searchActiveMemberForm">
<!-- 		<div class="controls controls-row"> -->
<!-- 			<div class="control-group" style="float: left;margin-left: 10px;"> -->
<!-- 				<label class="control-label">会员账号:</label> -->
<!-- 				<div class="controls"> -->
<%-- 					<input type="text" name="number" value="${form.number}"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="control-group" style="float: left;margin-left: 10px;"> -->
<!-- 				<label class="control-label">推荐编号:</label> -->
<!-- 				<div class="controls"> -->
<%-- 					<input type="text" name="recommendNumber"  value="${form.recommendNumber }"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="control-group" style="float: left;margin-left: 10px;"> -->
<!-- 				<label class="control-label">报单中心:</label> -->
<!-- 				<div class="controls"> -->
<%-- 					<input type="text" name="serviceNumber"   value="${form.serviceNumber }"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</form>
<!-- 	<div class="control-group" style="float: left;margin-left: 10px;"> -->
<!-- 			<a class="btn" onclick="searchMembers('searchActiveMemberForm')">查询</a> -->
<!-- 			</div> -->
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>账号</th>
								<th>姓名</th>
								<th>电话号码</th>
								<th>开户银行</th>
								<th>邮政编码</th>
								<th>推荐编号</th>
								<th>报单中心</th>
								<th>注册日期</th>
								<th>激活日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.number}</td>
									<td>${item.name}</td>
									<td>${item.phoneNumber }</td>
									<td>${item.bankName }</td>
									<td>${item.postNumber }</td>
									<td>${item.recommendNumber }</td>
									<td>${item.serviceNumber }</td>
									<td>${item.registerDate }</td>
									<td>${item.activeDate }</td>
									<td>
										<button class="btn" onclick="doMemberShowDetail('${item.id}')">详细信息</button>
										<button class="btn" onclick="doDeleteMember('${item.id}')">删除</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>