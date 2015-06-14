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
		极差积分发放
	</h1>
	<form action="/IntegralManagerController/showRangeIntegralIssueManager.do" method="POST"
							id="rangeIntegralForm">
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">年:</label>
				<input id="year_input" type="text">
				<label class="control-label">月:</label>
				<input id="month_input" type="text">
				<a class="btn" onclick="rangeIntegralIssue(year_input.value,month_input.value)">极差发放</a>
		</div>
	</form>
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
								<th>可发放积分</th>
								<th>当前服务积分</th>
								<th>当前积分</th>
								<th>日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.userNumber}</td>
									<td>${item.availableInt}</td>
									<td>${item.currentServiceInt}</td>
									<td>${item.currentInt}</td>
									<td>${item.createTime}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>