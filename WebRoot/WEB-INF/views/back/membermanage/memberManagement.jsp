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
		 table=$("#memberManagementTable").dataTable({
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
                "sAjaxSource": bathPath+"/membermanage/getMemberManagementPage.do",
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
                        { "name": "number", "value": $("#number").val() },
                        { "name": "recommendNumber", "value": $("#recommendNumber").val() },
                        { "name": "serviceNumber", "value": $("#serviceNumber").val() }
                        );
                },
                "aoColumns": [
                        { "mData": "number" },
                        { "mData": "name" },
                        { "mData": "phoneNumber" },
                        { "mData": "recommendNumber" },
                        { "mData": "activateNumber" },
                        { "mData": "recommendCount" },
                        { "mData": "activeDate" },
                        { "sDefaultContent" : ""}
                    ],
               "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
        			if (aData.isLock==1){
        				$('td:eq(7)', nRow).html(
                       	"<button class=\"btn\" onclick=\"doMemberLock("+aData.id+")\"><font color=\"red\">解锁</font></button>"+
                       	"<button class=\"btn\" onclick=\"doResetPwd("+aData.id+")\">密码重置</button>" +
                       	"<button class=\"btn\" onclick=\"deleteActiveMember("+aData.id+","+aData.number+","+aData.isService+","+aData.recommendId+","+aData.recommendNumber+","+aData.activateId+","+aData.activateNumber+")\">删除</button>");
        			}else if (aData.isLock==0){
        				$('td:eq(7)', nRow).html(
                       	"<button class=\"btn\" onclick=\"doMemberLock("+aData.id+")\">锁定</button>"+
                       	"<button class=\"btn\" onclick=\"doResetPwd("+aData.id+")\">密码重置</button>" +
                       	"<button class=\"btn\" onclick=\"deleteActiveMember("+aData.id+","+aData.number+","+aData.isService+","+aData.recommendId+","+aData.recommendNumber+","+aData.activateId+","+aData.activateNumber+")\">删除</button>");
        			}
                   	return nRow;
                 }
            });            
});
</script>
<div id="content-header">
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i
			class="icon-home"></i> Home</a> <a href="#" class="current">Buttons
			&amp; icons</a>
	</div>
	<h1>
		会员管理操作
	</h1>
	<form action="/membermanage/showMemberManagement.do" method="POST"
							id="searchMemberManagementForm">
		<div class="controls controls-row">
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">会员账号:</label>
				<div class="controls">
					<input type="text" id="number" name="number" value="${form.number}">
				</div>
			</div>
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">推荐编号:</label>
				<div class="controls">
					<input type="text" id="recommendNumber" name="recommendNumber"  value="${form.recommendNumber }">
				</div>
			</div>
			<div class="control-group" style="float: left;margin-left: 10px;">
				<label class="control-label">报单中心:</label>
				<div class="controls">
					<input type="text" id="serviceNumber" name="serviceNumber"   value="${form.serviceNumber }">
				</div>
			</div>
		</div>
	</form>
	<div class="control-group" style="float: left;margin-left: 10px;">
			<a class="btn" onclick="searchMembers()">查询</a>
			</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="memberManagementTable" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>账号</th>
								<th>姓名</th>
								<th>电话号码</th>
								<th>推荐编号</th>
								<th>报单中心</th>
								<th>推荐人数</th>
								<th>激活日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
<%--							<c:forEach var="item" items="${result}">--%>
<%--								<tr class="gradeX">--%>
<%--									<td>${item.number}</td>--%>
<%--									<td>${item.name}</td>--%>
<%--									<td>${item.phoneNumber }</td>--%>
<%--									<td>${item.recommendNumber }</td>--%>
<%--									<td>${item.activateNumber }</td>--%>
<%--									<td>${item.recommendCount }</td>--%>
<%--									<td>${item.activeDate }</td>--%>
<%--									<td>--%>
<%--<!--										<button class="btn" onclick="doMemberShowDetail('${item.id}')">详细信息</button>-->--%>
<%--										<button class="btn" onclick="doMemberLock('${item.id}')">--%>
<%--										<c:if test="${item.isLock eq 1 }">--%>
<%--											<font color="red">解锁</font>--%>
<%--										</c:if>--%>
<%--										<c:if test="${item.isLock eq 0 }">--%>
<%--											锁定--%>
<%--										</c:if>--%>
<%--										</button>--%>
<%--										<button class="btn" onclick="doResetPwd('${item.id}')">密码重置</button>--%>
<%--										<button class="btn" onclick="deleteActiveMember('${item.id}','${item.number}','${item.isService}','${item.recommendId}','${item.recommendNumber}','${item.activateId}','${item.activateNumber}')">删除会员</button>--%>
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