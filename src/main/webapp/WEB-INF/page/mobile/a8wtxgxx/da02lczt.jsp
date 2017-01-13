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
			p{
				color: black;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">流程状态</h1>
		</header>
		<div class="mui-content" style="height:100%">
			<c:forEach items="${workitems}" var="item" varStatus="status">
				<div class="mui-card">
					<div class="mui-card-content" style="margin-top: 1px">
						<div class="mui-card-content-inner">
							<p>节点名称：${item.activityname}</p>
							<p>接收时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.createtime}"/></p>
							<p>完成时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.finishtime}"/></p>
							<p>处理人：${item.receivername}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
	</body>
</html>