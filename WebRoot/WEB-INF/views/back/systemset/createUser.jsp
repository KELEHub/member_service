<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/CreateUserController/show.do" method="POST" id="searchUserForm">
	</form>
  </div>
<div class="container-fluid" style="overflow:auto; height:800px">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>管理员创建</h5>
				</div>
				<div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/CreateUserController/set.do" method="POST"
						id="createUserForm">
						<div class="control-group">
							<label class="control-label">用户名：</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="username" value="${bean.username}"
										class="span11">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">密码：</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="password" value="${bean.password}"
										class="span11">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">角色：</label>
							<div class="controls">
								<div class="input-append">
										<select name="id">
										    <c:forEach var="item" items="${roleList }">
								                 <option value="${item.id }">${item.roleNm }</option>
							                </c:forEach>
                                        </select>
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="添加"
							onclick="saveCreateUser('createUserForm')"
							class="btn btn-success">
					</div>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>用户名</th>
								<th>角色名称</th>
								<th>角色描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.username }</td>
									<td>${item.roleNm }</td>
									<td>${item.roleDes }</td>
									<td>
									<button class="btn btn-large" onclick="deleteUser('${item.username}')">删除</button>
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
