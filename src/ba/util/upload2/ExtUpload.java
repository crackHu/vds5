package ba.util.upload2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.jdbc.core.JdbcTemplate;

import ba.base.BoBase;


public class ExtUpload extends BoBase 
{
	public JdbcTemplate jdbcTemplate;
	//public  FileMgr fileMgr;
	private  final long serialVersionUID = 1L;
	public  final String UPLOAD_DIR = "D:\\Filefolder\\root";
	public  final String PATH_TYPE = "0";//默认是相对路径
	public  final long MAXSIZE = 1024 * 1024 * 1024;
    public  final String DIRECTID = "upDnFiles";  // "ciAll/upload";

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 从文件路径中取出文件�?
	 */
	public  String getFileName(String filePath)
	{
		int pos = filePath.lastIndexOf(File.separator);
		if (pos > 0)
		{
			return filePath.substring(pos + 1);
		}
		else
		{
			return filePath;
		}
	}

	public  void setUploadCfg(UploadCfg cfg)
	{
		cfg.uploadDir = (null == cfg.uploadDir ? UPLOAD_DIR : cfg.uploadDir);
		cfg.pathType = (null == cfg.pathType ? PATH_TYPE : cfg.pathType);
		cfg.maxsize = (null == cfg.maxsize ? MAXSIZE : cfg.maxsize);
        cfg.directId = (null == cfg.directId ? DIRECTID : cfg.directId);
	}

