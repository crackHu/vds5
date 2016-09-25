package medicalPHR.dto;

/**
 * 响应前端数据dto
 * @author crack
 */
public class WebResponse {
	
	//1:執行成功；-99：執行失敗
	private int resultCode;
	private String resultMsg;
	
	public WebResponse(int resultCode, String resultMsg) {
		super();
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	public int getResultCode() {
		return resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
