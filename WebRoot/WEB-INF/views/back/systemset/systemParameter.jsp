<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div class="container-fluid" id="content-header">
	<div class="row-fluid">
		<div class="span12">
            <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5><a>系统参数设置</a></h5>
        </div>
        <div class="widget-content nopadding">
          <form class="form-horizontal" action="/ParameterController/set.do" method="POST" id="parameterSetForm">

            <div class="control-group">
              <label class="control-label">是否提现:</label>
              <div class="controls">
                 <div class="input-prepend"> 
                 <c:choose>
                    <c:when  test="${bean.goldFlg=='open'}">
                       <select name="goldFlg">
                            <option value="open" selected="selected">是</option>
                            <option value="close">否</option>
                       </select>
                    </c:when>
                    <c:otherwise>
                        <select name="goldFlg">
                            <option value="open">是</option>
                            <option value="close" selected="selected">否</option>
                        </select>
                    </c:otherwise>
                 </c:choose>
                 </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">提现最高限额:</label>
                <div class="controls">
                <div class="input-append">
                  <input type="text" name="goldMax" value="${bean.goldMax}"   onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">提现最小限额:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="goldMin" value="${bean.goldMin}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputWarning">提现手续费:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="goldTake" value="${bean.goldTake}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputError">转账最大金额:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreMax" value="${bean.scoreMax}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputInfo">转账最小金额:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreMin" value="${bean.scoreMin}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">转账手续费:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreTake" value="${bean.scoreTake}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">葛粮币转换最小金额:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="glbMin" value="${bean.glbMin}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">葛粮币转换手续费:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="glbTake" value="${bean.glbTake}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">葛粮币充值最小金额:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreInMin" value="${bean.scoreInMin}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">葛粮币充值手续费:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreInTake" value="${bean.scoreInTake}" onfocusout="formatter(this)" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
          </form>
          <div class="form-actions">
                <input type="submit" value="设定" onclick="saveParameter('parameterSetForm')" class="btn btn-success">
          </div>
        </div>
      </div>
      </div>
	</div>
</div>
