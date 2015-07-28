<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
var table = null;
function searchGiftsAllTable(){
   table.fnDraw();
}
$(function(){
	var bathPath=$("#basePath").val();
	   table=$("#giftsAllTable").dataTable({
                "sPaginationType": "full_numbers",   
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
                "sAjaxSource": bathPath+"/GiftsDetailsController/getGiftsDetailsPage.do",
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
                        { "name": "number", "value": $("#number").val() }
                        );
                },
                "aoColumns": [
                		{ "mData": "number" },
                        { "mData": "name" },
                        { "mData": "createTime" },
                        { "mData": "remaind" },
                        { "sDefaultContent" : ""}
                        
                    ],
               "fnRowCallback" : function(nRow, aData, iDisplayIndex) {  
                       	$('td:eq(4)', nRow).html("<button class=\"btn\" onclick=\"editeDetail("+aData.id+")\">修改</button>");
                        return nRow;
                    }
            });            
});
</script>

<div style="overflow:auto; height:800px">
<div id="content-header">
	<h1>
		礼包信息管理
	</h1>
	<form action="/GiftsDetailsController/show.do" method="POST"
							id="searchGiftsForm">
		<div class="controls controls-row">
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">会员账号:</label>
				<div class="controls">
					<input type="text" name="number" id="number"  value="${form.number}">
				</div>
			</div>
		</div>
	</form>
	<div class="control-group" style="float: left;margin-left: 10px;">
			<a class="btn" onclick="searchGiftsAllTable()">查询</a>
			</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="giftsAllTable" class="table table-bordered data-table">
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
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</div>



