package ba.util;

import java.util.*;

import net.sf.json.JSONObject;

public class SqlFactory {
	private Visitor visitor = new Visitor();

	public SqlFactory() {
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public String insertData(String tablename, JSONObject jFields) {
		StringBuffer query = new StringBuffer("insert into " + tablename + " (");
		StringBuffer values = new StringBuffer();
		Iterator it = jFields.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			String columName = (String) m.getKey();
			query.append(columName);
			query.append(",");

			//Wrapper wrapper = (Wrapper) m.getValue();
			//String tmp = wrapper.action(getVisitor());
			String columValue = (String) m.getValue();

			values.append(columValue);
			values.append(",");
		}
		query.deleteCharAt(query.length() - 1);
		query.append(") values(");
		query.append(values);
		query.deleteCharAt(query.length() - 1);
		query.append(")");
		String insertStr = query.toString();
		return insertStr;
	}

	public String updateData(String tablename, Map map, String id) {
		// update vds_table2 set strCol='mqx',intCol=1,dataCol='2009-09-18
		// 14:40:31' where strCol='mqx'
		StringBuffer query = new StringBuffer("update " + tablename + " set ");
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			// m.getKey(),m.getValue()
			query.append(m.getKey());
			query.append("=");
			Wrapper wrapper = (Wrapper) m.getValue();
			String tmp = wrapper.action(getVisitor());
			query.append(tmp);
			query.append(",");
		}
		query.deleteCharAt(query.length() - 1);
		query.append(" where id='");
		query.append(id);
		query.append("'");
		String updateStr = query.toString();
		return updateStr;
	}

	public String deleteData(String tablename, String id) {
		StringBuffer query = new StringBuffer("delete from " + tablename
				+ " where id='");
		query.append(id);
		query.append("'");
		String delStr = query.toString();
		return delStr;
	}

	// start mqx 2011 01-10 增加对多个字段排序功能 增加一个方法 处理
	public String queryData(String tablename, Map map, String where, int start,
			int size, String sortInfoMoreOrder, String sort, String dir) {
		String orderBy = "";
		if (sort != null && !"".equals(sort)) {
			orderBy = " order by " + sort + " " + dir + " ,id";
		} else {
			if (sortInfoMoreOrder == null || "".equals(sortInfoMoreOrder)) {
				orderBy = " order by id ";
			} else {
				if (sortInfoMoreOrder.indexOf("order by") >= 0) {
					orderBy = sortInfoMoreOrder;
				} else {
					orderBy = " order by " + sortInfoMoreOrder + " ";
				}
			}
		}

		// select * from ( select TOP 10 * FROM ( SELECT TOP 20 * from
		// v_mg_image ORDER BY id ASC ) as aSysTable ORDER BY id DESC ) as
		// bSysTable ORDER BY id ASC
		StringBuffer whereCon = new StringBuffer("");
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			Wrapper wrapper = (Wrapper) m.getValue();
			if (wrapper instanceof WrapperDate) {
				whereCon.append(m.getKey());
				whereCon.append(" <= ");
				String tmp = wrapper.action(getVisitor());
				whereCon.append(tmp);
				whereCon.append(" and ");
			} else if (wrapper instanceof WrapperInteger) {
				whereCon.append(m.getKey());
				whereCon.append(" = ");
				String tmp = wrapper.action(getVisitor());
				whereCon.append(tmp);
				whereCon.append(" and ");
			} else if (wrapper instanceof WrapperString) {
				whereCon.append(m.getKey());
				whereCon.append(" like ");
				String tmp = wrapper.action(getVisitor());
				String tp = tmp.substring(1, tmp.length() - 1);
				whereCon.append("'%" + tp + "%'");
				whereCon.append(" and ");
			} else {
				whereCon.append(m.getKey());
				whereCon.append(" like ");
				String tmp = wrapper.action(getVisitor());
				String tp = tmp.substring(1, tmp.length() - 1);
				whereCon.append("'%" + tp + "%'");
				whereCon.append(" and ");
			}

		}
		if (whereCon.length() >= 5) {
			whereCon.delete(whereCon.length() - 5, whereCon.length());
		}

		String tempWhere = whereCon.toString();
		StringBuffer query = new StringBuffer("select top " + (size + start)
				+ " * from (select top " + (size + start) + " * from "
				+ tablename + " where 1=1  ");
		if (null != tempWhere && !"".equals(tempWhere)) {
			tempWhere = " and " + tempWhere;
		}
		if (null != where && !"".equals(where)) {
			tempWhere += " and (" + where + ") ";
		}
		String queryStr = "";
		queryStr = "select * from (select row_number() over(" + orderBy
				+ ") as row_num ,* from " + tablename + " where 1=1  "
				+ tempWhere + ") a where a.row_num between " + (1 + start)
				+ " and " + (size + start) + "";
		queryStr += orderBy;
		System.out.println("queryStr:" + queryStr);
		return queryStr;
	}

	// 2011-03-11 WQINGQUAN 添加
	public String queryData_01(String tablename, Map map, String where,
			int start, int size, String sortInfoMoreOrder, String sort,
			String dir) {

		String orderBy = "order by total_text";

		// select * from ( select TOP 10 * FROM ( SELECT TOP 20 * from
		// v_mg_image ORDER BY id ASC ) as aSysTable ORDER BY id DESC ) as
		// bSysTable ORDER BY id ASC
		StringBuffer whereCon = new StringBuffer("");
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			Wrapper wrapper = (Wrapper) m.getValue();
			if (wrapper instanceof WrapperDate) {
				whereCon.append(m.getKey());
				whereCon.append(" <= ");
				String tmp = wrapper.action(getVisitor());
				whereCon.append(tmp);
				whereCon.append(" and ");
			} else if (wrapper instanceof WrapperInteger) {
				whereCon.append(m.getKey());
				whereCon.append(" = ");
				String tmp = wrapper.action(getVisitor());
				whereCon.append(tmp);
				whereCon.append(" and ");
			} else if (wrapper instanceof WrapperString) {
				whereCon.append(m.getKey());
				whereCon.append(" like ");
				String tmp = wrapper.action(getVisitor());
				String tp = tmp.substring(1, tmp.length() - 1);
				whereCon.append("'%" + tp + "%'");
				whereCon.append(" and ");
			} else {
				whereCon.append(m.getKey());
				whereCon.append(" like ");
				String tmp = wrapper.action(getVisitor());
				String tp = tmp.substring(1, tmp.length() - 1);
				whereCon.append("'%" + tp + "%'");
				whereCon.append(" and ");
			}

		}
		if (whereCon.length() >= 5) {
			whereCon.delete(whereCon.length() - 5, whereCon.length());
		}

		String tempWhere = whereCon.toString();
		if (null != tempWhere && !"".equals(tempWhere)) {
			tempWhere = " and " + tempWhere;
		}
		if (null != where && !"".equals(where)) {
			tempWhere = " and  (" + where + ") ";
		}
		String queryStr = "";
		queryStr = "select * from (select row_number() over(" + orderBy
				+ ") as row_num ,* from " + tablename + " where 1=1  "
				+ tempWhere + ") a where a.row_num between " + (1 + start)
				+ " and " + (size + start) + "";
		queryStr += orderBy;
		System.out.println("queryStr:" + queryStr);
		return queryStr;
	}

	public String queryData(String tablename, Map map, String where, int start,
			int size, String sort, String dir) {
		String orderBy = "";
		if (sort != null && !"".equals(sort)) {
			orderBy = " order by " + sort + " " + dir + " ,id";
		} else {
			// orderBy=" order by id ";
		}

		// select * from ( select TOP 10 * FROM ( SELECT TOP 20 * from
		// v_mg_image ORDER BY id ASC ) as aSysTable ORDER BY id DESC ) as
		// bSysTable ORDER BY id ASC
		StringBuffer whereCon = new StringBuffer("");
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			Wrapper wrapper = (Wrapper) m.getValue();
			if (wrapper instanceof WrapperDate) {
				whereCon.append(m.getKey());
				whereCon.append(" <= ");
				String tmp = wrapper.action(getVisitor());
				whereCon.append(tmp);
				whereCon.append(" and ");
			} else if (wrapper instanceof WrapperInteger) {
				whereCon.append(m.getKey());
				whereCon.append(" = ");
				String tmp = wrapper.action(getVisitor());
				whereCon.append(tmp);
				whereCon.append(" and ");
			} else if (wrapper instanceof WrapperString) {
				whereCon.append(m.getKey());
				whereCon.append(" like ");
				String tmp = wrapper.action(getVisitor());
				String tp = tmp.substring(1, tmp.length() - 1);
				whereCon.append("'%" + tp + "%'");
				whereCon.append(" and ");
			} else {
				whereCon.append(m.getKey());
				whereCon.append(" like ");
				String tmp = wrapper.action(getVisitor());
				String tp = tmp.substring(1, tmp.length() - 1);
				whereCon.append("'%" + tp + "%'");
				whereCon.append(" and ");
			}

		}
		if (whereCon.length() >= 5) {
			whereCon.delete(whereCon.length() - 5, whereCon.length());
		}

		String tempWhere = whereCon.toString();
		if (null != tempWhere && !"".equals(tempWhere)) {
			tempWhere = " and " + tempWhere;
		}
		if (null != where && !"".equals(where)) {
			tempWhere = " and  (" + where + ") ";
		}
		String queryStr = "";
		queryStr = "select * from (select row_number() over(" + orderBy
				+ ") as row_num ,* from " + tablename + " where 1=1  "
				+ tempWhere + ") a where a.row_num between " + (1 + start)
				+ " and " + (size + start) + "";
		queryStr += orderBy;
		System.out.println("queryStr:" + queryStr);
		return queryStr;
	}

	public String queryTotalData(String tablename, Map map, String where) {
		StringBuffer query = new StringBuffer("select count(*) as total from "
				+ tablename + " where 1=1 ");
		StringBuffer query1 = new StringBuffer("");
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			Wrapper wrapper = (Wrapper) m.getValue();
			if (wrapper instanceof WrapperDate) {
				query1.append(m.getKey());
				query1.append(" <= ");
				String tmp = wrapper.action(getVisitor());
				query1.append(tmp);
				query1.append(" and ");
			} else if (wrapper instanceof WrapperInteger) {
				query1.append(m.getKey());
				query1.append(" = ");
				String tmp = wrapper.action(getVisitor());
				query1.append(tmp);
				query1.append(" and ");
			} else if (wrapper instanceof WrapperString) {
				query1.append(m.getKey());
				query1.append(" like ");
				String tmp = wrapper.action(getVisitor());
				String tp = tmp.substring(1, tmp.length() - 1);
				query1.append("'%" + tp + "%'");
				query1.append(" and ");
			} else {
				query1.append(m.getKey());
				query1.append(" like ");
				String tmp = wrapper.action(getVisitor());
				String tp = tmp.substring(1, tmp.length() - 1);
				query1.append("'%" + tp + "%'");
				query1.append(" and ");
			}

		}
		if (query1.length() >= 5) {
			query1.delete(query1.length() - 5, query1.length());
		}

		if (query1.length() > 0) {
			query = query.append(" and " + query1);
		}

		if (null != where && !"".equals(where)) {
			query.append(" and (" + where + ") ");
		}
		String queryStr = query.toString();
		return queryStr;
	}

	public String getRowData(String tablename, String id) {
		StringBuffer query = new StringBuffer("select * from  " + tablename
				+ " where id='");
		query.append(id);
		query.append("'");
		String getStr = query.toString();
		return getStr;
	}

	public String getRowData2(String tablename, String rowid) {
		StringBuffer query = new StringBuffer("select * from  " + tablename
				+ " where baprjSystem_id='");
		query.append(rowid);
		query.append("'");
		String getStr = query.toString();
		return getStr;
	}

	public String getRowData3(String tablename, String rowid) {
		StringBuffer query = new StringBuffer("select * from  " + tablename
				+ " where baprjStr_id='");
		query.append(rowid);
		query.append("'");
		String getStr = query.toString();
		return getStr;
	}

	public static void main(String[] args) {
		SqlFactory test = new SqlFactory();
		String tableName = "vds_table2";
		List columNameCollection = new ArrayList();
		String columName = "id";
		String columAge = "intCol";
		String columFunctionTime = "dataCol";
		columNameCollection.add(columName);
		columNameCollection.add(columAge);
		columNameCollection.add(columFunctionTime);
		List values = new ArrayList();
		String name = "mqx";
		Wrapper wrapper1 = new WrapperString(name);
		Wrapper wrapper2 = new WrapperInteger(1);
		Wrapper wrapper3 = new WrapperDate(new java.util.Date());
		values.add(wrapper1);
		values.add(wrapper2);
		values.add(wrapper3);
		Map map = new HashMap<String, Object>();
		for (int i = 0; i < columNameCollection.size(); i++) {
			map.put(columNameCollection.get(i), values.get(i));
		}
		// System.out.println(test.insertData(tableName, map));
		// System.out.println(test.updateData(tableName, map,"mqx"));
		// System.out.println(test.deleteData(tableName,"mqx"));
		// System.out.println(test.queryData(tableName, map,"",10,10,"",""));
		// System.out.println(test.getRowData(tableName,"mqx"));
	}
}
