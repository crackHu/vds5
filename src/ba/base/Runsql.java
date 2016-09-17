package ba.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Runsql {
	private static int _count = 0;

	public void runsql() {
		_count++;
		System.out.println("******************************");
		System.out.println("runsql created, count=" + _count);
	}

	public JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> execSql(Map<String, Object> dataIn)
			throws Exception {
		Map<String, Object> dataOut = new HashMap<String, Object>();

		dataOut.put("resultCode", 10000);
		dataOut.put("resultMsg", "成功执行传入sql语句");

		int _page = Integer.parseInt(dataIn.get("_page").toString());
		int _rows = Integer.parseInt(dataIn.get("_rows").toString());
		int _offset = (_page-1)*_rows;

		//
		JSONObject jo = new JSONObject();

		String sql;
		int count=0;
		sql = "select count(*) from test_store";
		count = jdbcTemplate.queryForInt(sql);
		
		jo.put("total", count);

		sql = "select * from test_store order by id limit "+_offset+","+_rows;

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		SqlRowSetMetaData rsm = rs.getMetaData();

		//
		JSONArray ja = new JSONArray(); // 记录列表

		while (rs.next()) {
			JSONObject jr = new JSONObject(); // 一条记录
			jr.put("itemid", rs.getString("id"));
			jr.put("productid", rs.getString("shopType"));
			jr.put("unitcost", 10.00);
			jr.put("status", rsm.getColumnNames());
			jr.put("listprice", "36.50");
			jr.put("attr1", "Large");
			//jr.put("itemid", "EST-1");

			ja.add(jr);
			// ... loop for jr
			//System.out.print(rs.getString("loginName"));
		}

		jo.put("rows", ja);

		dataOut.put("data", jo.toString());

		return dataOut;

	}
}

/*

*/
