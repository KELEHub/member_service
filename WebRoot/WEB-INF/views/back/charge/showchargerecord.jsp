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
		充值申请记录
	</h1>
	<form action="/charge/searchChargeRecord.do" method="POST"
							id="searchChargeForm">
		<div class="controls controls-row">
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">会员账号:</label>
				<div class="controls">
					<input type="text" name="number" value="${form.number}">
				</div>
		</div>
	</form>
	<div class="control-group" style="float: left; margin-left: 10px;">
		<a class="btn" onclick="searchMembers('searchChargeForm')">查询</a>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>流水号</th>
								<th>日期</th>
								<th>充值金额</th>
								<th>手续费</th>
								<th>实际金额</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.number}</td>
									<td>${item.tradeNo}</td>
									<td>${item.chargeDate }</td>
									<td>${item.chageAmt }</td>
									<td>${item.chargesurplus }</td>
									<td>${item.realGetAmt }</td>
									<td>
									<c:if test="${item.status eq 0}">未处理</c:if> 
									<c:if test="${item.status eq 1}">已处理</c:if>
									<c:if test="${item.status eq 2}">被拒绝</c:if>
									</td>
									<td><button class="btn" onclick="showChargeDetail('${item.id}')">会员备注</button>
									<c:if test="${item.status eq 2}">
										<button class="btn" onclick="showChargeRefuseReason('${item.id}')">拒绝理由</button>
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