
/**
 * 
 * @param method   请求方式 get 或post
 * @param url      请求的地址 
 * @param params   传输的参数   格式  键1=值1&键2=值2...
 * @param handle200  成功时候执行的函数  形参会接收到实参  responseText
 * @param asyn	   同步异步boolean  不写或写错默认异步
 * @param handle404  找不到页面执行的函数
 * @param handle500  服务器报错执行的函数
 * @param loading    接收数据中执行的函数
 */
function ajax(method,url,params,handle200,asyn,handle404,handle500,loading){
	//加上一个毫秒数防缓存
	if(url.indexOf("?")!=-1){
		url=url+"&"+new Date().getTime();
	}else{
		url=url+"?"+new Date().getTime();
	}
	
	if(method==="get"&&params){
		url=url+"&"+params;
	}
	//获取请求对象
	var request;
	if(window.XMLHttpRequest){
		request=new XMLHttpRequest();
	}else if(window.ActiveXObject){
		request=new ActiveXObject("Msxml2.XMLHTTP");
	}
	
	//访问服务器
	if(typeof asyn=="boolean"){
		request.open(method,url,asyn);
	}else{
		request.open(method,url);
	}
	
	//监听响应的状态
	request.onreadystatechange=function(){
		//当前的响应状态	
		var readyState=request.readyState;
		if(readyState==4){
			//获取状态码
			var status=request.status;
			if(status==200){
				//获取服务器write出来的信息
				var result=request.responseText;
				if(typeof handle200=="function"){
					handle200(result);
				}
			}else if(status==404){
				if(typeof handle404=="function"){
					handle404();
				}
			}else if(status==500){
				//服务器报错走的逻辑
				if(typeof handle500=="function"){
					handle500();
				}
			}
		}else{
			//数据接收中执行的代码
			if(typeof loading=="function"){
				loading();
			}
		}
	};
	
	//send方法 是专门给post设计的传参方法   get方法只能传null
	if(method==="post"){
		request.setRequestHeader("content-type","application/x-www-form-urlencoded");
		request.send(params);
	}else{
		request.send(null); 
	}
}