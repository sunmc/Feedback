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
			<!-- <a id="sy" class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a> -->
			<h1 class="mui-title">拖期任务汇总</h1>
		</header>
		<ul id="list" class="mui-table-view">
		</ul>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script type="text/javascript">
		mui.init({
			swipeBack: true //启用右滑关闭功能
		});
		mui('.mui-scroll-wrapper').scroll();
		var url = "/Feedback/wtcx/overtask.do";
		$(document).ready(function(){
			//加载紧急程度选项
			$.ajax({
				url:url,
				dataType:'json',
				type: "post", 
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					if(data.flag){
						var overtask = data.data;
						if(overtask.ytn > 0){
							var li = '<li class="mui-table-view-cell">' +
						        		'<a href="/Feedback/wtcx/overtaskmx.do?start=0&end=1" class="mui-navigate-right">' + overtask.ytn + '条拖期一天内任务</a>' +
							    	 '</li>';
							$("#list").append(li); 
						}
						if(overtask.stn > 0){
							var li = '<li class="mui-table-view-cell">' +
						        		'<a href="/Feedback/wtcx/overtaskmx.do?start=1&end=3" class="mui-navigate-right">' + overtask.stn + '条拖期三天内任务</a>' +
							    	 '</li>';
							$("#list").append(li); 
						}
						if(overtask.wtn > 0){
							var li = '<li class="mui-table-view-cell">' +
						        		'<a href="/Feedback/wtcx/overtaskmx.do?start=3&end=5" class="mui-navigate-right">' + overtask.wtn + '条拖期五天内任务</a>' +
							    	 '</li>';
							$("#list").append(li); 
						}
						if(overtask.wts > 0){
							var li = '<li class="mui-table-view-cell">' +
						        		'<a  href="/Feedback/wtcx/overtaskmx.do?end=5" class="mui-navigate-right">' + overtask.wts + '条拖期五天以上任务</a>' +
							    	 '</li>';
							$("#list").append(li); 
						}
					}
				}
			});
		});
		</script>
	</body>
</html>