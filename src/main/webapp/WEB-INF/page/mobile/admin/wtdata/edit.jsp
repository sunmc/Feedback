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
<!-- 
		<link rel="stylesheet" href="/Feedback/resource/css/mui.min.css">
		<link rel="stylesheet" href="/Feedback/resource/css/ch.css">
		<link rel="stylesheet" type="text/css" href="/Feedback/resource/css/app.css" /> -->
		<style type="text/css">
			input{
				font-size: 16px;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<!-- <a id="sy" class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a> -->
			<h1 class="mui-title">选项修改界面</h1>
		</header>
		<div class="mui-content">
			<div class="mui-input-group" style="margin: 5px;">
				<div class="mui-input-row">
					<label>选项类别</label>
					<select id="xxlb" onchange="getList(this.options[this.options.selectedIndex].value)">
						<option></option>
					</select>
				</div>
			</div>
			<form id="wdsform" action="/Feedback/wtdata/save.do" method="post">
				<div id="xxnr">
				
				</div>
			</form>
		</div>
		
		
		<button onclick="save()">保存</button>
		<button onclick="addOption()">添加选项</button>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script>
		$(document).ready(function(){
			//获取选项类别
			$.ajax({
				url:'/Feedback/wtdata/wtlb.do',
				dataType:'json',
				type: "post", 
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					if(data.flag){
						var wtlbdata = data.data;
						for(var i = 0; i < wtlbdata.length; i++){
							var option = $('<option>').val(wtlbdata[i].trim()).text(wtlbdata[i].trim());
							$("#xxlb").append(option);
						}
					}
				}
			});
		});
		function getList(v){
			//获取选项列表
			$.ajax({
				url:'/Feedback/wtdata/getwd.do',
				dataType:'json',
				data:{belong:v},
				type: "post", 
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					if(data.flag){
						$("#xxnr").empty();
						var wtlbdata = data.data;
						for(var i = 0; i < wtlbdata.length; i++){
							var li = '<li>'+
										'实际值<input name="wds['+i+'].value" type="text" value="' + wtlbdata[i].value + '">'+
										'显示文本<input name="wds['+i+'].text" type="text" value="' + wtlbdata[i].text + '">'+
										'排序值<input name="wds['+i+'].sort" type="text" value="' + wtlbdata[i].sort + '">'+
										'<input name="wds['+i+'].objectid" type="text" value="' + wtlbdata[i].objectid + '" hidden="hidden">' + 
										'<input name="wds['+i+'].belongto" type="text" value="' + wtlbdata[i].belongto + '" hidden="hidden">' + 
									 '</li>';
							 var br = '<br />';
							$("#xxnr").append(li);
							//$("#xxnr").append(br);
						}
					}
				}
			});
		}
		function addOption(){
			var i = $("#xxnr").children().length
			var li = '<li>'+
						'实际值<input name="wds['+i+'].value" type="text" >'+
						'显示文本<input name="wds['+i+'].text" type="text" >'+
						'排序值<input name="wds['+i+'].sort" type="text" >'+
						'<input name="wds['+i+'].belongto" type="text" hidden="hidden" value="'+$('#xxlb option:selected') .val()+'">' + 
					 '</li>';
			$("#xxnr").append(li);
		}
		function save(){
			
			$.post("/Feedback/wtdata/save.do",$("#wdsform").serialize(),function(data){
					if(data.flag){
						$("#xxnr").empty();
						var wtlbdata = data.data;
						for(var i = 0; i < wtlbdata.length; i++){
							var li = '<li>'+
										'实际值<input name="value['+i+']" type="text" value="' + wtlbdata[i].value + '">'+
										'显示文本<input name="text['+i+']" type="text" value="' + wtlbdata[i].text + '">'+
										'排序值<input name="sort['+i+']" type="text" value="' + wtlbdata[i].sort + '">'+
										'<input name="objectid['+i+']" type="text" value="' + wtlbdata[i].objectid + '" hidden="hidden">' + 
										'<input name="belongto['+i+']" type="text" value="' + wtlbdata[i].belongto + '" hidden="hidden">' + 
									 '</li>';
							$("#xxnr").append(li);
						}
					}else{
						alert(data.message);
					}
				});
			/* $.ajax({
                cache: true,
                type: "POST",
                dataType: 'json',
                contentType: "application/json", 
                url:'/Feedback/wtdata/save.do',
                data:$('#wdsform').serializeArray(),// 你的formid
                async: false,
                error: function(request) {
                    alert(request.message);
                },
                success: function(data) {
                	if(data.flag){
						$("#xxnr").empty();
						var wtlbdata = data.data;
						for(var i = 0; i < wtlbdata.length; i++){
							var li = '<li>'+
										'实际值<input name="value['+i+']" type="text" value="' + wtlbdata[i].value + '">显示文本<input name="text['+i+']" type="text" value="' + wtlbdata[i].text + '">'+
										'<input name="objectid['+i+']" type="text" value="' + wtlbdata[i].objectid + '" hidden="hidden">' + 
										'<input name="belongto['+i+']" type="text" value="' + wtlbdata[i].belongto + '" hidden="hidden">' + 
									 '</li>';
							$("#xxnr").append(li);
						}
					}
                }
            }); */
		}
		</script>
	</body>
</html>