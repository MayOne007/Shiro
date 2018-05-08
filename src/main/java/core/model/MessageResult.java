package core.model;

import java.util.HashMap;
import java.util.Map;

public class MessageResult {

	private boolean success;
	private String msg;
	
	public MessageResult() {
		this.success = true;
		this.msg = "操作成功";
	}
	
	public MessageResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}


	public boolean getSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String,Object> toMap(){
		Map<String,Object> res = new HashMap<>();
		res.put("success", getSuccess());
		res.put("msg",  getMsg());
		return res;
	}
}
