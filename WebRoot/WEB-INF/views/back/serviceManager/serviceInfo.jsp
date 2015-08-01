<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
$(function(){
	var bathPath=$("#basePath").val();
		$("#serviceInfoTable").dataTable({
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
                "sAjaxSource": bathPath+"/ServiceManagerController/getServiceManagerPage.do",
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
                        { "mData": "number" },
                        { "mData": "name" },
                        { "mData": "shoppingMoney" },
                        { "mData": "phoneNumber" },
                        { "mData": "bankName" },
                        { "mData": "postNumber" },
                        { "mData": "recommendNumber" },
                         { "mData": "serviceCount" },
                        { "mData": "serviceSum" },
                        { "sDefaultContent" : ""}
                    ],
               "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
        			$('td:eq(9)', nRow).html(
                       	"<button class=\"btn\" onclick=\"serviceInfoDetail("+aData.id+")\">详细信息</button>"+
                       	"<button class=\"btn\" onclick=\"forbiddenService("+aData.id+")\">禁用</button>");
                   	return nRow;
                 }
            });            
});
</script>
<div style="overflow-x:hidden; height:85%">
  <div id="content-header">
    <form action="/ServiceManagerController/showServiceManager.do" method="POST" id="serviceManagerForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>报单中心信息</h5>
				</div>
				<div class="form-actions">
				<c:if test="${ifFrobid eq 0}">
						<input type="submit" value="禁止报单"
							onclick="forbidForm()"
							class="btn btn-success">
				</c:if>
				<c:if test="${ifFrobid eq 1}">
						<input type="submit" value="允许报单"
							onclick="permitForm()"
							class="btn btn-success">
				</c:if>
				</div>
				<div class="widget-content nopadding">
					<table id="serviceInfoTable" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>账号</th>
								<th>姓名</th>
								<th style="width:80px">积分余额</th>
								<th>电话号码</th>
								<th>开户银行</th>
								<th>邮政编码</th>
								<th style="width:100px">推荐报单中心编号</th>
								<th style="width:90px">本月新报单人数</th>
								<th style="width:80px">历史报单人数</th>
								<th style="width:140px">操作</th>
							</tr>
						</thead>
						<tbody>
<%--							<c:forEach var="item" items="${result}">--%>
<%--								<tr class="gradeX">--%>
<%--									<td>${item.number}</td>--%>
<%--									<td>${item.name}</td>--%>
<%--									<td>${item.shoppingMoney}</td>--%>
<%--									<td>${item.phoneNumber}</td>--%>
<%--									<td>${item.bankName}</td>--%>
<%--									<td>${item.postNumber}</td>--%>
<%--									<td>${item.recommendNumber}</td>--%>
<%--									<td>${item.serviceCount}</td>--%>
<%--									<td>${item.serviceSum}</td>--%>
<%--									<td>--%>
<%--									<button class="btn" onclick="serviceInfoDetail('${item.id}')" id="serviceInfo_Info">详细信息</button>--%>
<%--<!--									<a href="#myModal" data-toggle="modal" class="btn btn-large" onclick="rechargeToForm('${item.id}')" id="serviceInfo_edit">后台充值</a>-->--%>
<%--									<button class="btn" onclick="forbiddenService('${item.id}')" id="serviceInfo_Forbidden">禁用</button>--%>
<%--									</td>--%>
<%--								</tr>--%>
<%--							</c:forEach>--%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<div id="myModal" class="modal hide">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>会员账户信息修改</h3>
	</div>
	<div class="widget-content nopadding">
		<form action="/ServiceManagerController/serviceRecharge.do" method="post"
			id="rechargeServiceForm" class="form-horizontal">
			<input type="hidden" class="span11" name="id" id="serviceInfo_serviceId" />
			<div class="control-group">
				<label class="control-label">
					会员账号:
				</label>
				<div class="controls">
					<input type="text" class="span11" name="number"
						id="serviceInfo_number" style="width:220;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					葛粮币:
				</label>
				<div class="controls">
					<input type="text" class="span11" name="crmMoney"
						id="serviceInfo_crmMoney" style="width:220;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					葛粮币累计:
				</label>
				<div class="controls">
					<input type="text" class="span11" name="crmAccumulative"
						id="serviceInfo_crmAccumulative" style="width:220;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					积分:
				</label>
				<div class="controls">
					<input type="text" class="span11" name="shoppingMoney"
						id="serviceInfo_shoppingMoney" style="width:220;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					积分累计:
				</label>
				<div class="controls">
					<input type="text" class="span11" name="shoppingAccumulative"
						id="serviceInfo_shoppingAccumulative" style="width:220;"/>
				</div>
			</div>
		</form>
	</div>
	<div class="form-actions">	
	<button type="submit" class="btn btn-success" onclick="rechargeService('rechargeServiceForm')">
		确认提交
	</button>
</div>
</div>