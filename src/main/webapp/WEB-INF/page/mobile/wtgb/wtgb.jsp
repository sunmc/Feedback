<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.bean.Common" %>
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
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">问题关闭</h1>
		</header>
		<div class="mui-content" >
			<div class="mui-input-group" style="margin: 5px;">
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
							<input name="khmc" type="text"  readonly="readonly" value="元丰">
						</div>
						<div class="mui-input-row">
							<label>产品名称</label>
							<input name="cpmc" type="text" readonly="readonly" value="全钢大两鼓">
						</div>
						<div class="mui-input-row">
							<label>项目阶段</label>
							<input name="xmjd" type="text" readonly="readonly" value="厂内调试">
						</div>
						<div class="mui-input-row">
							<label>部套名称</label>
							<input name="btmc" type="text" readonly="readonly" value="后压车">
						</div>
						<div class="mui-input-row">
							<label>图号</label>
							<input name="wtjth" type="text" readonly="readonly" value="">
						</div>
						<div class="mui-input-row">
							<label>物料编码</label>
							<input name="wlbm" type="text" readonly="readonly" value="">
						</div>
						<div class="mui-input-row">
							<div class="mui-inline">问题描述</div>
							<textarea name="wtms" rows="5" readonly="readonly">反包臂气缸快插需换成快插节流</textarea>
						</div>
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">图片</div>
							<div class="row image-list">
								<div id='image-list'>
									<img src="/Feedback/resource/images/shuijiao.jpg" class="image-item" data-preview-src=''  data-preview-group='1' >						
									<img src="/Feedback/resource/images/shuijiao.jpg" class="image-item" data-preview-src=''  data-preview-group='1' >
								</div>
							</div>
						</div>
					</div>
				</li>
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">问题受理信息</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row">
							<label>责任类别</label>
							<label>调试</label>
						</div>
						<div class="mui-input-row">
							<label>问题类别</label>
							<label>其他</label>
						</div>
						<div class="mui-input-row">
							<label>要求日期</label>
							<input name="yqrq" type="text" readonly="readonly" value="2016-10-11">
						</div>
						<div class="mui-input-row">
							<label>责任人</label>
							<input name="zrr" type="text" readonly="readonly" value="张三">
						</div>
					</div>
				</li>
			</ul>
			<div class="mui-inline">问题分析</div>
			<ul class="mui-table-view" style="margin: 0 5px 5px 5px ;">
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">非BOM物料计划流程</a>
					<div class="mui-collapse-content">
							<div class="mui-input-row">
								<label>流水号</label>
								<input type="text" id="lsh" value="OPM20.08.30-201610170002" readonly="readonly">
							</div>
							<div class="mui-input-row">
								<label>流程状态</label>
								<input type="text" id="lczt" value="进行中" readonly="readonly">
							</div>
					</div>
				</li>
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">图纸变更流程</a>
					<div class="mui-collapse-content">
						<div class="mui-input-group" style="margin: 10px 0 0 0;">
							<div class="mui-input-row">
								<label>单据号</label>
								<input type="text" id="lsh" value="123456789" readonly="readonly">
							</div>
							<div class="mui-input-row">
								<label>执行状态</label>
								<input type="text" id="zxzt" value="进行中" readonly="readonly">
							</div>
							<div class="mui-input-row">
								<label>承诺日期</label>
								<input type="date" id="cnrq" class=" mui-btn-block" value="2016-10-29" readonly="readonly">
							</div>
							<div class="mui-input-row">
								<label>实际日期</label>
								<input type="date" id="sjqi" class=" mui-btn-block" value="2016-10-29" readonly="readonly">
							</div>
							<div class="mui-input-row">
								<label>变更情况</label>
								<input type="text" id="bgqk" class=" mui-btn-block" value="进行中" readonly="readonly">
							</div>
						</div>
					</div>
				</li>
			</ul>
			<ul class="mui-table-view" style="margin: 0 5px 5px 5px ;">
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">原因及方案</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">原因分析</div>
							<textarea name="yyfx" rows="5" readonly="readonly">客户现场确定位置后固定</textarea>
						</div>
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">处理方案</div>
							<textarea name="clfa" rows="5" readonly="readonly">客户现场确定位置后固定</textarea>
						</div>
					</div>
				</li>
			</ul>
			<ul id="cs" class="mui-table-view" style="margin: 0 5px 5px 5px ;">
				
			</ul>
			<div class="mui-input-group" style="margin: 5px;">
				<div class="mui-input-row">
					<label>进度</label>
					<input type="text" value="80%" readonly="readonly">
				</div>
			</div>
			<div class="mui-button-row">
				<button type="button" class="mui-btn mui-btn-primary" onclick="return false;">确认</button>&nbsp;&nbsp;
				<button type="button" class="mui-btn mui-btn-danger" onclick="return false;">取消</button>
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
				addPlan();addPlan();
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
								d.text = sdata[i].name + "-" + sdata[i].code;
								dataselect.push(d);
							}
							picker.setData(dataselect);
							picker.show(function(items) {
								document.getElementById(textid).value = items[0].text;
							});
						}
					}
				});
				
			}
			var i = 0;
			function addPlan(){
				i++;
				$("#cs").append('<li id=cs'+i+' class="mui-table-view-cell mui-collapse">'+
						'<a class="mui-navigate-right" href="#">措施'+i+'</a>'+
						'<div class="mui-collapse-content">'+
							'<div class="mui-input-group">'+
								'<div class="mui-inline">工作内容</div>'+
								'<textarea name="clfa" rows="5"  readonly="readonly">11111</textarea>'+
								'<div class="mui-input-row" style="margin: 5px 0 0 0;">'+
									'<label>责任人</label>'+
									'<input type="text" id="zrr'+i+'" value="张三" readonly="readonly">'+
								'</div>'+
								'<div class="mui-input-row" style="margin: 5px 0 0 0;">'+
									'<label>时间计划</label>'+
									'<input type="date" id="sjjh'+i+'" class=" mui-btn-block" value="2016-10-27" readonly="readonly">'+
								'</div>'+
							'</div>'+
							'<div id="jj'+i+'" class="mui-input-group">'+
								'<div class="mui-inline">解决问题</div>'+
								'<textarea name="clfa" rows="5"  readonly="readonly">2222222</textarea>'+
								'<div class="mui-input-row" style="margin: 5px 0 0 0;">'+
									'<label>完成时间</label>'+
									'<input type="date" id="wcsj'+i+'" class="mui-btn-block" value="2016-10-27"  readonly="readonly">'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</li>');
			}
		</script>
	</body>

</html>