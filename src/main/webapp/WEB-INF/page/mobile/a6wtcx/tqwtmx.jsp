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
			<h1 class="mui-title">${title}</h1>
		</header>

		<!--下拉刷新容器-->
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
			<div class="mui-scroll">
				<ul class="mui-table-view mui-table-view-chevron">
					<c:forEach items="${items}" var="item" varStatus="status">
					    <li id='${status.index}' class="mui-table-view-cell mui-media wi" >
							<a class="mui-navigate-right">
								${item.lsh}  ${item.xmbh}  ${item.wtms}
								<p class='mui-ellipsis'>${item.fqrxm}      ${item.createtimes}</p>
							</a>
							<div hidden="hidden">
								<input type="text" id="objectid${status.index}" value="${item.objectid}">
								<input type="text" id="workitemid${status.index}" value="${item.workitemid}">
							</div>
						</li>
						<script>
							var wturl = '/Feedback/wtcx/wtcx.do?objectid=${item.objectid}';
							document.getElementById('${status.index}').addEventListener('tap', function() {
								mui.openWindow({
									url : wturl,
									id : 'wtcx',
									show : {
										aniShow : 'pop-in'
									},
									waiting : {
										autoShow : false
									}
								});
							});
						</script>
				    </c:forEach>
				</ul>
			</div>
		</div>
	<script src="/Feedback/resource/js/mui.min.js"></script>
	<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script>
			mui.init({
				
			});
			mui('.mui-scroll-wrapper').scroll();
			$(document).ready(function(){
				var index = 0;
				mui(".wi li").each(function() {
					var objectid = $("#objectid" + index ).val();
					var workitemid = $("#workitemid" + index ).val();
					index++;
				}); 
			});
		
		</script>
	</body>

</html>