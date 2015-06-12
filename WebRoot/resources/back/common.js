/**
 * 
 */
function ajaxRequestForMenu(reqUrl,reqData){
	var returnData={};
	var bathPath=$("#basePath").val();
	$.ajax({
		url : bathPath+reqUrl,
		type : 'POST',
		cache : false,
		async : false,
		data : reqData,
		contentType: "application/json",
		dataType : 'text',
		success : function(data) {
			returnData = data;
		},
		error : function (msg) {
			alert(msg);
        }
	});
	return returnData;
}

function menuClick(url){
	var reqDate = {};
	var result = ajaxRequestForMenu(url, reqDate);
	$("#content").empty();
	$("#content").html(result);
	findJqueryFileUpload("content");
	resetTable();
}

function ajaxRequestForFormGetJson(sFormId){
	var bathPath=$("#basePath").val();
	var reqUrl = $("#"+sFormId).attr('action')
	var reqObj = $('#' + sFormId).serializeJson();
	var reqData={};
	if(reqObj!=null){
		reqData = JSON.stringify(reqObj);
	}
	var returnData={};
	$.ajax({
		url : bathPath+reqUrl,
		type : 'POST',
		cache : false,
		async : false,//同步 or 异步
		data :  reqData,
		contentType: "application/json",
		dataType : 'json',
		success : function(data) {
				returnData=data;
		},
		error : function (msg) {
			alert(msg);
       }
	});
	return returnData;
}

function ajaxRequestForJsonGetJson(reqUrl,reqObj){
	var bathPath=$("#basePath").val();
	var reqData={};
	if(reqObj!=null){
		reqData = JSON.stringify(reqObj);
	}
	var returnData={};
	$.ajax({
		url : bathPath+reqUrl,
		type : 'POST',
		cache : false,
		async : false,//同步 or 异步
		data :  reqData,
		contentType: "application/json",
		dataType : 'json',
		success : function(data) {
				returnData=data;
		},
		error : function (msg) {
			alert(msg);
       }
	});
	return returnData;
}

function ajaxRequestForFormGetJsp(sFormId){
	var bathPath=$("#basePath").val();
	var reqUrl = $("#"+sFormId).attr('action')
	var reqObj = $('#' + sFormId).serializeJson();
	var reqData={};
	if(reqObj!=null){
		reqData = JSON.stringify(reqObj);
	}
	var returnData={};
	$.ajax({
		url : bathPath+reqUrl,
		type : 'POST',
		cache : false,
		async : false,//同步 or 异步
		data :  reqData,
		contentType: "application/json",
		dataType : 'text',
		success : function(data) {
				$("#content").empty();
				$("#content").html(data);
		},
		error : function (msg) {
			alert(msg);
       }
	});
}

$.fn.serializeJson=function(){  
    var serializeObj={};  
    var disabled = $(this).find(':disabled');
    disabled.removeAttr('disabled');
    var array=this.serializeArray();  
    disabled.attr('disabled','disabled');
    var str=this.serialize();  
    $(array).each(function(){  
        if(serializeObj[this.name]!=undefined){  
            if($.isArray(serializeObj[this.name])){  
                serializeObj[this.name].push(this.value);  
            }else{  
                serializeObj[this.name]=[serializeObj[this.name],this.value];  
            }  
        }else{  
            serializeObj[this.name]=this.value;   
        }  
    });  
    return serializeObj;  
};

function saveRole(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}

function searchMembers(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
	resetTable();
}

function doMemberShowDetail(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/membermanage/showMemberDetails.do", reqObj, "showmemberdetail");
	region_init("select_province","select_city","select_area");
	region_init("select_province1","select_city1","select_area1");
}

function showWithDrawalsDetail(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/withdrawals/showWithDrawalsDetail.do", reqObj, "showWithDrawalsDetail");
}

function showOrderListDetail(id){
	var reqObj = {};
	reqObj["id"] = id;
	showDynamicDialog("/orderlist/getOrderListDetail.do", reqObj, "showOrderListDetail");
}

