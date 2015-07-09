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

function initData(){
	region_init("select_province","select_city","select_area");
	 $("#select_province").find("option:selected").text();
}

function changeData(){
	var reqObj = {};
	var number=$("#number").attr("value");
	reqObj["number"] = number;
	var result = ajaxRequestForJsonGetJson("/RegisterController/change.do",
				reqObj);
	if(result.success){
			var name = result.msg;
			$("#number").attr("value",name);
		}
}

function selectRegister(){
	var reqObj = {};
	var refereeNumber=$("#refereeNumber").attr("value");
	reqObj["refereeNumber"] = refereeNumber;
	var result = ajaxRequestForJsonGetJson("/RegisterController/select.do",
				reqObj);
	if(result.success){
			var name = result.msg;
			$("#refereename").attr("value",name);
		}
}




function registerData(){
	 var reqObj = {};
	 var bankProvince = $("#select_province").find("option:selected").text();
	 var bankCity = $("#select_city").find("option:selected").text();
	 var bankCounty = $("#select_area").find("option:selected").text();
	 var number=$("#number").attr("value");
	 var refereeNumber=$("#refereeNumber").attr("value");
	 var username=$("#username").attr("value");
	 var identity=$("#identity").attr("value");
	 var phoneNumber=$("#phoneNumber").attr("value");
	 var bankname = $("#bankname").find("option:selected").text();
	 var bankCard=$("#bankCard").attr("value");
	 var bankAddress=$("#bankAddress").attr("value");
	 reqObj["bankProvince"] = bankProvince;
	 reqObj["bankCity"] = bankCity;
	 reqObj["bankCounty"] = bankCounty;
	 reqObj["number"] = number;
	 reqObj["refereeNumber"] = refereeNumber;
	 reqObj["username"] = username;
	 reqObj["identity"] = identity;
	 reqObj["phoneNumber"] = phoneNumber;
	 reqObj["bankname"] = bankname;
	 reqObj["bankCard"] = bankCard;
	 reqObj["bankAddress"] = bankAddress;
		var result = ajaxRequestForJsonGetJson("/RegisterController/register.do",
				reqObj);
		if(!result.success){
			var name = result.msg;
			alert(name);
		}else{
			    var name = result.msg;
			    alert(name);
			    $("#content-header").find("form[id='toActivateForm']").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
			    findJqueryFileUpload("content");
				resetTable();
			});
		}
	 
	
}

