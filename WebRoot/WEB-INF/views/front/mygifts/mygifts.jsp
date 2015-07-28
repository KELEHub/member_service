<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div style="overflow:auto; height:800px">
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>我的礼包</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>礼包名称</th>
								<th>获取日期</th>
								<th>说明</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.name}</td>
									<td>${item.createTime }</td>
									<td>${item.remaind }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</div>