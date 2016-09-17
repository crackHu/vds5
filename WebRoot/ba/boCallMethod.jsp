<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="org.springframework.web.context.WebApplicationContext"/>
<jsp:directive.page import="org.springframework.web.context.support.WebApplicationContextUtils"/>
<jsp:directive.page import="java.util.Map"/>
<jsp:directive.page import="java.util.HashMap"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="net.sf.json.JSONObject"/>
<jsp:directive.page import="net.sf.json.JSONSerializer"/>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>

// [Mod-A1]:本jsp调用的类
<jsp:directive.page import="ba.base.BoBase"/>

<%
// [Mod-A1]
//String sBoName="bo_vds_unified";  // 对应 bean 变量名，因此首字母 小写
//String sFuncName="vds_unified_getById";  // 对应 方法名

Date dateBg = new Date();

String sSession = session.getId();// .toString();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//System.out.println("path="+basePath);
request.setCharacterEncoding("utf-8");
HttpSession session1 = request.getSession();

// 获取ajax参数，ajax格式是  data:<dataIn json to string>
// 前端js语句: $.ajax({ data:{data:JSON.stringify(dataIn) })
// 以下语句使得  sDataIn = <dataIn json to string>，并转化为 JSONObject jDataIn
// 在 bean中 jDataIn 恢复为 jason 结构
// bean语句: jDataIn.getString("<name>"); 获得参数
String sDataIn=request.getParameter("data");
JSONObject jDataIn = (JSONObject) JSONSerializer.toJSON(sDataIn);
String appPath = request.getSession().getServletContext().getRealPath("");
//jDataIn.put("appPath", appPath);
//jDataIn.put("session",session1);

JSONObject jsonObj = JSONObject.fromObject(jDataIn.toString());

//获取要执行的function
String sBoName = (String)jsonObj.get("boName");
String sFuncName= (String)jsonObj.get("funcName"); 

/* String sBoName=request.getParameter("boName");
String sFuncName=request.getParameter("funcName"); */

// [Dbg-1]
System.out.format("[%s][%s][%s]in=[%s]%n",sSession,sBoName,dateBg.toGMTString(),sDataIn);

WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
// 获取bean，该bean必须在 applicationContext.xml(或之下)中定义，并且在本jsp上面 import 该类(如 com.ba.base.Bo<name>)
// 注意类的大小写。规范：beanClassName= Bo<name>;  xml配置bean= bo<name>;  本jsp变量名= bo<name>
BoBase boNameMgr = (BoBase)wac.getBean(sBoName);

// 调用该类的某个属性名
//JSONObject jDataOut = boLogin.vds_unified_getById(jDataIn);
JSONObject jDataOut = new JSONObject();
if (boNameMgr != null) {
	jDataOut = boNameMgr.callMethod(sFuncName, jDataIn);
} else {
	//logger.error("没有找到该BO服务类:" + boName);
}

// 将 jDataOut 转化为 string，返回前端
out.clear();
if(sFuncName.equals("login")){
	session.setAttribute("userId", jDataOut.get("userId"));
	session.setAttribute("loginName", jDataOut.get("loginName"));				
}
out.print(jDataOut.toString());

// 调试信息
// [Dbg-2]
Date dateEnd = new Date();
Long dateMs = dateEnd.getTime() - dateBg.getTime();

System.out.format("[%s][%s][%d ms]out=[%s]%n",sSession,sBoName,dateMs,jDataOut.toString());

%>
