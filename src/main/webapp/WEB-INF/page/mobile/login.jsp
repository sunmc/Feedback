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

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">登录</h1>
		</header>
		<div class="mui-content">
			<form method="post" action="/Feedback/User/LoginValidate.do">
				<div class="mui-content-padded" style="margin: 5px;">
					<div class="mui-input-group" >
						<div class="mui-input-row">
							<label>账号</label>
							<input id='code' name="code" type="text" class="mui-input-clear mui-input" placeholder="请输入账号">
						</div>
						<div class="mui-input-row">
							<label>密码</label>
							<input id='passwd' name="passwd" type="password" class="mui-input-clear mui-input" placeholder="请输入密码">
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
		<script src="js/mui.min.js"></script>
		<script src="js/app.js"></script>
		<script>
		</script>
	</body>
</html>