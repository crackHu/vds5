package healthArchive;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import net.sf.json.JSONObject;

public class BaseQuery {
	public SqlRowSet rs = null;
	/**
	 * 查询表单公共方法
	 * @return
	 */
	public JSONObject query(JdbcTemplate jdbc,String sql){
		
		JSONObject json = new JSONObject();
		
		rs = jdbc.queryForRowSet(sql);
		
		if (rs.next()) {
			int totalColumn = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumn; i++) {
				String tmp = rs.getMetaData().getColumnLabel(i);
				String type = rs.getMetaData().getColumnTypeName(i);
				if ("DATETIME".equals(type)) {
					Date date = rs.getDate(tmp);
					if (null == date) {
						json.put(tmp, "");
					}else {
						json.put(tmp, DateUtil.getStrYMDHMSByDate(date));
					}
				}
				json.put(tmp, rs.getString(tmp));
			}
		}
		return json;
	}
	
	/**
	 * 查询列表公共方法
	 * @return
	 */
	public List<Object> queryList(JdbcTemplate jdbc,String sql,DateFormat sdfd){

		List<Object> list = new ArrayList<Object>();
		rs = jdbc.queryForRowSet(sql);
		JSONObject json = null;
		while (rs.next()) {
			json = new JSONObject();
			int totalColumn = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumn; i++) {
				String tmp = rs.getMetaData().getColumnLabel(i);
				String type = rs.getMetaData().getColumnTypeName(i);
				if ("DATETIME".equals(type)) {
					Date date = rs.getDate(tmp);
					if (null == date) {
						json.put(tmp, "");
					}else {
						json.put(tmp, sdfd.format(date));
					}
				}else{
					json.put(tmp, rs.getString(tmp));
				}
			}
			list.add(json);
		}
		return list;
	}

	/**
	 * 重写查询列表公共方法，列表里面包含列表
	 * @return
	 */
	public List<Object> queryList(JdbcTemplate jdbc,String sql,String listSQL,String listColumn){
		List<Object> list = new ArrayList<Object>();
		rs = jdbc.queryForRowSet(sql);
		JSONObject json = null;
		List<Object> columnList = null;
		while (rs.next()) {
			json = new JSONObject();
			columnList = new ArrayList<Object>();
			int totalColumn = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumn; i++) {
				String tmp = rs.getMetaData().getColumnLabel(i);
				String type = rs.getMetaData().getColumnTypeName(i);
				if ("DATETIME".equals(type)) {
					Date date = rs.getDate(tmp);
					if (null == date) {
						json.put(tmp, "");
					}else {
						json.put(tmp, DateUtil.getStrYMDHMSByDate(date));
					}
				}
				json.put(tmp, rs.getString(tmp));
			}
			columnList = queryList(jdbc,listSQL,null);
			json.put(listColumn,columnList);

			list.add(json);
		}
		return list;
	}

}
