<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
var table = null;
function searchMembers(){
document.getElementById("number").value = document.getElementById("numberss").value;
document.getElementById("numberss").value='';
   table.fnDraw();
}
$(function(){
	var bathPath=$("#basePath").val();
		table=$("#showNotDealWithDrawalsRecordTable").dataTable({
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
                "sAjaxSource": bathPath+"/withdrawals/getDealWithdrawalszRecordPage.do",
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
                        { "mData": "tradeDate" },
                        { "mData": "tradeAmt" },
                        { "mData": "tradeFee" },
                        { "mData": "realGetAmt" },
                        { "mData": "numberName" },
                        { "mData": "numberPhone" },
                        { "mData": "bankName" },
                        { "mData": "bankCardNo" },
                        { "sDefaultContent" : ""},
                        { "sDefaultContent" : ""}
                    ],
               "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
        			if (aData.status==0){
        				$('td:eq(10)', nRow).html("未处理");
        				$('td:eq(11)', nRow).html(
						"<button class=\"btn\" onclick=\"agreewithdrawals("+aData.id+")\">提现</button>"+
						"<button class=\"btn\" onclick=\"disAgreewithdrawals("+aData.id+")\">拒绝提现</button>"
						);
        			}else if (aData.status==1){
        				$('td:eq(10)', nRow).html("已处理");
        			}
                   	return nRow;
                 }
            });            
});
</script>
<div  style="overflow-x:auto; height:85%">  
<div class="container-fluid" id="content-header">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			   <div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>提现申请处理</h5>
				</div>
			   <div class="widget-content nopadding">
					<form 
						action="/withdrawals/dealWithdrawalszRecord.do" method="POST"
						id="searchWidhdrawalsxForm">
						</form>
						<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">会员账号:</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" id="numberss" name="numberss" value="${form.number}">
									<input type="hidden" id="number" name="number" value="${form.number}">
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<input type="submit" value="查询"
							onclick="searchMembers()"
							class="btn btn-success">
							<input type="submit" value="导出未处理提现申请"
							onclick="exportNotDealTiXian('searchWidhdrawalsxForm')"
							class="btn btn-success">
					</div>
				</div>
				<div class="widget-content nopadding">
					<table id="showNotDealWithDrawalsRecordTable" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>流水号</th>
								<th style="width:150px">日期</th>
								<th>提现金额</th>
								<th>手续费</th>
								<th>实际金额</th>
								<th style="width:6%">会员姓名</th>
								<th style="width:6%">电话</th>
								<th style="width:10%">银行</th>
								<th style="width:10%">卡号</th>
								<th>状态</th>
								<th style="width:200px">操作</th>
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