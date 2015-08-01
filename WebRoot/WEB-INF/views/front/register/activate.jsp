<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div style="overflow-x:hidden; height:85%">
  <div id="content-header">
    <form action="/RegisterController/showActivate.do" method="POST" id="searchActivateForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>会员信息审核</h5>
				</div>
				<div class="widget-content nopadding">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">葛粮币余额：</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="goldmoneybalance" id="goldmoneybalance" disabled="true" value="${goldmoneybalance}">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>账号</th>
								<th>姓名</th>
								<th>身份证号码</th>
								<th>电话号码</th>
								<th>联系地址</th>
								<th>推荐人</th>
								<th>注册时间</th>
								<th>激活状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.number }</td>
									<td>${item.name }</td>
									<td>${item.identity }</td>
									<td>${item.phoneNumber }</td>
									<td>${item.linkAddress }</td>
									<td>${item.recommendNumber }</td>
									<td>${item.registerDate }</td>
									<td>${item.activate }</td>
									<td>
									<c:if test="${item.flg=='0'}">
									<button class="btn" onclick="deleteuser('${item.number}')">删除</button>
									<button class="btn" onclick="activityuser('${item.number}')">激活</button>
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
</div>
