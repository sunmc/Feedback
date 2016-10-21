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

		<link rel="stylesheet" href="/Feedback/resource/css/mui.min.css">
		<link rel="stylesheet" href="/Feedback/resource/css/ch.css">
	</head>

	<body>
		<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
			<!--侧滑菜单部分-->
			<aside id="offCanvasSide" class="mui-off-canvas-left">
				<div id="offCanvasSideScroll" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						
						<div class="title" style="margin-bottom: 25px;" >目录列表</div>
						<ul class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
							<li class="mui-table-view-cell" id="wttc">
								<a class="mui-navigate-right">
									问题提出
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtsl">
								<a class="mui-navigate-right">
									问题受理
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtfx">
								<a class="mui-navigate-right">
									问题分析
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtjj">
								<a class="mui-navigate-right">
									问题解决
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtgb">
								<a class="mui-navigate-right">
									问题关闭
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtgz">
								<a class="mui-navigate-right">
									问题跟踪
								</a>
							</li>
						</ul>
					</div>
				</div>
			</aside>
			<!--主界面部分-->
			<div class="mui-inner-wrap">
				<header class="mui-bar mui-bar-nav">
					<a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
					<!-- <a class="mui-action-back mui-btn mui-btn-link mui-pull-right">关闭</a> -->
					<h1 class="mui-title">div模式右滑菜单</h1>
				</header>
				<div id="offCanvasContentScroll" class="mui-content mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="mui-content-padded">
							<p>这是可拖动式右滑导航示例，主页面和菜单在一个HTML文件中， 优点是支持拖动手势（跟手），缺点是不支持菜单内容在多页面的复用； 当前页面为主界面，你可以在主界面放置任何内容； 打开侧滑菜单有多种方式： 1、在当前页面向右拖动； 2、点击页面左上角的
								<span class="mui-icon mui-icon-bars"></span> 图标； 3、通过JS API触发（例如点击如下蓝色按钮体验）；
								<span class="android-only">4、Android手机按menu键；</span>
							</p>
							<p style="padding: 5px 20px;margin-bottom: 5px;">
								<button id="offCanvasShow" type="button" class="mui-btn mui-btn-primary mui-btn-block" style="padding: 10px;">
									显示侧滑菜单
								</button>
							</p>
							
							<p>侧滑菜单的移动动画，支持多种效果，切换如下选项体验不同动画效果：</p>

						</div>

						<form class="mui-input-group" style="margin-bottom: 15px;">
							<div class="mui-input-row mui-radio">
								<label>主界面移动、菜单不动</label>
								<input name="style" type="radio" checked="" value="main-move">
							</div>
							<div class="mui-input-row mui-radio">
								<label>主界面不动、菜单移动</label>
								<input name="style" type="radio" value="menu-move">
							</div>
							<div class="mui-input-row mui-radio mui-hidden" id="move-togger">
								<label>整体移动</label>
								<input name="style" type="radio" value="all-move">
							</div>
							<div class="mui-input-row mui-radio">
								<label>缩放式侧滑（类手机QQ）</label>
								<input name="style" type="radio" value="main-move-scalable">
							</div>
						</form>

					</div>
				</div>
				<!-- off-canvas backdrop -->
				<div class="mui-off-canvas-backdrop"></div>
			</div>
		</div>
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script>
			mui.init();
			 //侧滑容器父节点
			var offCanvasWrapper = mui('#offCanvasWrapper');
			 //主界面容器
			var offCanvasInner = offCanvasWrapper[0].querySelector('.mui-inner-wrap');
			 //菜单容器
			var offCanvasSide = document.getElementById("offCanvasSide");
			if (!mui.os.android) {
				document.getElementById("move-togger").classList.remove('mui-hidden');
				var spans = document.querySelectorAll('.android-only');
				for (var i = 0, len = spans.length; i < len; i++) {
					spans[i].style.display = "none";
				}
			}
			 //移动效果是否为整体移动
			var moveTogether = false;
			 //侧滑容器的class列表，增加.mui-slide-in即可实现菜单移动、主界面不动的效果；
			var classList = offCanvasWrapper[0].classList;
			
			 
			 //主界面和侧滑菜单界面均支持区域滚动；
			mui('#offCanvasSideScroll').scroll();
			mui('#offCanvasContentScroll').scroll();
			 //实现ios平台原生侧滑关闭页面；
			if (mui.os.plus && mui.os.ios) {
				mui.plusReady(function() { //5+ iOS暂时无法屏蔽popGesture时传递touch事件，故该demo直接屏蔽popGesture功能
					plus.webview.currentWebview().setStyle({
						'popGesture': 'none'
					});
				});
			}
			 
			
			document.getElementById('wttc').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wttc.do',
					id : 'wtmx',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtsl').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtsl.do',
					id : 'wtmx',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtfx').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtfx.do',
					id : 'wtmx',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtjj').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtjj.do',
					id : 'wtmx',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtgb').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtgb.do',
					id : 'wtmx',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtgz').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtgz.do',
					id : 'wtmx',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
		</script>
	</body>

</html>