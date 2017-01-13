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
			<c:if test="${user.role == 1}">
				<a id="cz" class="mui-icon mui-pull-right" href="#topPopover" style="font-size:14px;margin-top: 4px">操作</a>
			</c:if>
		</header>
		<div class="mui-content" style="height:100%">
			<input type="text" id="objectid" value="${objectid }" hidden="hidden"/>
			<c:forEach items="${workitems}" var="item" varStatus="status">
				<div class="mui-card">
					<div class="mui-card-content" style="margin-top: 1px">
						<div class="mui-card-content-inner">
							<input id="bizobjectid" type="text" value="${item.bizobjectid }" hidden="hidden"/>
							<p>节点名称：${item.activityname}</p>
							<p>接收人：${item.receivername}</p>
							<p>接收时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.receivetime}"/></p>
							<p>完成人：${item.finishername}</p>
							<p>完成时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.finishtime}"/></p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<!--右上角弹出菜单-->
		<div id="topPopover" class="mui-popover" style="height:150px">
			<div class="mui-popover-arrow"></div>
			<div class="mui-scroll-wrapper">
				<div class="mui-scroll">
					<div class="mui-input-group">
						<div class="mui-input-row" id="confirmBtn">
							<label>删除流程</label>
						</div>	
						<div class="mui-input-row" id="adjustActivity">
							<label>调整活动</label>
						</div>
					</div>				
				</div>
			</div>
		</div>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script type="text/javascript">
			//mui初始化
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			document.getElementById("confirmBtn").addEventListener('tap', function() {
				var btnArray = ['否', '是'];
				mui.confirm('确认删除？', '警告', btnArray, function(e) {
					if (e.index == 1) {
						delwt();
					} else {
						
					}
				})
			});
			document.getElementById("adjustActivity").addEventListener('tap', function() {
				window.location.href = "/Feedback/admin/adjustView.do?objectid=" + $("#objectid").val();
			});
			function delwt(){
				var id = $("#objectid").val();
				$.ajax({
					url:'/Feedback/wtcx/del.do',
					data:{objectid:id},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							alert('删除成功！');
							window.location.href = "/Feedback/wtcx.do";
						}
					}
				});
			}
		</script>
	</body>
</html>