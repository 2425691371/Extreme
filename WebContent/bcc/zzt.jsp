<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="../util/echarts.js"></script>
<script src="/Extreme/util/ajaxUtil.js"></script>
<body>
	<div id="main" style="width: 600px;height:400px;"></div>
	
</body>
</html>
<script type="text/javascript">
      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById('main'));

      // 指定图表的配置项和数据
      var option = {
          title: {
              text: '缴费金额统计',
              subtext:'全小区总缴费金额统计'
          },
          tooltip: {},
          legend: {
              data:['金额']
          },
          xAxis: {
              data: []
          },
          yAxis: {},
          series: [{
              name: '金额',
              type: 'bar',
              data: []
          }]
      };

       myChart.on('click', function (params) {
    	   window.location="/Extreme/renShuAction?mark=queryFei&pname="+params.name;
       });
      // 使用刚指定的配置项和数据显示图表。
      ajax("get","/Extreme/renShuAction","mark=queryZong",function(data){
    	  eval("var zongArray="+data);
    	  for (var i = 0; i < zongArray.length; i++) {
			option.xAxis.data.push(zongArray[i].pname);
			option.series[0].data.push(zongArray[i].price);
		}
       myChart.setOption(option);
      });
  </script>
