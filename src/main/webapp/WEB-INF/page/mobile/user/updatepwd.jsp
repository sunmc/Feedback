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
	<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">密码修改</h1>
		</header>
		<div class="mui-content">
			<form method="post" action="/Feedback/User/update.do">
				<div class="mui-content-padded" style="margin: 5px;">
					<div class="mui-input-group" >
						<div class="mui-input-row">
							<label>新密码</label>
							<input id='passwd' name="passwd" type="password" class="mui-input-clear mui-input" placeholder="请输入新密码">
						</div>
						<div class="mui-input-row">
							<label>密码确认</label>
							<input id='passwd1' name="passwd1" type="password" class="mui-input-clear mui-input" placeholder="请确认新密码">
						</div>
					</div>
					
					<div class="mui-content-padded oauth-area">
		
					</div>
					<div class="mui-button-row">
						<input type="submit" class="mui-btn mui-btn-primary" >
					</div>
				</div>
			</form>
		</div>
		
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script>
		$(document).ready(function(){
			alert("您的密码过于简单，请修改密码！");
			//表单验证
			$("form").submit(function(event){
				var pwd = $("#passwd").val();
				var pwd1 = $("#passwd1").val();
				if(!pwd){
					alert("请输入新密码！");
					return false;
				}
				if(!pwd1){
					alert("请确认新密码！");
					return false;
				}
				if(pwd != pwd1){
					alert("两次输入的密码不一致！");
					return false;
				}
			});
		});
		</script>
	</body>
</html>