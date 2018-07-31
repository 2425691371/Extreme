<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="../util/echarts.js"></script>
<body>
	<div id="main" style="width: 600px;height:400px;"></div>
	
</body>
</html>
<script src="../util/ajaxUtil.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
    	    title : {
    	        text: '缴费分析',
    	        subtext: '全小区缴费人数',
    	        x:'center'
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        orient: 'vertical',
    	        left: 'left',
    	        data: []
    	    },
    	    series : [
    	        {
    	            name: '人数',
    	            type: 'pie',
    	            radius : '55%',
    	            center: ['50%', '60%'],
    	            data:[
    	                /* {value:335, name:'停车费'},
    	                {value:310, name:'物业费'},
    	                {value:234, name:'燃气费'},
    	                {value:135, name:'卫生费'},
    	                {value:1548, name:'取暖费'},
    	                {value:1548, name:'水费'},
    	                {value:1548, name:'电费'} */
    	            ],
    	            itemStyle: {
    	                emphasis: {
    	                    shadowBlur: 10,
    	                    shadowOffsetX: 0,
    	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
    	                }
    	            }
    	        }
    	    ]
    	};
    ajax("get","/Extreme/renShuAction","mark=queryRen",function(data){
    	eval("var renArray="+data);
    	for (var i = 0; i < renArray.length; i++) {
			option.legend.data.push(renArray[i].pname);
			option.series[0].data[i]={value:renArray[i].count,name:renArray[i].pname};
	    
	    myChart.setOption(option);
		}
    })
    	
    myChart.on('click', function (params) {
	    window.location="/Extreme/renShuAction?mark=queryShu&pname="+params.name+"&count="+params.value;
	});

    // 使用刚指定的配置项和数据显示图表。
</script>
