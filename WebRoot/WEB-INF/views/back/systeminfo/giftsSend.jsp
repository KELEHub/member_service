<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
$(function(){
	var bathPath=$("#basePath").val();
		$("#sendGiftsDetailsTable").dataTable({
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
                "sAjaxSource": bathPath+"/GiftsDetailsController/getSendGiftsDetailsPage.do",
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
    <form action="/GiftsDetailsController/showSend.do" method="POST" id="initGiftsForm">
	</form>
   <form action="/GiftsDetailsController/send.do" method="POST"
						id="giftsDetailsSendForm">
						</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>礼包积分发放</h5>
				</div>
				<div class="widget-content nopadding">
		<div  class="form-horizontal"> 
		
		<div class="control-group">
			<label class="control-label">
				${firstGold}:
			</label>
			<div class="controls">
				<div >
				   <input type="submit" value="发送积分"
							onclick="sendGifts('giftsDetailsSendForm')"
							class="btn btn-success">
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">
				${SecondGold}:
			</label>
			<div class="controls">
				<div >
				   <input type="submit" value="发送积分"
							class="btn btn-success">
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">
				${threeGold}:
			</label>
			<div class="controls">
				<div>
				   <input type="submit" value="发送积分"
							class="btn btn-success">
				</div>
			</div>
		</div>
		
		
	</div>
				<div class="widget-content nopadding">
					<table id="sendGiftsDetailsTable" class="table table-bordered data-table">
						<thead>
							<tr>
								<th style="width: 80px">会员账号</th>
								<th style="width: 100px">项目</th>
								<th>收入</th>
								<th style="width: 60px">支出</th>
								<th>积分余额</th>
								<th>日期</th>
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
