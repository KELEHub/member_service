<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html lang="en">
<head>
<base href="<%=basePath%>">
<title>管理中心</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/fullcalendar.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/matrix-style.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/matrix-media.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/js/ztree/css/zTreeStyle/metro.css">
<link  type="text/css"  rel="stylesheet"  href="<%=basePath%>/resources/back/css/lightbox.css"/>
<link href="<%=basePath%>/resources/back/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.useso.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<script src="<%=basePath%>/resources/back/js/excanvas.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.ui.custom.js"></script> 
<script src="<%=basePath%>/resources/back/js/bootstrap.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.flot.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.flot.resize.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.peity.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/matrix.js"></script> 
<script src="<%=basePath%>/resources/back/js/fullcalendar.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/matrix.calendar.js"></script> 
<script src="<%=basePath%>/resources/back/js/matrix.chat.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.validate.js"></script> 
<script src="<%=basePath%>/resources/back/js/matrix.form_validation.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.wizard.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.uniform.js"></script> 
<script src="<%=basePath%>/resources/back/js/select2.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/matrix.popover.js"></script> 
<script src="<%=basePath%>/resources/back/js/jquery.dataTables.min.js"></script> 
<script src="<%=basePath%>/resources/back/js/matrix.tables.js"></script> 
<script src="<%=basePath%>/resources/back/js/matrix.interface.js"></script>
<script src="<%=basePath%>/resources/back/js/nicEdit.js"></script>
<script src="<%=basePath%>/resources/back/js/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/back/js/fileUpload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/back/js/fileUpload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/back/js/fileUpload/jquery.fileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/back/js/lightbox/lightbox.min.js"></script>
<script src="<%=basePath%>/resources/back/common_map.js"></script>
<script src="<%=basePath%>/resources/back/bankInfo.js"></script>
<script src="<%=basePath%>/resources/back/common.js"></script>
<script type="text/javascript">
$(function(){
	var bathPath=$("#basePath").val();
// 	alert(bathPath);
// 	$('#testPage').dataTable({
// 		"bLengthChange": false,
// 		"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
// 		"bJQueryUI" : true, //是否使用 jQury的UI theme  
// //		"bPaginate" : true, //是否显示（应用）分页器  
// 		"bAutoWidth" : true, //是否自适应宽度 
// 		"bScrollCollapse" : true,
// 		"bSort": false,  
// 		"sPaginationType":"full_numbers",
// 		"bInfo": true,//页脚信息
// 		"bFilter" : false,//是否启动过滤、搜索功能
// 		"bServerSide": true,
// 		"sAjaxSource": bathPath+"/testPage/getPage", //给服务器发请求的url
// 		"oLanguage": { //国际化配置  
// 	        "sProcessing" : "正在获取数据，请稍后...",    
// 	        "sLengthMenu" : "显示 _MENU_ 条",    
// 	        "sZeroRecords" : "没有您要搜索的内容",    
// 	        "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",    
// 	        "sInfoEmpty" : "记录数为0",    
// 	        "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
// 	        "sInfoPostFix" : "",    
// 	        "sSearch" : "搜索",    
// 	        "sUrl" : "",    
// 	        "oPaginate": {    
// 	            "sFirst" : "第一页",    
// 	            "sPrevious" : "上一页",    
// 	            "sNext" : "下一页",    
// 	            "sLast" : "最后一页"    
// 	        }  
// 		}
// 	});
		$("#testPage").dataTable({
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
                "sAjaxSource": bathPath+"/testPage/getPage.do",
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
                        { "mData": "id" },
                        { "mData": "testName" },
                        { "mData": "testDate" }
                    ]
                //"fnServerParams": function (aoData) {
                //    aoData.push({ "name": "name1", "value": "value1" });
                //    aoData.push({ "name": "name2", "value": "value2" });
                //}
            });
});
</script>
</head>
<body>
<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">会员管理系统</a></h1>
</div>
<!--close-Header-part--> 

<!--top-Header-menu-->
<input id="basePath" type="hidden" value="<%=basePath%>">
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a><i class="icon icon-user"></i>  <span class="text" style="font-size: 22px">Welcome ${username}</span><b class="caret"></b></a>
    </li>
    <li class=""><a title="" onclick="logout('/LoginController/logout.do')"><i class="icon icon-share-alt"></i> <span class="text"  style="font-size: 22px">退出系统</span></a></li>
  </ul>
</div>

<!--close-top-serch--> 

<!--sidebar-menu-->

<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard2</a>
  <ul>
       <li class="submenu"> 
       	<a href="#"><i class="icon icon-info-sign"></i> <span>分页测试</span></a>
       		<ul>
	          <li><a onclick="">分页测试</a></li>
	      </ul>
        </li>
    </ul>
</div>
<div id="content" style="overflow: auto;">
<table id="testPage" width="100%" border="0" cellspacing="0" cellpadding="0">
      <thead>
        <tr>
          <td>列1</td>
          <td>列2</td>
          <td>列3</td>
        </tr>
      </thead>
      <tbody></tbody>
    </table>
					</table>
</div>
 <div id="showDialogDiv">
 </div>
<!--Footer-part-->
<div class="row-fluid">
</div>
<!--end-Footer-part-->
</body>
</html>