function deleteuser(number){
	if (window.confirm('您确定要删除当前会员么？')) {
		var reqObj = {};
		reqObj["number"] = number;
		var result = ajaxRequestForJsonGetJson("/RegisterController/deleteUser.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$("#content-header").find("form[id='searchActivateForm']").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
	
	
}

function activityuser(number){
	if (window.confirm('您确定要激活当前会员么？')) {
		var reqObj = {};
		reqObj["number"] = number;
		var result = ajaxRequestForJsonGetJson("/RegisterController/activateUser.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$("#content-header").find("form[id='searchActivateForm']").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
	
	
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



function ajaxRequestForFormGetJspByParamter(reqUrl,reqObj){
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

function selectData(){
		var reqObj = {};
		var toUserNumber=$("#toUserNumber").attr("value");
		reqObj["toUserNumber"] = toUserNumber;
		var result = ajaxRequestForJsonGetJson("/TransferController/select.do",
				reqObj);
		if(result.success){
			var name = result.msg;
			$("#toUserName").attr("value",name);
		}
		
//		$("#content-header").find("form[id='showForm']").each(function(){
//				var formid = this.id;
//				ajaxRequestForFormGetJsp(formid);
//		});
		
}

function savetransfer(){
		var reqObj = {};
		var toUserNumber=$("#toUserNumber").attr("value");
		var toGoldMoney=$("#toGoldMoney").attr("value");
		var payPassword=$("#payPassword").attr("value");
		reqObj["toUserNumber"] = toUserNumber;
		reqObj["toGoldMoney"]= toGoldMoney;
		reqObj["payPassword"]= payPassword;
		var result = ajaxRequestForJsonGetJson("/TransferController/transferData.do",
				reqObj);
		if(result.success){
			var name = result.msg;
			alert(name);
			var cemoney = result.msgCode;
			if(cemoney != null){
				$("#goldmoneybalance").attr("value",cemoney);
			}
		}
		
//		$("#content-header").find("form[id='showForm']").each(function(){
//				var formid = this.id;
//				ajaxRequestForFormGetJsp(formid);
//		});
		
}

function saveconvert(){
	var reqObj = {};
		var toUserNumber=$("#shoppingMoney").attr("value");
		var toGoldMoney=$("#toCmrMoney").attr("value");
		var payPassword=$("#payPassword").attr("value");
		reqObj["shoppingMoney"] = toUserNumber;
		reqObj["toCmrMoney"]= toGoldMoney;
		reqObj["payPassword"]= payPassword;
		var result = ajaxRequestForJsonGetJson("/ConvertController/convertData.do",
				reqObj);
		if(result.success){
			var name = result.msg;
			alert(name);
			$('#myModal').modal('hide');
		    $("#content-header").find("form[id='toConvertForm']").each(function() {
			var formid = this.id;
			ajaxRequestForFormGetJsp(formid);
			resetTable();
		});
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
}

function searchAccount(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
	resetTable();
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

function agreecharge(id){
	
	if (window.confirm('您确定充值么？')) {
		var reqObj={};
		reqObj["id"]=id;
		var result = ajaxRequestForJsonGetJson("/charge/agreeCharge.do", reqObj);
		alert(result.msg);
		if (result.success) {
			$('#myModal').modal('hide');
			$("#content-header").find("form[id='searchDealChargezRecordForm']").each(function() {
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
			});
		}
	}
}

function disAgreewithdrawals(id){
	//TODO
	alert("留言板敬请期待。。。。");
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

function change1Password(sFormId){
	var sPassword = $("input[name='onelevelpassword']").val();
	var sPasswordconfirm = $("input[name='onelevelpasswordconfirm']").val();
	if(sPassword==sPasswordconfirm){
		saveWithFormAndDoNothint(sFormId);
	}else{
		alert("您2次输入的密码不一样");
	}
}

function chenge2password(sFormId){
	var sPassword = $("input[name='twolevelpassword']").val();
	var sPasswordconfirm = $("input[name='twolevelpasswordconfirm']").val();
	if(sPassword==sPasswordconfirm){
		saveWithFormAndDoNothint(sFormId);
	}else{
		alert("您2次输入的密码不一样");
	}
}

function initMemberInfoUpdateSelect(){
	region_init("select_province","select_city","select_area",provinceMap.get($("#bankProvince").val()),cityMap.get($("#bankCity").val()),countryMap.get($("#bankCounty").val()));
	region_init("select_province1","select_city1","select_area1",provinceMap.get($("#linkProvince").val()),cityMap.get($("#linkCity").val()),countryMap.get($("#linkCounty").val()));
}
function saveWithFormAndDoNothint(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	alert(result.msg);
}

function saveMemberInfo(sFormId){
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
			alert(data.msg);
		},
		error : function (msg) {
			alert(msg);
       }
	});
}

function doAccCharge(sFormId){
	if (window.confirm('您确定要提交充值申请么?')) {
		var result = ajaxRequestForFormGetJson(sFormId);
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

function doAccWithdrawals(sFormId){
	if (window.confirm('您确定要提交提现申请么?')) {
		var result = ajaxRequestForFormGetJson(sFormId);
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

function showAccChargeDetail(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/acc/showAccChargeDetail.do", reqObj, "showAccChargeDetail");
}

function showAccChargeRefuseReason(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/acc/showRefuseReason.do", reqObj, "showAccChargeRefuseReason");
}

function showAccWithDrawalsRefuseReason(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/acc/showWithdrawalsRefuseReason.do", reqObj, "showAccChargeRefuseReason");
}

function showAccWithdrawalsDetail(memberId){
	var reqObj = {};
	reqObj["id"] = memberId;
	showDynamicDialog("/acc/showAccWithdrawalsDetail.do", reqObj, "showAccWithdrawalsDetail");
}

function editProduct(id){
	var reqObj = {};
	reqObj["id"] = id;
	showDynamicDialog("/product/getProduDetail.do", reqObj, "showProductDetail");
}

function viewMemberOrderProduct(id){
	var reqObj = {};
	reqObj["id"] = id;
	showDynamicDialog("/frontorderlist/viewProduDetail.do", reqObj, "showMemberOrderProductDetail");
}

function getProduct(id){
	if (window.confirm('您确定要提货么？')) {
		var reqObj = {};
		reqObj["id"] = id;
		var result = ajaxRequestForJsonGetJson("/frontorderlist/getProduct.do",reqObj);
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

function rangeIntegralIssue(sFormId){
	var result = ajaxRequestForFormGetJsp(sFormId);
	resetTable();
}

function viewLatestNews(arg_title,arg_content){
	$("#viewLatestNews_title").val(arg_title);
	$("#viewLatestNews_content").val(arg_content);
//	$("#latestNewsDiv").hide();
}

function deleteApplyQualification(applyId){
	if (window.confirm('您确定删除该申请？')) {
		var reqObj = {};
		reqObj["id"] = applyId;
		var result = ajaxRequestForJsonGetJson("/ApplyQualificationController/deleteApplyQualification.do",reqObj);
		if (result.success) {
			alert(result.msg);
			$('#myModal').modal('hide');
			$("#content-header").find("form[id='applyQualificationForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
			});
		}
	}
}

function showForbidFailureCause(failureCause){
	$("#forbid_failureCause").val(failureCause);
}

function queryMemberInfo(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		$("#applyMember_info").val(result.msgCode);
		$("#applyMember_applyId").val(result.extension);
		$("#applyMember_password").val("");
	}else{
		alert(result.msg);
	}
}

function submitApply(applyId,applyNumber,submitReason,protectPassword){
	if (window.confirm('您确定提交该申请？')) {
		var reqObj = {};
		reqObj["applyId"] = applyId;
		reqObj["applyNumber"] = applyNumber;
		reqObj["submitReason"] = submitReason;
		reqObj["protectPassword"] = protectPassword;
		var result = ajaxRequestForJsonGetJson("/ApplyQualificationController/submitApply.do",reqObj);
		alert(result.msg);
		if (result.success) {
			$('#myModal').modal('hide');
			$("#content-header").find("form[id='applyQualificationForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
			});
		}
	}
}

function releaseTickling(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
		$('#myModal').modal('hide');
		$("#content-header").find("form[id='inboxForm']").each(function(){
				var formid = this.id;
				ajaxRequestForFormGetJsp(formid);
				resetTable();
		});
	}
}

function getNews(id) {
	var reqObj = {};
	reqObj["noticeId"] = id;
	ajaxRequestForFormGetJspByParamter("/LatestNewsController/getNews.do", reqObj);
    findJqueryFileUpload("content");
	resetTable();
}

function checkInboxInfo(arg_id){
	var reqObj = {};
	reqObj["id"] = arg_id;
	var result = ajaxRequestForJsonGetJson("/TicklingManagerController/getTicklingInfo.do",reqObj);
	if (result.success) {
	document.getElementById("inbox_title").value = result.elseExtend.title;
	document.getElementById("inbox_content").value = result.elseExtend.content;
	document.getElementById("inbox_replyContent").value = result.elseExtend.replyContent;
	}
}

function checkProtocol(result){
	var reqObj = {};
	reqObj["result"] = result;
	if(result=='agree'){
		ajaxRequestForFormGetJspByParamter("/RegisterController/showRegister.do", reqObj);
	}else if (result=='oppose'){
		ajaxRequestForFormGetJspByParamter("/LatestNewsController/showLatestNews2.do", reqObj);
	}
	findJqueryFileUpload("content");
	resetTable();
}