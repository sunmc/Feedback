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
			<!-- <a class="mui-action-back mui-btn mui-btn-link mui-pull-right">关闭</a> -->
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
							var wturl = '/Feedback/${item.activitycode}/${item.activitycode}.do?objectid=${item.objectid}&workitemid=${item.workitemid}';
							document.getElementById('${status.index}').addEventListener('tap', function() {
								mui.openWindow({
									url : wturl,
									id : 'wtcl',
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
			document.getElementById('wtpd').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtpd.do',
					id : 'wtpd',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtcl').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtcl.do',
					id : 'wtcl',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtqr').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtqr.do',
					id : 'wtqr',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtgb').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtgb.do',
					id : 'wtgb',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtcx').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtcx.do',
					id : 'wtcx',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			function openwi(id){
				var objectid = $("#objectid" + id).val();
				var workitemid = $("#workitemid" + id).val();
				window.location.href="/Feedback/wtcl/wtcl.do?objectid=" + objectid + "&workitemid="+workitemid;
			}
		</script>
	</body>

</html>