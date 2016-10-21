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
		<link rel="stylesheet" type="text/css" href="/Feedback/resource/css/app.css" />
	</head>

	<body>
	<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
		<!--ä¾§æ»èåé¨å-->
		<aside id="offCanvasSide" class="mui-off-canvas-left">
			<div id="offCanvasSideScroll" class="mui-scroll-wrapper">
				<div class="mui-scroll">

					<div class="title" style="margin-bottom: 25px;">ç®å½åè¡¨</div>
					<ul
						class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
						<li class="mui-table-view-cell" id="wtsl"><a
							class="mui-navigate-right"> é®é¢åç </a></li>
						<li class="mui-table-view-cell" id="wtfx"><a
							class="mui-navigate-right"> é®é¢åæ </a></li>
						<li class="mui-table-view-cell" id="wtjj"><a
							class="mui-navigate-right"> é®é¢è§£å³ </a></li>
						<li class="mui-table-view-cell" id="wtgb"><a
							class="mui-navigate-right"> é®é¢å³é­ </a></li>
						<li class="mui-table-view-cell" id="wtcx"><a
							class="mui-navigate-right"> é®é¢æ¥è¯¢ </a></li>

					</ul>
				</div>
			</div>
		</aside>
		<!--ä¸»çé¢é¨å-->
		<div class="mui-inner-wrap">
			<header class="mui-bar mui-bar-nav">
				<a href="#offCanvasSide"
					class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
				<!-- <a class="mui-action-back mui-btn mui-btn-link mui-pull-right">å³é­</a> -->
				<h1 class="mui-title">é®é¢åè¡¨</h1>
			</header>
			<div class="mui-content" style="height:100%">
				<div class="mui-content-padded" style="margin: 5px;">
					<div class="mui-input-row mui-search">
						<input id="search" type="search" class="mui-input-clear" placeholder="æç´¢é¡¹ç®" onclick="showHint()" onblur="hideHint()" onkeyup="searchXM(this.value)">
					</div>
					<ul class="mui-table-view mui-table-view-chevron">
								<li id='1' class="mui-table-view-cell mui-media">
									<a class="mui-navigate-right">
										S-201407048
										<p class='mui-ellipsis'>ååèæ°ç¼¸å¿«æéæ¢æå¿«æèæµ</p>
									</a>
								</li>
								<li id='2' class="mui-table-view-cell mui-media">
									<a class='mui-navigate-right' href="javascript:;">
										S-201407049
										<p class='mui-ellipsis'>ååè½¦æè½¬é¶ç¹ãè´æéå¼å³æ¯æ¶ç°ç¨ä¸ä¸ªèºä¸åºå®ï¼æ¶é´é¿äºä¼æ¾å¨ï¼å»ºè®®è°æ´å¥½ä½ç½®åï¼æå­ï¼ç¨ä¿©èºä¸åºå®ï¼049åï¼</p>
									</a>
								</li>
								<li id='1' class="mui-table-view-cell mui-media">
									<a class="mui-navigate-right">
										S-201407049
										<p class='mui-ellipsis'>ååè½¦å¾åæ­£ãè´ãé¶ç¹å¼å³ä¸åéï¼éç°åºè°æ´å¥½ä½ç½®åéæ°åºå®ãï¼049åï¼</p>
									</a>
								</li>
							</ul>
					<!--ä¸æå·æ°å®¹å¨-->
					<!-- <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
						<div class="mui-scroll">
							<ul class="mui-table-view mui-table-view-chevron">
								<li id='1' class="mui-table-view-cell mui-media">
									<a class="mui-navigate-right">
										S-201407048
										<p class='mui-ellipsis'>ååèæ°ç¼¸å¿«æéæ¢æå¿«æèæµ</p>
									</a>
								</li>
								<li id='2' class="mui-table-view-cell mui-media">
									<a class='mui-navigate-right' href="javascript:;">
										S-201407049
										<p class='mui-ellipsis'>ååè½¦æè½¬é¶ç¹ãè´æéå¼å³æ¯æ¶ç°ç¨ä¸ä¸ªèºä¸åºå®ï¼æ¶é´é¿äºä¼æ¾å¨ï¼å»ºè®®è°æ´å¥½ä½ç½®åï¼æå­ï¼ç¨ä¿©èºä¸åºå®ï¼049åï¼</p>
									</a>
								</li>
								<li id='1' class="mui-table-view-cell mui-media">
									<a class="mui-navigate-right">
										S-201407049
										<p class='mui-ellipsis'>ååè½¦å¾åæ­£ãè´ãé¶ç¹å¼å³ä¸åéï¼éç°åºè°æ´å¥½ä½ç½®åéæ°åºå®ãï¼049åï¼</p>
									</a>
								</li>
							</ul>
						</div>
					</div> -->
				</div>
			</div>
		</div>
	</div>
	<script src="/Feedback/resource/js/mui.min.js"></script>
		<script>
			mui.init({
				
			});
			document.getElementById('1').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtgz/wtgz.do',
					id : 'wtgz',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('2').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtgz/wtgz.do',
					id : 'wtgz',
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
			document.getElementById('wtcx').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtcx.do',
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