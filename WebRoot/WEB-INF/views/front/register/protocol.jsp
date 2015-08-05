<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div style="overflow-x:hidden; height:85%">
<div id="content-header">
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
				<div style="width: 800px;height:auto;position: relative;padding: 15px;overflow-y: auto;">
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
</div>
