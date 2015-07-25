<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
var table = null;
function searchIntegral(){
   table.fnDraw();
}
$(function(){
	var bathPath=$("#basePath").val();
		 table=$("#integralHistoryTable").dataTable({
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
                "sAjaxSource": bathPath+"/IntegralManagerController/getIntegralHistory.do",
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
                        { "name": "number", "value": $("#userNumber").val() }
                        );
                },
                "aoColumns": [
                        { "mData": "userNumber" },
                        { "mData": "project" },
                        { "mData": "income" },
                        { "mData": "pay" },
                        { "mData": "pointbalance" },
                        { "mData": "createTime" }
                    ]
            });            
});
</script>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/BankRechargeController/show.do" method="POST" id="updateRechargeForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>历史积分统计</h5>
				</div>
				<div class="widget-content nopadding">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">会员账号：</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="userNumber" id="userNumber"
										class="span11">
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<input type="submit" value="检索"
							onclick="searchIntegral()"
							class="btn btn-success">
					</div>
				</div>
			
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="integralHistoryTable" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>项目</th>
								<th>收入</th>
								<th>支出</th>
								<th>积分余额</th>
								<th>日期</th>
							</tr>
						</thead>
					
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

