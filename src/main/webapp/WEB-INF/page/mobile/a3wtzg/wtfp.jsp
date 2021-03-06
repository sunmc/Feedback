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

		<!--标准mui.css-->
		<link rel="stylesheet" href="/Feedback/resource/css/mui.min.css">
		<link rel="stylesheet" href="/Feedback/resource/css/feedback.css">
		<link href="/Feedback/resource/css/mui.picker.css" rel="stylesheet" />
		<link href="/Feedback/resource/css/mui.poppicker.css" rel="stylesheet" />
		<style type="text/css">
			.mui-preview-image.mui-fullscreen {
				position: fixed;
				z-index: 20;
				background-color: #000;
			}
			.mui-preview-header,
			.mui-preview-footer {
				position: absolute;
				width: 100%;
				left: 0;
				z-index: 10;
			}
			.mui-preview-header {
				height: 44px;
				top: 0;
			}
			.mui-preview-footer {
				height: 50px;
				bottom: 0px;
			}
			.mui-preview-header .mui-preview-indicator {
				display: block;
				line-height: 25px;
				color: #fff;
				text-align: center;
				margin: 15px auto 4;
				width: 70px;
				background-color: rgba(0, 0, 0, 0.4);
				border-radius: 12px;
				font-size: 16px;
			}
			.mui-preview-image {
				display: none;
				-webkit-animation-duration: 0.5s;
				animation-duration: 0.5s;
				-webkit-animation-fill-mode: both;
				animation-fill-mode: both;
			}
			.mui-preview-image.mui-preview-in {
				-webkit-animation-name: fadeIn;
				animation-name: fadeIn;
			}
			.mui-preview-image.mui-preview-out {
				background: none;
				-webkit-animation-name: fadeOut;
				animation-name: fadeOut;
			}
			.mui-preview-image.mui-preview-out .mui-preview-header,
			.mui-preview-image.mui-preview-out .mui-preview-footer {
				display: none;
			}
			.mui-zoom-scroller {
				position: absolute;
				display: -webkit-box;
				display: -webkit-flex;
				display: flex;
				-webkit-box-align: center;
				-webkit-align-items: center;
				align-items: center;
				-webkit-box-pack: center;
				-webkit-justify-content: center;
				justify-content: center;
				left: 0;
				right: 0;
				bottom: 0;
				top: 0;
				width: 100%;
				height: 100%;
				margin: 0;
				-webkit-backface-visibility: hidden;
			}
			.mui-zoom {
				-webkit-transform-style: preserve-3d;
				transform-style: preserve-3d;
			}
			.mui-slider .mui-slider-group .mui-slider-item img {
				width: auto;
				height: auto;
				max-width: 100%;
				max-height: 100%;
			}
			.mui-android-4-1 .mui-slider .mui-slider-group .mui-slider-item img {
				width: 100%;
			}
			.mui-android-4-1 .mui-slider.mui-preview-image .mui-slider-group .mui-slider-item {
				display: inline-table;
			}
			.mui-android-4-1 .mui-slider.mui-preview-image .mui-zoom-scroller img {
				display: table-cell;
				vertical-align: middle;
			}
			.mui-preview-loading {
				position: absolute;
				width: 100%;
				height: 100%;
				top: 0;
				left: 0;
				display: none;
			}
			.mui-preview-loading.mui-active {
				display: block;
			}
			.mui-preview-loading .mui-spinner-white {
				position: absolute;
				top: 50%;
				left: 50%;
				margin-left: -25px;
				margin-top: -25px;
				height: 50px;
				width: 50px;
			}
			.mui-preview-image img.mui-transitioning {
				-webkit-transition: -webkit-transform 0.5s ease, opacity 0.5s ease;
				transition: transform 0.5s ease, opacity 0.5s ease;
			}
			@-webkit-keyframes fadeIn {
				0% {
					opacity: 0;
				}
				100% {
					opacity: 1;
				}
			}
			@keyframes fadeIn {
				0% {
					opacity: 0;
				}
				100% {
					opacity: 1;
				}
			}
			@-webkit-keyframes fadeOut {
				0% {
					opacity: 1;
				}
				100% {
					opacity: 0;
				}
			}
			@keyframes fadeOut {
				0% {
					opacity: 1;
				}
				100% {
					opacity: 0;
				}
			}
			p img {
				max-width: 100%;
				height: auto;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title"><%= Common.wtzg %></h1>
		</header>
		<div class="mui-content" >
			<div class="mui-input-group" style="margin: 5px;">
				<div class="mui-input-row">
					<label>流水号</label>
					<input type="text" readonly="readonly" value="${project.lsh }">
				</div>
				<div class="mui-input-row">
					<label>发起人</label>
					<input type="text" readonly="readonly" value="${project.fqr.xm }">
				</div>
				<div class="mui-input-row" style="line-height: 40px">
					<label>发起时间</label>
					<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${project.createtime }" />
				</div>
				<div class="mui-input-row">
					<label>项目编号</label>
					<input name="xmbh" type="text" readonly="readonly" value="S-201407048">
				</div>
			</div>
			<ul class="mui-table-view" style="margin: 0 5px 5px 5px ;">
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">项目问题信息</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row">
							<label>客户名称</label>
							<input name="khmc" type="text"  readonly="readonly" value="${project.khmc}">
						</div>
						<div class="mui-input-row">
							<label>产品名称</label>
							<input name="cpmc" type="text" readonly="readonly" value="${project.cpmc }">
						</div>
						<div class="mui-input-row">
							<label>项目阶段</label>
							<input name="xmjd" type="text" readonly="readonly" value="${project.xmjd }">
						</div>
						<div class="mui-input-row">
							<label>部套名称</label>
							<input name="btmc" type="text" readonly="readonly" value="${project.btmc }">
						</div>
						<div class="mui-input-row" style="height:auto;line-height: 40px">
							<label >要求完成日期</label>
							<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${project.yqwcsj }" />
						</div> 
						<div class="mui-input-row mui-input-range">
							<label>紧急程度</label>
			            	<input name="jjcd" type="text"  value="${project.jjcd }" readonly="readonly" >
			        	</div>
						<div class="mui-input-row">
							<label>图号</label>
							<input name="wtjth" type="text" readonly="readonly" value="${project.wtjth }">
						</div>
						<div class="mui-input-row">
							<label>物料编码</label>
							<input name="wlbm" type="text" readonly="readonly" value="${project.wlbm }">
						</div>
						<div class="mui-input-row">
							<div class="mui-inline">问题描述</div>
							<textarea name="wtms" rows="5" readonly="readonly">${project.wtms }</textarea>
						</div>
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">图片</div>
							<div class="row image-list">
								<div id='image-list'>
									<c:forEach items="${project.wttps}" var="tppath">
								      <img src="${tppath.downloadurl}" class="image-item" data-preview-src=''  data-preview-group='1' >
								    </c:forEach>
								</div> 
							</div>
						</div>
					</div>
				</li>
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">问题判定信息</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row">
							<label>责任类别</label>
							<input name="zrlb" type="text" readonly="readonly" value="${project.zrlb }">
						</div>
						<div class="mui-input-row">
							<label>问题类别</label>
							<input name="wtlb" type="text" readonly="readonly" value="${project.wtlb }">
						</div>
						<div class="mui-input-row">
							<label>责任人</label>
							<input name="uzrr.xm" type="text" readonly="readonly" value="${project.uzrr.xm }">
						</div>
					</div>
				</li>
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">问题原因及方案</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">原因分析</div>
							<textarea name="yyfx" rows="5" readonly="readonly">${project.yyfx }</textarea>
						</div>
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">处理方案</div>
							<textarea name="clfa" rows="5" readonly="readonly">${project.clfa }</textarea>
						</div>
					</div>
				</li>
			</ul>
			<div class="required">
			
				<form action="/Feedback/wtzg/submitfp.do" method="post">
					<div class="mui-input-row mui-input-range">
						<label>工作进度</label>
						<input name="gzjd" type="range"  value="0" min="0" max="100" >
					</div>
					<div class="mui-input-row">
						<div class="mui-inline">工作内容</div>
						<textarea id="implementations[0].jjcsjjwt" name="implementations[0].jjcsjjwt" rows="3" placeholder="请详细描述您本次的工作内容..."></textarea>
					</div>
					<div id="cs">
					</div>
					<div class="mui-button-row">
						<input type="submit" class="mui-btn mui-btn-primary" value="提交">
						<button type="button" class="mui-btn mui-btn-primary" onclick="addPlan()">工作分派</button>
					</div>
					<div hidden="hidden">
						<input type="text"  name="objectid" value="${project.objectid }">
					</div>
				</form>
			</div>
			<div hidden="hidden">
				<input type="text" id="error" value="${error} ">				
			</div>
			
		</div>
				
		
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script src="/Feedback/resource/js/mui.zoom.js"></script>
		<script src="/Feedback/resource/js/mui.previewimage.js"></script>
		<script src="/Feedback/resource/js/mui.picker.js"></script>
		<script src="/Feedback/resource/js/mui.poppicker.js"></script>
		<script>
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			mui('.mui-scroll-wrapper').scroll();
			mui.previewImage();
			var url = "/Feedback/wtdata.do";
			$(document).ready(function(){
				var error = $("#error").val();
				if(error != null && error.trim().length > 0){
					alert(error);
				}
				//form提交验证
				$("form").submit(function(event){
					var check = true;
					var invalid;
					mui(".required input").each(function() {
						if (!this.value || this.value.trim() == "") {
							invalid = this.id;
							check = false;
						}
					}); 
					mui(".required textarea").each(function() {
						if (!this.value || this.value.trim() == "") {
							invalid = this.id;
							check = false;
						}
					});
					if(!check){
						var text = $("#" + invalid).prev().text();
						if(!text){
							var i = invalid.substring(invalid.indexOf("[") + 1, invalid.indexOf("]"));
							i ++;
							var name = invalid.substring(invalid.indexOf(".") + 1);
							if(name.indexOf("jjcs") >= 0){
								name = "工作任务";
							}else if(name.indexOf("jjcszrrshow") >= 0){
								name = "责任人";
							}else if(name.indexOf("jjcsjhwcsj") >= 0){
								name = "时间计划"
							}
							alert("第" + i  + "条指派内容,[" + name + "]不能为空");
						}else{
							alert("[" + text + "]不能为空!");
						}
					}
					return check;
				});
			});
			function search(textid){
				var picker = new mui.PopPicker(); 
				var dataselect = new Array();
				var textv = document.getElementById(textid).value;
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
								document.getElementById(textid).value = items[0].text;
								textid = textid.substring(0,textid.indexOf("show"));
								document.getElementById(textid).value = items[0].value;
							});
						}
					}
				});
				
			}
			var i=1;
			function addPlan(){
				$("#cs").append(
							'<div class="mui-input-group required" id="cs'+i+'" >'+
								'<a href="#" style="float:right;" onclick="delPlan(cs'+i+')">移除分派</a>'+
								'<div id="123" class="mui-inline">工作任务</div>'+
								'<textarea id="implementations['+i+'].jjcs" name="implementations['+i+'].jjcs" rows="5" placeholder="请详细描述任务内容..."></textarea>'+
								'<div class="mui-input-row" style="margin: 5px 0 0 0;">'+
									'<label>责任人</label>'+
									'<input type="text" id="implementations['+i+'].jjcszrrshow" class="责任人 mui-btn-block" onchange="search(this.id)">'+
								'</div>'+
								'<div class="mui-input-row" style="margin: 5px 0 0 0;">'+
									'<label>时间计划</label>'+
									'<input type="date" id="implementations['+i+'].jjcsjhwcsj" name="implementations['+i+'].jjcsjhwcsj" class=" mui-btn-block">'+
								'</div>'+
								'<div hidden="hidden">'+
									'<input type="text" id="implementations['+i+'].jjcszrr" name="implementations['+i+'].jjcszrr" class=" mui-btn-block" '+
								'</div>'+
							'</div>');
				i++;
			}
			function delPlan(item){
				item.remove();
			}
		</script>
	</body>

</html>