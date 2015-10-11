<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content-header">
	<div id="breadcrumb">
		<form action="/shoping/showShopingCart.do" method="POST" id="showcartform">
	</form>
	</div>
</div>
<div style="overflow-x: hidden; height: 85%">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="icon-th"></i>
						</span>
						<h5>
							我的购物车
						</h5>
					</div>
					<div class="widget-content nopadding">
						<table id="testexample2" class="table table-bordered data-table">
							<thead>
								<tr>
									<th>
										商品图片
									</th>
									<th>
										商品名字
									</th>
									<th>
										商品编号
									</th>
									<th>
										服务站
									</th>
									<th>
										服务站电话
									</th>
									<th>
										服务站地址
									</th>
									<th>
										商品单价
									</th>
									<th>
										数量
									</th>
									<th>
										商品总价
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${result }">
									<tr class="gradeX">
										<input type="hidden" disabled="true" id="price${item.id}" 
											value="${item.shopPrice }">
										<input type="hidden" disabled="true" name="shopId" 
											value="${item.id }">
										<td>
											<img src="${item.productTarget}" />
										</td>
										<td>
											${item.productName}
										</td>
										<td>
											${item.productNumber }
										</td>
										<td>
											${item.serverNumber }
										</td>
										<td>
											${item.phone }
										</td>
										<td>
											${item.address }
										</td>
										<td>
											${item.shopPrice }
										</td>
										<td>
											<input type="text" name="shopCount" id="${item.id}"
												value="${item.shopCount}"
												onfocusout="changeShopCount('${item.id}')"
												style="width: 80px; height: 40px">
										</td>
										<td>
											<input type="text" name="priceall" value="${item.priceall}"
												disabled="true" id="priceall${item.id}"
												style="width: 80px; height: 40px">
										</td>
										<td>
											<button class="btn">
												删除
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="control-group" style="float: left; margin-left: 10px;">
						<label class="control-label">
							商品总价：
						</label>
						<div class="controls">
							<div class="input-append">
								<input type="text" name="shoprice" id="shoprice"
									disabled="true" value="${count}" style="width: 110px; height: 40px">
							</div>
						</div>
					</div>
					<br>
					<div class="form-actions">
						<input type="submit" value="确认支付" onclick="confimPay()"
							class="btn btn-success">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>