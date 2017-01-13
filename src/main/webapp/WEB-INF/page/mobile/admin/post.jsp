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
		<link href="/Feedback/resource/css/mui.picker.css" rel="stylesheet" />
		<link href="/Feedback/resource/css/mui.poppicker.css" rel="stylesheet" />
	</head>

	<body>
		<header class="mui-bar mui-bar-nav" style="height:40px;line-height: 40px">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">管理岗位</h1>
		</header>
		
						<%-- <c:forEach items="${posts}" var="post">
					    	<option value="${post.postcode }">${post.postname }</option>
					    </c:forEach> --%>
						
		<div class="mui-content" >
			<div class="mui-input-group" style="margin: 5px;">
				<div class="mui-input-row">
					<label>岗位名称</label>
					<select id="postcode" name="postcode" onchange="load()">
						<option><option>
						<option value="wtpd">问题判定岗</option>
						<option value="bmfzr">部门负责人</option>
						<option value="cpjsfzr">产品技术负责人</option>
						<option value="zrr">责任人</option>
						<option value="xmjl">项目经理</option>
					</select>
				</div>
				<div class="mui-input-row">
					<label>选择人员</label>
					<input id="xz" type="text" onchange="search(this.value)"></input>
				</div>
			</div>
			
			<div>
				<div class="mui-inline required">岗位参与者</div>
				<textarea id="owner" name="owner" rows="3"></textarea>
			</div>
		</div>
		
		<div class="mui-button-row">
			<input id="bt_save" type="button" class="mui-btn mui-btn-primary" value="保存" onclick="save()">
		</div>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script src="/Feedback/resource/js/mui.picker.js"></script>
		<script src="/Feedback/resource/js/mui.poppicker.js"></script>
		<script>
			$(document).ready(function(){
				$("#bt_cancel").hide();
				$("#bt_active").hide();
			});
			function load(){
				$("#bt_save").hide();
				var postcode = $("#postcode").val();
				$.ajax({
					url:'/Feedback/post/getByCode.do',
					dataType:'json',
					data:{code:postcode},
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var wtlbdata = data.data;
							var names = "";
							for(var i = 0; i < wtlbdata.length; i++){
								var name = wtlbdata[i].user.xm;
								name = name.replace(/(^\s*)|(\s*$)/g, "") + ";";
								names += name;
							}
							$("#owner").val(names);
							$("#bt_save").show();
						}
					}
				});
			}
			function save(){
				var postcode = $("#postcode").val();
				var postname = $("#postcode").find("option:selected").text();
				var owner = $("#owner").val();
				$.ajax({
					url:'/Feedback/post/update.do',
					data:{postcode:postcode,postname:postname,owner:owner},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							alert(data.message);
							window.location.reload();
						}else{
							alert(data.message);
						}
					}
				});
			}
			function search(textv){
				var picker = new mui.PopPicker(); 
				var dataselect = new Array();
				$.ajax({
					url:'/Feedback/User/search.do',
					data:{text:textv},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var sdata = data.data;
							for(var i = 0; i < sdata.length; i++){
								var d = new Object();
								d.value = sdata[i].objectid;
								d.text = sdata[i].xm + "-" + sdata[i].zh;
								dataselect.push(d);
							}
							picker.setData(dataselect);
							picker.show(function(items) {
								var names = $("#owner").val();
								names = names + items[0].text + ";";
								$("#owner").val(names);
							});
						}
					}
				});
			}
		</script>
	</body>
</html>