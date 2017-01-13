<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.bean.Common" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html class="feedback">

	<head>
		<meta charset="utf-8">
		<link rel="shortcut icon" href="/Feedback/resource/images/xmwtfl.png">
		<title><%= Common.title %></title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<style type="text/css">
			td{
				width:80px;
			}
			td input{
				width:97%;
				height:97%;
				font-size:16px;
			}
			td select{
				width:97%;
				height:97%;
				font-size:16px;
			}
		</style>
	</head>

	<body>
		<form id="form" action="/Feedback/wtpd/plpd.do" method="post">
		<table id="table" border="1">
			<tr>
				<td style="width:40px">序号</td>
				<td>问题号</td>
				<td style="width:100px">提报日期</td>
				<td style="width:120px">客户名称</td>
				<td>产品类别</td>
				<td>项目编号</td>
				<td>提报人</td>
				<td>项目阶段</td>
				<td>紧急程度</td>
				<td>部套名称</td>
				<td>问题件图号</td>
				<td>物料编码</td>
				<td style="width:120px">问题描述</td>
				<td>问题图片</td>
				<td>责任类别</td>
				<td>问题类别</td>
				<td>责任人</td>
				<td>要求完成时间</td>
			</tr>
			<c:forEach items="${works}" var="work" varStatus="status">
				<tr>
					<td style="width:40px">${status.index + 1}</td>
					<td>${work.projectIssueManage.lsh}</td>
					<td>
						<label id="projects[${status.index}].createtime" > </label>
						<script>
							var date = new Date("${work.projectIssueManage.createtime}");
							var year = date.getFullYear().toString();
							var month = (date.getMonth()+1).toString();
							if(month.length < 2){
								month = "0" + month;
							}
							var day = date.getDate().toString();
							if(day.length < 2){
								day = "0" + day;
							}
							document.getElementById("projects[${status.index}].createtime").innerHTML = year+"-"+month+"-"+day;
						</script>
					</td>
					<td><input type="text" id="projects[${status.index}].khmc" name="projects[${status.index}].khmc" value="${work.projectIssueManage.khmc}"></td>
					<td>
						<select id="projects[${status.index}].cpmc" name="projects[${status.index}].cpmc">
							<option></option>
							<c:forEach items="${wtlbs}" var="wtlb">
								<option value="${wtlb.value}">${wtlb.text}</option>
							</c:forEach>
						</select>
					</td>
					<td><input type="text" id="projects[${status.index}].xmbh" name="projects[${status.index}].xmbh" value="${work.projectIssueManage.xmbh}"></td>
					<td>${work.projectIssueManage.fqr.xm}</td>
					<td>
						<select id="projects[${status.index}].xmjd" name="projects[${status.index}].xmjd">
							<option></option>
							<c:forEach items="${xmjds}" var="xmjd">
								<option value="${xmjd.value}">${xmjd.text}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select id="projects[${status.index}].jjcd" name="projects[${status.index}].jjcd">
							<option></option>
							<c:forEach items="${jjcds}" var="jjcd">
								<c:if test="${jjcd.value == work.projectIssueManage.jjcd}">
									<option value="${jjcd.value}" selected="selected">${jjcd.text}</option>
								</c:if>
								<c:if test="${jjcd.value != work.projectIssueManage.jjcd}">
									<option value="${jjcd.value}">${jjcd.text}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td><input type="text" id="projects[${status.index}].btmc" name="projects[${status.index}].btmc" value="${work.projectIssueManage.btmc}"></td>
					<td><input type="text" id="projects[${status.index}].wtjth" name="projects[${status.index}].wtjth" value="${work.projectIssueManage.wtjth}"></td>
					<td><input type="text" id="projects[${status.index}].wlbm" name="projects[${status.index}].wlbm" value="${work.projectIssueManage.wlbm}"></td>
					<td><textarea id="projects[${status.index}].wtms" name="projects[${status.index}].wtms" style="width:97%;height:97%">${work.projectIssueManage.wtms}</textarea></td>
					<td>${work.projectIssueManage.wttp }</td>
					<td>
						<select id="projects[${status.index}].zrlb" name="projects[${status.index}].zrlb">
							<option></option>
							<c:forEach items="${zrlbs}" var="zrlb">
								<option value="${zrlb.value}">${zrlb.text}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select id="projects[${status.index}].wtlb" name="projects[${status.index}].wtlb">
							<option></option>
							<c:forEach items="${wtlbs}" var="wtlb">
								<option value="${wtlb.value}">${wtlb.text}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="text" id="projects[${status.index}].zrr" name="projects[${status.index}].zrr">
					</td>
					<td><input type="date" id="projects[${status.index}].yqwcsj" name="projects[${status.index}].yqwcsj" style="font-size:12px;"></td>
				</tr>
			</c:forEach>
		</table>
		<input type="button" value="提交" onclick="submit()" style="width:60px;height:30px;margin:1% 0 50% 50%">
		</form>
		<script src="/Feedback/resource/js/jquery1.8.0.min.js"></script>
		<script type="text/javascript">
		function submit(){
			//校验责任人
			
			$.ajax({
					url:'/Feedback/User/check.do',
					data:{names:'产品类别'},
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
			//提交表单
			$.post("/Feedback/wtpd/plpd.do",
			         $("#form").serialize(),
			         function(data){
				
			});
		}
		
		</script>
	</body>
</html>