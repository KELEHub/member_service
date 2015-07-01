<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			 <div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>产品订单记录</h5>
				</div>
			
			<div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/orderlist/searchOrderListRecord.do" method="POST"
						id="searchOrderListRecordForm">
						<div class="control-group">
							<label class="control-label">会员账号:</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="memeberNumber" value="${form.memeberNumber}">
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="查询"
							onclick="searchOrderList('searchOrderListRecordForm')"
							class="btn btn-success">
					</div>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>流水号</th>
								<th>下单日期</th>
								<th>报单中心</th>
								<th>套餐名称</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.memeberNumber}</td>
									<td>${item.tradeNo}</td>
									<td>${item.orderDate }</td>
									<td>${item.serviceNumber }</td>
									<td>${item.productName }</td>
									<td>
									<c:if test="${item.status eq 0}">未发货</c:if> 
									<c:if test="${item.status eq 1}">已发货</c:if>
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