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

	<!--标准mui.css-->
	<link rel="stylesheet" href="/Feedback/resource/css/mui.min.css">
</head>
<body>
	<header class="mui-bar mui-bar-nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">联系人</h1>
	</header>
	<div class="mui-content" >
			<div class="mui-input-row mui-search">
				<input id="search" type="search" class="mui-input-clear" placeholder="搜索" onkeyup="search(this.value)">
			</div>
		<div class="mui-content-padded" style="margin: 5px;">
			<div id="contact" class="mui-input-group" style="margin: 1px 0 0 0;position: static;">
				<ul id="xmlist" class="mui-table-view">
				</ul>
			</div>
		</div>
	</div>
	<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
	<script src="/Feedback/resource/js/mui.min.js"></script>
		
	<script type="text/javascript">
		mui.init({
			swipeBack: true //启用右滑关闭功能
		});
		mui('.mui-scroll-wrapper').scroll();
		function search(sval){
			$("#xmlist").find("li").remove(); 
			$.ajax({
				url:'/Feedback/User/search.do',
				data:{text:sval},
				dataType:'json',
				type: "post", 
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					if(data.flag){
						var xmdata = data.data;
						for(var i = 0; i < xmdata.length; i++){
							$("#xmlist").append('<li class="mui-table-view-cell"> <a href="tel:'+xmdata[i].mobile+'">'+xmdata[i].name+'-'+xmdata[i].bmmc+'</a> </li>');
						}
					}
				}
			});
		}
	</script>
</body>
</html>