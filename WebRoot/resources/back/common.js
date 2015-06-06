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

function saveParameter(sFormId){
	var result = ajaxRequestForFormGetJson(sFormId);
	if(result.success){
		alert(result.msg);
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
				$("#showDialogDiv").append(data);
				$('#'+dialogId).modal('show');
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
		"bPaginate" : true, //是否显示（应用）分页器  
		"bAutoWidth" : true, //是否自适应宽度 
		"bScrollCollapse" : true,
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
	$.fn.zTree.init($("#showAllocationAuthTree"), authTreeSetting, zNodes);
}

function authTreeOnCheck(event, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getCheckedNodes(true);
//	var roleNm = $("#editRoleNm").val();
	var selectNodeArr = [];
	for(var i=0;i<nodes.length;i++){
		var node = nodes[i];
		var selectedNodeObj={};
//		selectedNodeObj["roleNm"] = roleNm;
		if(node.isParent){
			selectedNodeObj["menuId"] = node.menuId;
			selectedNodeObj["authCode"] = "";
		}else{
			selectedNodeObj["menuId"] = node.parMenuId;
			selectedNodeObj["authCode"] = node.menuId;;
		}
		selectNodeArr.push(selectedNodeObj);
	}
	if(treeId.indexOf("newAuth") >=0){
		$("#newAuthMenuIds").val(JSON.stringify(selectNodeArr));
	}else{
		$("#editAuthMenuIds").val(JSON.stringify(selectNodeArr));
	}
}