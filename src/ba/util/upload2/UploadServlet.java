package ba.util.upload2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class UploadServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet
{

	/**
	 * 
	 */
	
	public ExtUpload extUpload;
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart)
		{
			UploadCfg cfg = new UploadCfg();
			//cfg.uploadDir = "/temp";// 相对于Web根目录
			cfg.uploadDir = request.getParameter("uploadPath");
			cfg.pathType = request.getParameter("pathType");
            cfg.directId = request.getParameter("directId");
            cfg.userId = request.getParameter("userId");
            String multUpload = request.getParameter("multUpload");
            String path = request.getRealPath("");
            WebApplicationContext wac =
            	   WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
            extUpload = (ExtUpload)wac.getBean("extUpload2");
            cfg.uploadDir=path;
            if("am".equals(cfg.pathType)){
            	extUpload.fileUpload_am(request, response, cfg,multUpload);
            	
            }else if("emailAtt".equals(cfg.pathType)){
            	extUpload.fileUpload_emailAtt(request, response, cfg,multUpload);
            	
            }else{
            	extUpload.fileUpload(request, response, cfg,multUpload);
            	
            }
		}
	}


	
	
}
