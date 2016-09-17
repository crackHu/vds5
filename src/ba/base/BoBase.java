package ba.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

public class BoBase {
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONObject callMethod(String method, JSONObject jDataIn) {

		Class cls = null;
		try {
			cls = Class.forName(this.getClass().getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class partypes[] = new Class[1];
		partypes[0] = JSONObject.class;
		Method meth = null;
		try {
			meth = cls.getMethod(method, partypes);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object returnValue = null;
		try {
			returnValue = meth.invoke(this, jDataIn);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			JSONObject jDataOut = new JSONObject();
			String msg = e.getMessage();
			jDataOut.put("resultCode", -99);
			jDataOut.put("resultMsg", msg);
			e.printStackTrace();
			return jDataOut;
		}
		JSONObject jDataOut = (JSONObject) returnValue;

		return jDataOut;
	}
}
