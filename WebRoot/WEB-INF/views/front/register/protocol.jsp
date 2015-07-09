<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content-header">
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i
			class="icon-home"></i> Home</a>
		<a href="#" class="current">Buttons &amp; icons</a>
<%--		 <form action="/RegisterController/showRegister.do" method="POST" id="protocolForm">--%>
<%--	</form>--%>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i> </span>
					<h5>
						服务协议
					</h5>
				</div>
				<div class="widget-content nopadding">
					<div class="form-horizontal">
						${result}
					</div>
					<div class="form-actions">
						<input type="submit" value="同意"
							onclick="checkProtocol('agree')" class="btn btn-success">
						<input type="submit" value="不同意"
							onclick="checkProtocol('oppose')" class="btn btn-success">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
