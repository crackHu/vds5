package ba.util;

import java.util.Date;

import ba.util.DateUtil;

public class WrapperDate extends Wrapper {
	private Date date;

	public WrapperDate(Date date) {
		this.date = date;
	}

	public String action(Visitor visitor) {
		return (visitor.visit(this));
	}

	public String toString() {
		if (date == null) {
			return "null";
		}
		return "'" + DateUtil.getStrYMDHMSByDate(date) + "'";
	}
}
