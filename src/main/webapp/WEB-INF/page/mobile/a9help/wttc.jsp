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
		    <h1 class="mui-title">问题提出</h1>
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
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc6.jpg">
		                        </a>
		                    </li>
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">应用号界面</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">提出问题界面</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc0.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">项目查询</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc1.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">选择项目经理</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc2.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">编辑部套名称</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc3.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">选择部套名称</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc4.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">拍照或选择图库图片</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc5.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">提交</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc6.jpg">
		                        </a>
		                    </li>
		                    
		                </ul>
		            </div>
		            <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一个图文表格) -->
		            <div class="mui-slider-item mui-slider-item-duplicate">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-12">
		                    	<a href="#">
		                            <div class="mui-media-body">应用号界面</div>
		                    		<img class="mui-media-object" src="/Feedback/resource/images/help/wttc.jpg">
		                        </a>
		                    </li>
		                </ul>
		            </div>
		        </div>
		        <div class="mui-slider-indicator" style="position: static;background-color: #fff;">
		            <span class="mui-action mui-action-previous mui-icon mui-icon-back"></span>
		            <div class="mui-number">
		                <span>1</span> /8
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