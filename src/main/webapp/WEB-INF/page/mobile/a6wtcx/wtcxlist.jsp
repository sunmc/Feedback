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
		<link href="/Feedback/resource/css/mui.picker.css" rel="stylesheet" />
		<link href="/Feedback/resource/css/mui.poppicker.css" rel="stylesheet" />
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="/Feedback/resource/css/app.css" />
		<script src="/Feedback/resource/js/mui.min.js"></script>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script src="/Feedback/resource/js/mui.picker.js"></script>
		<script src="/Feedback/resource/js/mui.poppicker.js"></script>
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
				<a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
				<a id="menu" class="mui-action-menu mui-icon mui-icon-search mui-pull-right" href="#topPopover"></a>
				<h1 class="mui-title"><%= Common.wtcx %></h1>
			</header>
			<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
				<div class="mui-scroll">
					<ul id="itemlist" class="mui-table-view mui-table-view-chevron">
						
					</ul>
					<div class="mui-content-padded">
						<ul class="mui-pager">
							<li id="prev">
								<a> 上一页 </a>
							</li>
							<li>
								<select id="page" style="width:50px;height:40px;text-align: center" onchange="selectPage()">
									<option selected="selected" value="1">1</option>
								</select>
							</li>
							<li id="next">
								<a>
									下一页
								</a>
							</li>
							<li><b id="current">1</b>/<b id="pagecount">1</b>&nbsp;&nbsp;共<b id="size"></b>条</li>
						</ul>
					</div>
					<div>
						<div style="height:30px;width:150px;background: #87CEFA;text-align: center;line-height: 30px">处理中</div>
						<div style="height:30px;width:150px;background: #DDFFF5;text-align: center;line-height: 30px">按期完成</div>
						<div style="height:30px;width:150px;background: #FA8072;text-align: center;line-height: 30px">拖期未完成</div>
						<div style="height:30px;width:150px;background: #FFB6C1;text-align: center;line-height: 30px">拖期完成</div>
						<div style="height:30px;width:150px;background: #efeff4;text-align: center;line-height: 30px">已关闭</div>
					</div>
				</div>
				
			</div>
		</div>
		</div>
		<!--右上角弹出菜单-->
		<div id="topPopover" class="mui-popover" style="height:80%">
			<div class="mui-popover-arrow"></div>
			<div class="mui-scroll-wrapper">
				<div class="mui-scroll">
					<form id="form">
						<div class="mui-input-group">
							<div class="mui-input-row">
								<label>问题号</label>
								<input type="text" id="lsh" name="lsh"/>
							</div>
							<div class="mui-input-row">
								<label>项目编号</label>
								<input type="text" id="xmbh" name="xmbh"/>
							</div>
							<div class="mui-input-row">
								<label>问题类别</label>
								<select id="wtlb" name="wtlb">
									<option></option>
								</select>
							</div>
							<div class="mui-input-row">
								<label>项目阶段</label>
								<select id="xmjd" name="xmjd">
									<option></option>
								</select>
							</div>
							<div class="mui-input-row">
								<label>责任类别</label>
								<select id="zrlb" name="zrlb">
									<option></option>
								</select>
							</div>
							<div class="mui-input-row">
								<label>产品类别</label>
								<select id="cplb" name="cplb">
									<option></option>
								</select>
							</div>
							<div class="mui-input-row">
								<label>项目经理</label>
								<select id="xmjl" name="xmjl">
									<option></option>
									<c:forEach items="${xmjls}" var="xmjl" varStatus="status">
										<option value="${xmjl.user.objectid}">${xmjl.user.xm}</option>
									</c:forEach>
								</select>
								<c:if test="${user.role == '3' }">
									<script type="text/javascript">
										$("#xmjl").val("${user.objectid}");
									</script>
								</c:if>
							</div>
							<div class="mui-input-row">
								<label>责任人</label>
								<select id="zrr" name="zrr">
									<option></option>
									<c:forEach items="${zrrs}" var="zrr" varStatus="status">
										<option value="${zrr.user.objectid}">${zrr.user.xm}</option>
									</c:forEach>
								</select>
							</div>
							<div class="mui-input-row" style="height:auto;">
								<label>是否重复发生问题</label>
								<select id="wtsfcffs" name="wtsfcffs">
									<option></option>
									<option value="是">是</option>
									<option value="否">否</option>
								</select>
							</div>
							<div class="mui-input-row" style="height:auto;">
								<label>是否有临时件流程</label>
								<select id="sfylsjlc" name="sfylsjlc">
									<option></option>
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</div>
							<div class="mui-input-row" style="">
								<label>发起人</label>
								<input type="text" id="fqr" name="far" class="mui-btn-block" onchange="search(this.id)">
								<c:if test="${user.role == '0' }">
									<script type="text/javascript">
										$("#fqr").val("${user.xm}");
									</script>
								</c:if>
							</div>
							<div class="mui-input-row" style="height:auto;">
								<label>问题状态</label>
								<select id="state" name="state">
									<option></option>
									<option value="1" selected="selected">进行中</option>
									<option value="3">已解决</option>
									<option value="6">已关闭</option>
								</select>
							</div>
							<div hidden="hidden">
								<input type="text" id="pagecurrent" name="pagecurrent" value="1"/>
							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div>
		
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
			var pagecurrent = 1;
			var pagecount = 1;
			var url = "/Feedback/wtdata.do";
			$(document).ready(function(){
				//加载产品类别选项
				$.ajax({
					url:url,
					data:{belong:'产品类别'},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var zrlbdata = data.data;
							for(var i = 0; i < zrlbdata.length; i++){
								var option = $('<option>').val(zrlbdata[i].value).text(zrlbdata[i].text);
								$("#cplb").append(option);
							}
						}
					}
				});
				//加载责任类别选项
				$.ajax({
					url:url,
					data:{belong:'责任类别'},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var zrlbdata = data.data;
							for(var i = 0; i < zrlbdata.length; i++){
								var option = $('<option>').val(zrlbdata[i].value).text(zrlbdata[i].text);
								$("#zrlb").append(option);
							}
						}
					}
				});
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
				//加载项目阶段选项
				$.ajax({
					url:url,
					data:{belong:'项目阶段'},
					dataType:'json',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success:function(data){
						if(data.flag){
							var zrlbdata = data.data;
							for(var i = 0; i < zrlbdata.length; i++){
								var option = $('<option>').val(zrlbdata[i].value).text(zrlbdata[i].text);
								$("#xmjd").append(option);
							}
						}
					}
				});
			});
			/**
			 * 下拉刷新具体业务实现
			 */
			function pulldownRefresh() {
				picker.hide();
				$.post("/Feedback/wtcx/search.do",
				         $("#form").serialize(),
				         function(data){
							if(data.flag){
								$("#size").text(data.count);
								pagecount = Math.ceil(data.count/20);
								$("#current").text(pagecurrent);
								$("#pagecount").text(pagecount);
								$("#page").empty();
								for(var i = 1; i <= pagecount; i++){
									var option = "<option value='" + i + "'>" + i + "</option>";
									$("#page").append(option);
								}
								$("#page").val(pagecurrent);
								var zrlbdata = data.data;
								var urls = new Array(); 
								$("#itemlist").empty();
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
									urls[i] = '/Feedback/wtcx/wtcx.do?objectid=' + zrlbdata[i].objectid;
									document.getElementById(i).addEventListener('tap', function() {
										mui.openWindow({
											url : urls[this.id],
											id : 'wtcx' + i,
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
			var picker = new mui.PopPicker(); 
			function search(textid){
				
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
								d.value = sdata[i].xm;
								d.text = sdata[i].xm + "-" + sdata[i].zh;
								dataselect.push(d);
							}
							picker.setData(dataselect);
							picker.show(function(items) {
								document.getElementById(textid).value = items[0].value;
							});
						}
					}
				});
			}
			
			function openwi(id){
				var objectid = $("#objectid" + id).val();
				window.location.href="/Feedback/wtcx/wtcx.do?objectid=" + objectid ;
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
			document.getElementById('prev').addEventListener('tap', function() {
				if(pagecurrent > 1){
					pagecurrent--;
					$("#pagecurrent").val(pagecurrent);
					pulldownRefresh();
				}else{
					alert("已经是第一页了");
				}
				
			})
			document.getElementById('next').addEventListener('tap', function() {
				if(pagecurrent < pagecount){
					pagecurrent++;
					$("#pagecurrent").val(pagecurrent);
					pulldownRefresh();
				}else{
					alert("已经是最后一页了");
				}
			})
			function selectPage(){
				var selectvalue = $('#page option:selected') .val();
				pagecurrent = selectvalue;
				$("#pagecurrent").val(pagecurrent);
				pulldownRefresh();
			}
		</script>
	</body>

</html>