<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div style="overflow-x:hidden; height:85%">
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>
									<img src="${item.productTarget1}"  onclick="shopingDetails('${item.id1}')"   style="width: 200px"/>
									<br/>
									${item.productName1}
									<br/>
									售价：${item.productPrice1}
									</td>
									<c:if test="${item.flg2 eq 1 }">
									<td>
									<img src="${item.productTarget2}" onclick="shopingDetails('${item.id2}')" style="width: 200px"/>
									<br/>
									${item.productName2}
									<br/>
									售价：${item.productPrice2}
									</td>
									</c:if>
									<c:if test="${item.flg2 eq 0 }">
									<td>
									</td>
									</c:if>
									<c:if test="${item.flg3 eq 1 }">
									<td>
									<img src="${item.productTarget3}" onclick="shopingDetails('${item.id3}')" style="width: 200px"/>
									<br/>
									${item.productName3}
									<br/>
									售价：${item.productPrice3}
									</td>
									</c:if>
									<c:if test="${item.flg3 eq 0 }">
									<td>
									</td>
									</c:if>
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


