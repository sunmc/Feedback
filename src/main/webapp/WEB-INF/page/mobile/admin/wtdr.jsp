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
	</head>
		
	<body>
		<header class="mui-bar mui-bar-nav">
			<!-- <a id="sy" class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a> -->
			<h1 class="mui-title">问题导入</h1>
		</header>
		<div class="mui-content" >
		<form id="form" action="/Feedback/admin/wtdr.do" method="post" enctype="multipart/form-data" >
			<div class="mui-input-group" style="margin: 5px 0 0 0;">
				<div class="mui-input-row">
					<label>导入文件</label>
					<input id="file" name="file" type="file" >
				</div>
			</div>
			<div class="mui-button-row">
				<input type="submit" class="mui-btn mui-btn-primary" value="提交">
			</div>
		</form>
		</div>
		
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script>
		$(document).ready(function(){
			$('#form').submit(function() {
			   $(this).ajaxSubmit(function(result) {   
			      //回调操作   
			      if(result.flag){
			    	  
			      }
			   });
			   return false; //阻止表单默认提交
			});
			
		});
		
		</script>
	</body>
</html>