package com.collect.common.base;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;
	private String msg;
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public Result() {

	}

	public Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result(int code, String msg, Object data) {
		this(code, msg);
		this.data = data;
	}
	
	public static Result success() {
		return new Result(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public static Result success(Object obj) {
		return new Result(HttpStatus.OK.value(), HttpStatus.OK.name(), obj);
	}

	public static Result failure(String message) {
		return new Result(HttpStatus.BAD_REQUEST.value(), message);
	}

	public static Result error(String message) {
		return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

}
