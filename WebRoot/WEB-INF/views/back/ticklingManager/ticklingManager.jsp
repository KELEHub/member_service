<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/TicklingManagerController/showDoneTicklingManager.do" method="POST" id="doneTicklingManagerForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>会员留言记录</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>标题</th>
								<th>日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.memberNumber}</td>
									<td>${item.title}</td>
									<td>${item.ticklingDate}</td>
									<td>
									<a href="#myModal" data-toggle="modal" class="btn btn-large" onclick="checkTickling('${item.id}','${item.title}','${item.content}','${item.replyContent}')">查看</a>
									<button class="btn btn-large" onclick="deleteDoneTickling('${item.id}')">删除</button>
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
		<button data-dismiss="modal" class="close" type="button">
			×
		</button>
		<h3>
			历史留言查看
		</h3>
	</div>
	<div class="widget-content nopadding">
		<div class="control-group">
			<label class="control-label">
				标题:
			</label>
			<div class="controls">
				<input type="text" class="span11" name="title"
					id="checkTickling_title" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				内容:
			</label>
			<div class="controls">
				<div class="widget-content">
					<div class="control-group">
						<div class="controls">
							<textarea class="textarea_editor span12" rows="6" name="content"
								id="checkTickling_content"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				回复内容:
			</label>
			<div class="controls">
				<div class="widget-content">
					<div class="control-group">
						<div class="controls">
							<textarea class="textarea_editor span12" rows="6"
								name="replyContent" id="checkTickling_replyContent"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>