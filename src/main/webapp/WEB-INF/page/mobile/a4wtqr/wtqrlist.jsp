<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.bean.Common" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<script>
			var urls = new Array();
		</script>
	</head>

	<body>
	<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
		<!--侧滑菜单部分-->
		<aside id="offCanvasSide" class="mui-off-canvas-left">
				<div id="offCanvasSideScroll" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="title" style="margin-bottom: 25px;" ><%= Common.mllb %></div>
						<ul class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
							<li class="mui-table-view-cell" id="wtpd">
								<a class="mui-navigate-right">
									<%= Common.wtpd %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtcl">
								<a class="mui-navigate-right">
									<%= Common.wtcl %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtqr">
								<a class="mui-navigate-right">
									<%= Common.wtqr %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtgb">
								<a class="mui-navigate-right">
									<%= Common.wtgb %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtcx">
								<a class="mui-navigate-right">
									<%= Common.wtcx %>
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
				<!-- <a class="mui-action-back mui-btn mui-btn-link mui-pull-right">关闭</a> -->
				<h1 class="mui-title"><%= Common.wtqr + Common.lb%></h1>
			</header>

			<!--下拉刷新容器-->
			<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
				<div class="mui-scroll">
					<ul id="itemlist" class="mui-table-view mui-table-view-chevron">
						
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script src="/Feedback/resource/js/mui.min.js"></script>
	<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script>
			mui.init({
				pullRefresh : {
					container : '#pullrefresh',
					down : {
						contentrefresh : '正在加载...',
						callback : pulldownRefresh
					}
				}
			});
			mui('.mui-scroll-wrapper').scroll();
			mui('body').on('shown', '.mui-popover', function(e) {
			});
			mui('body').on('hidden', '.mui-popover', function(e) {
				mui('#pullrefresh').pullRefresh().pulldownLoading();
			});
			 /**
			 * 下拉刷新具体业务实现
			 */
			function pulldownRefresh() {
				
				$.post("/Feedback/wtqr/wtqrlist.do",
				         function(data){
							if(data.flag){
								var wtqrdata = data.data;
								var urls = new Array(); 
								$("#itemlist").empty();
								for(var i = 0; i < wtqrdata.length; i++){
									var li = '<li id='+ i +' class="mui-table-view-cell mui-media wi">'+
												'<a class="mui-navigate-right">' + 
												wtqrdata[i].lsh + ' ' +  wtqrdata[i].xmbh + ' ' + wtqrdata[i].wtms +
													'<p class="mui-ellipsis">' + wtqrdata[i].fqrxm + ' ' + wtqrdata[i].createtimes + '</p>' +
												'</a>' +
												'<div hidden="hidden">' +
													'<input type="text" id="objectid' + i + '" value="' + wtqrdata[i].objectid + '">' + 
													'<input type="text" id="workitemid' + i + '" value="' + wtqrdata[i].workitemid + '">' + 
												'</div>' +
											'</li>';
											
									//下拉刷新，新纪录插到最前面；
									$("#itemlist").prepend(li);
									urls[i] = '/Feedback/wtqr/wtqr.do?objectid=' + wtqrdata[i].objectid + '&workitemid=' + wtqrdata[i].workitemid;
									document.getElementById(i).addEventListener('tap', function() {
										mui.openWindow({
											url : urls[this.id],
											id : 'wtpd',
											show : {
												aniShow : 'pop-in'
											},
											waiting : {
												autoShow : false
											}
										});
									});
								}
							}
							mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
				        });
				
			}
			
			if (mui.os.plus) {
				mui.plusReady(function() {
					setTimeout(function() {
						mui('#pullrefresh').pullRefresh().pulldownLoading();
					}, 1000);
	
				});
			} else {
				mui.ready(function() {
					mui('#pullrefresh').pullRefresh().pulldownLoading();
				});
			}
			$(document).ready(function(){
				var index = 0;
				mui(".wi li").each(function() {
					var objectid = $("#objectid" + index ).val();
					var workitemid = $("#workitemid" + index ).val();
					index++;
				}); 
			});
			document.getElementById('wtpd').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtpd.do',
					id : 'wtpd',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtcl').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtcl.do',
					id : 'wtcl',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			document.getElementById('wtqr').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtqr.do',
					id : 'wtqr',
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
			document.getElementById('wtcx').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtcx.do',
					id : 'wtcx',
					show : {
						aniShow : 'pop-in'
					},
					waiting : {
						autoShow : false
					}
				});
			})
			function openwi(id){
				var objectid = $("#objectid" + id).val();
				var workitemid = $("#workitemid" + id).val();
				window.location.href="/Feedback/wtqr/wtqr.do?objectid=" + objectid + "&workitemid="+workitemid;
			}
		</script>
	</body>

</html>