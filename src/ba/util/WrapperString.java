package ba.util;

public class WrapperString extends Wrapper {
	private String wrapper;

	public WrapperString(String wrapper) {
		this.wrapper = wrapper;
	}

	public WrapperString(char[] wrap) {
		wrapper = new String(wrap);
	}

	public String action(Visitor visitor) {
		return (visitor.vistit(this));
	}

	public String toString() {
		if (wrapper == null) {
			return "null";
		}
		return "'" + wrapper + "'";
	}
}
