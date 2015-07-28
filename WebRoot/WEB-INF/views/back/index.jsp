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
<body style="overflow:hidden">
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
<div id="content">
 
<!--   <div  class="quick-actions_homepage"> -->
<!--     <ul class="quick-actions"> -->
<!--       <li class="bg_lb"> <a href="#"> <i class="icon-dashboard"></i> My Dashboard </a> </li> -->
<!--       <li class="bg_lg"> <a href="#"> <i class="icon-shopping-cart"></i> Shopping Cart</a> </li> -->
<!--       <li class="bg_ly"> <a href="#"> <i class=" icon-globe"></i> Web Marketing </a> </li> -->
<!--       <li class="bg_lo"> <a href="#"> <i class="icon-group"></i> Manage Users </a> </li> -->
<!--       <li class="bg_ls"> <a href="#"> <i class="icon-signal"></i> Check Statistics</a> </li> -->
<!--     </ul> -->
<!--   </div> -->
  <div class="container-fluid">
    <div class="row-fluid">
           <div align="center">
            <img src="<%=basePath%>/resources/back/img/000.jpg"  alt="欢迎使用后台管理系统！" height="100%" width="100%" />
       	 </div>
<!--     <div class="row-fluid"> -->
<!--       <div class="span12"> -->
<!--         <div class="widget-box widget-calendar"> -->
<!--           <div class="widget-title"> <span class="icon"><i class="icon-calendar"></i></span> -->
<!--             <h5>Calendar</h5> -->
<!--             <div class="buttons"> <a id="add-event" data-toggle="modal" href="#modal-add-event" class="btn btn-inverse btn-mini"><i class="icon-plus icon-white"></i> Add new event</a> -->
<!--               <div class="modal hide" id="modal-add-event"> -->
<!--                 <div class="modal-header"> -->
<!--                   <button type="button" class="close" data-dismiss="modal">×</button> -->
<!--                   <h3>Add a new event</h3> -->
<!--                 </div> -->
<!--                 <div class="modal-body"> -->
<!--                   <p>Enter event name:</p> -->
<!--                   <p> -->
<!--                     <input id="event-name" type="text" /> -->
<!--                   </p> -->
<!--                 </div> -->
<!--                 <div class="modal-footer"> <a href="#" class="btn" data-dismiss="modal">Cancel</a> <a href="#" id="add-event-submit" class="btn btn-primary">Add event</a> </div> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--           <div class="widget-content"> -->
<!--             <div class="panel-left"> -->
<!--               <div id="fullcalendar"></div> -->
<!--             </div> -->
<!--             <div id="external-events" class="panel-right"> -->
<!--               <div class="panel-title"> -->
<!--                 <h5>Drag Events to the calander</h5> -->
<!--               </div> -->
<!--               <div class="panel-content"> -->
<!--                 <div class="external-event ui-draggable label label-inverse">My Event 1</div> -->
<!--                 <div class="external-event ui-draggable label label-inverse">My Event 2</div> -->
<!--                 <div class="external-event ui-draggable label label-inverse">My Event 3</div> -->
<!--                 <div class="external-event ui-draggable label label-inverse">My Event 4</div> -->
<!--                 <div class="external-event ui-draggable label label-inverse">My Event 5</div> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
    </div>
  </div>
</div>
 <div id="showDialogDiv">
 </div>
<!--Footer-part-->
<div class="row-fluid">
</div>
<!--end-Footer-part-->
</body>
</html>