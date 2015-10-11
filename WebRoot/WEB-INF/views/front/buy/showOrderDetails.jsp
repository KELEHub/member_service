<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div style="overflow-x:hidden; height:85%">
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>订单详情</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								    <th>商品图片</th>
									<th>商品名字</th>
									<th>商品编号</th>
									<th>服务站</th>
									<th>服务站电话</th>
									<th>服务站地址</th>
									<th>商品单价</th>
									<th>数量</th>
									<th>商品总价</th>
									<th>商品状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${list }">
								<tr class="gradeX">
									<td><img src="${item.productTarget}" /></td>
									<td>${item.productName }</td>
									<td>${item.productNumber }</td>
									<td>${item.serverNumber }</td>
									<td>${item.phone }</td>
									<td>${item.address }</td>
									<td>${item.shopPrice }</td>
									<td>${item.shopCount }</td>
									<td>${item.priceall }</td>
									<td>${item.stauts }</td>
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