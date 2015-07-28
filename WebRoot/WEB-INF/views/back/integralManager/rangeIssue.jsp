<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH) + 1;
%>
<div style="overflow:auto; height:800px">
 <div id="content-header">
    <form action="/IntegralManagerController/showRangeIntegralIssueManager.do" method="POST" id="rangeIntegralForm">
	</form>
  </div>
<div id="content-header">
	<h1>
		极差积分发放
	</h1>
	<div class="widget-content nopadding">
		<form class="form-horizontal"
			action="/IntegralManagerController/doRangeIssue.do" method="POST"
			id="rangeIssueDateForm">
			<div class="control-group">
				<label class="control-label">
					年:
				</label>
				<div class="controls">
					<div class="input-append">
						<input type="text" name="year" value="<%=year%>">
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					月:
				</label>
				<div class="controls">
					<div class="input-append">
						<input type="text" name="month" value="<%=month%>">
					</div>
				</div>
			</div>
		</form>
		<div class="form-actions">
			<input type="submit" value="极差发放"
				onclick="rangeIntegralIssue('rangeIssueDateForm')"
				class="btn btn-success">
		</div>
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
								<th>可发放服务积分</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.userNumber}</td>
									<td>${item.availableInt}</td>
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