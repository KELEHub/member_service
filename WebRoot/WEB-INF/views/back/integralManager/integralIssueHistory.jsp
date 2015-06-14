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
		历史积分统计
	</h1>
	<form action="/IntegralManagerController/showIntegralHistory.do" method="POST"
							id="integralHistoryForm">
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">会员账号:</label>
				<div class="controls">
					<input type="text" name="memeberNumber" value="${form.memeberNumber}">
				</div>
		</div>
	</form>
	<div class="control-group" style="float: left; margin-left: 10px;">
		<a class="btn" onclick="searchOrderList('integralHistoryForm')">查询</a>
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
								<th>服务积分</th>
								<th>积分</th>
								<th>合计</th>
								<th>日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.userId}</td>
									<td>${item.tradeNo}</td>
									<td>${item.serviceNumber }</td>
									<td>${item.productName }</td>
									<td>${item.orderDate }</td>
									<td>
									<c:if test="${item.status eq 0}">未发货</c:if> 
									<c:if test="${item.status eq 1}">已发货</c:if>
									</td>
									<td><button class="btn" onclick="showOrderListDetail('${item.id}')">收货人详细</button>
									<c:if test="${item.status eq 0}">
									  <button class="btn" onclick="deliveryOrder('${item.id}')">发货</button>
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