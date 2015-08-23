<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div style="overflow-x:hidden; height:85%">
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
		<div class="widget-content nopadding">
					<form class="form-horizontal"  action="/userEditeController/searchHistory.do" method="POST"
						id="searchHistoryForm">
						<div class="control-group">
							<label class="control-label">会员账号：</label>
							<div class="controls">
								<div class="input-append">
										<input type="text" name="userNumber" id="userNumber"
										class="span11">
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="检索"
							onclick="searchUserHistory('searchHistoryForm')"
							class="btn btn-success">
					</div>
				</div>
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>账户修改记录</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账户</th>
								<th>日期</th>
								<th>备注</th>
								<th>修改人</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.numeber }</td>
									<td>${item.createDate }</td>
									<td>${item.remaind }</td>
									<td>${item.oprationMan }</td>
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
