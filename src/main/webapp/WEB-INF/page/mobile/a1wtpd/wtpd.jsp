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
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title"><%= Common.wtpd %></h1>
		</header>
		<div class="mui-content" >
			
			<div class="mui-input-group" style="margin: 5px;">
				<div class="mui-input-row">
					<label>问题号</label>
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
					<input name="xmbh" type="text" readonly="readonly" value="${project.xmbh }">
				</div>
			</div>
			<form action="/Feedback/wtpd/submit.do" method="post">
				<ul class="mui-table-view" style="margin: 0 5px 5px 5px ;">
					<li class="mui-table-view-cell mui-collapse mui-active">
						<a class="mui-navigate-right" href="#">项目问题信息</a>
						<div class="mui-collapse-content">
							<div class="mui-input-row" hidden="hidden">
								<label>客户名称</label>
								<input name="khmc" type="text" value="${project.khmc}">
							</div>
							<div class="mui-input-row">
								<label>项目名称</label>
								<input name="xmmc" type="text" value="${project.xmmc}">
							</div>
							<div class="mui-input-row">
								<label>项目经理</label>
								<select id="xmjl" name="xmjl">
									<option></option>
									<c:forEach items="${xmjls}" var="xmjl" varStatus="status">
										<option value="${xmjl.user.objectid}">${xmjl.user.xm}</option>
									</c:forEach>
								</select>
								<input id="xmjlvalue" type="text" value="${project.xmjl}" hidden="hidden"/>
								<script>
									var xmjl = $("#xmjlvalue").val();
									$("#xmjl").val(xmjl);
								</script>
							</div>
							<div class="mui-input-row mui-input-range">
								<label>紧急程度</label>
								<input hidden="hidden" type="text" id="jjcds" value="${project.jjcd }" />
								<select id="jjcd" name="jjcd">
									<option></option>
								</select>
				        	</div>
							<div class="mui-input-row">
								<label>图号</label>
								<input name="wtjth" type="text"  value="${project.wtjth }">
							</div>
							<div class="mui-input-row">
								<label>物料编码</label>
								<input name="wlbm" type="text"  value="${project.wlbm }">
							</div>
							<div class="mui-input-row">
								<div class="mui-inline">问题描述</div>
								<textarea name="wtms" rows="5" >${project.wtms }</textarea>
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
				</ul>
					
				<div class="mui-content-padded" style="margin: 5px;">
					<div class="mui-input-group required">
						<div class="mui-input-row required">
							<label  style="color: red;">产品类别</label>
							<select id="cplb" name="cplb">
								<option />
								<c:forEach items="${cplbs}" var="cplb">
									<c:if test="${cplb.value == project.cplb}">
										<option value="${cplb.value}" selected="selected">${cplb.text}</option>
									</c:if>
									<c:if test="${cplb.value != project.cplb}">
										<option value="${cplb.value}">${cplb.text}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="mui-input-row required">
							<label style="color: red;">项目阶段</label>
							<select id="xmjd" name="xmjd">
								<option></option>
								<c:forEach items="${xmjds}" var="xmjd">
									<c:if test="${xmjd.value == project.xmjd}">
										<option value="${xmjd.value}" selected="selected">${xmjd.text}</option>
									</c:if>
									<c:if test="${xmjd.value != project.xmjd}">
										<option value="${xmjd.value}">${xmjd.text}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="mui-input-row required">
							<label>部套名称</label>
							<input id="btmc" name="btmc" type="text" onclick="btpickershow()" value="${project.btmc }">
						</div>
						<div class="mui-input-row required">
							<label style="color: red;">部套数量</label>
							<input id="btsl" name="btsl" type="number" class="" value="${project.btsl }" >
						</div>
						<div class="mui-input-row">
							<label>责任类别</label>
							<select id="zrlb" name="zrlb" class=" mui-btn-block">
								<option></option>
								<c:forEach items="${zrlbs}" var="zrlb">
									<c:if test="${zrlb.value == project.zrlb}">
										<option value="${zrlb.value}" selected="selected">${zrlb.text}</option>
									</c:if>
									<c:if test="${zrlb.value != project.zrlb}">
										<option value="${zrlb.value}">${zrlb.text}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="mui-input-row">
							<label>问题类别</label>
							<select id="wtlb" name="wtlb" class=" mui-btn-block">
								<option></option>
								<c:forEach items="${wtlbs}" var="wtlb">
									<c:if test="${wtlb.value == project.wtlb}">
										<option value="${wtlb.value}" selected="selected">${wtlb.text}</option>
									</c:if>
									<c:if test="${wtlb.value != project.wtlb}">
										<option value="${wtlb.value}">${wtlb.text}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="mui-input-row">
							<label>责任人</label>
							<input type="text" id="zrrshow" class=" mui-btn-block" onchange="search(this.id)"> 
						</div>
						<div class="mui-input-row" style="height:auto;">
							<label style="width: 40%">要求完成日期</label>
							<input style="width: 60%" type="date" id="yqwcsj" name="yqwcsj"  value="${project.yqwcsj }" />
						</div> 
						<div class="mui-input-row" style="height:auto;">
							<label style="width: 40%">重复发生问题</label>
							<select id="wtsfcffs" name="wtsfcffs" class=" mui-btn-block" style="width: 60%">
								<option></option>
								<option value="是">是</option>
								<option value="否">否</option>
							</select>
						</div>
					</div>
					<div class="mui-button-row">
						<input type="submit" class="mui-btn mui-btn-primary" value="提交" >
					</div>
					<div hidden="hidden">
						<input type="text"  name="objectid" value="${project.objectid }">
						<input type="text" id="zrr" name="zrr" >
					</div>
				</div>
			</form>
				
		</div>
		
		
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
			var btpicker = new mui.PopPicker(); 
			var databt = new Array();
			$(document).ready(function(){
				
				
				//加载紧急程度选项
				$.ajax({
					url:url,
					data:{belong:'紧急程度'},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var zrlbdata = data.data;
							for(var i = 0; i < zrlbdata.length; i++){
								var option = $('<option>').val(zrlbdata[i].value).text(zrlbdata[i].text);
								$("#jjcd").append(option);
							}
							//设置紧急程度选项
							var jjcd = $("#jjcds").val();
							$("#jjcd").val(jjcd);
						}
					}
				});
				//加载部套名称选项
				$.ajax({
					url:'/Feedback/wtdata.do',
					data:{belong:'部套名称'},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var sdata = data.data;
							for(var i = 0; i < sdata.length; i++){
								var d = new Object();
								d.value = sdata[i].value;
								d.text = sdata[i].text;
								databt.push(d);
							}
							btpicker.setData(databt);
							
						}
					}
				});
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
						alert("[" + text + "]不能为空!");
					}
					return check;
				});
			});
			function btpickershow(){
				btpicker.show(function(items) {
					document.getElementById("btmc").value = items[0].value;
				});
			}
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
								valueid = textid.substring(0,textid.indexOf("show"));
								document.getElementById(valueid).value = items[0].value;
							});
						}
					}
				});
			}
			var i=0;
			function addPlan(){
				i++;
				$("#cs").append('<li id=cs'+i+' class="mui-table-view-cell mui-collapse">'+
						'<a class="mui-navigate-right" href="#">措施'+i+'</a>'+
						'<div class="mui-collapse-content">'+
							'<div class="mui-input-group">'+
								'<div class="mui-inline">解决措施</div>'+
								'<textarea name="clfa" rows="5" placeholder="请详细描述解决措施..."></textarea>'+
								'<div class="mui-input-row" style="margin: 5px 0 0 0;">'+
									'<label>责任人</label>'+
									'<input type="text" id="zrr'+i+'" class=" mui-btn-block" onchange="search(this.id)">'+
								'</div>'+
								'<div class="mui-input-row" style="margin: 5px 0 0 0;">'+
									'<label>时间计划</label>'+
									'<input type="date" id="sjjh'+i+'" class=" mui-btn-block">'+
								'</div>'+
							'</div>'+
							'<div id="jj'+i+'" class="mui-input-group">'+
								'<div class="mui-inline">解决问题</div>'+
								'<textarea name="clfa" rows="5" placeholder="请详细描述工作内容..."></textarea>'+
								'<div class="mui-input-row  mui-input-range" style="margin: 5px 0 0 0;">'+
									'<label>进度</label>'+
									'<input type="range" name="" class="mui-btn-block">'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</li>');
			}
			function delPlan(){
				if(i==0){
					return;
				}
				$('#cs' + i).remove();
				--i;
			}
			function setValue(selectid){
				var selectvalue = $('#' + selectid + ' option:selected') .val();
				var inputid = selectid.substring(0, selectid.indexOf("select"));
				$("#" + inputid).val(selectvalue);
			}
			function searchUser(textid){
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
								valueid = textid.substring(0,textid.indexOf("name"));
								document.getElementById(valueid).value = items[0].value;
							});
						}
					}
				});
			}
		</script>
	</body>

</html>