<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$(function(){
	var bathPath=$("#basePath").val();
		$("#notActiveMemberTable").dataTable({
                "bLengthChange": false,
                "bFilter": false,
                "bSort": false,
                "sPaginationType": "full_numbers",   
        		"bJQueryUI" : true, //是否使用 jQury的UI theme  
        		"bPaginate" : true, //是否显示（应用）分页器  
        		"bAutoWidth" : true, //是否自适应宽度 
        		"bScrollCollapse" : true,
        		"iDisplayLength": 5,//每页显示5条数据
        		"bLengthChange":true,
        		"aLengthMenu":[5,10],
        		"bSort": false,  
        		"bInfo": true,//页脚信息
        		"bFilter" : false,//是否启动过滤、搜索功能
                "bProcessing": true,
                "bServerSide": true,
                "sAjaxSource": bathPath+"/membermanage/getNotActivationMemberPage.do",
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
                        { "mData": "phoneNumber" },
                        { "mData": "recommendNumber" },
                        { "mData": "activateNumber" },
                        { "mData": "registerDate" },
                        { "sDefaultContent" : ""}
                        
                    ],
               "fnRowCallback" : function(nRow, aData, iDisplayIndex) {  
                       	$('td:eq(6)', nRow).html("<button class=\"btn\" onclick=\"doMemberShowDetail("+aData.id+")\">详细信息</button>" +
                       	"<button class=\"btn\" onclick=\"doDeleteMember("+aData.id+")\">删除</button>");
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
		未激活会员资料
	</h1>
	<form action="/membermanage/searchNotActivationMember.do" method="POST"
							id="searchActiveMemberForm">
<!-- 		<div class="controls controls-row"> -->
<!-- 			<div class="control-group" style="float: left;margin-left: 10px;"> -->
<!-- 				<label class="control-label">会员账号:</label> -->
<!-- 				<div class="controls"> -->
<%-- 					<input type="text" name="number" value="${form.number}"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="control-group" style="float: left;margin-left: 10px;"> -->
<!-- 				<label class="control-label">推荐编号:</label> -->
<!-- 				<div class="controls"> -->
<%-- 					<input type="text" name="recommendNumber"  value="${form.recommendNumber }"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="control-group" style="float: left;margin-left: 10px;"> -->
<!-- 				<label class="control-label">报单中心:</label> -->
<!-- 				<div class="controls"> -->
<%-- 					<input type="text" name="serviceNumber"   value="${form.serviceNumber }"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</form>
<!-- 	<div class="control-group" style="float: left;margin-left: 10px;"> -->
<!-- 			<a class="btn" onclick="searchMembers('searchActiveMemberForm')">查询</a> -->
<!-- 			</div> -->
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="notActiveMemberTable" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>账号</th>
								<th>姓名</th>
								<th>电话号码</th>
								<th>推荐编号</th>
								<th>报单中心</th>
								<th>注册日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
<%--							<c:forEach var="item" items="${result }">--%>
<%--								<tr class="gradeX">--%>
<%--									<td>${item.number}</td>--%>
<%--									<td>${item.name}</td>--%>
<%--									<td>${item.phoneNumber }</td>--%>
<%--									<td>${item.recommendNumber }</td>--%>
<%--									<td>${item.activateNumber }</td>--%>
<%--									<td>${item.registerDate }</td>--%>
<%--									<td>--%>
<%--										<button class="btn" onclick="doMemberShowDetail('${item.id}')">详细信息</button>--%>
<%--										<button class="btn" onclick="doDeleteMember('${item.id}')">删除</button>--%>
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