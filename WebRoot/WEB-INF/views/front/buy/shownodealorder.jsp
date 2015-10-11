<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
var table = null;
function searchOrderNumberTable(){
document.getElementById("tnumber").value = document.getElementById("tnumberss").value;
document.getElementById("tnumberss").value='';
   table.fnDraw();
}
$(function(){
	var bathPath=$("#basePath").val();
	   table=$("#nodealorderTable").dataTable({
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
                "sAjaxSource": bathPath+"/shoping/showOrderDetails.do",
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
                        { "name": "tnumber", "value": $("#tnumber").val() }
                        );
                },
                "aoColumns": [
                		{ "mData": "tnumber" },
                        { "mData": "allPrice" },
                        { "mData": "createTime" },
                        { "sDefaultContent" : ""}
                        
                    ],
               "fnRowCallback" : function(nRow, aData, iDisplayIndex) {  
                       	$('td:eq(3)', nRow).html("<button class=\"btn\" onclick=\"editeOderDetail("+aData.id+")\">查看详细</button>");
                        return nRow;
                    }
            });            
});
</script>

<div style="overflow-x:hidden; height:85%">
<div id="content-header">
    <br/>
    <br/>
    <br/>
    <br/>
	<form action="/GiftsDetailsController/show.do" method="POST"
							id="searchGiftsForm">
		<div class="controls controls-row">
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">订单编号:</label>
				<div class="controls">
				    <input type="text" name="tnumberss" id="tnumberss" >
					<input type="hidden" name="tnumber" id="tnumber" >
				</div>
			</div>
		</div>
	</form>
	<div class="form-actions">
		<input type="submit" value="查询" onclick="searchOrderNumberTable()"
			class="btn btn-success">
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="nodealorderTable" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>订单编号</th>
								<th>消费总金额</th>
								<th>生成日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</div>



