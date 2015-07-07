<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/ServiceManagerController/showApplyServiceManager.do" method="POST" id="applyServiceManagerForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>审批报单中心</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>申请会员账号</th>
								<th>提交会员账号</th>
								<th>申请日期</th>
								<th>状态</th>
								<th>申请原因</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.applyNumber}</td>
									<td>${item.submitNumber}</td>
									<td>${item.applyDate}</td>
									<td>
									<c:if test="${item.state eq 0}">未处理</c:if> 
									<c:if test="${item.state eq 1}">已通过</c:if>
									<c:if test="${item.state eq 2}">已拒绝</c:if>
									</td>
									<td>${item.submitReason}</td>
									<td>
									<c:if test="${item.state eq 0}">
										<a href="#myModal" data-toggle="modal" class="btn" onclick="editFailureCause('${item.id}')">审核失败</a>
										<button class="btn" onclick="checkSuccess('${item.id}','${item.submitId}','${item.submitNumber}','${item.applyId}','${item.applyNumber}')">审核通过</button>
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

<div id="myModal" class="modal hide">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>失败原因</h3>
	</div>
	<div class="widget-content nopadding">
		<form action="/ServiceManagerController/applyCheckFailure.do" method="post"
			id="applyCheckFailureForm" class="form-horizontal">
			<input type="hidden" class="span11" name="id" id="applyCheckFailureId" />
			<div class="control-group">
					<div class="control-group">
						<div class="controls">
							<textarea class="textarea_editor span12" rows="6" name="failureCause"
								style="width:220;"></textarea>
						</div>
					</div>
			</div>
		</form>
	</div>
	<div class="form-actions">	
	<button type="submit" class="btn btn-success" onclick="checkFailure('applyCheckFailureForm')">
		提交
	</button>
</div>
</div>