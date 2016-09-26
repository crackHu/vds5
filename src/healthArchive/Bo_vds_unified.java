package healthArchive;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ba.base.BoBase;
import medicalPHR.dto.WebResponse;
import net.sf.json.JSONObject;

public class Bo_vds_unified extends BoBase {

	public JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * {"din":{"where":"1=1","page":1,"rows":10}} 获取个人档案列表
	 */
	@SuppressWarnings("unchecked")
	public List getPdData(String dataIn) {

		List resultsMap = new ArrayList();
		resultsMap = (List) jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				String proc = "{call test()}";// 调用存储过程的sql语句
				CallableStatement cs = con.prepareCall(proc);// 调用存储过程
				// cs.setString("input", dataIn);// 设置输入参数的值
				// cs.registerOutParameter("output", Types.VARCHAR);// 注册输出参数的类型
				return cs;
			}
		}, new CallableStatementCallback<Object>() {
			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				List resultsMap = new ArrayList();
				ResultSet rs = (ResultSet) cs.executeQuery();// 获取游标一行的值
				while (rs.next()) {// 转换每行的返回值到Map中
					Map rowMap = new HashMap();
					rowMap.put("grbh", rs.getString("grbh"));
					rowMap.put("grda_xm", rs.getString("grda_xm"));
					resultsMap.add(rowMap);
				}
				rs.close();
				return resultsMap;
				// return cs.getString("output");// 在这里返回输出参数的值
			}
		});

		return resultsMap;
	}

	/**
	 * 保存檔案信息
	 * 
	 * @request http://localhost:8080/vds5s1/ba/boCallMethodPC.jsp?data=
	 * @param jDataIn:{"din":{"data":"1=1"},"boName":"saveArchiveData","funcName":"boVdsUnified"}
	 * @return response
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONObject saveArchiveData(JSONObject jDataIn) throws Exception {

		String tableName = "phr_grda_jbzl";
		JSONObject jsonObject = new JSONObject();
		WebResponse webResponse = null;
		try {
			String sql = insertData(tableName, jDataIn);
			System.out.println(sql);
			jdbcTemplate.execute(sql);
			webResponse = new WebResponse(1, "ok");
		} catch (Exception e) {
			webResponse = new WebResponse(1, e.getMessage());
			e.printStackTrace();
			throw new Exception(e);
		}
		return jsonObject.fromObject(webResponse);
	}

	public String insertData(String tableName, JSONObject jDataIn) {
		String insertStr = null;
		try {
			// 组成insert sql
			StringBuffer query = new StringBuffer("insert into " + tableName + " (");
			StringBuffer values = new StringBuffer();
			Iterator it = jDataIn.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry m = (Map.Entry) it.next();
				String columName = (String) m.getKey();
				query.append(columName);
				query.append(",");

				String columValue = (String) m.getValue();

				values.append(columValue);
				values.append(",");
			}

			query.deleteCharAt(query.length() - 1);
			query.append(") values(");
			query.append(values);
			query.deleteCharAt(query.length() - 1);
			query.append(")");
			insertStr = query.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertStr;
	}

	public String updateData(String tablename, JSONObject jDataIn, String id) {
		// update vds_table2 set strCol='mqx',intCol=1,dataCol='2009-09-18
		// 14:40:31' where strCol='mqx'
		StringBuffer query = new StringBuffer("update " + tablename + " set ");
		Iterator it = jDataIn.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			// m.getKey(),m.getValue()
			query.append(m.getKey());
			query.append("=");

			String columValue = (String) m.getValue();

			query.append(columValue);
			query.append(",");
		}
		query.deleteCharAt(query.length() - 1);
		query.append(" where id='");
		query.append(id);
		query.append("'");
		String updateStr = query.toString();
		return updateStr;
	}

	public static void main(String[] args) {

		WebResponse status = new WebResponse(-99, "adfadsf");
		JSONObject jsonObject = new JSONObject();
		jsonObject = jsonObject.fromObject(status);
		System.out.println(jsonObject);
	}
}
