package com.next.sample.rest.common;

/**
 * 공통 Restful JsonResponse 객체
 * @author ohjic
 *
 */
public class RestResponse {

	/**
	 * 성공 여부
	 */
	private boolean success;

	/**
	 * Response Data
	 */
	private Object data;

	/**
	 * 메시지
	 */
	private String message;

	/**
	 * 응답 코드
	 */
	private String code;
	
	private ResponseCode resCode ;
	
	public RestResponse() {
		success = true;
		this.setResCode(ResponseCode.SUCCESS);
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setResCode(ResponseCode resCode) {
		this.code = resCode.getCode();
		this.message = resCode.getMessage();
	}
}
