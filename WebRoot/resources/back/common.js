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
	if (typeof ndinstance1_replyTickling_replyContent != "undefined") {
		if (ndinstance1_replyTickling_replyContent) {
			reqObj["replyContent"] = ndinstance1_replyTickling_replyContent.getContent();
		}
	}
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

function searchGifts(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
	resetTable();
}

function doAlertInfo(id){
	alert(id);
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

function showWithDrawalsRefuseReason(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/withdrawals/showRefuseReason.do", reqObj, "showRefuseReason1");
}

function showChargeDetail(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/charge/showChargeDetail.do", reqObj, "showChargeDetail");
}

function showChargeRefuseReason(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/charge/showRefuseReason.do", reqObj, "showRefuseReason");
}

function showOrderListDetail(id){
	var reqObj = {};
	reqObj["id"] = id;
	showDynamicDialog("/orderlist/getOrderListDetail.do", reqObj, "showOrderListDetail");
}

function deliveryOrder(id){
	if (window.confirm('您确定发货吗？')) {
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
	var bathPath=$("#basePath").val();
	var reqUrl = $("#"+sFormId).attr('action')
	var reqObj = $('#' + sFormId).serializeJson();
	
	//处理银行的省市区
	reqObj["bankProvince"] = provinceMap2.get(parseInt(reqObj["bankProvince"]));
	reqObj["bankCity"] = cityMap2.get(parseInt(reqObj["bankCity"]));
	reqObj["bankCounty"] = countryMap2.get(parseInt(reqObj["bankCounty"]));
	//处理联系地址的省市区
	reqObj["linkProvince"] = provinceMap2.get(parseInt(reqObj["linkProvince"]));
	reqObj["linkCity"] = cityMap2.get(parseInt(reqObj["linkCity"]));
	reqObj["linkCounty"] = countryMap2.get(parseInt(reqObj["linkCounty"]));
	
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
			result=data;
		},
		error : function (msg) {
			alert(msg);
       }
	});
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
	if (window.confirm('您确定要操作该用户么？')) {
		var reqObj = {};
		reqObj["id"] = memberId;
		var result = ajaxRequestForJsonGetJson("/membermanage/doMemberLock.do",
				reqObj);
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
		$("#content-header").find("form").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
	});
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

