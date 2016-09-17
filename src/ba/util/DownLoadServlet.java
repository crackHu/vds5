package ba.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class DownLoadServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet
{

	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// url = location="DownLoadServlet.do?filePath="+field_path+field_name;
		// 例如 DownLoadServlet.do?filePath=20120621103512_a9e527a8-efd2-434c-9892-ee899680c50c\\科2012.0615.docx
		// 读取的文件位置在: <tomcat 应用的目录>/upDnFiles/<filePath>
		// 上传的类请见 src/baj/util/upload2/ExtUpload.java
		
		request.setCharacterEncoding("utf8");
		
		
        String filePath=request.getParameter("filePath");
        
        // url中参数用"ISO8859_1"编码，因此要转为"UTF-8"
        if (filePath!=null && !filePath.equals("")){
        	System.out.println("filePath bf="+filePath);
        	filePath = new String(filePath.getBytes("ISO8859_1"),"UTF-8");
        	System.out.println("filePath af="+filePath);
        }
        
        String filename=request.getParameter("filename");
        String imagePath=request.getParameter("imagePath");
        String investMGfilename=request.getParameter("investMGfilename");
        String investMGImgfilename=request.getParameter("investMGImgfilename");
        String imageName=request.getParameter("imageName");
        String iconPath=request.getParameter("iconPath");
        String iconName=request.getParameter("iconName");
        String fileName=request.getParameter("fileName");
        String downType=request.getParameter("downType");
        String oldfileName=request.getParameter("oldfileName");
        String templateMobnaPath = request.getParameter("templateMobnaPath");
        
        String templatePath=request.getParameter("templatePath");
        
        
        String wordPath=request.getParameter("wordPath");
        //url中参数用"ISO8859_1"编码，因此要转为"UTF-8"
        if (wordPath!=null && !wordPath.equals("")){
        	wordPath = new String(wordPath.getBytes("ISO8859_1"),"UTF-8");
        }
        
        String xlsPath=request.getParameter("xlsPath");
        if (xlsPath!=null && !xlsPath.equals("")){
        	xlsPath = new String(xlsPath.getBytes("ISO8859_1"),"UTF-8");
        }

        if(fileName!=null && !fileName.equals("")){
        	fileName = java.net.URLDecoder.decode(fileName,"utf-8");
        }
        
        if(filePath!=null && !filePath.equals("")){
        	filePath = java.net.URLDecoder.decode(filePath,"UTF-8");
        }
        
        String suffixname=request.getParameter("suffixname");
        String fileId=request.getParameter("fileId");
        String CA = request.getParameter("CA");
        String userGuide = request.getParameter("userGuide");

        if(downType!=null && !"".equals(downType) && "am_att".equals(downType))
        {
        	downFileAm(request, response, filename, filePath);
        }
        
        if(templateMobnaPath!=null && templateMobnaPath.equals("true")){
            String path = request.getRealPath("/excelTemplate/template.rtf");
            File file=new File(path);
            Download.setFileToResponse(request,response, file, "传真模板.doc");
        }
        
        if(investMGImgfilename!=null && !investMGImgfilename.equals("")){
            File file=new File(investMGImgfilename);
            Download.setFileToResponse(request,response, file, "图片.zip");
        }
        if(investMGfilename!=null && !investMGfilename.equals("")){
            File file=new File(investMGfilename);
            Download.setFileToResponse(request,response, file, "附件.zip");
        }
        if(userGuide!=null && userGuide.equals("userGuideDownLoad")){
            File file=new File("D:\\filefolder\\CA\\操作手册.doc");
            Download.setFileToResponse(request,response, file, "用户操作手册.doc");
        }
        if(CA!=null && CA.equals("CADownLoad")){
            File file=new File("D:\\filefolder\\CA\\GDCA数字证书驱动程序.rar");
            Download.setFileToResponse(request,response, file, "GDCA数字证书驱动程序.rar");
        }
        if(filename!=null && !filename.equals("")){
            File file=new File(filename);
//            file = FileSecurity.fileDec(file,xlsPath);
            Download.setFileToResponse(request,response, file, "附件.zip");
        }
        if(xlsPath!=null && !xlsPath.equals("")){
            File file=new File(xlsPath);
//            file = FileSecurity.fileDec(file,xlsPath);
            System.out.println("full pathname="+xlsPath);
            Download.setFileToResponse(request,response, file, "导出的文件.xls");
        }
        if(imagePath!=null && !imagePath.equals("")){
            File file=new File(request.getRealPath("")+imagePath);
            Download.setFileToResponse(request,response, file, imageName);
        }
        if(imagePath!=null && !imagePath.equals("")){
            File file=new File(request.getRealPath("")+imagePath);
            Download.setFileToResponse(request,response, file, imageName);
        }
        if(iconPath!=null && !iconPath.equals("")){
            File file=new File(request.getRealPath("")+iconPath);
            Download.setFileToResponse(request,response, file, iconName);
        }
        
        if(filePath!=null && !filePath.equals("")){  // filePath 是相对目录+文件名
        	String fullPath;
        	
    		// 以下是特殊处理，解决多个tomcat容器或上传目录非本tomcat的情况
    		// 实现策略：在 prj.properties 文件定义一个属性 upDnFile_main
    		// 例如：upDnFile_main = \\\\172.16.47.94\\ext3\\shared
    		// 使得上传文件的根目录在 upDnFile_main\\upDnFiles 下
    		//
    		// 要求js端，调用参数加上 pathflag=qy
    		//

        	String otherPathFlag = request.getParameter("pathflag");
    		otherPathFlag = (null==otherPathFlag?"def":otherPathFlag);
    		
    		// debug
    		//otherPathFlag = "qy";
    		if ("qy".equalsIgnoreCase(otherPathFlag)){
    			Properties p = new Properties();
    			p.load(this.getClass().getClassLoader().getResourceAsStream("prj.properties"));
    			fullPath = p.getProperty("upDnFile_main");
    			
    			fullPath = fullPath+File.separator+"upDnFiles\\"+filePath;
    		} else {
    			fullPath=request.getRealPath("")+"\\upDnFiles\\"+filePath;
    		}
    		
            File file=new File(fullPath);
            System.out.println("full pathname="+fullPath);
            Download.setFileToResponse(request,response, file, fileName);
        }
        
        if(wordPath!=null && !wordPath.equals("")){
          File file=new File(wordPath);
          System.out.println("full pathname="+wordPath);
          Download.setFileToResponse(request,response, file, "导出的文件.doc");
      }
        
        if(templatePath!=null && !templatePath.equals("")){
        	if("am_customer".equalsIgnoreCase(templatePath.trim())){
        		String path = request.getRealPath("/excelTemplate/importExcelTemplateAM.xls");
        		File file=new File(path);
        		Download.setFileToResponse(request,response, file, "导入模板.xls");
        	}else if("am_progress".equalsIgnoreCase(templatePath.trim())){
        		String path = request.getRealPath("/excelTemplate/importExcelTemplateAM_progree.xls");
        		File file=new File(path);
        		Download.setFileToResponse(request,response, file, "导入模板.xls");
        	}else if("am_preChecklist".equalsIgnoreCase(templatePath.trim())){
        		String path = request.getRealPath("/excelTemplate/importExcelTemplateAM_preChecklist.xls");
        		File file=new File(path);
        		Download.setFileToResponse(request,response, file, "导入模板.xls");
        	}else{
        		String path = request.getRealPath("/excelTemplate/importExcelTemplate.xls");
        		File file=new File(path);
//            file = FileSecurity.fileDec(file,xlsPath);
        		Download.setFileToResponse(request,response, file, "导入模板.xls");
        		
        		
        	}
        }
	}
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	
	protected void downFileAm(HttpServletRequest request,
		HttpServletResponse response ,String filename,String filePath)
		throws ServletException, IOException
	{
		if(filename!=null && !filename.equals("")){
			String id = request.getParameter("id");
			if(id != null && !"".equals(id)){
				String sql = " select remark from am_att where id ='"+id+"'";
				WebApplicationContext springcontext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
				jdbcTemplate = (JdbcTemplate)springcontext.getBean("jdbcTemplate");
				Map map = jdbcTemplate.queryForMap(sql);
				String path = request.getRealPath("")+filePath;
				File file=new File(path);
				Download.setFileToResponse(request,response, file, (String)map.get("remark"));
				return;
			}
        }
	}
}
