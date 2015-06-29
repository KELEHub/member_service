<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container-fluid" id="latestNewsDiv">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>最新资讯</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>类型</th>
								<th>标题</th>
								<th>日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.category}</td>
									<td>${item.title}</td>
									<td>${item.date}</td>
									<td> <button class="btn" onclick="getNews('${item.id}')">查看</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>