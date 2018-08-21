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
	</head>
	<script src="/Extreme/util/echarts.js"></script>
	<script src="/Extreme/util/ajaxUtil.js"></script>
	<body onload="getSalary()">
		<div id="main" style="width: 600px;height:400px;"></div>
	</body>
</html>
<script type="text/javascript">
    //通过ajax给option加载x轴名字和数据
    function getSalary(){
    	 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '工资分布图'
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
        myChart.on('click', function (params) {
            var salary=params.name;
            var count=params.value;
            window.open("employeeServelet?mark=queryBySalary&salary="+salary+"&count="+count,"newWin","width=600,height=500");
        });
        ajax("get","employeeServelet","mark=salaryArea",function(data){
        	eval("var salaryArray="+data);
	    	for(var i=0;i<salaryArray.length;i++){
	    		option.xAxis.data.push(salaryArray[i].salaryArea);
	    		option.series[0].data.push(salaryArray[i].count);
	    	}
	    	//使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
    	});
    }
</script>