package healthArchive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@SuppressWarnings("static-access")
public class VdsResponse implements Serializable {
	Dout dout;

	public VdsResponse() {
		dout = new Dout();
	}

	public VdsResponse(String jsonStr) {
		dout = new Dout();
		try {
			JSONObject json = new JSONObject().fromObject(jsonStr);
			JSONObject doutJson = json.getJSONObject("dout");
			dout.getStatus().jsonStringToHeader(doutJson.getJSONObject("status"));
			dout.jsonStringToData(doutJson.getString("dout"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public class Dout implements Serializable {

		Status status;
		List<Object> dout;

		public Dout() {
			status = new Status();
			dout = new ArrayList<Object>();
		}

		public List<Object> getDout() {
			return dout;
		}

		public void setDout(List<Object> dout) {
			this.dout = dout;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		public void jsonStringToData(String str) {
			try {
				JSONArray ja = new JSONArray().fromObject(str);
				for (int i = 0; i < ja.size(); i++) {
					dout.add(ja.getString(i));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}

	public class Status implements Serializable {
		int resultCode;
		String resultMsg;

		public Status() {
		}

		public int getResultCode() {
			return resultCode;
		}

		public void setResultCode(int resultCode) {
			this.resultCode = resultCode;
		}

		public String getResultMsg() {
			return resultMsg;
		}

		public void setResultMsg(String resultMsg) {
			this.resultMsg = resultMsg;
		}

		public void jsonStringToHeader(JSONObject json) {
			try {
				Integer result_code = (Integer) json.get("resultCode");
				this.resultCode = result_code != null ? -1 : result_code;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public Dout getDout() {
		return dout;
	}

	public void setDout(Dout dout) {
		this.dout = dout;
	}

	public List<Object> _getDout() {
		return this.getDout().getDout();
	}

	public void _setData(List<Object> dout) {
		this.getDout().setDout(dout);
	}

	public Status _getStatus() {
		return this.getDout().getStatus();
	}

	public void _setStatus(Status Status) {
		this.getDout().setStatus(Status);
	}

	public String toString(VdsResponse ep) {
		return JSONObject.fromObject(ep).getJSONObject("dout").toString();
	}
}
