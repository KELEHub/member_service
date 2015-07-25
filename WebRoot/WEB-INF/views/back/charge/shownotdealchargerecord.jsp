<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
var table = null;
function searchMembers(){
   table.fnDraw();
}
$(function(){
	var bathPath=$("#basePath").val();
		table=$("#dealchargeRecordTable").dataTable({
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
                "sAjaxSource": bathPath+"/charge/getNoChargezRecordPage.do",
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
                        { "mData": "tradeNo" },
                        { "mData": "chargeDate" },
                        { "mData": "chageAmt" },
                        { "mData": "chargesurplus" },
                        { "mData": "realGetAmt" },
                        { "sDefaultContent" : ""},
                        { "sDefaultContent" : ""}
                    ],
               "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
        			if (aData.status==0){
        				$('td:eq(6)', nRow).html("未处理");
        				$('td:eq(7)', nRow).html(
						"<button class=\"btn\" onclick=\"agreecharge("+aData.id+")\">充值完成</button>"+
						"<button class=\"btn\" onclick=\"disAgreeCharge("+aData.id+")\">拒绝充值</button>");
        			}else if (aData.status==1){
        				$('td:eq(6)', nRow).html("已处理");
        			}
                   	return nRow;
                 }
            });            
});
</script>
<div class="container-fluid" id="content-header">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			   <div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>充值申请处理</h5>
				</div>
				<div class="widget-content nopadding">
					<form 
						action="/charge/dealChargeRecord.do" method="POST"
						id="searchChargeForm">
						</form>
						<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">会员账号:</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="number" id="number" value="${form.number}">
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<input type="submit" value="查询"
							onclick="searchMembers()"
							class="btn btn-success">
					</div>
				</div>
				
				<div class="widget-content nopadding">
					<table id="dealchargeRecordTable" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>流水号</th>
								<th>日期</th>
								<th>提现金额</th>
								<th>手续费</th>
								<th>实际金额</th>
								<th>状态</th>
								<th style="width:190px">操作</th>
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