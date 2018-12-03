package com.next.sample.exception;

import com.next.sample.rest.common.ResponseCode;

public class IdDuplicatedException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public IdDuplicatedException() {
		super(ResponseCode.ID_DUPLICATED);
	}
}
