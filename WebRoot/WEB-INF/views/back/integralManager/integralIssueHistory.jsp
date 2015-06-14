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
					<input id="userNumber_input" type="text" name="userNumber">
					<a class="btn" onclick="searchIntegralHistory(userNumber_input.value)">查询</a>
				</div>
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
								<th>项目</th>
								<th>收入</th>
								<th>支出</th>
								<th>积分余额</th>
								<th>日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.userNumber}</td>
									<td>${item.project}</td>
									<td>${item.income}</td>
									<td>${item.pay}</td>
									<td>${item.pointbalance}</td>
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