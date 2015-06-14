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
		礼包信息管理
	</h1>
	<form action="/GiftsDetailsController/search.do" method="POST"
							id="searchGiftsForm">
		<div class="controls controls-row">
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">会员账号:</label>
				<div class="controls">
					<input type="text" name="number" value="${form.number}">
				</div>
			</div>
		</div>
	</form>
	<div class="control-group" style="float: left;margin-left: 10px;">
			<a class="btn" onclick="searchGifts('searchGiftsForm')">查询</a>
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
								<th>会员编号</th>
								<th>礼包名称</th>
								<th>生成日期</th>
								<th>说明</th>
								<th>修改</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.number}</td>
									<td>${item.name}</td>
									<td>${item.createTime }</td>
									<td>${item.remaind }</td>
									<td>
										<button class="btn" onclick="editeDetail('${item.id}')">修改</button>
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




