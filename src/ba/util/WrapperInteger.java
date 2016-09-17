package ba.util;

public class WrapperInteger extends Wrapper {
	private Integer value;

	public WrapperInteger(Integer value) {
		this.value = value;
	}

	public WrapperInteger(int value) {
		this.value = new Integer(value);
	}

	public WrapperInteger(String value) {
		this.value = new Integer(value);
	}

	public String action(Visitor visitor) {
		return (visitor.visit(this));
	}

	public String toString() {
		if (value == null) {
			return "null";
		}
		return value.toString();
	}
}
