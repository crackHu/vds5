//
// 前端 dummy 桥接bo
//
// js 端调用规范
/*
	var dataIn={};  // dataIn=<YourJSONObject>;
	$.ajax({
		url:_ba.app+"ba/tmpl/boDummy.jsp",
		cache:false,
		type:"post",
		data:{data:JSON.stringify(dataIn)},
		dataType:"json",
		success:function(dataOut){
			// dataOut.resultCode >=0 是成功执行（语义由类自定义）;  <0 执行失败
			alert("code="+dataOut._c+"\nmsg="+dataOut._m);
		},
		error: function(dataOut){
			alert("失败");
		}
	});
*/

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<jsp:directive.page import="org.springframework.web.context.WebApplicationContext"/>
<jsp:directive.page import="org.springframework.web.context.support.WebApplicationContextUtils"/>
<jsp:directive.page import="java.util.Map"/>
<jsp:directive.page import="java.util.HashMap"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="net.sf.json.JSONObject"/>
<jsp:directive.page import="net.sf.json.JSONSerializer"/>
<jsp:directive.page import="java.io.BufferedReader"/>
<jsp:directive.page import="java.io.InputStreamReader"/>

// [Mod-A1]:本jsp调用的类
<jsp:directive.page import="ba.base.BoBase"/>
<%
// [Mod-A1]

//post地址：http://localhost:8080/vds5s1/prjX/boVdsRequest.jsp
//json格式：{"din":{"参数1":"11","参数2":"22"},"pid":"bo名","fid":"函数名"}

Date dateBg = new Date();
String sSession = session.getId();// .toString();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("path="+basePath);
request.setCharacterEncoding("utf-8");

// 获取ajax参数，ajax格式是  data:<dataIn json to string>
// 前端js语句: $.ajax({ data:{data:JSON.stringify(dataIn) })
// 以下语句使得  sDataIn = <dataIn json to string>，并转化为 JSONObject jDataIn
// 在 bean中 jDataIn 恢复为 jason 结构
// bean语句: jDataIn.getString("<name>"); 获得参数

// 将接收的post请求数据解码
BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
String line = null;
StringBuilder sb = new StringBuilder();
while((line = br.readLine())!=null){
    sb.append(line);
}
String sDataIn = sb.toString();//java.net.URLDecoder.decode(sb.toString(), "utf-8");
        
JSONObject jDataIn = (JSONObject) JSONSerializer.toJSON(sDataIn);
JSONObject jsonObj = JSONObject.fromObject(jDataIn.toString());

//获取要执行的function
String sBoName = (String)jsonObj.get("pid");
String sFuncName= (String)jsonObj.get("fid"); 
// [Dbg-1]
System.out.format("[%s][%s][%s]in=[%s]%n",sSession,sBoName,dateBg.toGMTString(),sDataIn);

WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
// 获取bean，该bean必须在 applicationContext.xml(或之下)中定义，并且在本jsp上面 import 该类(如 com.ba.base.Bo<name>)
// 注意类的大小写。规范：beanClassName= Bo<name>;  xml配置bean= bo<name>;  本jsp变量名= bo<name>
BoBase boNameMgr = (BoBase)wac.getBean(sBoName);
JSONObject jDataOut = null;

// 调用该类的某个属性名
jDataOut = boNameMgr.callMethod(sFuncName, jDataIn);

// 一般情况下，除非有必要，不需要以下代码（对 jDataOut.resultCode 处理）
// ------------------------------
// ------------------------------

// 将 jDataOut 转化为 string，返回前端
out.clear();
out.print(jDataOut.toString());

// 调试信息
// [Dbg-2]
Date dateEnd = new Date();
Long dateMs = dateEnd.getTime() - dateBg.getTime();
System.out.format("[%s][%s][%d ms]out=[%s]%n",sSession,sBoName,dateMs,jDataOut.toString());

%>
