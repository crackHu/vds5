package ba.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class BaseUtil
{
	/**
	 * 判斷對象是否為空,或者字符串是否為空和""
	 * @param object 普通對象或者字符串
	 * @return 若為空,則返回true
	 */
	public static Boolean isNull(Object object)
    {
    	if(object instanceof String) 
		{
    		if(null==object || "".equals((String)object)) return true;
		}
    	else 
		{
    		if(null==object) return true;
		}
    	return false;
    }
	/**
	 * 截取List的長度
	 * @param list
	 * @param start
	 * @param end
	 * @return
	 */
	public static List subList(List list, int start, int end)
	{
		if (null == list) return new ArrayList();
		if (list.size() > end - 1) 
			return list.subList(start, end);
		return list;
	}
	public static String subStr(String str, int start, int end)
	{
		if (isNull(str)) return "";
		if (str.length() > end - 1) return str.substring(start, end)+"...";
		return str;
	}
	
	public static void setProp(Object obj,String propertyName,String contents,int x,int y) throws Exception
	{
		String typeName="";
		try
        {
            Field field = obj.getClass().getField(propertyName);
            if(null==field)
            {
            	System.out.println(propertyName+"-------");
            	return;
            }
            typeName = field.getType().getSimpleName();
            if(typeName.equals("String") || typeName.equals("string"))
            	field.set(obj, contents);
            else if(typeName.equals("Integer") || typeName.equals("int"))
                field.set(obj, Integer.valueOf(contents));
            else if(typeName.equals("Float") || typeName.equals("float"))
            	field.set(obj, Float.valueOf(contents));
            else if(typeName.equals("Double") || typeName.equals("double"))
            	field.set(obj, Double.valueOf(contents));
            else if(typeName.equals("Boolean") || typeName.equals("boolean"))
            	field.set(obj, Boolean.valueOf(contents));
            else if("Timestamp".equals(typeName) || "Date".equals(typeName))
            {
            	SimpleDateFormat formatter = null;
        		if(contents.length()==10) formatter=new SimpleDateFormat("yyyy-MM-dd");
        		else if(contents.length()==16) formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        		else if(contents.length()==19) formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dt = formatter.parse(contents);
                field.set(obj, dt);
            }
        }
        catch(Exception e)
        {
            throw new Exception("第 "+(y+1)+" 行,第 "+(x+1)+" 列的數據:\t"+contents+"\t不能轉換為"+typeName+"類型");
        }
	}
	
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("^[-+]?[0-9]+$"); 
	    return pattern.matcher(str).matches();    
	 } 
	
	public static void main(String [] args){
    System.out.println(isNumeric("229929929"));
	}
}
