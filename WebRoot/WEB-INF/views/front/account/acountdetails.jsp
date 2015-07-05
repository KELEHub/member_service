<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content-header">
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i
			class="icon-home"></i> Home</a> <a href="#" class="current">Buttons
			&amp; icons</a>
	</div>
	<h1>
		账户明细展示
	</h1>
	<form  action="/AccountDetailsController/selectData.do" method="POST" id="acountDetailsForm">
	<div class="controls controls-row">
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				种类：
			</label>
			<div class="controls">
				<div class="input-append">
					<select name="goldFlg">
						<option value="space" <c:if test='${goldFlg == "space"}'>  selected='selected'  </c:if>></option>
						<option value="crmMoney" <c:if test='${goldFlg == "crmMoney"}'>  selected='selected'  </c:if>>葛粮币</option>
						<option value="shoppingMoney" <c:if test='${goldFlg == "shoppingMoney"}'>  selected='selected'  </c:if>>积分</option>
					</select>
				</div>
			</div>
		</div>
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label">
				项目：
			</label>
			<div class="controls">
				<div class="input-append">
					<select name="projectFlg">
						<option value="space" <c:if test='${projectFlg == "space"}'>  selected='selected'  </c:if>></option>
                        <option value="tootherman" <c:if test='${projectFlg == "tootherman"}'>  selected='selected'  </c:if>>会员转账</option>
                        <option value="recharge" <c:if test='${projectFlg == "recharge"}'>  selected='selected'  </c:if>>充值</option>
                        <option value="fromback" <c:if test='${projectFlg == "fromback"}'>  selected='selected'  </c:if>>后台调整</option>
                        <option value="frompointsadd" <c:if test='${projectFlg == "frompointsadd"}'>  selected='selected'  </c:if>>积分转葛粮币增加</option>
                        <option value="servicepoints" <c:if test='${projectFlg == "servicepoints"}'>  selected='selected'  </c:if>>服务积分</option>
                        <option value="togoldmoneycut" <c:if test='${projectFlg == "togoldmoneycut"}'>  selected='selected'  </c:if>>积分转葛粮币减少</option>
                        <option value="pointcash" <c:if test='${projectFlg == "pointcash"}'>  selected='selected'  </c:if>>积分提现</option>
                        <option value="fromgifts" <c:if test='${projectFlg == "fromgifts"}'>  selected='selected'  </c:if>>礼包发放</option>
                        <option value="activateMember" <c:if test='${projectFlg == "activateMember"}'>  selected='selected'  </c:if>>激活会员</option>
                        <option value="cost" <c:if test='${projectFlg == "cost"}'>  selected='selected'  </c:if>>扣费</option>
                   </select>
				</div>
			</div>
		</div>
		<div class="control-group" style="float: left; margin-left: 10px;">
			<label class="control-label">
				年:
			</label>
			<div class="controls">
				<div class="input-append">
					<select name="yearFlg">
						<option value="space" <c:if test='${yearFlg == "space"}'>  selected='selected'  </c:if>></option>
						<option value="2017" <c:if test='${yearFlg == "2017"}'>  selected='selected'  </c:if>>2017</option>
						<option value="2016" <c:if test='${yearFlg == "2016"}'>  selected='selected'  </c:if>>2016</option>
						<option value="2015" <c:if test='${yearFlg == "2015"}'>  selected='selected'  </c:if>>2015</option>
						<option value="2014" <c:if test='${yearFlg == "2014"}'>  selected='selected'  </c:if>>2014</option>
						<option value="2013" <c:if test='${yearFlg == "2013"}'>  selected='selected'  </c:if>>2013</option>
					</select>
				</div>
			</div>
		</div>
		<div class="control-group" style="float: left; margin-left: 10px;">
			<label class="control-label">
				月:
			</label>
			<div class="controls">
				<div class="input-append">
					<select name="monthFlg">
						<option value="space" <c:if test='${monthFlg == "space"}'>  selected='selected'  </c:if>></option>
						<option value="1" <c:if test='${monthFlg == "1"}'>  selected='selected'  </c:if>>1</option>
						<option value="2" <c:if test='${monthFlg == "2"}'>  selected='selected'  </c:if>>2</option>
						<option value="3" <c:if test='${monthFlg == "3"}'>  selected='selected'  </c:if>>3</option>
						<option value="4" <c:if test='${monthFlg == "4"}'>  selected='selected'  </c:if>>4</option>
						<option value="5" <c:if test='${monthFlg == "5"}'>  selected='selected'  </c:if>>5</option>
						<option value="6" <c:if test='${monthFlg == "6"}'>  selected='selected'  </c:if>>6</option>
						<option value="7" <c:if test='${monthFlg == "7"}'>  selected='selected'  </c:if>>7</option>
						<option value="8" <c:if test='${monthFlg == "8"}'>  selected='selected'  </c:if>>8</option>
						<option value="9" <c:if test='${monthFlg == "9"}'>  selected='selected'  </c:if>>9</option>
						<option value="10" <c:if test='${monthFlg == "10"}'>  selected='selected'  </c:if>>10</option>
						<option value="11" <c:if test='${monthFlg == "11"}'>  selected='selected'  </c:if>>11</option>
						<option value="12" <c:if test='${monthFlg == "12"}'>  selected='selected'  </c:if>>12</option>
					</select>
				</div>
			</div>
		</div>
		</div>
	</form>
	<div class="controls controls-row">
		<div class="control-group"  style="float: left; margin-left: 10px;">
			<label class="control-label" style="color: red">${error }</label>
		</div>
	</div>
	<div class="form-actions">
		<input type="submit" value="查询" onclick="searchAccount('acountDetailsForm')"
			class="btn btn-success">
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>种类</th>
								<th>日期</th>
								<th>项目</th>
								<th>收入</th>
								<th>支出</th>
								<th>积分余额</th>
								<th>葛粮币余额</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.kindData }</td>
									<td>${item.createTime }</td>
									<td>${item.project }</td>
									<td>${item.income }</td>
									<td>${item.pay }</td>
									<td>${item.pointbalance }</td>
									<td>${item.goldmoneybalance }</td>
									<td>${item.redmin }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>




