<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div class="container-fluid" id="content-header">
	<div class="row-fluid">
		<div class="span12">
            <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5><a>制度参数设置</a></h5>
        </div>
        <div class="widget-content nopadding">
          <form class="form-horizontal" action="/InstitutionController/set.do" method="POST" id="institutionForm">
            <div class="control-group">
              <label class="control-label">注册金额:</label>
              <div class="controls">
                  <div class="input-append">
                      <input type="text" name="registerGold" value="${bean.registerGold}" class="span11">
                       <span class="add-on">$</span> </div>
                  </div>    
           </div>
            <div class="control-group">
              <label class="control-label">直播礼包数:</label>
              <div class="controls">
                <div class="input-append">
                      <input type="text" name="preCount" value="${bean.preCount}" class="span11">
                       <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">第一期:</label>
                <div class="controls">
                <div class="input-append">
                  <input type="text" name="preaFirst" value="${bean.preaFirst}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">第二期:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="preaSecond" value="${bean.preaSecond}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputWarning">第三期:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="preaThree" value="${bean.preaThree}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputError">第四期:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="preaFour" value="${bean.preaFour}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputInfo">第五期:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="preaFive" value="${bean.preaFive}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
             <div class="control-group">
              <label class="control-label" for="inputInfo">服务费:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="serviceCash" value="${bean.serviceCash}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
          </form>
          <div class="form-actions">
                <input type="submit" value="设定" onclick="saveParameter('institutionForm')" class="btn btn-success">
          </div>
        </div>
      </div>
      </div>
	</div>
</div>
