<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="container-fluid" id="content-header">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			   <div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>充值申请处理</h5>
				</div>
				<div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/charge/searchDealChargezRecord.do" method="POST"
						id="searchChargeForm">
						<div class="control-group">
							<label class="control-label">会员账号:</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="number" value="${form.number}">
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="查询"
							onclick="searchMembers('searchChargeForm')"
							class="btn btn-success">
					</div>
				</div>
				
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>流水号</th>
								<th>日期</th>
								<th>提现金额</th>
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
									</td>
									<td><button class="btn" onclick="agreecharge('${item.id}')">充值完成</button>
									<button class="btn" onclick="disAgreeCharge('${item.id}')">拒绝充值</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>