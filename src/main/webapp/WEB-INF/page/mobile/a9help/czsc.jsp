<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.bean.Common" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<link rel="stylesheet" type="text/css" href="/Feedback/resource/css/app.css" />
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">操作手册</h1>
		</header>
		<div class="mui-content">
		    <ul id="itemlist" class="mui-table-view mui-table-view-chevron">
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right" href="/Feedback/help/wttc.do">
						问题提出
					</a>
				</li>		
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right" href="/Feedback/help/wtpd.do">
						问题判定
					</a>
				</li>
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right" href="/Feedback/help/wtcl.do">
						问题处理
					</a>
				</li>
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right" href="/Feedback/help/wtjj.do">
						问题解决
					</a>
				</li>
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right" href="/Feedback/help/wtgb.do">
						问题关闭
					</a>
				</li>
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right" href="/Feedback/help/wtcx.do">
						问题查询
					</a>
				</li>
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right" href="/Feedback/help/wtts.do">
						问题推送
					</a>
				</li>
			</ul>
		</div>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script>
			mui.init({
				swipeBack:true //启用右滑关闭功能
			});
		</script>
	</body>
</html>