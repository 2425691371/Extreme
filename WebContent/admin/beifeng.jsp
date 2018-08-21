
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<base href="<%=basePath%>">
	</head>
<body>
<table border="1" width="100%" >
    <tr>
        <th>物业管理相关数据表</th>
        <td>&nbsp;</td>
        <th>预备份数据表</th>
    </tr>
    <tr>
        <td valign="top" width="40%">
            <table border="1" width="100%" id="leftTab">
                <tr>
                    <td width="10%"><input type="button"  value="反选" onclick="chooseallleft()" /></td>
                    <th width="90%">业务表名</th>
                </tr>
                <c:forEach items="${tablename }" var="table">
                <tr>
                    <td><input type="checkbox"  name="leftTabCb"  value="${table }" /></td>
                    <td align="center">${table }</td>
                </tr>
                </c:forEach>
            </table>
        </td>
        <td width="20%">
            <table   width="100%">
                <tr>
                    <td align="center"><input type="button" value="加入>>" onclick="moveToRight()" /></td><br/>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><input type="button" value="<<移除" onclick="moveToLeft()" /></td><br/>
                </tr>
            </table>
        </td>
        <td valign="top" width="40%">
            <form method="post"  action="adminServlet?mark=bfdata" onsubmit="return verify()" >
            <table border="1" width="100%" id="rightTab">

                <tr  >
                    <td width="10%"><input type="button" value="反选" onclick="chooseallright()" /></td>
                    <th width="90%">预备份数据表</th>
                </tr>
                <tr>
                    <td colspan="2">
                                    备份标签:<input type="text" style="width: 150px"  name="backupsname"  />
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        <input type="submit" value="备份" />
                    </td>
                </tr>
            </table>
</form>
        </td>
    </tr>
</table>
</body>
</html>
<script src="util/util.js"></script>
<script>
function chooseallleft(){
	var chec = document.getElementsByName("leftTabCb");
	for(var i in chec){
		chec[i].checked = !chec[i].checked;
	}
}

function chooseallright(){
	var chec = document.getElementsByName("rightTabCb");
	for(var i in chec){
		chec[i].checked = !chec[i].checked;
	}
}
    var leftTbody=getLastChild($("#leftTab"));
    var rightTbody=getLastChild($("#rightTab"));
    function moveToRight(){
        var cbs=$("leftTabCb");
        for(var i=0;i<cbs.length;i++){
            if(cbs[i].checked){
                var delTr=cbs[i].parentNode.parentNode;
                var newcbs=delTr.cloneNode(true);
                getFirstChild(getFirstChild(newcbs)).name="rightTabCb";
                getFirstChild(getFirstChild(newcbs)).checked=false;
                leftTbody.removeChild(delTr);
                i--;
                rightTbody.appendChild(newcbs);
            }
        }
    }
    function moveToLeft(){
        var cbs=$("rightTabCb");
        for(var i=0;i<cbs.length;i++){
            if(cbs[i].checked){
                var delTr=cbs[i].parentNode.parentNode;
                var newcbs=delTr.cloneNode(true);
                getFirstChild(getFirstChild(newcbs)).name="leftTabCb";
                getFirstChild(getFirstChild(newcbs)).checked=false;
                rightTbody.removeChild(delTr);
                i--;
                leftTbody.appendChild(newcbs);
            }
        }
    }
    
    function verify(){
    	var cbs=$("rightTabCb");
    	for(var i=0;i<cbs.length;i++){
    		if(cbs[i].checked){
    			return true;
    		}
    	}
    	alert("还没有选中要备份的表");
    	return false;
    }
</script>