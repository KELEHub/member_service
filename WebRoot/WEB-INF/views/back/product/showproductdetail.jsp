<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<base href="<%=basePath%>">
<div id="showProductDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>产品信息修改.</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<div class="widget-content nopadding">
				<form action="/product/updateProduct.do" method="POST"
					id="updateProductForm">
					<input type="hidden" name="id" value="${result.id }"/>
					<div class="controls controls-row">
					   <div class="control-group" style="float: left;margin-left: 10px;">
						   <label class="control-label">商品名称</label>
						   <div class="controls">
							     <input type="text" name="productName" value="${result.productName }"/>
						   </div>
					   </div>
					   <div class="control-group" style="float: left;margin-left: 10px;">
						   <label class="control-label">商品编号</label>
						   <div class="controls">
							   <input type="text"  name="productNumber" value="${result.productNumber }"/>
						  </div>
					  </div>
					  <div class="control-group" style="float: left;margin-left: 10px;">
						   <label class="control-label">商品规格</label>
						   <div class="controls">
							   <input type="text"  name="productModel" value="${result.productModel }"/>
						   </div>
					 </div>
					   
				   </div>
				   <div class="controls controls-row">
				       <div class="control-group" style="float: left;margin-left: 10px;">
						    <label class="control-label">商品价格</label>
						    <div class="controls">
							     <input type="text"  name="productPrice" value="${result.productPrice }"/>
						    </div>
					   </div>
				       <div class="control-group" style="float: left;margin-left: 10px;">
						    <label class="control-label">总库存</label>
						    <div class="controls">
						        <input type="text" name="productAllKC" value="${result.productAllKC }"/>
						    </div>
				   	  </div>
				   
				   </div>
					   <div class="control-group">
						    <label class="control-label">商品介绍</label>
						    <div class="controls">
						        <textarea rows="6" cols="24" name="productIntroduction" >${result.productIntroduction }</textarea>
					        </div>
					  </div>
					<div class="control-group">
						<label class="control-label">图片</label>
						<div class="controls">
						<input type="hidden" name="productTarget" value="${result.productTarget }" />
						<input
									type="file" para="{relativelyPath:'product'}" name="files"
									multiple data-url='<%=basePath%>/upload/uploadFile.do'
									onchange='uploadFileWithPar(this)'
									style="width: 100%; height: 28px" /> 
							 <a class="upfile" href="${result.productTarget }"
									rel="lightbox" id="linkboxfileupload9"> 
							  <img src="${result.productTarget }"
									width="100px">		
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="save" type="button" onclick="saveWithFormAddRefeshTable('updateProductForm','showProductDetail')">确认修改</button>
	</div>
</div>