function deliveryOrder(id){
	if (window.confirm('您去顶发货吗？')) {
		var reqObj = {};
		reqObj["id"] = id;
		var result = ajaxRequestForJsonGetJson("/orderlist/deliveryOrder.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$('#myModal').modal('hide');
			$("#content-header").find("form").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
}

function searchOrderList(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
	resetTable();
}

function saveMemberDetail(sFormId) {
	var result = ajaxRequestForFormGetJson(sFormId);
	if (result.success) {
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form").each(function() {
			var formid = this.id;
			ajaxRequestForFormGetJsp(formid);
			resetTable();
		});
	}
}

function doMemberLock(memberId){
	if (window.confirm('您确定要锁定该用户么？')) {
		var reqObj = {};
		reqObj["id"] = memberId;
		var result = ajaxRequestForJsonGetJson("/membermanage/doMemberLock.do",
				reqObj);
		if (result.success) {
			alert(result.msg);
		}
	}
}

function doResetPwd(memberId){
	if (window.confirm('您确定要重置该会员密码么？')) {
		var reqObj = {};
		reqObj["id"] = memberId;
		var result = ajaxRequestForJsonGetJson("/membermanage/doResetPwd.do",
				reqObj);
		if (result.success) {
			alert(result.msg);
		}
	}
}

function doDeleteMember(memberId){
	if (window.confirm('您确定要删除当前用户么？')) {
		var reqObj = {};
		reqObj["id"] = memberId;
		var result = ajaxRequestForJsonGetJson("/membermanage/deleteMember.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$('#myModal').modal('hide');
			$("#content-header").find("form").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
}

function saveParameter(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
	}
}

function saveLimeteDeclaration(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$("#content-header").find("form[id='searchLimiteForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
		});
	}
}

