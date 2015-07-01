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
<script src="<%=basePath%>/resources/back/js/ztree/jquery.ztree.all-3.5.min.js"></script>
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
<body>
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
  <div class="container-fluid">
    <div class="row-fluid">
         <div align="center">
            <font size="30">欢迎使用会员消费平台！祝生活天天开心！</font>
       	 </div>
<!--       <div class="span6"> -->
<!--         <div class="widget-box"> -->
<!--           <div class="widget-title bg_ly" data-toggle="collapse" href="#collapseG2"><span class="icon"><i class="icon-chevron-down"></i></span> -->
<!--             <h5>Latest Posts</h5> -->
<!--           </div> -->
<!--           <div class="widget-content nopadding collapse in" id="collapseG2"> -->
<!--             <ul class="recent-posts"> -->
<!--               <li> -->
<%--                 <div class="user-thumb"> <img width="40" height="40" alt="User" src="<%=basePath%>/resources/back/images/demo/av1.jpg"> </div> --%>
<!--                 <div class="article-post"> <span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span> -->
<!--                   <p><a href="#">This is a much longer one that will go on for a few lines.It has multiple paragraphs and is full of waffle to pad out the comment.</a> </p> -->
<!--                 </div> -->
<!--               </li> -->
<!--               <li> -->
<%--                 <div class="user-thumb"> <img width="40" height="40" alt="User" src="<%=basePath%>/resources/back/images/demo/av2.jpg"> </div> --%>
<!--                 <div class="article-post"> <span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span> -->
<!--                   <p><a href="#">This is a much longer one that will go on for a few lines.It has multiple paragraphs and is full of waffle to pad out the comment.</a> </p> -->
<!--                 </div> -->
<!--               </li> -->
<!--               <li> -->
<%--                 <div class="user-thumb"> <img width="40" height="40" alt="User" src="<%=basePath%>/resources/back/images/demo/av4.jpg"> </div> --%>
<!--                 <div class="article-post"> <span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span> -->
<!--                   <p><a href="#">This is a much longer one that will go on for a few lines.Itaffle to pad out the comment.</a> </p> -->
<!--                 </div> -->
<!--               <li> -->
<!--                 <button class="btn btn-warning btn-mini">View All</button> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="widget-box"> -->
<!--           <div class="widget-title"> <span class="icon"><i class="icon-ok"></i></span> -->
<!--             <h5>To Do list</h5> -->
<!--           </div> -->
<!--           <div class="widget-content"> -->
<!--             <div class="todo"> -->
<!--               <ul> -->
<!--                 <li class="clearfix"> -->
<!--                   <div class="txt"> Luanch This theme on Themeforest <span class="by label">Nirav</span> <span class="date badge badge-important">Today</span> </div> -->
<!--                   <div class="pull-right"> <a class="tip" href="#" title="Edit Task"><i class="icon-pencil"></i></a> <a class="tip" href="#" title="Delete"><i class="icon-remove"></i></a> </div> -->
<!--                 </li> -->
<!--                 <li class="clearfix"> -->
<!--                   <div class="txt"> Manage Pending Orders <span class="by label">Alex</span> <span class="date badge badge-warning">Today</span> </div> -->
<!--                   <div class="pull-right"> <a class="tip" href="#" title="Edit Task"><i class="icon-pencil"></i></a> <a class="tip" href="#" title="Delete"><i class="icon-remove"></i></a> </div> -->
<!--                 </li> -->
<!--                 <li class="clearfix"> -->
<!--                   <div class="txt"> MAke your desk clean <span class="by label">Admin</span> <span class="date badge badge-success">Tomorrow</span> </div> -->
<!--                   <div class="pull-right"> <a class="tip" href="#" title="Edit Task"><i class="icon-pencil"></i></a> <a class="tip" href="#" title="Delete"><i class="icon-remove"></i></a> </div> -->
<!--                 </li> -->
<!--                 <li class="clearfix"> -->
<!--                   <div class="txt"> Today we celebrate the great looking theme <span class="by label">Admin</span> <span class="date badge badge-info">08.03.2013</span> </div> -->
<!--                   <div class="pull-right"> <a class="tip" href="#" title="Edit Task"><i class="icon-pencil"></i></a> <a class="tip" href="#" title="Delete"><i class="icon-remove"></i></a> </div> -->
<!--                 </li> -->
<!--                 <li class="clearfix"> -->
<!--                   <div class="txt"> Manage all the orders <span class="by label">Mark</span> <span class="date badge badge-info">12.03.2013</span> </div> -->
<!--                   <div class="pull-right"> <a class="tip" href="#" title="Edit Task"><i class="icon-pencil"></i></a> <a class="tip" href="#" title="Delete"><i class="icon-remove"></i></a> </div> -->
<!--                 </li> -->
<!--               </ul> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--       <div class="span6"> -->
<!--         <div class="widget-box"> -->
<!--           <div class="widget-title"> <span class="icon"><i class="icon-ok"></i></span> -->
<!--             <h5>Progress Box</h5> -->
<!--           </div> -->
<!--           <div class="widget-content"> -->
<!--             <ul class="unstyled"> -->
<!--               <li> <span class="icon24 icomoon-icon-arrow-up-2 green"></span> 81% Clicks <span class="pull-right strong">567</span> -->
<!--                 <div class="progress progress-striped "> -->
<!--                   <div style="width: 81%;" class="bar"></div> -->
<!--                 </div> -->
<!--               </li> -->
<!--               <li> <span class="icon24 icomoon-icon-arrow-up-2 green"></span> 72% Uniquie Clicks <span class="pull-right strong">507</span> -->
<!--                 <div class="progress progress-success progress-striped "> -->
<!--                   <div style="width: 72%;" class="bar"></div> -->
<!--                 </div> -->
<!--               </li> -->
<!--               <li> <span class="icon24 icomoon-icon-arrow-down-2 red"></span> 53% Impressions <span class="pull-right strong">457</span> -->
<!--                 <div class="progress progress-warning progress-striped "> -->
<!--                   <div style="width: 53%;" class="bar"></div> -->
<!--                 </div> -->
<!--               </li> -->
<!--               <li> <span class="icon24 icomoon-icon-arrow-up-2 green"></span> 3% Online Users <span class="pull-right strong">8</span> -->
<!--                 <div class="progress progress-danger progress-striped "> -->
<!--                   <div style="width: 3%;" class="bar"></div> -->
<!--                 </div> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="widget-box"> -->
<!--           <div class="widget-title bg_lo"  data-toggle="collapse" href="#collapseG3" > <span class="icon"> <i class="icon-chevron-down"></i> </span> -->
<!--             <h5>News updates</h5> -->
<!--           </div> -->
<!--           <div class="widget-content nopadding updates collapse in" id="collapseG3"> -->
<!--             <div class="new-update clearfix"><i class="icon-ok-sign"></i> -->
<!--               <div class="update-done"><a title="" href="#"><strong>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</strong></a> <span>dolor sit amet, consectetur adipiscing eli</span> </div> -->
<!--               <div class="update-date"><span class="update-day">20</span>jan</div> -->
<!--             </div> -->
<!--             <div class="new-update clearfix"> <i class="icon-gift"></i> <span class="update-notice"> <a title="" href="#"><strong>Congratulation Maruti, Happy Birthday </strong></a> <span>many many happy returns of the day</span> </span> <span class="update-date"><span class="update-day">11</span>jan</span> </div> -->
<!--             <div class="new-update clearfix"> <i class="icon-move"></i> <span class="update-alert"> <a title="" href="#"><strong>Maruti is a Responsive Admin theme</strong></a> <span>But already everything was solved. It will ...</span> </span> <span class="update-date"><span class="update-day">07</span>Jan</span> </div> -->
<!--             <div class="new-update clearfix"> <i class="icon-leaf"></i> <span class="update-done"> <a title="" href="#"><strong>Envato approved Maruti Admin template</strong></a> <span>i am very happy to approved by TF</span> </span> <span class="update-date"><span class="update-day">05</span>jan</span> </div> -->
<!--             <div class="new-update clearfix"> <i class="icon-question-sign"></i> <span class="update-notice"> <a title="" href="#"><strong>I am alwayse here if you have any question</strong></a> <span>we glad that you choose our template</span> </span> <span class="update-date"><span class="update-day">01</span>jan</span> </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
    </div>
  </div>
</div>
 <div id="showDialogDiv">
 </div>
<!--Footer-part-->
<div class="row-fluid">
  <div id="footer" class="span12"> 2013 &copy; Matrix Admin. More Templates <a href="http://www.mycodes.net/" target="_blank">源码之家</a></div>
</div>
<!--end-Footer-part-->
</body>
</html>
