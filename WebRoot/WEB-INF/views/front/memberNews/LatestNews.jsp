<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Widgets</a> </div>
    <br/>
    <h1>最新资讯</h1>
    <form action="/LatestNewsController/showLatestNews.do" method="POST" id="latestNewsForm">
	</form>
  </div>

<div id="content">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6">
				<div class="accordion" id="collapse-group">
					<c:forEach var="item" items="${result}" varStatus="status">
						<div class="accordion-group widget-box">
							<div class="accordion-heading">
								<div class="widget-title">
									<a data-parent="#collapse-group" href="#${status.count}"
										data-toggle="collapse"> <span class="icon"><i
											class="icon-eye-open"></i> </span>
										<h5>
											${item.title}
										</h5> </a>
								</div>
							</div>
							<div class="collapse accordion-body" id="${status.count}">
								<div class="widget-content">
									${item.content}
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>