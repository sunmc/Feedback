<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.bean.Common" %>
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
		<div align="center" style="margin-top: 240px">
		提交成功！<span id="time">5</span>秒后跳转到查询页面<br/>
		<button type="button" data-loading-icon-position="right" class="mui-btn" id="bt">点击查看问题明细</button>
		</div>
		<div hidden="hidden">
			<input id="objectid" value="${objectid}">
		</div>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script type="text/javascript">
			document.getElementById('bt').addEventListener('tap', function() {
				var objectid = $("#objectid").val();
				var urls = '/Feedback/wtcx/wtcx.do?objectid='+objectid;
				mui.openWindow({
					url : urls,
					id : 'wtgz',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			$(document).ready(function(){
				var count = 5;
				setInterval(function(){
					if(count == 0){
						window.location = "/Feedback/common.do";
					}else{
						count--;
						document.getElementById("time").innerHTML = count;
					}
				}, 1000);
			});
		</script>
	</body>
</html>