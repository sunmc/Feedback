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
		<style type="text/css">
			li{
				height:40px;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<!-- <a id="sy" class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a> -->
			<h1 class="mui-title">拖期问题汇总</h1>
		</header>
		<div class="mui-content">
			<ul id="list"  class="mui-table-view mui-table-view-chevron">
				<c:if test="${tqproject.ytnwjj != null && tqproject.ytnwjj > 0}">
					 <li class="mui-table-view-cell"> 
		        		 <a href="/Feedback/wtcx/tqwtmx.do?start=0&end=1&wtstate=1" class="mui-navigate-right">${tqproject.ytnwjj}条一天内未解决问题</a> 
			    	 </li>
				</c:if>
				<c:if test="${tqproject.stnwjj != null && tqproject.stnwjj > 0}">
					 <li class="mui-table-view-cell"> 
		        		 <a href="/Feedback/wtcx/tqwtmx.do?start=1&end=3&wtstate=1" class="mui-navigate-right">${tqproject.stnwjj}条三天内未解决问题</a> 
			    	 </li>
				</c:if>
				<c:if test="${tqproject.wtnwjj != null && tqproject.wtnwjj > 0}">
					 <li class="mui-table-view-cell"> 
		        		 <a href="/Feedback/wtcx/tqwtmx.do?start=3&end=5&wtstate=1" class="mui-navigate-right">${tqproject.wtnwjj}条五天内未解决问题</a> 
			    	 </li>
				</c:if>
				<c:if test="${tqproject.wtswjj != null && tqproject.wtswjj > 0}">
					 <li class="mui-table-view-cell"> 
		        		 <a href="/Feedback/wtcx/tqwtmx.do?start=5&wtstate=1" class="mui-navigate-right">${tqproject.wtswjj}条超过五天未解决问题</a> 
			    	 </li>
				</c:if>
				<c:if test="${tqproject.ytnwgb != null && tqproject.ytnwgb > 0}">
					 <li class="mui-table-view-cell"> 
		        		 <a href="/Feedback/wtcx/tqwtmx.do?start=0&end=1&wtstate=3" class="mui-navigate-right">${tqproject.ytnwgb}条一天内未关闭问题</a> 
			    	 </li>
				</c:if>
				<c:if test="${tqproject.stnwgb != null && tqproject.stnwgb > 0}">
					 <li class="mui-table-view-cell"> 
		        		 <a href="/Feedback/wtcx/tqwtmx.do?start=1&end=3&wtstate=3" class="mui-navigate-right">${tqproject.stnwgb}条三天内未关闭问题</a> 
			    	 </li>
				</c:if>
				<c:if test="${tqproject.wtnwgb != null && tqproject.wtnwgb > 0}">
					 <li class="mui-table-view-cell"> 
		        		 <a href="/Feedback/wtcx/tqwtmx.do?start=3&end=5&wtstate=3" class="mui-navigate-right">${tqproject.wtnwgb}条五天内未关闭问题</a> 
			    	 </li>
				</c:if>
				<c:if test="${tqproject.wtswgb != null && tqproject.wtswgb > 0}">
					 <li class="mui-table-view-cell"> 
		        		 <a href="/Feedback/wtcx/tqwtmx.do?start=5&wtstate=3" class="mui-navigate-right">${tqproject.wtswgb}条超过五天未关闭问题</a> 
			    	 </li>
				</c:if>
			</ul>
		</div>
		
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script type="text/javascript">
		mui.init({
			swipeBack: true //启用右滑关闭功能
		});
		mui('.mui-scroll-wrapper').scroll();
		var url = "/Feedback/wtcx/overtask.do";
		$(document).ready(function(){
			
		});
		</script>
	</body>
</html>