<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div style="overflow:auto; height:700px">
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/ApplyQualificationController/showApplyQualification.do" method="POST" id="applyQualificationForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
		<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>申请资格</h5>
				</div>
				<input type="hidden" class="span11" id="applyMember_applyId">
				<div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/ApplyQualificationController/queryMemberInfo.do" method="POST"
						id="applyMemberNumberForm">
						<div class="control-group">
							<label class="control-label">申请会员账号:</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="applyNumber" class="span11" id="applyMember_applyNumber">
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="查看"
							onclick="queryMemberInfo('applyMemberNumberForm')"
							class="btn btn-success">
					</div>
				</div>
				<div class="widget-content nopadding">
					<form action="/ApplyQualificationController/submitApply.do"
						method="post" id="submitApplyForm" class="form-horizontal">
						<div class="control-group">
							<label class="control-label">
								申请会员账号信息:
							</label>
							<div class="controls">
								<input type="text" class="span11" id="applyMember_info" readonly="readonly"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								推荐理由说明:
							</label>
							<div class="controls">
								<textarea class="span12" rows="6" name="submitReason" id="applyMember_reason"></textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								二级密码:
							</label>
							<div class="controls">
								<input type="password" class="span11" name="protectPassword" id="applyMember_password"/>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<button type="submit" class="btn btn-success"
							onclick="submitApply($('#applyMember_applyId').val(),$('#applyMember_applyNumber').val(),$('#applyMember_reason').val(),$('#applyMember_password').val())">
							确认申请
						</button>
					</div>
				</div>
			</div>
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>申请记录</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>申请会员账号</th>
								<th>申请日期</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.applyNumber}</td>
									<td>${item.applyDate}</td>
									<td>
									<c:if test="${item.state eq 0}">未处理</c:if> 
									<c:if test="${item.state eq 1}">已通过</c:if>
									<c:if test="${item.state eq 2}">已拒绝</c:if>
									</td>
									<td>
									<c:if test="${item.state eq 0}">
										<button class="btn" onclick="deleteApplyQualification('${item.id}')">删除</button>
									</c:if>
									<c:if test="${item.state eq 2}">
										<a href="#myModal" data-toggle="modal" class="btn" onclick="showForbidFailureCause('${item.failureCause}')">查看理由</a>
									</c:if>
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
</div>
<div id="myModal" class="modal hide" style="width: 400;height: 300;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>拒绝理由</h3>
	</div>
	<div class="widget-content nopadding">
		<div class="control-group">
				<div class="controls">
					<textarea class="textarea_editor span12" rows="6" readonly="readonly"
						id="forbid_failureCause" style="width: 400;height: 230;resize:none"></textarea>
				</div>
		</div>
	</div>
</div>