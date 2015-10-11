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
<script type="text/javascript">
ndPanel = new nicEditor({fullPanel : true}).panelInstance('editNotice_content');
ndinstance1 = ndPanel.nicInstances[0];
var table = null;
function searchProductsff(){
document.getElementById("productName").value = document.getElementById("productNamess").value;
document.getElementById("productNamess").value='';
document.getElementById("productNumber").value = document.getElementById("productNumberss").value;
document.getElementById("productNumberss").value='';
table.fnDraw();
}
$(function(){
	var bathPath=$("#basePath").val();
		 table=$("#productShowTable").dataTable({
		        "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",   
        		"bJQueryUI" : true, //是否使用 jQury的UI theme  
        		"bPaginate" : true, //是否显示（应用）分页器  
        		"bAutoWidth" : true, //是否自适应宽度 
        		"bScrollCollapse" : true,
        		"iDisplayLength": 10,//每页显示5条数据
        		"bLengthChange":false,
        		"bSort": false,
        		"bInfo": true,//页脚信息
        		"bFilter" : false,//是否启动过滤、搜索功能
                "bProcessing": true,
                "bServerSide": true,
                "sAjaxSource": bathPath+"/product/getProductPage.do",
                "oLanguage": { //国际化配置  
        	        "sProcessing" : "正在获取数据，请稍后...",    
        	        "sLengthMenu" : "显示 _MENU_ 条",    
        	        "sZeroRecords" : "没有您要搜索的内容",    
        	        "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",    
        	        "sInfoEmpty" : "记录数为0",    
        	        "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
        	        "sInfoPostFix" : "",    
        	        "sSearch" : "搜索",    
        	        "sUrl" : "",    
        	        "oPaginate": {    
        	            "sFirst" : "第一页",    
        	            "sPrevious" : "上一页",    
        	            "sNext" : "下一页",    
        	            "sLast" : "最后一页"    
        	        }  
        		},
                "sServerMethod": "POST",
                "fnServerParams": function (aoData) {  //查询条件
                    aoData.push(
                        { "name": "productName", "value": $("#productName").val() },
                        { "name": "productNumber", "value": $("#productNumber").val() }
                        );
                },
                "aoColumns": [
                        { "sDefaultContent" : ""},
                        { "mData": "productName" },
                        { "mData": "productNumber" },
                        { "mData": "productModel" },
                        { "mData": "productPrice" },
                        { "mData": "productAllKC" },
                        { "mData": "operDate" },
                        { "sDefaultContent" : ""}
                    ],
               "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
        				$('td:eq(7)', nRow).html(
        				"<button class=\"btn\" onclick=\"deleteProduct("+aData.id+")\">删除</button>"
                       );
                       $('td:eq(0)', nRow).html(
        				"<img src="+aData.productTarget+" />"
                       );
        		
                   	return nRow;
                 }
                
            });            
});
</script>
<div id="content-header">
	<br/>
	<h1>
		<h1><a href="#showAddProductDetail" data-toggle="modal" class="btn btn-large">添加产品信息</a></h1>
	</h1>
	<form action="/product/getProductList.do" method="POST"
							id="searchProductForm">
	</form>
		<!--"<button class=\"btn\" onclick=\"editProduct("+aData.id+")\">修改</button>"-->
		<div class="controls controls-row">
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">商品名称:</label>
				<div class="controls">
					<input type="text" id="productNamess" name="productNamess" value="${form.productName}">
					<input type="hidden" id="productName" name="productName" value="${form.productName}">
				</div>
			</div>
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">商品编号:</label>
				<div class="controls">
					<input type="text" id="productNumberss" name="productNumberss"  value="${form.productNumber }">
					<input type="hidden" id="productNumber" name="productNumber"  value="${form.productNumber }">
				</div>
			</div>
		</div>
	<div class="control-group" style="float: left;margin-left: 10px;">
			<a class="btn" onclick="searchProductsff()">查询</a>
			</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table  id="productShowTable" class="table table-bordered data-table">
						<thead>
							<tr>
							    <th>商品图片</th>
								<th>商品名称</th>
								<th>商品编号</th>
								<th>商品规格</th>
								<th>商品价格</th>
								<th>市场库存</th>
								<th>日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<!--
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.productName}</td>
									<td>
									<c:if test="${item.productCategory eq 1}">
									注册赠送
									</c:if>
									<c:if test="${item.productCategory eq 2}">
									复消赠送
									</c:if>
									</td>
									<td>${item.operDate }</td>
									<td>
									<button class="btn" onclick="editProduct('${item.id}')">修改</button>
									<button class="btn" onclick="deleteProduct('${item.id}')">删除</button>
									</td>
								</tr>
							</c:forEach>
						-->
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
			
					<div class="controls controls-row">
					    <div class="control-group" style="float: left;margin-left: 10px;">
						    <label class="control-label">商品名称</label>
						    <div class="controls">
						       <input type="text" name="productName" id="productNamett"/>
					        </div>
					   </div>
					   <div class="control-group" style="float: left;margin-left: 10px;">
						   <label class="control-label">商品编号</label>
						   <div class="controls">
							   <input type="text"  name="productNumber" id="productNumbertt"/>
						  </div>
					  </div>
					  <div class="control-group" style="float: left;margin-left: 10px;">
						   <label class="control-label">商品规格</label>
						   <div class="controls">
							   <input type="text"  name="productModel" id="productModel"/>
						   </div>
					 </div>
				 </div>
				 <div class="controls controls-row">
				     <div class="control-group" style="float: left;margin-left: 10px;">
						<label class="control-label">商品价格</label>
						<div class="controls">
							<input type="text"  name="productPrice" id="productPrice"/>
						</div>
					</div>
				    <div class="control-group" style="float: left;margin-left: 10px;">
						<label class="control-label">市场库存</label>
						<div class="controls">
						<input type="text" name="allfirm" id="allfirm"/>
						</div>
					</div>
				 
				 </div>
					
					<!--
					<div class="control-group">
						<label class="control-label">套餐名称</label>
						<div class="controls">
							<input type="text" placeholder="套餐名称" name="productName" />
						</div>
					</div>
					-->
					<div class="control-group">
						<label class="control-label">商品介绍</label>
						<div class="controls">
								<textarea cols="50" id="editNotice_content" name="content"  style="width: 450px; height: 300px; overflow:auto; border: 1px solid #000;"></textarea>
