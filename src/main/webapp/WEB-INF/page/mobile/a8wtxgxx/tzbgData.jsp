<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.bean.Common" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html class="feedback">

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
			<h1 class="mui-title">图纸变更流程程信息</h1>
			<a id="lczt" class="mui-icon mui-pull-right" style="font-size:14px;margin-top: 4px">流程状态</a>
		</header>
		<div class="mui-content" >
			<div class="mui-input-group" style="margin: 5px;">
				<div class="mui-input-row">
					<%-- <input id="lsh" type="text" readonly="readonly" value="${tzbg[0].PHT_FILENO}" style="width:100%"> --%>
					<input id="lsh" type="text" readonly="readonly" value="${wth}" style="width:100%">
				</div>
			</div>
			<c:forEach items="${tzbg}" var="item" varStatus="status">
				<div class="mui-card">
					<span class="mui-badge">${status.index + 1 } </span>
					<div class="mui-card-content" style="margin-top: 1px">
						<div class="mui-card-content-inner">
							<p>物料编码：${item.PHT_ITEMIDINFO}</p>
							<p>物料描述：${item.PHT_ITEMNAME}</p>
							<p>图号：${item.PHT_ITEMTH}</p>
							<p>变更前数量：${item.PHT_ORIGINALNUM}</p>
							<p>变更后数量：${item.PHT_NUM}</p>
						</div>
					</div>
				</div>
			</c:forEach>
					
		</div>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script type="text/javascript">
		mui.init({
			swipeBack: true //启用右滑关闭功能
		});
		mui('.mui-scroll-wrapper').scroll();
		$(document).ready(function(){
			
		});
		document.getElementById('lczt').addEventListener('tap', function() {
			var urls = '/Feedback/xgxx/tzbgWorkItem.do?lsh=' + $("#lsh").val();
			mui.openWindow({
				url : urls,
				id : 'lczt',
				show : {
					aniShow : 'pop-in'
				},
				waiting : {
					autoShow : false
				}
			});
		})
		</script>
	</body>
</html>