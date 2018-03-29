package com.yatoooon.managementsystem.utils;


/**
 * @author yatoooon
 */
public class JsonResult {

	public static final int CODE_SUCCESS = 20000;
	public static final int CODE_ERROR = 40000;

	public static final String MESSAGE_SUCCESS = "操作成功";
	public static final String MESSAGE_ERROR = "操作失败";

	private int code;
	private boolean success;
	private String msg;
	private Object data;

	public JsonResult(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.success = (code == 20000);
	}

	public JsonResult(int code, String msg) {
		this(code, msg, null);
	}

	public JsonResult(int code, Object data) {
		this(code, code == 20000 ? MESSAGE_SUCCESS : MESSAGE_ERROR, data);
	}

	public JsonResult(int code) {
		this(code, code == 20000 ? MESSAGE_SUCCESS : MESSAGE_ERROR, null);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
