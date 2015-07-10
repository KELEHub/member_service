<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/GiftsDetailsController/showSend.do" method="POST" id="initGiftsForm">
	</form>
   <form action="/GiftsDetailsController/send.do" method="POST"
						id="giftsDetailsSendForm">
						</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>礼包积分发放</h5>
				</div>
				<div class="widget-content nopadding">
		<div  class="form-horizontal"> 
		
		<div class="control-group">
			<label class="control-label">
				${firstGold}:
			</label>
			<div class="controls">
				<div >
				   <input type="submit" value="发送积分"
							onclick="sendGifts('giftsDetailsSendForm')"
							class="btn btn-success">
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">
				${SecondGold}:
			</label>
			<div class="controls">
				<div >
				   <input type="submit" value="发送积分"
							class="btn btn-success">
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">
				${threeGold}:
			</label>
			<div class="controls">
				<div>
				   <input type="submit" value="发送积分"
							class="btn btn-success">
				</div>
			</div>
		</div>
		
		
	</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>礼包名称</th>
								<th>流水号</th>
								<th>积分</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.number }</td>
									<td>${item.name }</td>
									<td>${item.countNumber }</td>
									<td>${item.goldMoney }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
