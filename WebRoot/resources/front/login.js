
function checkspace(checkstr) {
  var str = '';
  for(i = 0; i < checkstr.length; i++) {
    str = str + ' ';
  }
  return (str == checkstr);
}


// 登录
function login() {
	var name = $("#txtUserName").val();
	if (checkspace(name)) {
		alert("对不起，用户名不能为空，请重新输入！");
		$("#txtUserName").focus();
		return false;
	}
	var pwd = $("#txtPwd").val();
	if (checkspace(pwd)) {
		alert("对不起，密码不能为空，请重新输入！");
		$("#txtPwd").focus();
		return false;
	}

	$("#memberloginForm").submit();
}

// 回车
function txtUserNamekeyEnter(e) {
	var ev = document.all ? window.event : e;
	if (ev.keyCode == 13) {
		$("#txtPwd").focus();
	}
}

function txtPwdkeyEnter(e) {
	var ev = document.all ? window.event : e;
	if (ev.keyCode == 13) {
		login();
	}
}

$(document).ready(function() {
		/*	    $.getScript(basePath+'js/jquery/jquery.md5.js',function(){
			    });*/
				$("#txtUserName").focus();

				$("#btnLogin").click(function() {
							login();
						});
				// 重置
				$("#btnCancel").click(function() {
							$("#txtUserName").val("");
							$("#txtPwd").val("");
							$("#txtUserName").focus();
						});
//				$("#txtUserName").change(function() {
//							$("#txtPwd").val(frame.Cookie.getValue('userPassword'));
//						});
				$("#txtUserName").bind('keydown', txtUserNamekeyEnter);
				$("#txtPwd").bind('keydown', txtPwdkeyEnter);
			
		});