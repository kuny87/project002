package com.next.sample.exception;

import com.next.sample.rest.common.ResponseCode;

/**
 * 공통 익셉션
 */
public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 응답 코드<br />
	 * default: UNKNOWN
	 *
	 * @see org.ohjic.flower.application.enumeration.ResponseCode
	 */
	private ResponseCode responseCode = ResponseCode.UNKOWN;

	/**
	 * 기본 생성자
	 *
	 * @param responseCode - 응답 코드
	 *
	 * @see org.ohjic.flower.application.enumeration.ResponseCode
	 */
	public CommonException(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * 응답 코드 반환
	 *
	 * @return 응답 코드
	 *
	 * @see org.ohjic.flower.application.enumeration.ResponseCode
	 */
	public ResponseCode getResponseCode() {
		return responseCode;
	}
}