<!--									<textarea class="textarea_editor span12" rows="6"-->
<!--										name="content"></textarea>-->
						</div>
					</div>
					<!--
					<div class="control-group">
						<label class="control-label">套餐介绍</label>
						<div class="controls">
						<textarea name="productIntroduction" >
						</textarea>
						</div>
					</div>
					-->
					<div class="control-group">
						<label class="control-label">图片</label>
						<div class="controls">
							<div>
								<input type="hidden" id="productTarget" name="productTarget" value="" /> <input
									type="file" para="{relativelyPath:'product'}" name="files"
									multiple data-url='<%=basePath%>/upload/uploadFile.do'
									onchange='uploadFileWithPar(this)'
									style="width: 100%; height: 28px" /> <a class="upfile" href=""
									rel="lightbox" id="linkboxfileupload9"> <img alt="" src=""
									width="100px"></a>
							</div>
						</div>
					</div>
					<!-- 
					<div class="control-group">
						<label class="control-label">套餐类型</label>
						<div class="controls">
							<select name="productCategory" >
								<option value="1">注册赠送</option>
								<option value="2">复消赠送</option>
							</select>
						</div>
					</div>
					-->
				
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="save" type="button" onclick="saveProduct(ndinstance1.getContent(),'saveProductForm','showAddProductDetail')">添加</button>
	</div>
</div>