package ba.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***********************************************
 *	File Name: Download.java             
 *	Created by: Administrator      
 *	Checked in by:    
 *	Date: 2007-8-22            
 *	Revision:         
 *	Description:
 *	Amendment History
 *	Modified Date			Modified By			Change Description 
 *
 ***********************************************/

public class Download
{
    public static HttpServletResponse setFileToResponse(HttpServletRequest request, HttpServletResponse response,File file,String filename)
    	throws ServletException, IOException
    {
        if (file != null)
        {
            response.reset();
            response.setContentType("application/octet-stream;charset=UTF-8");  //utf-8");
            response.setCharacterEncoding("UTF-8");
            
            filename=(null==filename || "".equals(filename))?file.getName():filename;
            
            String respFileName;
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE")){
            	respFileName= toUtf8String(filename);  // IE
            	System.out.println("IE, name="+respFileName);
            } else if (null != agent && -1 != agent.indexOf("Mozilla")){
            	respFileName= new String(filename.getBytes("UTF-8"),"iso-8859-1");  // firefox
            	System.out.println("Mozilla, name="+respFileName);
            } else {
            	respFileName= toUtf8String(filename);  // IE
            	System.out.println("others, name="+respFileName);
            }

            response.addHeader("Content-Disposition", "attachment; filename="+(respFileName));
                    //+ toUtf8String(filename));
            response.setContentLength((int) file.length());
            OutputStream os;

            try
            {
                os = response.getOutputStream();
                FileInputStream input = new FileInputStream(file);
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = input.read(b)) != -1)
                {
                    os.write(b, 0, len);
                }
                input.close();
                os.close();
                
                //
                System.out.println("file output end.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return response;
    }
    public static String toUtf8String(String s)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255)
            {
                sb.append(c);
            }
            else
            {
                byte[] b;
                try
                {
                    b = Character.toString(c).getBytes("utf-8");
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++)
                {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }

        return sb.toString();
    }
}
