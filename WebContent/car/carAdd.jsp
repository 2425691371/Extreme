<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <style>
        .td1{
            width: 20%;
        }
        .td2{
            border: 1px solid red;
            width: 25%;
        }
    </style>
</head>

<body  onload="carSpace(),carBrand()" >
<form action="/Extreme/carServlet?mark=carAdd" method="post" id="myForm" >
    <table style="background-color:lightskyblue ; width: 600px;margin-top: 100px ;border: 1px solid blue" align="center" >
        <tr>
            <td class="td1" name="carId" >车牌号：</td>
            <td class="td2">
                <input  type="text" onblur='carIdQuery(this.value)' id="carId"  onchange="checkValue()" name="carId"  value=""/>
            </td>
            <td style="border: 1px solid blue;" ><span style="color: red"  id="span1"></span></td>
        </tr>
        <tr>
            <td  class="td1">车类型：</td>
            <td class="td2">
                <select id="se1" name="se1" onchange="checkValue()"  >
                    <option value="" >请选择：</option>
                </select>
            </td>
            <td><span style="color: red" id="span2" ></span></td>
        </tr>
        <tr>
            <td  class="td1">停放地址：</td>
            <td class="td2">
                <select id="se2" name="carSpace" onchange="checkValue()" >
                    <option value="" >请选择：</option>
                </select>
            </td>
            <td><span style="color: red" id="span3"></span></td>
        </tr>
        <tr>
            <td  class="td1">车主身份证：</td>
            <td class="td2">
                <input type="text" id="IdCard" name="idCard" onchange="checkValue()" />
            </td>
            <td><span style="color: red" id="span4" ></span></td>
        </tr>
        <tr>
            <td  class="td1">车主姓名：</td>
            <td class="td2">
                <input type="text" id="uname"  name="uname" onchange="checkValue()" />
            </td>
            <td><span style="color: red" id="span5" ></span></td>
        </tr>
        <tr >
            <td style="width: 500px" colspan="2" align="center"> <input type="button" value="提交" onclick="submitBut()" /> <input type="reset" value="重置" onclick="resetBut()" /></td>
            <td><span style="color: red" id="span6" ></span></td>
        </tr>
    </table>

</form>
</body>
</html>
<script src="/util/ajaxUtil.js" ></script>

