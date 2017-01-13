<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.bean.Common" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<link rel="shortcut icon" href="/Feedback/resource/images/xmwtfl.png">
		<title><%= Common.title %></title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="/Feedback/resource/css/mui.min.css">
		<link rel="stylesheet" href="/Feedback/resource/css/ch.css">
		<link rel="stylesheet" type="text/css" href="/Feedback/resource/css/app.css" />
	</head>

	<body>
	<header class="mui-bar mui-bar-nav">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">问题处理</h1>
		</header>
		<div class="mui-content">
		    <div class="mui-slider">
		        <div class="mui-slider-group mui-slider-loop">
		        	<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一个图文表格) -->
		            <div class="mui-slider-item mui-slider-item-duplicate">
		                <ul class="mui-table-view mui-grid-view">
		                     <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">提出问题界面</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl46.jpg">
		                        </a>
		                    </li>
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">应用号界面</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl00.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">项目问题信息展示</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl01.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">填写问题原因和处理方案，确定方案执行人和计划完成时间，填写完成情况已经进度</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl02.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">分派工作，如图纸变更流程或者非BOM流程等</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl03.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">进度到达100%后可以提交</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl04.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">方案执行人接收到微信通知</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl10.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">方案执行人可查看项目问题信息</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl11.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">方案执行人填写完成情况和完成进度</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl12.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">方案执行人可以分派任务</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl13.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">在进度到达100%后可以提交</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl14.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">分派的任务后，负责人收到微信通知</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl20.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">查看项目问题信息</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl21.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">查看工作任务，填写完成进度</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl22.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">查看工作任务，填写完成进度</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl23.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">进度到达100后提交，完成任务</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl24.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">非BOM流程发起，填写问题号</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl30.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">在流程数据中查看非BOM流程</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl31.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">非BOM流程物料信息</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl32.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">非BOM流程运行情况</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl33.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">图纸变更流程</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl40.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">图纸变更流程</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl41.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">图纸变更流程</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl42.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">查看图纸变更流程数据</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl43.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">图纸变更流程物料和图纸信息</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl44.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">图纸变更流程物料和图纸信息</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl45.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">图纸变更流程的运行状态</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl46.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一个图文表格) -->
		            <div class="mui-slider-item mui-slider-item-duplicate">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">提出问题界面</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wtcl00.jpg">
		                        </a>
		                    </li>
		                </ul>
		            </div>
		        </div>
		        <div class="mui-slider-indicator" style="position: static;background-color: #fff;">
		            <span class="mui-action mui-action-previous mui-icon mui-icon-back"></span>
		            <div class="mui-number">
		                <span>1</span> /26
		            </div>
		            <span class="mui-action mui-action-next mui-icon mui-icon-forward"></span>
		        </div>
		    </div>
		</div>
	<script src="/Feedback/resource/js/mui.min.js"></script>
	<script>
	    mui.init();
	</script>
	</body>
</html>