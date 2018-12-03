package com.next.sample.exception;

import com.next.sample.rest.common.ResponseCode;

public class PasswordNoMatchException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public PasswordNoMatchException() {
		super(ResponseCode.PASSWORD_NO_MATCH);
	}
}
