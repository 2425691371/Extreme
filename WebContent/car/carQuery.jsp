<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body onload="carQuery()">
<form>
    <table style="width:80%;border: 1px solid yellowgreen " align="center" border="1px" id="tab">
        <tr>
            <td colspan="8" align="center" >
                <input type="button" value="全选" onclick="checkAll(1)" >
                <input type="button" value="全不选" onclick="checkAll(2)"  >
                <input type="button" value="反选" onclick="checkAll(3)"  >
                <input type="button" value="选择删选"  onclick="removeCh(null)" >
            </td>
        </tr>
        <tr >
            <th  >选择</th>
            <th>序号</th>
            <th>车主</th>
            <th>车牌</th>
            <th>身份证</th>
            <th>类型</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
    </table>
</form>
</body>
</html>
<script src="../util/util.js" ></script>
<script>
    function carQuery(){
        var tab=document.getElementById("tab");
        var request;
        if(window.XMLHttpRequest){
            request=new XMLHttpRequest();
        }else if(window.ActiveXObject){
            request=new ActiveXObject("Msxml2.XMLHTTP");
        }
        request.open("get","/Extreme/carServlet?mark=carQueryAll");
        request.onreadystatechange=function(){
            var state=request.readyState;
            if(state==4){
                var status=request.status;
                if(status==200){
                    var result=request.responseText;
                    eval("var resultArr="+result);
                    var n=1;
                    for(var i=0;i<resultArr.length;i++){
                        var newTr=tab.insertRow();
                        newTr.align="center";
                        newTr.insertCell().innerHTML="<input type='checkbox' name='cbx' value="+resultArr[i][1]+" />";
                        newTr.insertCell().innerHTML=n++;
                        for(var j=0;j<resultArr[i].length;j++){
                        	var newTd=newTr.insertCell();
                            newTd.innerHTML=resultArr[i][j];
                            if(j==1){
                            	newTd.id=resultArr[i][j];
                            }
                        }
                        var carId=resultArr[i][1];
//                     newTr.insertCell().innerHTML="<a href='#' onclick='update(this)'  carId="+resultArr[i][1]+">[编辑]</a>";
                        newTr.insertCell().innerHTML="<a href='carUpdate.jsp?carId="+resultArr[i][1]+"&name="+resultArr[i][0]+"&userId="+resultArr[i][2]+"&brand="+resultArr[i][3]+"'>[编辑]</a>";
                        newTr.insertCell().innerHTML="<a href='#' onclick='removeCh(this)' value="+resultArr[i][1]+" >[删除]</a>";
                    }
                }
            }
        }
        request.send(null);
    }
    function update(tagA){
		var carId=tagA.getAttribute("carId");
	
		window.location="/Extreme/carServlet?mark=queryById&carId="+carId;
		alert("123");
    }
    function checkAll(n){
        var cbx=document.getElementsByName('cbx');
        if(n==1){
            for(var i=0;i<cbx.length;i++){
                cbx[i].checked=true;
            }
        }else if(n==2){
            for(var i=0;i<cbx.length;i++){
                cbx[i].checked=false;
            }
        }else if(n==3){
            for(var i=0;i<cbx.length;i++){
                if( cbx[i].checked){
                    cbx[i].checked=false;
                }else {
                    cbx[i].checked=true;
                }
            }
        }
    }
    function removeCh(val){
    	
    	var temp=[];
    	if(!val){
    		 var cbx=document.getElementsByName('cbx');
             for(var i=0;i<cbx.length;i++) {
                 if (cbx[i].checked) {
                     temp.push(cbx[i].value);
                 }
             }
    	}else {
    		var value=val.getAttribute("value");
    		temp.push(value);
    	}
          var request;
          if (window.XMLHttpRequest) {
              request = new XMLHttpRequest();
          } else if (window.ActiveXObject) {
              request = new ActiveXObject("Msxml2.XMLHTTP");
          }
          request.open("post","/Extreme/carServlet?mark=delete");
          request.onreadystatechange = function () {
              var state = request.readyState;
              if(state==4){
                  var status=request.status;
                  if(status==200){
                	  		window.location="/Extreme/car/carQuery.jsp";
                	  }
                  }
          }
          request.setRequestHeader("content-type","application/x-www-form-urlencoded");
          request.send("carId="+temp);
          
    }
</script>