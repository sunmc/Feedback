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
			<h1 class="mui-title">调整活动</h1>
		</header>
		<div class="mui-content" >
			<div class="mui-input-group" style="margin: 5px;">
				<div class="mui-input-row">
					<label>节点名称</label>
					<select id="activitycode" name="activitycode" onchange="load(this.options[this.options.selectedIndex].value)">
						<option><option>
						<option value="wttc">问题提出</option>
						<option value="wtpd">问题判定</option>
						<option value="wtcl">问题处理</option>
						<option value="wtjj">问题解决</option>
						<option value="wtgb">问题关闭</option>
					</select>
				</div>
				<div class="mui-input-row">
					<label>选择参与人</label>
					<input id="xz" type="text" onchange="search(this.value)"></input>
				</div>
			</div>
			
			<div>
				<div class="mui-inline required">参与人</div>
				<textarea id="receivername" name="receivername" rows="3"></textarea>
			</div>
		</div>
		<div hidden="hidden">
			<input id="obejctid" type="text" value="${objectid}"/>
			<textarea id="receiver" name="receiver" ></textarea>
		</div>
		
		<div class="mui-button-row">
			<input id="bt_active" type="button" class="mui-btn mui-btn-primary" value="激活活动" onclick="active()">
			<input id="bt_cancel" type="button" class="mui-btn mui-btn-primary" value="取消活动" onclick="cancel()">
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
			function load(activity){
				var objectid = $("#obejctid").val();
				$.ajax({
					url:'/Feedback/workitem/clrs.do',
					dataType:'json',
					data:{bizobjectid:objectid,activitycode:activity},
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var wtlbdata = data.data;
							var receivers = "";
							var receivernames = "";
							for(var i = 0; i < wtlbdata.length; i++){
								var recevier = wtlbdata[i].receiver;
								recevier = recevier.replace(/(^\s*)|(\s*$)/g, "") + ";";
								receivers += recevier;
								var receivername = wtlbdata[i].receivername;
								receivername = receivername.replace(/(^\s*)|(\s*$)/g, "") + ";";
								receivernames += receivername;
							}
							$("#receiver").val(receivers);
							$("#receivername").val(receivernames)
							$("#bt_active").show();
							if(wtlbdata.length > 0){
								$("#bt_cancel").show();
							}else{
								$("#bt_cancel").hide();
							}
						}
					}
				});
			}
			function active(){
				var objectid = $("#obejctid").val();
				var activity = $("#activitycode").val();
				var activityname = $("#activitycode").find("option:selected").text();
				var receiver = $("#receivername").val();
				$.ajax({
					url:'/Feedback/admin/adjust.do',
					data:{bizobjectid:objectid,activitycode:activity,activityname:activityname,receivername:receiver,bizobjectschemacode:activity},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							alert(data.data);
							window.location.reload();
						}else{
							alert(data.message);
						}
					}
				});
			}
			function cancel(){
				var objectid = $("#obejctid").val();
				var activity = $("#activitycode").val();
				var activityname = $("#activitycode").find("option:selected").text();
				var receiver = $("#receivername").val();
				$.ajax({
					url:'/Feedback/admin/cancelWork.do',
					data:{bizobjectid:objectid,activitycode:activity,activityname:activityname,receivername:receiver,bizobjectschemacode:activity},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							alert(data.data);
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
								var receivers = $("#receiver").val();
								receivers = receivers + items[0].value + ";";
								$("#receiver").val(receivers);
								var receivernames = $("#receivername").val();
								receivernames = receivernames + items[0].text + ";";
								$("#receivername").val(receivernames);
							});
						}
					}
				});
			}
		</script>
	</body>
</html>