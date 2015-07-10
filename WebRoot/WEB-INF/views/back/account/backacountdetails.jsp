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
		会员账户明细展示
	</h1>
	<form  action="/BackAccountController/selectData.do" method="POST" id="backacountDetailsForm">
	<div class="controls controls-row">
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				会员账号：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="number" id="number" >
					
				</div>
			</div>
		</div>
		</div>
	</form>
	<div class="controls controls-row">
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label" style="color: red">${error }</label>
		</div>
	</div>
	<div class="form-actions">
		<input type="submit" value="查询" onclick="searchBackAccount('backacountDetailsForm')"
			class="btn btn-success">
	</div>
		<div class="controls controls-row">
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				葛粮币余额：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="goldmoneybalance" id="goldmoneybalance" disabled="true" value="${goldmoneybalance}">
				</div>
			</div>
		</div>
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				积分余额：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="pointsbalance" id="pointsbalance" disabled="true" value="${pointsbalance}">
				</div>
			</div>
		</div>
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				服务积分：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="serverbalance" id="serverbalance" disabled="true" value="${serverbalance}">
				</div>
			</div>
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
								<th>种类</th>
								<th>日期</th>
								<th>项目</th>
								<th>收入</th>
								<th>支出</th>
								<th>积分余额</th>
								<th>葛粮币余额</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.kindData }</td>
									<td>${item.createTime }</td>
									<td>${item.project }</td>
									<td>${item.income }</td>
									<td>${item.pay }</td>
									<td>${item.pointbalance }</td>
									<td>${item.goldmoneybalance }</td>
									<td>${item.redmin }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>




