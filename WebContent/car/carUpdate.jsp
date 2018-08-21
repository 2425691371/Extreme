<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
    String carId=new String( request.getParameter("carId").getBytes("ISO8859_1"),"utf8");
    String name=new String( request.getParameter("name").getBytes("ISO8859_1"),"utf8");
    String userId=new String( request.getParameter("userId").getBytes("ISO8859_1"),"utf8");
    String brand=new String( request.getParameter("brand").getBytes("ISO8859_1"),"utf8");
 %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="keyVal()">
<form action="/Extreme/carServlet?mark=carUpdate"   method="post" id="myForm" >
    <table style="background-color:lightskyblue ; width: 600px;margin-top: 100px ;border: 1px solid blue" align="center" border="1px" >
       <tr>
           <td>车主：</td>
           <td><%= name %></td>
           <td style="color: red" >不可修改！</td>
       </tr>
        <tr>
            <td>车牌：</td>
            <td><input type="text" value="<%= carId %>" id="carId" onblur="verifyId(this.value)" name="newCarId" />  </td>
            <td><span id="span1"  style="color: red"  ></span>
            <input type="hidden" name="oldCarId" value="" id="hid" > 
            </td>
        </tr>
        <tr>
            <td>身份证：</td>
            <td><%= userId %></td>
            <td style="color: red" >不可修改！</td>
        </tr>
        <tr>
            <td>类型：</td>
            <td><input type="text" id="brand" value="<%= brand %>" onblur="verifyBrand(this.value)" name="brand" /> </td>
            <td ><span style="color: red"  id="span2"></span></td>
        </tr>
        <tr align="center" >
            <td colspan="2" >
                <input type="button" value="提交" onclick="subBut()" />

            </td>
			<td><span id="span3" style="color: red" ></span></td>
        </tr>

    </table>

</form>
</body>
</html>
<script>
		var carId=null;
		var span1=document.getElementById("span1");
		var span2=document.getElementById("span2");
		var span3=document.getElementById("span3");
		var carId=document.getElementById("carId");
		var brand=document.getElementById("brand");
		var hid=document.getElementById("hid");
		var myForm=document.getElementById("myForm");
		function keyVal(){
			carId=document.getElementById("carId");
			oldCarId=carId.value;
			hid.value=oldCarId;
		}
		
		function subBut(){
			newCarId=document.getElementById("carId").value;

			if(span1.innerHTML||span2.innerHTML){
				span3.innerHTML="页面信息填写不正确"
			}else {
				myForm.submit();
				window.location="carQuery.jsp";
			}
		}
		
		function verifyId(val){

				span3.innerHTML="";
			
			   var car=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
			 	if(!car.test(val)){
			 		span1.innerHTML="请填写正确的车牌号"
			 	}else {
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
			                    if(result){
			                    	span1.innerHTML=result;
			                    }else {
			                    	span1.innerHTML="";
			                    }
			                }
			            }
			        }
			        request.setRequestHeader("content-type","application/x-www-form-urlencoded");
			        request.send("carId="+val);
			 	}
		}
		
		function verifyBrand(val){
			span3.innerHTML=""
			var request;
	        if(window.XMLHttpRequest){
	            request=new XMLHttpRequest();
	        }else if(window.ActiveXObject){
	            request=new ActiveXObject("Msxml2.XMLHTTP");
	        }
	        request.open("post","/Extreme/carServlet?mark=carBrandQuery");
	        request.onreadystatechange=function(){
	            var state=request.readyState;
	            if(state==4){
	                var status=request.status;
	                if(status==200){
	                    var result=request.responseText;
	                    if(result){
	                    	span2.innerHTML=result;
	                    }else {
	                    	span2.innerHTML="";
	                    }
	                }
	            }
	        }
	        request.setRequestHeader("content-type","application/x-www-form-urlencoded");
	        request.send("brand="+val);
		}
		
</script>
