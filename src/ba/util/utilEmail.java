package ba.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.*;
//import org.apache.commons.mail.EmailAttachment;
//import org.apache.commons.mail.MultiPartEmail;

public class utilEmail {
	
	// !!
	// 关于发送各种邮件，请阅 http://commons.apache.org/proper/commons-email/userguide.html
	//
	
	public static void main(String[] args)
	{
		List<String> sendTos=new ArrayList<String>();
		sendTos.add("mqx0465@163.com");
		sendTos.add("254290929@qq.com");
		sendSimpleEmail(sendTos,"","","title","内容",null);
	}
	public static void sendSimpleEmail(Object sendTos,String title,String content,Object[] attachments)
	{
    try
    {     
      String smtp="";
      String emailAddressUser = "";
      String emailAddressPassword = "";
      String emailSender = "";
      
      Properties p = new Properties();
      p.load(utilEmail.class.getClassLoader().getResourceAsStream("email.properties"));
      smtp= p.getProperty("smtp");
      emailAddressUser=p.getProperty("emailAddressUser");
      emailAddressPassword=p.getProperty("emailAddressPassword");
      emailSender=p.getProperty("emailSender");
      MultiPartEmail email = new MultiPartEmail();
      email.setHostName(smtp);
      email.setCharset("utf-8");
      email.setAuthentication(emailAddressUser,emailAddressPassword);
      if (sendTos instanceof String[])
      {
        for(Object sendTo:(Object[])sendTos)
        {
          email.addTo((String)sendTo);
        }
      }
      else if (sendTos instanceof List)
      {
        for(String sendTo:(List<String>)sendTos)
        {
          email.addTo(sendTo);
        }
      }
      
      
      email.setFrom(emailSender, "人事提醒电子票管理员");
      email.setSubject(title);
      email.setMsg((null==content||"".equals(content))?" ":content);
      //email.setMsg("dddd");
      if(null!=attachments)
      {
        if (attachments instanceof File[])
        {
          for(Object file:attachments)
          {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(((File)file).getAbsolutePath());
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            email.attach(attachment);
          }
        }
        else if (attachments instanceof String[])
        {
          for(Object file:attachments)
          {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath((String)file);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            email.attach(attachment);
          }
        }
      }
      
      email.send();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void sendSimpleEmail(Object sendTos,Object cc,Object bcc,String title,String content,Object[] attachments)
  {
    try
    {     
      String smtp="";
      String emailAddressUser = "";
      String emailAddressPassword = "";
      String emailSender = "";
      
      Properties p = new Properties();
      p.load(utilEmail.class.getClassLoader().getResourceAsStream("email.properties"));
      smtp= p.getProperty("smtp");
      emailAddressUser=p.getProperty("emailAddressUser");
      emailAddressPassword=p.getProperty("emailAddressPassword");
      emailSender=p.getProperty("emailSender");
      MultiPartEmail email = new MultiPartEmail();
      email.setHostName(smtp);
      email.setCharset("utf-8");
      email.setAuthentication(emailAddressUser,emailAddressPassword);
      if (sendTos instanceof String[])
      {
        for(Object sendTo:(Object[])sendTos)
        {
          email.addTo((String)sendTo);
        }
      }
      else if (sendTos instanceof List)
      {
        for(String sendTo:(List<String>)sendTos)
        {
          email.addTo(sendTo);
        }
      }
      
      if (cc instanceof String[])
      {
        for(Object ccTo:(Object[])cc)
        {
          email.addCc((String)cc);
        }
      }
      else if (cc instanceof List)
      {
        for(String ccTo:(List<String>)cc)
        {
          email.addCc(ccTo);
        }
      }
      
      if (bcc instanceof String[])
      {
        for(Object bccTo:(Object[])bcc)
        {
          email.addBcc((String)bccTo);
        }
      }
      else if (bcc instanceof List)
      {
        for(String bccTo:(List<String>)bcc)
        {
          email.addBcc(bccTo);
        }
      }
      email.setFrom(emailSender, "人事系统电子票管理员");
      email.setSubject(title);
      email.setMsg((null==content||"".equals(content))?" ":content);
      //email.setMsg("dddd");
      if(null!=attachments)
      {
        if (attachments instanceof File[])
        {
          for(Object file:attachments)
          {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(((File)file).getAbsolutePath());
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            email.attach(attachment);
          }
        }
        else if (attachments instanceof String[])
        {
          for(Object file:attachments)
          {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath((String)file);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            email.attach(attachment);
          }
        }
      }
      
      email.send();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
