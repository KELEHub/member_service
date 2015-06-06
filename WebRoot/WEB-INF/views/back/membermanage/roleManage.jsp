<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <h1><a href="#myModal" data-toggle="modal" class="btn btn-large">添加新角色</a></h1>
    <form action="/rolemanage/showRoleManage.do" method="POST" id="searchRoleForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>系统角色管理</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>角色编号</th>
								<th>角色名称</th>
								<th>角色描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.id }</td>
									<td>${item.roleNm }</td>
									<td>${item.roleDes }</td>
									<td>
									<button class="btn btn-large" onclick="allocationAuth('${item.id}','${item.roleDes }')">分配菜单</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="myModal" class="modal hide">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>添加角色</h3>
	</div>
	<div class="modal-body">
		<form action="/rolemanage/insertRole.do" method="POST" id="saveRoleForm">
			<div class="control-group">
				<label class="control-label">角色名称</label>
				<div class="controls">
					<input type="text"  placeholder="角色名称" name="roleNm" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">角色描述</label>
				<div class="controls">
					<input type="text" placeholder="角色描述" name="roleDsc"/>
				</div>
			</div>
		</form>
		<button class="btn btn-large" onclick="saveRole('saveRoleForm')">保存</button>
	</div>
</div>