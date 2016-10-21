<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="feedback">

	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--æ åmui.css-->
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
			<h1 class="mui-title">é®é¢å³é­</h1>
		</header>
		<div class="mui-content" >
			<div class="mui-input-group" style="margin: 5px;">
				<div class="mui-input-row">
					<label>é¡¹ç®ç¼å·</label>
					<input name="xmbh" type="text" readonly="readonly" value="S-201407048">
				</div>
			</div>
			<ul class="mui-table-view" style="margin: 0 5px 5px 5px ;">
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">é¡¹ç®ä¿¡æ¯</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row">
							<label>å®¢æ·åç§°</label>
							<input name="khmc" type="text"  readonly="readonly" value="åä¸°">
						</div>
						<div class="mui-input-row">
							<label>äº§ååç§°</label>
							<input name="cpmc" type="text" readonly="readonly" value="å¨é¢å¤§ä¸¤é¼">
						</div>
						<div class="mui-input-row">
							<label>é¡¹ç®é¶æ®µ</label>
							<input name="xmjd" type="text" readonly="readonly" value="ååè°è¯">
						</div>
						<div class="mui-input-row">
							<label>é¨å¥åç§°</label>
							<input name="btmc" type="text" readonly="readonly" value="ååè½¦">
						</div>
						<div class="mui-input-row">
							<label>å¾å·</label>
							<input name="wtjth" type="text" readonly="readonly" value="">
						</div>
						<div class="mui-input-row">
							<label>ç©æç¼ç </label>
							<input name="wlbm" type="text" readonly="readonly" value="">
						</div>
						<div class="mui-input-row">
							<div class="mui-inline">é®é¢æè¿°</div>
							<textarea name="wtms" rows="5" readonly="readonly">ååèæ°ç¼¸å¿«æéæ¢æå¿«æèæµ</textarea>
						</div>
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">å¾ç</div>
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
					<a class="mui-navigate-right" href="#">è´£ä»»åç±»</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row">
							<label>è´£ä»»ç±»å«</label>
							<label>è°è¯</label>
						</div>
						<div class="mui-input-row">
							<label>é®é¢ç±»å«</label>
							<label>å¶ä»</label>
						</div>
						<div class="mui-input-row">
							<label>è¦æ±æ¥æ</label>
							<input name="yqrq" type="text" readonly="readonly" value="2016-10-11">
						</div>
						<div class="mui-input-row">
							<label>è´£ä»»äºº</label>
							<input name="zrr" type="text" readonly="readonly" value="å¼ ä¸">
						</div>
					</div>
				</li>
			</ul>
			<div class="mui-inline">ç¸å³ä¿¡æ¯</div>
			<ul class="mui-table-view" style="margin: 0 5px 5px 5px ;">
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">éBOMç©æè®¡åæµç¨</a>
					<div class="mui-collapse-content">
						<div class="mui-input-group" style="margin: 10px 0 0 0;">
							<div class="mui-input-row">
								<label>æµæ°´å·</label>
								<input type="text" id="lsh">
							</div>
							<div class="mui-input-row">
								<label>æµç¨ç¶æ</label>
								<select>
									<option></option>
									<option>è¿è¡ä¸­</option>
									<option>å·²ç»æ</option>
								</select>
							</div>
						</div>
					</div>
				</li>
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">å¾çº¸åæ´æµç¨</a>
					<div class="mui-collapse-content">
						<div class="mui-input-group" style="margin: 10px 0 0 0;">
							<div class="mui-input-row">
								<label>åæ®å·</label>
								<input type="text" id="lsh">
							</div>
							<div class="mui-input-row">
								<label>æ§è¡ç¶æ</label>
								<select>
									<option></option>
									<option>è¿è¡ä¸­</option>
									<option>å·²ç»æ</option>
								</select>
							</div>
							<div class="mui-input-row">
								<label>æ¿è¯ºæ¥æ</label>
								<input type="date" id="cnrq" class=" mui-btn-block">
							</div>
							<div class="mui-input-row">
								<label>å®éæ¥æ</label>
								<input type="date" id="cnrq" class=" mui-btn-block">
							</div>
							<div class="mui-input-row">
								<label>åæ´æåµ</label>
								<select>
									<option></option>
									<option>å¾å®</option>
									<option>è¿è¡ä¸­</option>
									<option>å·²ç»æ</option>
								</select>
							</div>
						</div>
					</div>
				</li>
			</ul>
			<ul class="mui-table-view" style="margin: 0 5px 5px 5px ;">
				<li class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">é®é¢åæ</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">åå åæ</div>
							<textarea name="yyfx" rows="5" readonly="readonly">å®¢æ·ç°åºç¡®å®ä½ç½®ååºå®</textarea>
						</div>
						<div class="mui-input-row" style="margin: 5px 0 0 0;">
							<div class="mui-inline">å¤çæ¹æ¡</div>
							<textarea name="clfa" rows="5" readonly="readonly">å®¢æ·ç°åºç¡®å®ä½ç½®ååºå®</textarea>
						</div>
					</div>
				</li>
			</ul>
			<div class="mui-inline">è§£å³æªæ½</div>
			<ul id="cs" class="mui-table-view" style="margin: 0 5px 5px 5px ;">
				<li id=cs1 class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">æªæ½1</a>
					<div class="mui-collapse-content">
						<div class="mui-input-group">
							<div class="mui-inline">å·¥ä½åå®¹</div>
							<textarea name="clfa" rows="5" readonly="readonly">å¾çº¸ä¸ºå¤å­æ¹ï¼å¤åæ´æ¢</textarea>
							<div class="mui-input-row" style="margin: 5px 0 0 0;">
								<label>è´£ä»»äºº</label>
								<input type="text" id="zrr1" class=" mui-btn-block" readonly="readonly" value="çå°æ¯">
							</div>
							<div class="mui-input-row" style="margin: 5px 0 0 0;">
								<label>æ¶é´è®¡å</label>
								<input type="date" id="sjjh1" class=" mui-btn-block" readonly="readonly" value="2016-10-27">
							</div>
							<div class="mui-input-row mui-input-range">
								<label>å·¥ä½è¿åº¦</label>
								<input id='jjcd' type="range"  value="80" min="1" max="100" readonly="readonly">
							</div>
						</div>
					</div>
				</li>
				<li id=cs2 class="mui-table-view-cell mui-collapse">
					<a class="mui-navigate-right" href="#">æªæ½2</a>
					<div class="mui-collapse-content">
						<div class="mui-input-group">
							<div class="mui-inline">å·¥ä½åå®¹</div>
							<textarea name="clfa" rows="5" readonly="readonly">å¾çº¸ä¸ºå¤å­æ¹ï¼å¤åæ´æ¢</textarea>
							<div class="mui-input-row" style="margin: 5px 0 0 0;">
								<label>è´£ä»»äºº</label>
								<input type="text" id="zrr2" class=" mui-btn-block" readonly="readonly" value="çå°æ¯">
							</div>
							<div class="mui-input-row" style="margin: 5px 0 0 0;">
								<label>æ¶é´è®¡å</label>
								<input type="date" id="sjjh2" class=" mui-btn-block" readonly="readonly" value="2016-10-27">
							</div>
							<div class="mui-input-row mui-input-range">
								<label>å·¥ä½è¿åº¦</label>
								<input id='jjcd' type="range"  value="80" min="1" max="100" readonly="readonly">
							</div>
						</div>
					</div>
				</li>
			</ul>	
			<div class="mui-input-group" style="margin: 10px 0 0 0;">
				<div class="mui-input-row mui-input-range">
					<label>å·¥ä½è¿åº¦</label>
					<input id='jjcd' type="range"  value="80" min="1" max="100"  readonly="readonly">
				</div>
			</div>
			<div class="mui-button-row">
				<button type="button" class="mui-btn mui-btn-primary" onclick="return false;">ç¡®è®¤</button>&nbsp;&nbsp;
				<button type="button" class="mui-btn mui-btn-danger" onclick="return false;">åæ¶</button>
			</div>
				
		</div>
		
		<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script src="/Feedback/resource/js/mui.zoom.js"></script>
		<script src="/Feedback/resource/js/mui.previewimage.js"></script>
		<script src="/Feedback/resource/js/mui.picker.js"></script>
		<script src="/Feedback/resource/js/mui.poppicker.js"></script>
		<script>
			mui.init({
				swipeBack: true //å¯ç¨å³æ»å³é­åè½
			});
			mui('.mui-scroll-wrapper').scroll();
			mui.previewImage();
			var url = "/Feedback/wtdata.do";
			$(document).ready(function(){
				
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
		</script>
	</body>

</html>