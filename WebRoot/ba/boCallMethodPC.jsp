<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="org.springframework.web.context.WebApplicationContext"/>
<jsp:directive.page import="org.springframework.web.context.support.WebApplicationContextUtils"/>
<jsp:directive.page import="java.util.Map"/>
<jsp:directive.page import="java.util.HashMap"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.net.URLDecoder"/>
<jsp:directive.page import="net.sf.json.JSONObject"/>
<jsp:directive.page import="java.io.ByteArrayOutputStream"/>
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


ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
byte[] data = new byte[100000];  
int count = -1;  
while((count = request.getInputStream().read(data,0,4096)) != -1)  
	outStream.write(data, 0, count);  
  

String sout = new String(outStream.toByteArray(),"UTF-8");
//sout = "msg=%7B%22content%22%3A%7B%22textContent%22%3A%7B%22text%22%3A%22hello%22%7D%2C%22contentType%22%3A1%7D%2C%22openIdEx%22%3A%7B%22tag%22%3A6%2C%22openId%22%3A208355%7D%2C%22tag%22%3A0%2C%22createdAt%22%3A1460648246630%2C%22conversationId%22%3A%22208355%3A214244%22%2C%22creatorType%22%3A1%2C%22type%22%3A1%2C%22messageId%22%3A4227401525%2C%22memberTag%22%3A0%7D&msgid=4227401525&senduid=%5B%7B%22isWithExt%22%3Afalse%2C%22isUpdating%22%3Afalse%2C%22orgEmployees%22%3A%5B%5D%2C%22userOverageModel%22%3A%7B%7D%2C%22userPermissionModel%22%3A%7B%7D%2C%22friendRequestModel%22%3A%7B%7D%2C%22userProfileModel%22%3A%7B%22isDataComplete%22%3Atrue%2C%22isActive%22%3Atrue%2C%22uid%22%3A208355%2C%22dingTalkId%22%3A%22earname%22%2C%22nick%22%3A%22EarName%22%2C%22status%22%3A2%2C%22orgEmail%22%3A%22nZ2k%2BBmCaZsPx6MrAU9cq4rfLfxSmYPgd%2BZCMmUg50I%3D%22%2C%22activeTime%22%3A1418486400000%2C%22gender%22%3A%22M%22%2C%22nickPinyin%22%3A%22E%5Ea%5Er%5EN%5Ea%5Em%5Ee%22%2C%22ver%22%3A6%2C%22type%22%3A0%2C%22avatarMediaId%22%3A%22%40lADOAARKLM0C7s0C1A%22%2C%22city%22%3A%22%E5%B9%BF%E4%B8%9C-%E5%B9%BF%E5%B7%9E%22%2C%22avatarUrl%22%3A%22https%3A%2F%2Fstatic.dingtalk.com%2Fmedia%2FlADOAARKLM0C7s0C1A_724_750.jpg_60x60q90.jpg%22%7D%2C%22employeeBaseModel%22%3A%7B%7D%2C%22employeeRequestPool%22%3A%7B%7D%2C%22remarkNameObj%22%3A%7B%22openId%22%3A208355%2C%22alias%22%3Anull%2C%22pinyin%22%3Anull%7D%2C%22followObj%22%3A%7B%22openId%22%3A208355%2C%22status%22%3Anull%2C%22lastModify%22%3Anull%2C%22tag%22%3Anull%7D%7D%5D&atturl=&imgurl=";
String sdcout = URLDecoder.decode(sout, "UTF-8");
System.out.println("==============sdcout111: "+ sdcout);
JSONObject jDataIn = null;
if("".equals(sdcout)){
	String sDataIn=request.getParameter("data");
	System.out.println(sDataIn);
	jDataIn = (JSONObject) JSONSerializer.toJSON(sDataIn);
}else{
	//jDataIn = (JSONObject) JSONSerializer.toJSON(sdcout);
	//jDataIn = (JSONObject) JSONSerializer.toJSON(URLDecoder.decode(sdcout, "UTF-8"));
	jDataIn  = JSONObject.fromObject(sdcout);
}


HttpSession session1 = request.getSession();

// 获取ajax参数，ajax格式是  data:<dataIn json to string>
// 前端js语句: $.ajax({ data:{data:JSON.stringify(dataIn) })
// 以下语句使得  sDataIn = <dataIn json to string>，并转化为 JSONObject jDataIn
// 在 bean中 jDataIn 恢复为 jason 结构
// bean语句: jDataIn.getString("<name>"); 获得参数
//String sDataIn=request.getParameter("data");

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
System.out.format("[%s][%s][%s]in=[%s]%n",sSession,sBoName,dateBg.toGMTString(),jDataIn);

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
