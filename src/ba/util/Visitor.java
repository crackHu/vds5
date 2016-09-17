package ba.util;

public class Visitor {
	public Visitor() {
	}

	public String vistit(WrapperString wrap) {
		return wrap.toString();
	}

	public String visit(WrapperInteger value) {
		return value.toString();
	}

	public String visit(WrapperDate date) {
		return date.toString();
	}
}