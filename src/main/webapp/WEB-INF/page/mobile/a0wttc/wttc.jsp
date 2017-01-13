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
		<link rel="stylesheet" type="text/css" href="/Feedback/resource/css/app.css" />
		<link href="/Feedback/resource/css/mui.picker.css" rel="stylesheet" />
		<link href="/Feedback/resource/css/mui.poppicker.css" rel="stylesheet" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
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
			<!-- <a id="sy" class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a> -->
			<h1 class="mui-title">问题提交</h1>
		</header>
		<div class="mui-content" >
			<form id="wtform" action="/Feedback/wttc/submit.do" method="post">
				<div class="mui-content-padded" style="margin: 5px;">
					<div class="mui-input-row mui-search">
						<input id="search" type="search" class="mui-input-clear" placeholder="搜索项目" onclick="showHint()" onblur="hideHint()" onkeyup="searchXM(this.value)">
					</div>
					<div  id="hint" class="mui-scroll-wrapper" style="margin: 1px 0 0 0;display:none;position: static;height:200px;">
						<div class="mui-scroll">
							<ul id="xmlist" class="mui-table-view">
							</ul>
						</div>
					</div>
					<div class="mui-input-group" style="margin: 0 0 0 0;">
						<div class="mui-input-row required">
							<label style="color: red;">项目编号</label>
							<input id="xmbh" name="xmbh" type="text" class="mui-input-clear" placeholder="项目编号">
						</div>
						<div class="mui-input-row" hidden="hidden">
							<label>客户名称</label>
							<input id="khmc" name="khmc" type="text" class="mui-input-clear" placeholder="客户名称">
						</div>
						<div class="mui-input-row">
							<label>项目名称</label>
							<input id="xmmc" name="xmmc" type="text" class="mui-input-clear" placeholder="项目名称">
						</div>
						<div class="mui-input-row" hidden="hidden">
							<label>产品名称</label>
							<input id="cpmc" name="cpmc" type="text" class="mui-input-clear" placeholder="产品名称">
						</div>
						<div class="mui-input-row" hidden="hidden">
							<label>项目阶段</label>
							<input id="xmjd" name="xmjd" type="text" class="mui-input-clear" placeholder="项目阶段">
						</div>
						<div class="mui-input-row">
							<label>项目经理</label>
							<select id="xmjl" name="xmjl" onchange="setXMJLName(this.options[this.selectedIndex].text)">
								<option></option>
								<c:forEach items="${xmjls}" var="xmjl" varStatus="status">
									<option value="${xmjl.user.objectid}">${xmjl.user.xm}</option>
								</c:forEach>
							</select>
							<input type="text" hidden="hidden" id="xmjlname" name="xmjlname" />
						</div>
						<div class="mui-input-row mui-input-range">
							<label>紧急程度</label>
							<select id="jjcd" name="jjcd">
							</select>
			        	</div>
			        </div>
					<div class="mui-input-group" style="margin: 10px 0 0 0;">
						<div class="mui-input-row required">
							<label style="color: red;">部套名称</label>
							<input id="btmc" name="btmc" type="text" class="" onclick="btpickershow()"  placeholder="部套名称">
						</div>
						<div class="mui-input-row required">
							<label style="color: red;">部套数量</label>
							<input id="btsl" name="btsl" type="number" class="" placeholder="部套数量" >
						</div>
						<div class="mui-input-row">
							<label>图号</label>
							<input id="wtjth" name="wtjth" type="text" class="mui-input-clear" placeholder="问题件图号">
						</div>
						<div class="mui-input-row">
							<label>物料编码</label>
							<input id="wlbm" name="wlbm" type="text" class="mui-input-clear" placeholder="物料编码">
						</div>
					</div>
					<div class="mui-input-row required" style="margin: 5px 0 0 0;">
						<div class="mui-inline" style="color: red;">问题描述</div>
						<textarea id="wtms" name="wtms" rows="5" placeholder="请详细描述你的问题..."></textarea>
					</div>
					<div class="mui-inline">图片(选填,提供问题截图,总大小10M以下)</div>
					<div class="mui-inline" style="color: red">请严格遵守客户现场的安全生产规范</div>
					<div class="">
						<div id='image-list'>			
							<img id='addImg' src='/Feedback/resource/images/add.png' class="image-item"  onclick="add()" />
						</div>
					</div>
					<!-- <div class="mui-inline">
						<label>视频/语音</label>
						<a id="addSP" style="margin: 9px ;" class="mui-icon mui-icon-plus" onclick="document.getElementById('wtmsspf').click();"></a>
					</div>
					<embed src="/Feedback/resource/images/2016-10-20/nulla/1476931142095.MOV" height="200" width="200"/> -->
				</div>
				
				<div id="img-tc" class="mui-input-row mui-plus-visible">
					<input id="wtmstpf" name="wtmstpf" type="file" onchange="setImg(this)">
					<input id="wtmsspf" name="wtmsspf" type="file" onchange="setSP(this)">
					<input id="wtsp" name="wtsp" type="text">
					<input id="wttp" name="wttp" type="text">
				</div>
				<div class="mui-button-row">
					<input type="submit" class="mui-btn mui-btn-primary" value="提交">
				</div>
			</form>
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
			var btpicker = new mui.PopPicker(); 
			var databt = new Array();
			$(document).ready(function(){
				//表单验证
				$("form").submit(function(event){
					var wtms = $("#wtms").val();
					$("#wtms").val(wtms);
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
					//设置图片信息
					setImgInput();
					return check;
				});
				//加载紧急程度选项
				$.ajax({
					url:'/Feedback/wtdata.do',
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
			});
			function btpickershow(){
				btpicker.show(function(items) {
					document.getElementById("btmc").value = items[0].value;
				});
			}
			var imgid = 1;
			function add(){
				//图片数量
				var count = $("#image-list").children().size();
				if(count > 9){
					mui.alert('最多只能选择9张图片', '提示', function() {
						//info.innerText = '你刚关闭了警告框';
					});
					return null;
				} 
			    return	document.getElementById('wtmstpf').click();
			}
			function setImg(img){
				
				var fileObj = img.files[0]; // 获取文件对象
		         var FileController = "/Feedback/upload/file.do";                    // 接收上传文件的后台地址 
		         // FormData 对象
		         var form = new FormData();
		         form.append("file", fileObj);                           // 文件对象
		         // XMLHttpRequest 对象
		         var xhr = new XMLHttpRequest();
		         xhr.open("post", FileController, true);
		         xhr.onload = function () {
		        	 var data = JSON.parse(this.response);
		        	 var imgsrc = "<div class='image-item' id='img"+ ++imgid+"'>"+
		        	 					"<img src='/" + data.data + "' class='image-item' data-preview-src=''  data-preview-group='1' />"+
		        	 					"<a href='#' class='image-close' onclick='removeImg(\"img"+ imgid+"\")'>X</a>"+
		        	 			  "</div>";
		        	 var checkbox = $(imgsrc).appendTo($("#image-list"));
		        	 $("#WTTP"+imgid).val('/'+data.data);
		        	 imgid++;
		         };
		         xhr.send(form);
				$("#wtmstpf").remove();
				$("#img-tc").append('<input id="wtmstpf" name="wtmstpf" type="file" onchange="setImg(this)">');
			}
			function removeImg(img){
				imgid=img.substring(3,4);
				$("#WTTP"+imgid).val('');
				img = "#"+img;
				$(img).remove();
			}
			function setImgInput(){
				var count = $("#image-list").children().size();
				var imgpaths = "";
				for(var i = 1; i < count; i++){
					var divid = $("#image-list").children().get(i).id;
					var src = $("#" + divid).children().get(0).src;
					imgpaths = imgpaths + src + ";";
				}
				$("#wttp").val(imgpaths);
			}
			function setSP(sp){
				
				var fileObj = sp.files[0]; // 获取文件对象
		         var FileController = "/Feedback/upload/file.do";                    // 接收上传文件的后台地址 
		         // FormData 对象
		         var form = new FormData();
		         form.append("file", fileObj);                           // 文件对象
		         // XMLHttpRequest 对象
		         var xhr = new XMLHttpRequest();
		         xhr.open("post", FileController, true);
		         xhr.onload = function () {
		        	 var data = JSON.parse(this.response);
		        	 $("#spyy").attr('src','/Feedback/resource/images/'+data.data);
		         };
		         xhr.send(form);
				$("#wtmsspf").remove();
				$("#img-tc").append('<input id="wtmsspf" name="wtmsspf" type="file" onchange="setSP(this)">');
			}
			function showHint(){
				$('#hint').fadeIn();
				$('#search').focus();
			}
			function hideHint(){
				$('#hint').fadeOut();
				$('#search').val('');
			}
			function searchXM(sval){
				$("#xmlist").empty(); 
				$.ajax({
					url:'/Feedback/xmxx/searchxm.do',
					data:{text:sval},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var xmdata = data.data;
							for(var i = 0; i < xmdata.length; i++){
								var li = '<li class="mui-table-view-cell" '+
											'onclick="setXMXX(\''+xmdata[i].xmbh+'\',\''+xmdata[i].khmc+'\',\''+xmdata[i].cpmc+'\',\''+xmdata[i].cpjd+'\',\''+xmdata[i].xmjl+'\',\''+xmdata[i].xmjlname+'\')"> '+
											xmdata[i].xmbh+'-'+xmdata[i].cpmc+'</li>';
								$("#xmlist").append(li);
							}
						}
					}
				});
			}
			function setXMXX(xmbh,khmc,cpmc,xmjd,xmjl,xmjlname){
				$('#hint').fadeOut();
				$('#xmbh').val(xmbh);
				$('#khmc').val(khmc);
				$('#cpmc').val(cpmc);
				$('#xmjd').val(xmjd);
				$('#xmmc').val(cpmc);
				if(xmjl != null && xmjl != 'null' && xmjl.length > 0){
					$('#xmjl').val(xmjl);
					$("#xmjlname").val(xmjlname);
				}	
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
			function setXMJLName(xmjlname){
				$("#xmjlname").val(xmjlname);
			}
			
		</script>
	</body>

</html>