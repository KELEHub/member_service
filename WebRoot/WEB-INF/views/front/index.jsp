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
<title>会员中心</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/fullcalendar.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/matrix-style.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/css/matrix-media.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/back/js/ztree/css/zTreeStyle/metro.css">
<link type="text/css"  rel="stylesheet"  href="<%=basePath%>/resources/back/css/lightbox.css"/>
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
<script src="<%=basePath%>/resources/back/js/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="<%=basePath%>/resources/back/js/nicEdit.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/front/js/fileUpload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/front/js/fileUpload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/front/js/fileUpload/jquery.fileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/front/js/lightbox/lightbox.min.js"></script>
<script src="<%=basePath%>/resources/front/common_map.js"></script>
<script src="<%=basePath%>/resources/front/bankInfo.js"></script>
<script src="<%=basePath%>/resources/front/common.js"></script>
<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }
  setProvinceMap();
// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
</script>
</head>
<body style="overflow: hidden;">
<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">会员中心</a></h1>
</div>
<!--close-Header-part--> 

<!--top-Header-menu-->
<input id="basePath" type="hidden" value="<%=basePath%>">
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a><i class="icon icon-user"></i>  <span class="text">Welcome ${username}</span><b class="caret"></b></a>
    </li>
    <li class=""><a title="" onclick="logout('/MemberLoginController/logout.do')"><i class="icon icon-share-alt"></i> <span class="text">退出系统</span></a></li>
  </ul>
</div>

<!--close-top-serch--> 

<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard2</a>
  <ul>
    <c:forEach var="item1" items="${menuList}">
       <li class="submenu"> 
       	<a href="#"><i class="icon icon-info-sign"></i> <span>${item1.menunm}</span></a>
       		<ul>
	      <c:forEach var="item2" items="${item1.childMenu}" >
	          <li><a onclick="menuClick('${item2.menuurl}')">${item2.menunm}</a></li>
	      </c:forEach>
	      </ul>
        </li>
    </c:forEach>
    </ul>
</div>
<div id="content" style="overflow: auto;">
  <div id="content-header">
    <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<div class="container-fluid" id="latestNewsDiv">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>最新资讯</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered">
						<thead>
							<tr>
								<th>类型</th>
								<th>标题</th>
								<th>日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.category}</td>
									<td>${item.title}</td>
									<td>${item.date}</td>
									<td> <button class="btn" onclick="getNews('${item.id}')">查看</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
 <div id="showDialogDiv">
 </div>
<!--Footer-part-->
<!--<div class="row-fluid">-->
<!--  <div id="footer" class="span12"> 2013 &copy; Matrix Admin. More Templates <a href="http://www.mycodes.net/" target="_blank">源码之家</a></div>-->
<!--</div>-->
<!--end-Footer-part-->
</body>
</html>
