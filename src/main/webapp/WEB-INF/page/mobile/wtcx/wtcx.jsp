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
		<!--侧滑菜单部分-->
		<aside id="offCanvasSide" class="mui-off-canvas-left">
			<div id="offCanvasSideScroll" class="mui-scroll-wrapper">
				<div class="mui-scroll">

					<div class="title" style="margin-bottom: 25px;">目录列表</div>
					<ul
						class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
						<li class="mui-table-view-cell" id="wtsl"><a
							class="mui-navigate-right"> 问题受理 </a></li>
						<li class="mui-table-view-cell" id="wtfx"><a
							class="mui-navigate-right"> 问题分析 </a></li>
						<li class="mui-table-view-cell" id="wtjj"><a
							class="mui-navigate-right"> 问题解决 </a></li>
						<li class="mui-table-view-cell" id="wtgb"><a
							class="mui-navigate-right"> 问题关闭 </a></li>
						<li class="mui-table-view-cell" id="wtcx"><a
							class="mui-navigate-right"> 问题查询 </a></li>

					</ul>
				</div>
			</div>
		</aside>
		<!--主界面部分-->
		<div class="mui-inner-wrap">
			<header class="mui-bar mui-bar-nav">
				<a href="#offCanvasSide"
					class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
				<!-- <a class="mui-action-back mui-btn mui-btn-link mui-pull-right">关闭</a> -->
				<h1 class="mui-title">问题列表</h1>
			</header>
			<div class="mui-content" style="height:100%">
				<div class="mui-content-padded" style="margin: 5px;">
					<div class="mui-input-row mui-search">
						<input id="search" type="search" class="mui-input-clear" placeholder="搜索项目" onclick="showHint()" onblur="hideHint()" onkeyup="searchXM(this.value)">
					</div>
					<ul class="mui-table-view mui-table-view-chevron">
								<li id='1' class="mui-table-view-cell mui-media">
									<a class="mui-navigate-right">
										S-201407048
										<p class='mui-ellipsis'>反包臂气缸快插需换成快插节流</p>
									</a>
								</li>
								<li id='2' class="mui-table-view-cell mui-media">
									<a class='mui-navigate-right' href="javascript:;">
										S-201407049
										<p class='mui-ellipsis'>后压车摆转零点、负极限开关支架现用一个螺丝固定，时间长了会松动，建议调整好位置后，打孔，用俩螺丝固定（049同）</p>
									</a>
								</li>
								<li id='1' class="mui-table-view-cell mui-media">
									<a class="mui-navigate-right">
										S-201407049
										<p class='mui-ellipsis'>后压车径向正、负、零点开关不合适，需现场调整好位置后重新固定。（049同）</p>
									</a>
								</li>
							</ul>
					<!--下拉刷新容器-->
					<!-- <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
						<div class="mui-scroll">
							<ul class="mui-table-view mui-table-view-chevron">
								<li id='1' class="mui-table-view-cell mui-media">
									<a class="mui-navigate-right">
										S-201407048
										<p class='mui-ellipsis'>反包臂气缸快插需换成快插节流</p>
									</a>
								</li>
								<li id='2' class="mui-table-view-cell mui-media">
									<a class='mui-navigate-right' href="javascript:;">
										S-201407049
										<p class='mui-ellipsis'>后压车摆转零点、负极限开关支架现用一个螺丝固定，时间长了会松动，建议调整好位置后，打孔，用俩螺丝固定（049同）</p>
									</a>
								</li>
								<li id='1' class="mui-table-view-cell mui-media">
									<a class="mui-navigate-right">
										S-201407049
										<p class='mui-ellipsis'>后压车径向正、负、零点开关不合适，需现场调整好位置后重新固定。（049同）</p>
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