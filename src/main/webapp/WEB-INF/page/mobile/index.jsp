<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="/Feedback/resource/css/mui.min.css">
		<link rel="stylesheet" href="/Feedback/resource/css/ch.css">
	</head>

	<body>
		<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
			<!--ä¾§æ»èåé¨å-->
			<aside id="offCanvasSide" class="mui-off-canvas-left">
				<div id="offCanvasSideScroll" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						
						<div class="title" style="margin-bottom: 25px;" >ç®å½åè¡¨</div>
						<ul class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
							<li class="mui-table-view-cell" id="wttc">
								<a class="mui-navigate-right">
									é®é¢æåº
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtsl">
								<a class="mui-navigate-right">
									é®é¢åç
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtfx">
								<a class="mui-navigate-right">
									é®é¢åæ
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtjj">
								<a class="mui-navigate-right">
									é®é¢è§£å³
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtgb">
								<a class="mui-navigate-right">
									é®é¢å³é­
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtgz">
								<a class="mui-navigate-right">
									é®é¢è·è¸ª
								</a>
							</li>
						</ul>
					</div>
				</div>
			</aside>
			<!--ä¸»çé¢é¨å-->
			<div class="mui-inner-wrap">
				<header class="mui-bar mui-bar-nav">
					<a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
					<!-- <a class="mui-action-back mui-btn mui-btn-link mui-pull-right">å³é­</a> -->
					<h1 class="mui-title">divæ¨¡å¼å³æ»èå</h1>
				</header>
				<div id="offCanvasContentScroll" class="mui-content mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="mui-content-padded">
							<p>è¿æ¯å¯æå¨å¼å³æ»å¯¼èªç¤ºä¾ï¼ä¸»é¡µé¢åèåå¨ä¸ä¸ªHTMLæä»¶ä¸­ï¼ ä¼ç¹æ¯æ¯ææå¨æå¿ï¼è·æï¼ï¼ç¼ºç¹æ¯ä¸æ¯æèååå®¹å¨å¤é¡µé¢çå¤ç¨ï¼ å½åé¡µé¢ä¸ºä¸»çé¢ï¼ä½ å¯ä»¥å¨ä¸»çé¢æ¾ç½®ä»»ä½åå®¹ï¼ æå¼ä¾§æ»èåæå¤ç§æ¹å¼ï¼ 1ãå¨å½åé¡µé¢åå³æå¨ï¼ 2ãç¹å»é¡µé¢å·¦ä¸è§ç
								<span class="mui-icon mui-icon-bars"></span> å¾æ ï¼ 3ãéè¿JS APIè§¦åï¼ä¾å¦ç¹å»å¦ä¸èè²æé®ä½éªï¼ï¼
								<span class="android-only">4ãAndroidææºæmenué®ï¼</span>
							</p>
							<p style="padding: 5px 20px;margin-bottom: 5px;">
								<button id="offCanvasShow" type="button" class="mui-btn mui-btn-primary mui-btn-block" style="padding: 10px;">
									æ¾ç¤ºä¾§æ»èå
								</button>
							</p>
							
							<p>ä¾§æ»èåçç§»å¨å¨ç»ï¼æ¯æå¤ç§ææï¼åæ¢å¦ä¸éé¡¹ä½éªä¸åå¨ç»ææï¼</p>

						</div>

						<form class="mui-input-group" style="margin-bottom: 15px;">
							<div class="mui-input-row mui-radio">
								<label>ä¸»çé¢ç§»å¨ãèåä¸å¨</label>
								<input name="style" type="radio" checked="" value="main-move">
							</div>
							<div class="mui-input-row mui-radio">
								<label>ä¸»çé¢ä¸å¨ãèåç§»å¨</label>
								<input name="style" type="radio" value="menu-move">
							</div>
							<div class="mui-input-row mui-radio mui-hidden" id="move-togger">
								<label>æ´ä½ç§»å¨</label>
								<input name="style" type="radio" value="all-move">
							</div>
							<div class="mui-input-row mui-radio">
								<label>ç¼©æ¾å¼ä¾§æ»ï¼ç±»ææºQQï¼</label>
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
			 //ä¾§æ»å®¹å¨ç¶èç¹
			var offCanvasWrapper = mui('#offCanvasWrapper');
			 //ä¸»çé¢å®¹å¨
			var offCanvasInner = offCanvasWrapper[0].querySelector('.mui-inner-wrap');
			 //èåå®¹å¨
			var offCanvasSide = document.getElementById("offCanvasSide");
			if (!mui.os.android) {
				document.getElementById("move-togger").classList.remove('mui-hidden');
				var spans = document.querySelectorAll('.android-only');
				for (var i = 0, len = spans.length; i < len; i++) {
					spans[i].style.display = "none";
				}
			}
			 //ç§»å¨æææ¯å¦ä¸ºæ´ä½ç§»å¨
			var moveTogether = false;
			 //ä¾§æ»å®¹å¨çclassåè¡¨ï¼å¢å .mui-slide-inå³å¯å®ç°èåç§»å¨ãä¸»çé¢ä¸å¨çææï¼
			var classList = offCanvasWrapper[0].classList;
			
			 
			 //ä¸»çé¢åä¾§æ»èåçé¢åæ¯æåºåæ»å¨ï¼
			mui('#offCanvasSideScroll').scroll();
			mui('#offCanvasContentScroll').scroll();
			 //å®ç°ioså¹³å°åçä¾§æ»å³é­é¡µé¢ï¼
			if (mui.os.plus && mui.os.ios) {
				mui.plusReady(function() { //5+ iOSææ¶æ æ³å±è½popGestureæ¶ä¼ étouchäºä»¶ï¼æè¯¥demoç´æ¥å±è½popGestureåè½
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