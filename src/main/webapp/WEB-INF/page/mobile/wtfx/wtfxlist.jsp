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
		<link rel="stylesheet" type="text/css" href="/Feedback/resource/css/app.css" />
	</head>

	<body>
	<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
		<!--侧滑菜单部分-->
		<aside id="offCanvasSide" class="mui-off-canvas-left">
				<div id="offCanvasSideScroll" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="title" style="margin-bottom: 25px;" ><%= Common.mllb %></div>
						<ul class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
							<li class="mui-table-view-cell" id="wtsl">
								<a class="mui-navigate-right">
									<%= Common.wtsl %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtfx">
								<a class="mui-navigate-right">
									<%= Common.wtfx %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtjj">
								<a class="mui-navigate-right">
									<%= Common.wtjj %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtgb">
								<a class="mui-navigate-right">
									<%= Common.wtgb %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtgz">
								<a class="mui-navigate-right">
									<%= Common.wtgz %>
								</a>
							</li>
						</ul>
					</div>
				</div>
			</aside>
		<!--主界面部分-->
		<div class="mui-inner-wrap">
			<header class="mui-bar mui-bar-nav">
				<a href="#offCanvasSide"
					class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
				<!-- <a class="mui-action-back mui-btn mui-btn-link mui-pull-right">å³é­</a> -->
				<h1 class="mui-title"><%= Common.wtfx + Common.lb%></h1>
			</header>

			<!--下拉刷新容器-->
			<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
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
			</div>
		</div>
	</div>
	<script src="/Feedback/resource/js/mui.min.js"></script>
		<script>
			mui.init({
				pullRefresh : {
					container : '#pullrefresh',
					down : {
						callback : pulldownRefresh
					},
					up : {
						contentrefresh : '正在加载...',
						callback : pullupRefresh
					}
				}
			});
			/**
			 * 下拉刷新具体业务实现
			 */
			function pulldownRefresh() {
				setTimeout(function() {
					/* var table = document.body.querySelector('.mui-table-view');
					var cells = document.body
							.querySelectorAll('.mui-table-view-cell');
					for (var i = cells.length, len = i + 3; i < len; i++) {
						var li = document.createElement('li');
						li.className = 'mui-table-view-cell';
						li.innerHTML = '<a class="mui-navigate-right">Item '
								+ (i + 1) + '</a>';
						//下拉刷新，新纪录插到最前面；
						table.insertBefore(li, table.firstChild);
					} */
					mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
				}, 1500);
			}
			var count = 0;
			/**
			 * 上拉加载具体业务实现
			 */
			function pullupRefresh() {
				setTimeout(function() {
					mui('#pullrefresh').pullRefresh().endPullupToRefresh(
							(++count > 2)); //参数为true代表没有更多数据了。
							document.getElementById('1').addEventListener('tap', function() {
								mui.openWindow({
									url : '/Feedback/wtfx/wtfx.do',
									id : 'wtfx',
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
									url : '/Feedback/wtfx/wtfx.do',
									id : 'wtfx',
									show : {
										aniShow : 'pop-in'
									},
									waiting : {
										autoShow : false
									}
								});
							})
					/* var table = document.body.querySelector('.mui-table-view');
					var cells = document.body
							.querySelectorAll('.mui-table-view-cell');
					for (var i = cells.length, len = i + 20; i < len; i++) {
						var li = document.createElement('li');
						li.className = 'mui-table-view-cell';
						li.innerHTML = '<a class="mui-navigate-right">Item '
								+ (i + 1) + '</a>';
						table.appendChild(li);
					} */
				}, 1500);
			}
			if (mui.os.plus) {
				mui.plusReady(function() {
					setTimeout(function() {
						mui('#pullrefresh').pullRefresh().pullupLoading();
					}, 1000);

				});
			} else {
				mui.ready(function() {
					mui('#pullrefresh').pullRefresh().pullupLoading();
				});
			}
			document.getElementById('wtsl').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtsl.do',
					id : 'wtsl',
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
					id : 'wtfx',
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
					id : 'wtjj',
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
					id : 'wtgb',
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
					id : 'wtgz',
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