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
									<td>${item.state}</td>
									<td>${item.submitReason}</td>
									<td>
									<button class="btn btn-large" onclick="checkFailure('${item.id}')">审核失败</button>
									<button class="btn btn-large" onclick="checkSuccess('${item.id}')">审核通过</button>
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