	/**
	 * 处理文件上传：【入口函数，2012-06】
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public  void fileUpload(HttpServletRequest request,
			HttpServletResponse response, UploadCfg cfg,String multUpload)
			throws ServletException, IOException
	{
		setUploadCfg(cfg = (null == cfg ? new UploadCfg() : cfg));
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		if (request.getContentLength() > cfg.maxsize)
		{
			response.getWriter().write(
					"{'success':true,'errorCode':'1'}".replaceAll("'", "\""));
			return;
		}
//		String fileDiskPath = cfg.pathType.equals(PATH_TYPE)?request.getRealPath(cfg.uploadDir):cfg.uploadDir;
		
		// 以下是特殊处理，解决多个tomcat容器或上传目录非本tomcat的情况
		// 实现策略：在 prj.properties 文件定义一个属性 upDnFile_main
		// 例如：upDnFile_main = \\\\172.16.47.94\\ext3\\shared
		// 使得上传文件的根目录在 upDnFile_main\【cfg.directId】 下。一般 cfg.directId = upDnFiles
		//
		// 要求js端，调用参数加上 pathflag=qy
		//
		String otherPathFlag = request.getParameter("pathflag");
		otherPathFlag = (null==otherPathFlag?"def":otherPathFlag);
		String fileDiskPath;
		
		// debug
		//otherPathFlag = "qy";
		if ("qy".equalsIgnoreCase(otherPathFlag)){
			Properties p = new Properties();
			p.load(this.getClass().getClassLoader().getResourceAsStream("prj.properties"));
			fileDiskPath = p.getProperty("upDnFile_main");
			
			fileDiskPath = fileDiskPath+File.separator+cfg.directId;
		} else {
			fileDiskPath = cfg.uploadDir+File.separator+cfg.directId;
		}
        
		File file = new File(fileDiskPath);
		if (!file.isDirectory())
			file.mkdirs();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10240000);// 设置内存缓冲区，超过后写入临时文�?
		factory.setRepository(new File(fileDiskPath));// 设置临时文件存储位置
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(cfg.maxsize);// 设置整个request的最大�??

        List<String> uploadedFilePathArray=new ArrayList<String>(); 
        List<String> uploadedFileSize=new ArrayList<String>(); 
        List<String> uploadedFileFullPathArray=new ArrayList<String>(); 
        List<String> uploadedFileName=new ArrayList<String>(); 
        List<String> nameList=new ArrayList<String>(); 
         
        try
        {
            List<FileItem> items = upload.parseRequest(request);

            if("import".equalsIgnoreCase(request.getParameter("type")))
            {
                if(items.size()>0)
                {
                    FileItem item=items.get(0);
                    String fileName = getFileName(item.getName());
                    File importFile = new File(fileDiskPath + File.separator
                            + fileName);
                    item.write(importFile);
                    uploadedFilePathArray.add(importFile.getAbsolutePath());
                }
            }
            else if("multUpload".equalsIgnoreCase(multUpload)){
                for (FileItem item : items)// 处理文件上传
                {
                    if (!item.isFormField() && item.getName().length() > 0)
                    {
                    	
                        String size = getSizeToKb(item.getSize());
                        String fileName = getFileName(item.getName());
                        String fullPath = fileDiskPath + File.separator + fileName;
                        File uploadedFile = new File(fullPath);
//                        /////////////////////////加密算法
//                        byte[] bytes = MySecurity.getBytesFromFile(uploadedFile);
//                        //对bytes进行加密
//                        Streamenc streamenc = new Streamenc();
//                        byte [] fileBytes = streamenc.encrypt(bytes);
//                        //end
//                        uploadedFile = MySecurity.writeBytesToFile(fileBytes,fileDiskPath + File.separator+ fileName);
//                        //////////////////////////加密算法
                        item.write(uploadedFile);
                       // FileBean fileBean = new FileBean();
//                        fileBean.setFilename(fileName);
//                        fileBean.setFullPath(fullPath);
//                        fileBean.setSize(size);
                       // fileMgr.createFile(cfg.getDirectId(), fileBean, cfg.getUserId());
                    }
                }
            
            	
            }else
            {
                for (FileItem item : items)// 处理文件上传。【执行，2012-06】
                {
                    if (!item.isFormField() && item.getName().length() > 0)
                    {
                        String size = getSizeToKb(item.getSize());
                        String fileName = getFileName(item.getName());  // client 上传的文件名字，包括后缀，不包括路径
//                        File uploadedFile = new File(fileDiskPath + File.separator
//                                + fileName);
                        ///////////////////////////mqx
                        //String name = fileName.substring(0,fileName.lastIndexOf("."));
                        String fileExt = fileName.substring(fileName.lastIndexOf("."));  // 通过最后'.'获得文件后缀。注意后缀第一个字符包括'.'
                        //New file path name
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
                        Date nowDate = new Date();
                        String timeStamp = formatter.format(nowDate);
                        UUID uuid = UUID.randomUUID();
                        //String filePath = fileDiskPath + File.separator+ timeStamp+uuid.toString() + fileExt;
                        String relPath= timeStamp+"_"+uuid.toString();
                        System.out.println("upload path="+relPath);
                        
                        File file1 = new File(fileDiskPath + File.separator + relPath);
                        if (!file1.isDirectory())
                			file1.mkdirs();
                        
                        String filePath = fileDiskPath + File.separator + relPath + File.separator + fileName;
                        File uploadedFile = new File(filePath);  // 准备写入server的文件的全称地址
                        String name = timeStamp;//fileName.substring(0,fileName.lastIndexOf("."));
//                        /////////////////////////加密算法
//                        byte[] bytes = MySecurity.getBytesFromFile(uploadedFile);
//                        //对bytes进行加密
//                        Streamenc streamenc = new Streamenc();
//                        byte [] fileBytes = streamenc.encrypt(bytes);
//                        //end
//                        uploadedFile = MySecurity.writeBytesToFile(fileBytes,fileDiskPath + File.separator+ fileName);
//                        //////////////////////////加密算法
                        item.write(uploadedFile);
                        //nameList.add(name);
                        nameList.add(relPath+File.separator+fileName);  // cnName: 相对路径+文件名
                        //uploadedFilePathArray.add(uploadedFile.getAbsolutePath());
                        uploadedFilePathArray.add(relPath+File.separator);  // path: 含'\'的相对路径
                        uploadedFileName.add(fileName);  // name: 文件名
                        uploadedFileSize.add(size);
                        uploadedFileFullPathArray.add(uploadedFile.getAbsolutePath());
                    }
                }
            }
        }
        catch (FileUploadException e)
        {
            e.printStackTrace();
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
        Map<String,Object> rtMap=new HashMap<String,Object>();
        rtMap.put("path", uploadedFilePathArray.toArray());
        rtMap.put("size", uploadedFileSize.toArray());
        rtMap.put("name", uploadedFileName.toArray());
        rtMap.put("fullPath", uploadedFileFullPathArray.toArray());
        rtMap.put("cnName", nameList.toArray());
        rtMap.put("success", true);
        JSONObject jso=JSONObject.fromObject(rtMap);
//        System.out.println(jso);
        String jsoString = jso.toString();
//        System.out.println(jsoString);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsoString);
	}
	
	/**
	 * 处理文件上传
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public  void fileUpload_am(HttpServletRequest request,
			HttpServletResponse response, UploadCfg cfg,String multUpload)
			throws ServletException, IOException
	{
		setUploadCfg(cfg = (null == cfg ? new UploadCfg() : cfg));
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		if (request.getContentLength() > cfg.maxsize)
		{
			response.getWriter().write(
					"{'success':true,'errorCode':'1'}".replaceAll("'", "\""));
			return;
		}
//		String fileDiskPath = cfg.pathType.equals(PATH_TYPE)?request.getRealPath(cfg.uploadDir):cfg.uploadDir;
        String fileDiskPath = cfg.uploadDir+File.separator+cfg.directId;
		File file = new File(fileDiskPath);
		if (!file.isDirectory())
			file.mkdirs();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10240000);// 设置内存缓冲区，超过后写入临时文�?
		factory.setRepository(new File(fileDiskPath));// 设置临时文件存储位置
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(cfg.maxsize);// 设置整个request的最大�??

        List<String> uploadedFilePathArray=new ArrayList<String>(); 
        List<String> fileExthArray=new ArrayList<String>(); 
        List<String> uploadedFileSize=new ArrayList<String>(); 
        List<String> uploadedFileFullPathArray=new ArrayList<String>(); 
        List<String> uploadedFileName=new ArrayList<String>(); 
        List<String> nameList=new ArrayList<String>(); 
         
        try
        {
            List<FileItem> items = upload.parseRequest(request);

            if("import".equalsIgnoreCase(request.getParameter("type")))
            {
                if(items.size()>0)
                {
                    FileItem item=items.get(0);
                    String fileName = getFileName(item.getName());
                    File importFile = new File(fileDiskPath + File.separator
                            + fileName);
                    item.write(importFile);
                    uploadedFilePathArray.add(importFile.getAbsolutePath());
                }
            }
            else if("multUpload".equalsIgnoreCase(multUpload)){
                for (FileItem item : items)// 处理文件上传
                {
                    if (!item.isFormField() && item.getName().length() > 0)
                    {
                    	
                        String size = getSizeToKb(item.getSize());
                        String fileName = getFileName(item.getName());
                        String fullPath = fileDiskPath + File.separator + fileName;
                        File uploadedFile = new File(fullPath);
                        item.write(uploadedFile);
                    }
                }
            
            	
            }else
            {
                for (FileItem item : items)// 处理文件上传
                {
                    if (!item.isFormField() && item.getName().length() > 0)
                    {
                        String size = getSizeToKb(item.getSize());
                        String fileName = getFileName(item.getName());
                         String fileExt = fileName.substring(fileName.lastIndexOf("."));
                          //New file path name
                          UUID uuid = UUID.randomUUID();
                          String name = uuid.toString();
                         // System.out.println(timeStamp+uuid.toString());
                          String filePath = fileDiskPath + File.separator+ name + fileExt;
                          File uploadedFile = new File(filePath);
                        item.write(uploadedFile);
                        nameList.add(fileName);
                        uploadedFilePathArray.add(uploadedFile.getAbsolutePath());
                        uploadedFileName.add(name+fileExt);
                        fileExthArray.add(fileExt);
                        uploadedFileSize.add(size);
                        uploadedFileFullPathArray.add(uploadedFile.getAbsolutePath());
                    }
                }
            }
        }
        catch (FileUploadException e)
        {
            e.printStackTrace();
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
        Map<String,Object> rtMap=new HashMap<String,Object>();
        rtMap.put("path", uploadedFilePathArray.toArray());
        rtMap.put("size", uploadedFileSize.toArray());
        rtMap.put("name", uploadedFileName.toArray());
        rtMap.put("fullPath", uploadedFileFullPathArray.toArray());
        rtMap.put("cnName", nameList.toArray());
        rtMap.put("fileExt", fileExthArray.toArray());
        rtMap.put("success", true);
        JSONObject jso=JSONObject.fromObject(rtMap);
//        System.out.println(jso);
        String jsoString = jso.toString();
//        System.out.println(jsoString);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsoString);
	}
	
	/**
	 * 处理文件上传
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public  void fileUpload_emailAtt(HttpServletRequest request,
			HttpServletResponse response, UploadCfg cfg,String multUpload)
			throws ServletException, IOException
	{
		setUploadCfg(cfg = (null == cfg ? new UploadCfg() : cfg));
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		if (request.getContentLength() > cfg.maxsize)
		{
			response.getWriter().write(
					"{'success':true,'errorCode':'1'}".replaceAll("'", "\""));
			return;
		}
//		String fileDiskPath = cfg.pathType.equals(PATH_TYPE)?request.getRealPath(cfg.uploadDir):cfg.uploadDir;
        String fileDiskPath = cfg.uploadDir+File.separator+cfg.directId;
		File file = new File(fileDiskPath);
		if (!file.isDirectory())
			file.mkdirs();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10240000);// 设置内存缓冲区，超过后写入临时文�?
		factory.setRepository(new File(fileDiskPath));// 设置临时文件存储位置
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(cfg.maxsize);// 设置整个request的最大�??

        List<String> uploadedFilePathArray=new ArrayList<String>(); 
        List<String> fileExthArray=new ArrayList<String>(); 
        List<String> uploadedFileSize=new ArrayList<String>(); 
        List<String> uploadedFileFullPathArray=new ArrayList<String>(); 
        List<String> uploadedFileName=new ArrayList<String>(); 
        List<String> nameList=new ArrayList<String>(); 
         
        try
        {
            List<FileItem> items = upload.parseRequest(request);

            if("import".equalsIgnoreCase(request.getParameter("type")))
            {
                if(items.size()>0)
                {
                    FileItem item=items.get(0);
                    String fileName = getFileName(item.getName());
                    File importFile = new File(fileDiskPath + File.separator
                            + fileName);
                    item.write(importFile);
                    uploadedFilePathArray.add(importFile.getAbsolutePath());
                }
            }
            else if("multUpload".equalsIgnoreCase(multUpload)){
                for (FileItem item : items)// 处理文件上传
                {
                    if (!item.isFormField() && item.getName().length() > 0)
                    {
                    	
                        String size = getSizeToKb(item.getSize());
                        String fileName = getFileName(item.getName());
                        String fullPath = fileDiskPath + File.separator + fileName;
                        File uploadedFile = new File(fullPath);
                        item.write(uploadedFile);
                    }
                }
            
            	
            }else
            {
                for (FileItem item : items)// 处理文件上传
                {
                    if (!item.isFormField() && item.getName().length() > 0)
                    {
                        String size = getSizeToKb(item.getSize());
                        String fileName = getFileName(item.getName());
                         String fileExt = fileName.substring(fileName.lastIndexOf("."));
                         String name = fileName.substring(0,fileName.lastIndexOf("."));
                          //New file path name
//                          UUID uuid = UUID.randomUUID();
//                          String name = uuid.toString();
                         // System.out.println(timeStamp+uuid.toString());
                         String newName = FileOperate.checkFileName(fileName, fileDiskPath + File.separator);
                        String filePath = fileDiskPath + File.separator+ newName;
                        File uploadedFile = new File(filePath);
                        item.write(uploadedFile);
                        nameList.add(newName);
                        uploadedFilePathArray.add(uploadedFile.getAbsolutePath());
                        uploadedFileName.add(newName);
                        fileExthArray.add(fileExt);
                        uploadedFileSize.add(size);
                        uploadedFileFullPathArray.add(uploadedFile.getAbsolutePath());
                    }
                }
            }
        }
        catch (FileUploadException e)
        {
            e.printStackTrace();
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
        Map<String,Object> rtMap=new HashMap<String,Object>();
        rtMap.put("path", uploadedFilePathArray.toArray());
        rtMap.put("size", uploadedFileSize.toArray());
        rtMap.put("name", uploadedFileName.toArray());
        rtMap.put("fullPath", uploadedFileFullPathArray.toArray());
        rtMap.put("cnName", nameList.toArray());
        rtMap.put("fileExt", fileExthArray.toArray());
        rtMap.put("success", true);
        JSONObject jso=JSONObject.fromObject(rtMap);
//        System.out.println(jso);
        String jsoString = jso.toString();
//        System.out.println(jsoString);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsoString);
	}


//	public  void setFileMgr(FileMgr fileMgr) {
//		this.fileMgr = fileMgr;
//	}
	
	public String getSizeToKb(Long size){
		int intSize = size.intValue();
		intSize/=1024;
		return Integer.toString(intSize);
	}


}
