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
		<h1><a href="#showAddProductDetail" data-toggle="modal" class="btn btn-large">添加产品信息</a></h1>
	</h1>
	<form action="/product/getProductList.do" method="POST"
							id="searchProductForm">
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
								<th>套餐名称</th>
								<th>类型</th>
								<th>日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.productName}</td>
									<td>${item.productCategory}</td>
									<td>${item.operDate }</td>
									<td>
									<button class="btn" onclick="editProduct('${item.id}')">修改</button>
									<button class="btn" onclick="deleteProduct('${item.id}')">删除</button>
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
<div id="showAddProductDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>产品信息添加.</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<div class="widget-content nopadding">
				<form action="/product/saveProduct.do" method="POST"
					id="saveProductForm">
					<div class="control-group">
						<label class="control-label">套餐名称</label>
						<div class="controls">
							<input type="text" placeholder="套餐名称" name="productName" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">套餐介绍</label>
						<div class="controls">
							<input type="text" placeholder="套餐介绍" name="productIntroduction" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">图片</label>
						<div class="controls">
							<input type="text" placeholder="图片路径" name="productTarget" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">套餐类型</label>
						<div class="controls">
							<input type="text" placeholder="套餐类型" name="productCategory" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="save" type="button" onclick="saveWithFormAddRefeshTable('saveProductForm')">添加</button>
	</div>
</div>