<script>
var span1=document.getElementById("span1");
var span2=document.getElementById("span2");
var span3=document.getElementById("span3");
var span4=document.getElementById("span4");
var span5=document.getElementById("span5");
var span6=document.getElementById("span6");
var carId=  document.getElementById("carId");
var IdCard=document.getElementById("IdCard");
var uname= document.getElementById("uname");
var se1=document.getElementById("se1");
var se2=document.getElementById("se2");
var submit=false;
	function resetBut(){
		  span1.innerHTML="";
		  span2.innerHTML="";
		  span3.innerHTML="";
		  span4.innerHTML="";
		  span5.innerHTML="";
	}
	
	function checkValue(){
		span6.innerHTML="";
		
        var car=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
        var userId=/^^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
 //       var IdNum=userId.test(IdCard.value);
      if(carId.value){
    	  var carNum=car.test(carId.value);
    	  if(!carNum){
    		  span1.innerHTML="请填写正确的车牌号";
    		  span2.innerHTML="";
    		  span3.innerHTML="";
    		  span4.innerHTML="";
    		  span5.innerHTML="";
    	  }else {
    		  if(!se1.value){
    			  span2.innerHTML="请选择汽车品牌";
        		  span3.innerHTML="";
        		  span4.innerHTML="";
        		  span5.innerHTML="";
    		  }else {
    			  span2.innerHTML=""
    			 if(!se2.value){
       			 	  span3.innerHTML="请选择停车位";
	       			  span2.innerHTML="";
	        		  span4.innerHTML="";
	        		  span5.innerHTML="";
    			 }else {
    				 span3.innerHTML="";
    				 if(!IdCard.value){
    					  span2.innerHTML="";
           			 	  span4.innerHTML="请填写车主身份证";
	            		  span3.innerHTML="";
	            		  span5.innerHTML="";
    				 }else {
    					 span4.innerHTML="";
    					 var IdNum=userId.test(IdCard.value);
    					 if(IdNum){
    						 if(!uname.value){
    							 span4.innerHTML="";
                   			 	 span5.innerHTML="请填写车主姓名";
                    		     span2.innerHTML="";
                    		 	 span3.innerHTML="";
        					 }else{
        						 span5.innerHTML="";
        						 carQuery(uname.value,IdCard.value);
 //       						 if((!span1.innerHTML&&!span2.innerHTML&&!span3.innerHTML&&!span4.innerHTML&&!span5.innerHTML)){
 //      							 submit=true;
 //       							 myForm.submit();
 //       							 }
        					 }
    					 }else {
    						  span3.innerHTML="";
               			 	  span4.innerHTML="请填写正确的车主身份证";
	                		  span2.innerHTML="";
	                		  span5.innerHTML="";
    					 }
    				 }
    			 }
    		  }
    	  }
      }else {
    	  span1.innerHTML="请填写车牌号";
      }
	}
	
	function submitBut(){
		var myForm=document.getElementById("myForm");
		if(span1.innerHTML||span2.innerHTML||span3.innerHTML||span4.innerHTML||span5.innerHTML){
			
			span6.innerHTML="页面信息填写错误";
		}else {
			myForm.submit();
		}
	}
	
	 function carBrand(){
	    	if(carId.value){
	    		carIdQuery(carId.value)
	    	}
	        var request;
	        if(window.XMLHttpRequest){
	            request=new XMLHttpRequest();
	        }else if(window.ActiveXObject){
	            request=new ActiveXObject("Msxml2.XMLHTTP");
	        }
	        request.open("get","/Extreme/carServlet?mark=carBrand");
	        request.onreadystatechange=function(){
	            var state=request.readyState;
	            if(state==4){
	                var status=request.status;
	                if(status==200){
	                    var result=request.responseText;
	                    eval("var resultArr="+result);
	                    for(var i=0;i<resultArr.length;i++){
	                        se1.options.add(new Option(resultArr[i],resultArr[i]));
	                    }

	                }
	            }
	            /*
	             动态添加option
	             select对象.options.add(new Option(text,value));
	             动态删除option
	             清空所有的option
	             select.options.length=0;
	             删除某一个option
	             select.remove(index);
	             */
	        }
	        request.send(null);
	    }
	
	
	
    function carSpace(){
    	if(carId.value){
    		carIdQuery(carId.value)
    	}
    	
    	
        var request;
        if(window.XMLHttpRequest){
            request=new XMLHttpRequest();
        }else if(window.ActiveXObject){
            request=new ActiveXObject("Msxml2.XMLHTTP");
        }
        request.open("get","/Extreme/carServlet?mark=carSpace");
        request.onreadystatechange=function(){
            var state=request.readyState;
            if(state==4){
                var status=request.status;
                if(status==200){
                    var result=request.responseText;
                    eval("var resultArr="+result);
                    for(var i=0;i<resultArr.length;i++){
                        se2.options.add(new Option(resultArr[i],resultArr[i]));
                    }

                }
            }
            /*
             动态添加option
             select对象.options.add(new Option(text,value));
             动态删除option
             清空所有的option
             select.options.length=0;
             删除某一个option
             select.remove(index);
             */
        }
        request.send(null);
    }

    function carIdQuery(val){
        span1.innerHTML="";

        var request;
        if(window.XMLHttpRequest){
            request=new XMLHttpRequest();
        }else if(window.ActiveXObject){
            request=new ActiveXObject("Msxml2.XMLHTTP");
        }
        request.open("post","/Extreme/carServlet?mark=carIdQuery");
        request.onreadystatechange=function(){
            var state=request.readyState;
            if(state==4){
                var status=request.status;
                if(status==200){
                    var result=request.responseText;
                    span1.innerHTML=result;
                }
            }
        }

        request.setRequestHeader("content-type","application/x-www-form-urlencoded");
        request.send("carId="+val);

    }
    
    function carQuery(name,idCard){
        span1.innerHTML="";

        var request;
        if(window.XMLHttpRequest){
            request=new XMLHttpRequest();
        }else if(window.ActiveXObject){
            request=new ActiveXObject("Msxml2.XMLHTTP");
        }
        request.open("post","/Extreme/carServlet?mark=carQuery");
        request.onreadystatechange=function(){
            var state=request.readyState;
            if(state==4){
                var status=request.status;
                if(status==200){
                    var result=request.responseText;
                    span4.innerHTML=result;
                }
            }
        }
        request.setRequestHeader("content-type","application/x-www-form-urlencoded");
        request.send("IdCard="+IdCard.value+"&uname="+uname.value);

    }
</script>
