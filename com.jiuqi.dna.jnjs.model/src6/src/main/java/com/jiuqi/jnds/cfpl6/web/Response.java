package com.jiuqi.jnds.cfpl6.web;

import java.util.ArrayList;
import java.util.List;

public class Response {
	public final static int SUCCESS_CODE = 200;
	public final static int FAILD_CODE = 500;
	
	private int code;
	private List<?> successinfo = new ArrayList<>();
	private String errorinfo;
	
	private Response() {}
	
	private Response(int code, List<?> successinfo) {
		this.code = code;
		this.successinfo = successinfo;
	}
	
	private Response(int code, String errorinfo) {
		this.code = code;
		this.errorinfo = errorinfo;
	}
	
	public static Response success(List<?> successinfo) {
		return new Response(SUCCESS_CODE, successinfo);
	}
	
	public static Response faild(String errorinfo) {
		return new Response(FAILD_CODE, errorinfo);
	}

	public int getCode() {
		return code;
	}

	public List<?> getSuccessinfo() {
		return successinfo;
	}

	public String getErrorinfo() {
		return errorinfo;
	}
	
}
