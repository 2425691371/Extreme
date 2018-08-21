<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="test()">
	<h1>恭喜${param.name }缴费成功,3秒后跳转回主页面！</h1>
</body>
</html>
<script>
	function test(){
		window.setTimeout(function(){
			window.location="/Extreme/xmServlet?mark=query";
		},3000)
	}
</script>