function saveNotice(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form[id='noticeManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}

function saveTickling(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form[id='notdoTicklingManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}

function saveCreateUser(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$("#content-header").find("form[id='searchUserForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}

function serchUser(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
}


function saveBank(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$("#content-header").find("form[id='searchBankForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}



function deleteBank(bankname){
	 event.returnValue = confirm("删除是不可恢复的，你确认要删除吗？");
	 if(event.returnValue){
		 var reqObj={};
	   reqObj["bankName"]=bankname;
      var result = ajaxRequestForJsonGetJson("/BankController/delete.do", reqObj);
	  if(result.success){
		alert(result.msg);
		$("#content-header").find("form[id='searchBankForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
	 }
	
}


function deleteUser(username){
	 event.returnValue = confirm("删除是不可恢复的，你确认要删除吗？");
	 if(event.returnValue){
		 var reqObj={};
	   reqObj["username"]=username;
      var result = ajaxRequestForJsonGetJson("/CreateUserController/delete.do", reqObj);
	  if(result.success){
		alert(result.msg);
		$("#content-header").find("form[id='searchUserForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
	 }
	
}

function showDynamicDialog(reqUrl,reqObj,dialogId){
	var bathPath=$("#basePath").val();
	var reqData={};
	if(reqObj!=null){
		reqData = JSON.stringify(reqObj);
	}
	var returnData={};
	$.ajax({
		url : bathPath+reqUrl,
		type : 'POST',
		cache : false,
		async : false,//同步 or 异步
		data :  reqData,
		contentType: "application/json",
		dataType : 'text',
		success : function(data) {
				$("#showDialogDiv").html(data);
				$('#'+dialogId).modal('show');
				findJqueryFileUpload("showDialogDiv");
		},
		error : function (msg) {
			alert(msg);
       }
	});
}

function resetTable(){
	$('#testexample1').dataTable({
		"bLengthChange": false,
		"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
		"bJQueryUI" : true, //是否使用 jQury的UI theme  
//		"bPaginate" : true, //是否显示（应用）分页器  
		"bAutoWidth" : true, //是否自适应宽度 
		"bScrollCollapse" : true,
		"sPaginationType":"full_numbers",
		"bInfo": true,//页脚信息
		"bFilter" : false,//是否启动过滤、搜索功能
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
		}
	});
}

//权限菜单书的定义
var authTreeSetting = {
		check: {
			enable: true
		},
		data: {
			key:{
				name:"menuNm"
			},
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "parentMenuId",
				rootPId: "0"
			}
		},
		callback: {
			onCheck: authTreeOnCheck
		}
	};

function allocationAuth(roleId,roleDsc){
	var reqObj={};
	reqObj["id"]=roleId;
	reqObj["roleDsc"]=roleDsc;
	showDynamicDialog("/rolemanage/showAllocationAuth.do", reqObj, "showAllocationAuth")
	
	var result = ajaxRequestForJsonGetJson("/rolemanage/getMenuInfo.do", {});
	var zNodes = result.result;
	var treeObj = $.fn.zTree.init($("#showAllocationAuthTree"), authTreeSetting, zNodes);
	
//	var treeObj = $.fn.zTree.getZTreeObj("#showAllocationAuthTree");
	//展开所有节点
	treeObj.expandAll(true);
	
	//取得所有的节点
	var nodes = treeObj.transformToArray(treeObj.getNodes());
	//将userIds转换成数组
	var existMenuIds = $("#existMenuIds").val();
//	var existMenuIdArr = existMenuIds.split(",");
	var existMenuIdArr = eval(existMenuIds);
	//循环所有节点判断是否有选中
	for (var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		// 取得当前节点的编号
		var menuid = node.id;
		//获取当前菜单的子节点
		var leafNodeArr = [];
		getLeafNode(node,leafNodeArr);
		for (var m = 0; m < leafNodeArr.length; m++) {
			var leafNode = leafNodeArr[m];
			for (var l = 0; l < existMenuIdArr.length; l++) {
				var existMenuId = existMenuIdArr[l];
				if (leafNode.id == existMenuId) {
					treeObj.checkNode(leafNode, true, true);
				} else {

				}
			}
		}
	}
}

function getLeafNode(treeNode,reasult){
	if(treeNode.isParent){
		var childrenNodes = treeNode.children;
		for (var i = 0; i < childrenNodes.length; i++) {
			getLeafNode(childrenNodes[i],reasult);
		}
	}else{
		reasult.push(treeNode);
	}
}

function authTreeOnCheck(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getCheckedNodes(true);
	var selectNodeArr = [];
	for (var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		selectNodeArr.push(node.id);
	}
	$("#authMenuIds").val(JSON.stringify(selectNodeArr));
}

function saveAllocationAuth(){
	var reqObj={};
	reqObj["id"]=$("#authMenuRoleId").val();
	var authMenuIds = $("#authMenuIds").val();
	reqObj["menuids"]=eval(authMenuIds);
	var result = ajaxRequestForJsonGetJson("/rolemanage/saveAllocationAuth.do", reqObj);
	if(result.success){
		alert(result.msg);
//		$('#myModal').modal('hide');
//		$("#content-header").find("form").each(function(){
//				var formid = this.id;
//				ajaxRequestForFormGetJsp(formid);
//				resetTable();
//		});
	}
}

function agreewithdrawals(id){
	var reqObj={};
	reqObj["id"]=id;
	var result = ajaxRequestForJsonGetJson("/withdrawals/agreewithdrawals.do", reqObj);
	alert(result.msg);
	if (result.success) {
		$('#myModal').modal('hide');
		$("#content-header").find("form").each(function() {
			var formid = this.id;
			ajaxRequestForFormGetJsp(formid);
			resetTable();
		});
	}
}

function disAgreewithdrawals(id){
	//TODO
	alert("留言板敬请期待。。。。");
}

function saveWithFormAddRefeshTable(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	alert(result.msg);
	if(result.success){
		$('#showAddProductDetail').modal('hide');
		$("#content-header").find("form").each(function() {
			var formid = this.id;
			ajaxRequestForFormGetJsp(formid);
			resetTable();
		});
	}
}

function editProduct(id){
	var reqObj = {};
	reqObj["id"] = id;
	showDynamicDialog("/product/getProduDetail.do", reqObj, "showProductDetail");
}

function deleteProduct(id){
	if (window.confirm('您确定要删除当前产品么？')) {
		var reqObj = {};
		reqObj["id"] = id;
		var result = ajaxRequestForJsonGetJson("/product/deleteProduct.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$('#myModal').modal('hide');
			$("#content-header").find("form").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
}
//退出系统
function logout(reqUrl){
	var bathPath=$("#basePath").val();
	window.location.href=bathPath+reqUrl;
}

function editNotice(arg_id,arg_title,arg_category,arg_content){
	document.getElementById("noticeListId").value = arg_id;
	document.getElementById("editNotice_title").value = arg_title;
	document.getElementById("editNotice_category").value = arg_category;
	document.getElementById("editNotice_content").value = arg_content;
}

function doDeleteNotice(noticeId){
	if (window.confirm('您确定要删除该公告么？')) {
		var reqObj = {};
		reqObj["noticeId"] = noticeId;
		var result = ajaxRequestForJsonGetJson("/NoticeManagerController/deleteNotice.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$('#myModal').modal('hide');
			$("#content-header").find("form[id='noticeManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
			});
		}
	}
}

function replyTickling(arg_id,arg_title,arg_content){
	document.getElementById("replyTickling_id").value = arg_id;
	document.getElementById("replyTickling_title").value = arg_title;
	document.getElementById("replyTickling_content").value = arg_content;
}

function deleteTickling(ticklingId){
	if (window.confirm('您确定要删除该留言么？')) {
		var reqObj = {};
		reqObj["id"] = ticklingId;
		var result = ajaxRequestForJsonGetJson("/TicklingManagerController/deleteTickling.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$('#myModal').modal('hide');
			$("#content-header").find("form[id='notdoTicklingManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
			});
		}
	}
}

function checkTickling(arg_id,arg_title,arg_content,arg_replyContent){
	document.getElementById("checkTickling_title").value = arg_title;
	document.getElementById("checkTickling_content").value = arg_content;
	document.getElementById("checkTickling_replyContent").value = arg_replyContent;
}

function deleteDoneTickling(ticklingId){
	if (window.confirm('您确定要删除该留言么？')) {
		var reqObj = {};
		reqObj["id"] = ticklingId;
		var result = ajaxRequestForJsonGetJson("/TicklingManagerController/deleteTickling.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$('#myModal').modal('hide');
			$("#content-header").find("form[id='doneTicklingManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
			});
		}
	}
}

function findJqueryFileUpload(divID) {
	$("#" + divID).find("input[name='files']").each(function(index) {
		$(this).fileupload();
	});
}

function uploadFileWithPar(inputObj){
	$(inputObj).fileupload({
		formData:  eval("(" + $(inputObj).attr("para") + ")"),
		done:function(e,result){ 
			var returnArr = result.result;
			if(returnArr.success){
				var relativePath = returnArr.result[0].relativePath;
				var absolutePath = returnArr.result[0].absolutePath;
				//设置隐藏域的文件名
				$(e.target.previousElementSibling).val(relativePath);
				$(e.target).next().children(["img"]).attr('src',absolutePath+relativePath);
				$(e.target).next().attr('href',absolutePath+relativePath);
			}
		}
	});
}

function serviceInfoDetail(serviceId){
	var reqObj = {};
	reqObj["id"] = serviceId;
	showDynamicDialog("/ServiceManagerController/showServiceInfoDetail.do", reqObj, "showServiceDetail");
}

function rechargeService(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form[id='serviceManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}

function rechargeToForm(arg_id){
	document.getElementById("serviceInfo_serviceId").value = arg_id;
}

  //格式化金额
//优化负数格式化问题
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

function formatter(o, blur) {
	if(o.value==""){
		o.value = "0.00"
		return;
	}
	o.value = fmoney(o.value,2);
}