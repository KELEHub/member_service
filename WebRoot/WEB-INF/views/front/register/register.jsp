<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content-header">
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i
			class="icon-home"></i> Home</a>
		<a href="#" class="current">Buttons &amp; icons</a>
		 <form action="/RegisterController/show.do" method="POST" id="toActivateForm">
	</form>
	<form action="/RegisterController/showRegister.do" method="POST" id="showRegisterForm">
	</form>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i> </span>
					<h5>
						注册会员
					</h5>
				</div>
				<div class="widget-content nopadding">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">
								会员账号：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="number" id="number" disabled="true" value="${number}">
									<input type="submit" value="换一个"
										onclick="changeData()" class="btn btn-success">
								</div>
							</div>
						</div>	
						<div class="control-group">
							<label class="control-label">
								推荐人:：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="refereeNumber" id="refereeNumber" value="${refereeNumber}">
									<input type="submit" value="查看"
										onclick="selectRegister()" class="btn btn-success">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								推荐人姓名:：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="refereename"  disabled="true" id="refereename" value="${refereename}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								会员资格：
							</label>
							<div class="controls">
								<div class="input-append">
								   <input type="text" name="registerMoney" id="registerMoney" disabled="true" value="${registerMoney}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								姓名：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text"   name="username" id="username" value="${username}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								身份证号：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="identity" id="identity" value="${identity}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								电话号码：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="phoneNumber" id="phoneNumber" value="${phoneNumber}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								开户银行：
							</label>
							<div class="controls">
								<div class="input-append">
									<select name="bankname" id="bankname" >
                                            <c:forEach var="item" items="${list }">
								                 <option value="${item.id }">${item.bankName }</option>
							                </c:forEach>
                                    </select>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								开户卡号：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="bankCard" id="bankCard" value="${bankCard}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								开户地址：
							</label>
							<div class="controls">
								<div class="input-append">
									<select id="select_province" onclick="initData()" name="bankProvince"></select> 
								</div>
							</div>
						</div>
						<div class="control-group">
						   <label class="control-label">
							</label>
							<div class="controls">
								<div class="input-append">
									<select id="select_city"  name="bankCity"></select>
								</div>
							</div>
						</div>
						<div class="control-group">
						    <label class="control-label">
							</label>
							<div class="controls">
								<div class="input-append">
									<select id="select_area"  name="bankCounty"></select>
								</div>
							</div>
						</div>
						<div class="control-group">
						    <label class="control-label">
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="bankAddress" id="bankAddress" value="${bankAddress}">
								</div>
							</div>
						</div>
						
					</div>
					<div class="form-actions">
						<input type="submit" value="注册"
							onclick="registerData()" class="btn btn-success">
					</div>
					<div class="controls controls-row">
						<div class="control-group" style="float: left; margin-left: 10px;">
							<label class="control-label" style="color: red">
								${error }
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
