package com.yatoooon.managementsystem.utils;


public class JsonResult {
    private boolean success=true;
    private String message="";
    private int code=200;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(Object data) {
        if(data!=null){
            this.data = data;
        }
    }

    public JsonResult(boolean success, int code, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code=code;
    }

    public JsonResult(boolean success, String message, int code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public JsonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public JsonResult(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public JsonResult(String message) {
        this.message = message;
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

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        if(data!=null){
            this.data = data;
        }
    }
}
