package com.next.sample.exception;

import com.next.sample.rest.common.ResponseCode;

public class IdNoMatchException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public IdNoMatchException() {
		super(ResponseCode.ID_NO_MATCH);
	}
}
