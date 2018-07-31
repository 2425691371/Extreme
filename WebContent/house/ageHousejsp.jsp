<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<base href="<%=basePath%>">
	 <script src="/Extreme/util/echarts.js"></script>
	 <script src="/Extreme/util/ajaxUtil.js"></script>
</head>
<body onload="getAge()">
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
            title: {
                text: '年龄分布图'
            },
            tooltip: {},
            legend: {
                data:['人数']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '人数',
                type: 'bar',
                data: [],
            }]
        };
    
    	myChart.on("click",function(params){
    	//alert(params.name);
    		window.open("residentTest?mark=queryByAge&ageArea="+params.name+"&count="+params.data,"newWin","width=1000,height=600");
    });
    	
		function getAge(){
			ajax("get","residentTest","mark=ageShow",function(data){
				eval("var ageTest="+data);
				for(var i=0;i<ageTest.length;i++){
					option.xAxis.data.push(ageTest[i].agearea);
					option.series[0].data.push(ageTest[i].count);
				}
			},false);
   			 myChart.setOption(option);
		}
    // 使用刚指定的配置项和数据显示图表。
</script>
</body>
</html>
