<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div style="overflow-x:hidden; height:85%">
  <div id="content-header">
    <form action="/ServerToProduct/getServerToProduct.do" method="POST" id="ServerToProductShowForm">
	</form>
  </div>
  <br/>
  <br/>
  <br/>
  <form  action="/ServerToProduct/getStockList.do" method="POST" id="stockListForm">
	<div class="controls controls-row">
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				商品编码：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="productNumber" id="productNumber" oldvalue="${productNumber}" value="">
				</div>
			</div>
		</div>
		</div>
	</form>
	<div class="form-actions">
		<input type="submit" value="查询" onclick="searchFirm('stockListForm')"
			class="btn btn-success">
	</div>
  <div class="controls controls-row">
		
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				商品名称：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="productName" id="productName" disabled="true" value="${productName}">
				</div>
			</div>
		</div>
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				商品编号：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="productNumberss" id="productNumberss" disabled="true" value="${productNumber}">
				</div>
			</div>
		</div>
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				市场库存：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="productAllKC" id="productAllKC" disabled="true" value="${productAllKC}">
				</div>
			</div>
		</div>
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				公司库存：
			</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="compentFirm" id="compentFirm" disabled="true" value="${compentFirm}">
				</div>
			</div>
		</div>
	</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>库存分配</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>商品名</th>
								<th>商品编号</th>
								<th>库存</th>
							</tr>
						</thead>
						<tbody>
 							<c:forEach var="item" items="${result}"> 
 								<tr class="gradeX"> 
 									<td>${item.serverNumber}</td> 
 									<td>${item.productName}</td> 
 									<td>${item.productNumber}</td> 
 									<td><input type="text" name="serverFirm" id="${item.id}" value="${item.serverFirm}"></td> 
 								</tr>
 							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="form-actions">
						<input type="submit" value="确认分配"
							onclick="confirmDisbute()"
							class="btn btn-success">
				</div>
			</div>
		</div>
	</div>
</div>
</div>