function saveNotice(title,category,content){
	var reqObj={};
	reqObj["title"]=title;
	reqObj["category"]=category;
	reqObj["content"]=content;
    var result = ajaxRequestForJsonGetJson("/NoticeManagerController/releaseNotice.do", reqObj);
	//var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form[id='showNoticeForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}

function editNotice(noticeId,title,category,content){
	var reqObj={};
	reqObj["noticeId"]=noticeId;
	reqObj["title"]=title;
	reqObj["category"]=category;
	reqObj["content"]=content;
    var result = ajaxRequestForJsonGetJson("/NoticeManagerController/editNotice.do", reqObj);
	//var result = ajaxRequestForFormGetJson(sFormId);
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

function serchPointHistory(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
	resetTable();
}

function initMemberInfoUpdateSelect(){
	region_init("select_province","select_city","select_area",provinceMap.get($("#bankProvince").val()),cityMap.get($("#bankCity").val()),countryMap.get($("#bankCounty").val()));
	region_init("select_province1","select_city1","select_area1",provinceMap.get($("#linkProvince").val()),cityMap.get($("#linkCity").val()),countryMap.get($("#linkCounty").val()));
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


function sendGifts(sFormId){
	if(window.confirm('您确定要发送积分么？')){
		var result = ajaxRequestForFormGetJson(sFormId);
		if(result.success){
		     alert(result.msg);
		     $("#content-header").find("form[id='initGiftsForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
      }
	}
	
}

function editeDetail(giftsId){
	var reqObj = {};
	reqObj["id"] = giftsId;
	showDynamicDialog("/GiftsDetailsController/showDialog.do", reqObj, "editeGiftsDetails");
}

function saveGiftsDetail(sFormId) {
	if(window.confirm('您确定要修改么，修改后的数据不可恢复？')){
	  var result = ajaxRequestForFormGetJson(sFormId);
	  if (result.success) {
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form").each(function() {
			var formid = this.id;
			ajaxRequestForFormGetJsp(formid);
			resetTable();
		});
	}else{
		alert(result.msg);
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
		"bSort": false,  
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
	
	$("#testexample1_info").css({ float: "left", width: "30%"});
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
		$("#content-header").find("form[id='searchWidhdrawalsxForm']").each(function() {
			var formid = this.id;
			ajaxRequestForFormGetJsp(formid);
			resetTable();
		});
	}
}


function agreewithdrawalswithnopoints(id){
	var reqObj={};
	reqObj["id"]=id;
	var result = ajaxRequestForJsonGetJson("/withdrawals/agreewithdrawalswithnopoints.do", reqObj);
	alert(result.msg);
	if (result.success) {
		$('#myModal').modal('hide');
		$("#content-header").find("form[id='searchWidhdrawalsxForm']").each(function() {
			var formid = this.id;
			ajaxRequestForFormGetJsp(formid);
			resetTable();
		});
	}
}

function deletewithdrawals(id){
	if (window.confirm('删除的数据将不能被恢复，您确定要删除么？')) {
		var reqObj={};
		reqObj["id"]=id;
		var result = ajaxRequestForJsonGetJson("/withdrawals/deletewithdrawals.do", reqObj);
		alert(result.msg);
		if (result.success) {
			$('#myModal').modal('hide');
			$("#content-header").find("form[id='searchWidhdrawalsxForm']").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
}

function agreecharge(id){
	if (window.confirm('您确定充值么？')) {
		var reqObj={};
		reqObj["id"]=id;
		var result = ajaxRequestForJsonGetJson("/charge/agreeCharge.do", reqObj);
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
}

function disAgreewithdrawals(id){
	var reqObj = {};
	reqObj["id"] = id;
	showDynamicDialog("/withdrawals/showdisagreeWithdrawals.do", reqObj, "showdisagreewithdrawals");
}

function disAgreewithdrawalswithnopoints(id){
	var reqObj = {};
	reqObj["id"] = id;
	showDynamicDialog("/withdrawals/showdisagreeWithdrawalsppp.do", reqObj, "showdisagreewithdrawalsppp");
}

function disAgreeCharge(id){
	var reqObj = {};
	reqObj["id"] = id;
	showDynamicDialog("/charge/showdisagreecharge.do", reqObj, "showdisagreecharge");
}

function saveWithFormAddRefeshTable(sFormId,dialogId){
	var result = ajaxRequestForFormGetJson(sFormId);
	alert(result.msg);
	if(result.success){
		$('#'+dialogId).modal('hide');
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

function editNoticeWin(arg_id) {
	var reqObj = {};
	reqObj["noticeId"] = arg_id;
	var result = ajaxRequestForJsonGetJson(
			"/NoticeManagerController/getNoticeInfo.do", reqObj);
	if (result.success) {
		document.getElementById("noticeListId").value = result.elseExtend.id;
		document.getElementById("editNotice_title").value = result.elseExtend.title;
		document.getElementById("editNotice_category").value = result.elseExtend.category;
		 document.getElementById("editNotice_content").value = result.elseExtend.content;
		ndinstance1.setContent(result.elseExtend.content);
	}
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
				resetTable();
			});
		}
	}
}

function replyTickling(arg_id){
	var reqObj = {};
	reqObj["id"] = arg_id;
	var result = ajaxRequestForJsonGetJson("/TicklingManagerController/getTicklingInfo.do",reqObj);
	if (result.success) {
	document.getElementById("replyTickling_id").value = result.elseExtend.id;
	document.getElementById("replyTickling_title").value = result.elseExtend.title;
//	document.getElementById("replyTickling_content").value = result.elseExtend.content;
	ndinstance1_replyTickling_content.setContent(result.elseExtend.content);
	
	}
}

function checkTickling(arg_id){
	var reqObj = {};
	reqObj["id"] = arg_id;
	var result = ajaxRequestForJsonGetJson("/TicklingManagerController/getTicklingInfo.do",reqObj);
	if (result.success) {
	document.getElementById("checkTickling_title").value = result.elseExtend.title;
	document.getElementById("checkTickling_content").value = result.elseExtend.content;
	document.getElementById("checkTickling_replyContent").value = result.elseExtend.replyContent;
	ndinstance12_checkTickling_content.setContent(result.elseExtend.content);
	ndinstance12_checkTickling_replyContent.setContent(result.elseExtend.replyContent);
	
	}
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
				resetTable();
			});
		}
	}
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
				resetTable();
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

function searchBackAccount(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
	resetTable();
}


function forbiddenService(serviceId){
	if (window.confirm('您确定要禁用该报单中心？')) {
		var reqObj = {};
		reqObj["id"] = serviceId;
		var result = ajaxRequestForJsonGetJson("/ServiceManagerController/forbiddenService.do",reqObj);
		if (result.success) {
			alert(result.msg);
			//$('#myModal').modal('hide');
			$("#content-header").find("form[id='serviceManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
}

function forbidForm(){
	if (window.confirm('您确定要禁止报单？')) {
		var reqObj = {};
		reqObj["id"] = 1;
		var result = ajaxRequestForJsonGetJson("/ServiceManagerController/forbidFormManager.do",reqObj);
		if (result.success) {
			alert(result.msg);
			//$('#myModal').modal('hide');
			$("#content-header").find("form[id='serviceManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
}

function permitForm(){
	var reqObj = {};
	reqObj["id"] = 0;
	var result = ajaxRequestForJsonGetJson("/ServiceManagerController/forbidFormManager.do",reqObj);
		if (result.success) {
			alert(result.msg);
			//$('#myModal').modal('hide');
			$("#content-header").find("form[id='serviceManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
}

function checkSuccess(id,submitId,submitNumber,applyId,applyNumber){
	if (window.confirm('您确定该申请通过？')) {
		var reqObj = {};
		reqObj["id"] = id;
		reqObj["submitId"] = submitId;
		reqObj["applyId"] = applyId;
		reqObj["submitNumber"] = submitNumber;
		reqObj["applyNumber"] = applyNumber;
		var result = ajaxRequestForJsonGetJson("/ServiceManagerController/applyCheckSuccess.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$('#myModal').modal('hide');
			$("#content-header").find("form[id='applyServiceManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
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

function rangeIntegralIssue(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
	resetTable();
}

function deleteActiveMember(memberId,number,isService,recommendId,recommendNumber,activateId,activateNumber){
	if (window.confirm('您确定要删除当前用户么？')) {
		var reqObj = {};
		reqObj["id"] = memberId;
		reqObj["number"] = number;
		reqObj["isService"] = isService;
		reqObj["recommendId"] = recommendId;
		reqObj["recommendNumber"] = recommendNumber;
		reqObj["activateId"] = activateId;
		reqObj["activateNumber"] = activateNumber;
		var result = ajaxRequestForJsonGetJson("/membermanage/deleteActiveMember.do",reqObj);
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

function editFailureCause(arg_id){
	document.getElementById("applyCheckFailureId").value = arg_id;
}

function checkFailure(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form[id='applyServiceManagerForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}

function exportTiXian(sFormId){
	var form = $("<form>");   //定义一个form表单
    form.attr('style','display:none');   //在form表单中添加查询参数
    form.attr('target','');
    form.attr('method','post');
    form.attr('action',"/member_service/withdrawals/exportRecords.do");
    var input1 = $('<input>'); 
    input1.attr('type','hidden'); 
    input1.attr('name','number'); 
    input1.attr('value',$("#"+sFormId).find("input[name='number']").val()); 
   
    $('body').append(form);  //将表单放置在web中
    form.append(input1);   //将查询参数控件提交到表单上
    form.submit();   //表
}

function exportNotDealTiXian(sFormId){
	var form = $("<form>");   //定义一个form表单
    form.attr('style','display:none');   //在form表单中添加查询参数
    form.attr('target','');
    form.attr('method','post');
    form.attr('action',"/member_service/withdrawals/exportNotDealRecords.do");
    var input1 = $('<input>'); 
    input1.attr('type','hidden'); 
    input1.attr('name','number'); 
    input1.attr('value',$("#"+sFormId).find("input[name='number']").val()); 
   
    $('body').append(form);  //将表单放置在web中
    form.append(input1);   //将查询参数控件提交到表单上
    form.submit();   //表
}

function exportChongZhi(sFormId){
	var form = $("<form>");   //定义一个form表单
    form.attr('style','display:none');   //在form表单中添加查询参数
    form.attr('target','');
    form.attr('method','post');
    form.attr('action',"/member_service/charge/exportRecords.do");
    var input1 = $('<input>'); 
    input1.attr('type','hidden'); 
    input1.attr('name','number'); 
    input1.attr('value',$("#"+sFormId).find("input[name='number']").val()); 
   
    $('body').append(form);  //将表单放置在web中
    form.append(input1);   //将查询参数控件提交到表单上
    form.submit();   //表
}