<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.bean.Common" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="shortcut icon" href="/Feedback/resource/images/xmwtfl.png">
		<title><%= Common.title %></title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="/Feedback/resource/css/mui.min.css">
		<link rel="stylesheet" href="/Feedback/resource/css/ch.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="/Feedback/resource/css/app.css" />
		<style>
			/*跨webview需要手动指定位置*/
			
			.mui-plus header.mui-bar {
				display: none!important;
			}
			.mui-plus .mui-bar-nav~.mui-content {
				padding: 0!important;
			}
			
			.mui-plus .plus{
				display: inline;
			}
			
			.plus{
				display: none;
			}
			
			#topPopover {
				position: fixed;
				top: 16px;
				right: 6px;
			}
			#topPopover .mui-popover-arrow {
				left: auto;
				right: 6px;
			}
			p {
				text-indent: 22px;
			}
			span.mui-icon {
				font-size: 14px;
				color: #007aff;
				margin-left: -15px;
				padding-right: 10px;
			}
			.mui-popover {
				height: 220px;
			}
			.mui-content {
				padding: 0px;
			}
		</style>
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
							<li class="mui-table-view-cell" id="wtfx">
								<a class="mui-navigate-right">
									<%= Common.wtfx %>
								</a>
							</li>
							<li class="mui-table-view-cell" id="wtzg">
								<a class="mui-navigate-right">
									<%= Common.wtzg %>
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
				<a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
				<a id="menu" class="mui-action-menu mui-icon mui-icon-search mui-pull-right" href="#topPopover"></a>
				<h1 class="mui-title"><%= Common.wtgz %></h1>
			</header>
			<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
				<div class="mui-scroll">
					<ul id="itemlist" class="mui-table-view mui-table-view-chevron">
						
					</ul>
				</div>
			</div>
		</div>
		</div>
		<!--右上角弹出菜单-->
		<div id="topPopover" class="mui-popover">
			<div class="mui-popover-arrow"></div>
			<div class="mui-scroll-wrapper">
				<div class="mui-scroll">
					<div class="mui-input-group">
						<div class="mui-input-row">
							<label>流水号</label>
							<input type="text" id="lsh">
						</div>
						<div class="mui-input-row">
							<label>问题类别</label>
							<select id="wtlb">
								<option></option>
							</select>
						</div>
						<div class="mui-input-row" style="line-height: 40px">
							<label>项目号</label>
							<input type="text" id="xmbh" />
						</div>
						<div class="mui-input-row">
							<label>开始日期</label>
							<input type="date" id="createtimeStart" />
						</div>
						<div class="mui-input-row">
							<label>结束日期</label>
							<input type="date" id="createtimeEnd" />
						</div>
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
			var url = "/Feedback/wtdata.do";
			$(document).ready(function(){
				//加载问题类别选项
				$.ajax({
					url:url,
					data:{belong:'问题类别'},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var zrlbdata = data.data;
							for(var i = 0; i < zrlbdata.length; i++){
								var option = $('<option>').val(zrlbdata[i].value).text(zrlbdata[i].text);
								$("#wtlb").append(option);
							}
						}
					}
				});
				
			});
			/**
			 * 下拉刷新具体业务实现
			 */
			function pulldownRefresh() {
				
				var lshv = $("#lsh").val();
				var wtlbv = $("#wtlb").val();
				var xmbhv = $("#xmbh").val();
				var createtimeStartv = $("#createtimeStart").val();
				var createtimeEndv = $("#createtimeEnd").val();
		
				$("#itemlist").empty();
				$.ajax({
					url:'/Feedback/wtcx/search.do',
					data:{lsh:lshv,wtlb:wtlbv,xmbh:xmbhv,createtimeStart:createtimeStartv,createtimeEnd:createtimeEndv},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var zrlbdata = data.data;
							for(var i = 0; i < zrlbdata.length; i++){
								var li = '<li id='+ i +' class="mui-table-view-cell mui-media wi" style="background:' + zrlbdata[i].statecolor + '">'+
											'<a class="mui-navigate-right">' + 
												zrlbdata[i].lsh + ' ' +  zrlbdata[i].xmbh + ' ' + zrlbdata[i].wtms +
												'<p class="mui-ellipsis">' + zrlbdata[i].fqrxm + ' ' + zrlbdata[i].createtimes + '</p>' +
											'</a>' +
											'<div hidden="hidden">' +
												'<input type="text" id="objectid' + i + '" value="' + zrlbdata[i].objectid + '">' + 
											'</div>' +
										'</li>';
										
								//下拉刷新，新纪录插到最前面；
								$("#itemlist").prepend(li);
								var wturl = '/Feedback/wtgz/wtgz.do?objectid=' + zrlbdata[i].objectid
								document.getElementById(i).addEventListener('tap', function() {
									mui.openWindow({
										url : wturl,
										id : 'wtfx',
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
					}
				});
				mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
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
			function openwi(id){
				var objectid = $("#objectid" + id).val();
				window.location.href="/Feedback/wtgz/wtgz.do?objectid=" + objectid ;
			}
			document.getElementById('wtpd').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtpd.do',
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
			document.getElementById('wtzg').addEventListener('tap', function() {
				mui.openWindow({
					url : '/Feedback/wtzg.do',
					id : 'wtjj',
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
					id : 'wtgb',
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
					id : 'wtgn',
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