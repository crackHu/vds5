package healthArchive;

import java.util.Iterator;
import java.util.Map;

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
	 * 保存檔案信息
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
			StringBuffer query = new StringBuffer("insert into " + tableName
					+ " (");
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


