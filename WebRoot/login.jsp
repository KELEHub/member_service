<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String loginReturnWarnMsg = session
			.getAttribute("loginReturnWarnMsg") == null ? "" : session
			.getAttribute("loginReturnWarnMsg").toString();
	//移除  防止刷新时重复显示 
	session.removeAttribute("loginReturnWarnMsg");
%>
<!DOCTYPE html>
<html lang="cn">

	<head>
		<title>登录</title>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="resources/back/css/bootstrap.min.css" />
		<link rel="stylesheet"
			href="resources/back/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="resources/back/css/matrix-login.css" />
		<link href="resources/back/font-awesome/css/font-awesome.css"
			rel="stylesheet" />
		<link href='http://fonts.useso.com/css?family=Open+Sans:400,700,800'
			rel='stylesheet' type='text/css'>
		<script type="text/javascript"
			src="<%=basePath%>resources/back/js/jquery/jquery.js">
</script>

		
</script>
		<script type="text/javascript"
			src="<%=basePath%>resources/back/login.js">
</script>
		<script type="text/javascript">
basePath = "<%=basePath%>";
function refresh(obj) {
	obj.src = "<%=basePath%>servlet/ImageCodeMakerServlet?" + Math.random();
}

$(document).ready(
		function() {
			if (!window.console) {
				var names = [ "log", "debug", "info", "warn", "error",
						"assert", "dir", "dirxml", "group", "groupEnd", "time",
						"timeEnd", "count", "trace", "profile", "profileEnd" ];
				window.console = {};
				for ( var i = 0; i < names.length; ++i)
					window.console[names[i]] = function() {
					}
			}

			if ('<%=loginReturnWarnMsg%>' != null
					&& '<%=loginReturnWarnMsg%>' != '') {
				//  $("#btnCancel").after("<br><p color='red' align='left'>"+loginReturnMsg+"</p>");
				$("#loginInfo").get(0).innerHTML = '<%=loginReturnWarnMsg%>';

			}

		})
</script>
	</head>
	<body>
		<div id="loginbox">
		<form id="exprtForm" method="post" class="form-vertical"
				action="ExportController/export.do">
				</form>
			<form id="loginForm" method="post" class="form-vertical"
				action="LoginController/login.do">
				<div class="control-group normal_text">
					<h3>
						<img src="resources/back/img/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"><i class="icon-user"></i>
							</span>
							<input type="text" name="userName" id="txtUserName" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"><i class="icon-lock"></i>
							</span>
							<input type="password" name="password" id="txtPwd" />
						</div>
					</div>
				</div>



				<div class="form-actions">
					<span class="pull-right"><a type="submit" id='btnLogin'
						class="btn btn-success" /> 登录</a>
					</span>
				</div>
				<div class="form-actions">
					<span class="pull-right"><a type="submit" id='btnExport'
						class="btn btn-success" /> 数据导入</a>
					</span>
				</div>
			</form>
			<br>
			<p id="loginInfo" style="color: #FF3399;"></p>
			<table align=center style="padding-top: 10px">
				<tr>
					<td style="text-align: center">
					</td>
				</tr>
			</table>

		</div>

	</body>

</html>
