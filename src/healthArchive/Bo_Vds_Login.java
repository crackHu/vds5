package healthArchive;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ba.base.BoBase;
import bsh.StringUtil;

/**
 * 登陆获取数据
 * 
 * @author Asan
 * 
 */
public class Bo_Vds_Login extends BoBase {
	public JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate secondJdbcTemplate;

	public void setSecondJdbcTemplate(JdbcTemplate secondJdbcTemplate) {
		this.secondJdbcTemplate = secondJdbcTemplate;
	}
	
	//http://localhost:8080/vds5s1/ba/boCallMethodPC.jsp?data={%22sql%22:%22%22,%22boName%22:%22boVdsLogin%22,%22funcName%22:%22login%22,%22procedure%22:%22test%22,%22pageNo%22:1,%22pageSize%22:20}
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONObject login(JSONObject jDataIn) throws Exception {
		String procedure = null;
		String pageNo = null;
		String pageSize = null;
		
		JSONObject jDataOut = new JSONObject();
		try {
			int rst = -1000;
			String rstMsg = "";
			String sql = null;
			
			procedure = jDataIn.getString("procedure");
			if (StringUtils.isNotBlank(procedure)) {
				pageNo = jDataIn.getString("pageNo");
				pageSize = jDataIn.getString("pageSize");
			} else {
				throw new Exception("Procedure can't be blank");
			}
			sql = String.format("call %s (%s, %s)", procedure,pageNo,pageSize);
			System.out.println("sql:" + sql);
			
			jDataOut = jdbcTemplate.execute(sql, new CallableStatementCallback<JSONObject>() {
				@Override
				public JSONObject doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
					JSONArray jDataOut = new JSONArray();
					ResultSet rs = cs.executeQuery();
					while (rs.next()) {
						JSONObject newjson = new JSONObject();
						int totalColumn = rs.getMetaData().getColumnCount();
						for (int i = 1; i <= totalColumn; i++) {
							String tmp = rs.getMetaData().getColumnLabel(i);
							String type = rs.getMetaData().getColumnTypeName(i);
							if (StringUtils.equalsIgnoreCase("datetime", type)) {
								Date date = rs.getDate(tmp);
								if (null == date) {
									newjson.put(tmp, "");// 格式转换
								} else {
									SimpleDateFormat df = new SimpleDateFormat(
											"yyyy-MM-dd");
									newjson.put(tmp, df.format(date));
								}
							} else {
								newjson.put(tmp, rs.getString(tmp));
							}

						}
						jDataOut.add(newjson);
					}
					rs.close();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("data", jDataOut);
					jsonObject.put("total", jDataOut.size());
					jsonObject.put("timestamp", new Date().getTime());
					return jsonObject;
				}

			});

			rst = 1; // 正确
			rstMsg = "ok";

			// 返回 >=0 OK
			jDataOut.put("_c", rst);
			jDataOut.put("_m", rstMsg);
		} catch (Exception e) {
			// 返回 <0 error
			jDataOut.put("_c", -99);
			jDataOut.put("_m", "执行失败，err=" + e.getMessage());
			// conn.rollback();
		}
		return jDataOut;
	}
	
	
}
