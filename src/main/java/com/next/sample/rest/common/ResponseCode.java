package com.next.sample.rest.common;

/**
 * 응답 코드
 */
public enum ResponseCode {
	SUCCESS("SUCC00", ""),
	UNKOWN("ERR0000", "알 수 없는 오류입니다."),
	ID_DUPLICATED("ERR0001", "해당 아이디가 이미 존재합니다."),
	ID_NO_MATCH("ERR0002", "해당 아이디가 없습니다."),
	PASSWORD_NO_MATCH("ERR0003", "비밀번호가 맞지 않습니다."),
	EMPTY_ID("ERR0004", "아이디를 입력해 주세요."),
	EMPTY_PW("ERR0005", "비밀번호를 입력해 주세요."),
	EMPTY_NAME("ERR0006", "이름을 입력해 주세요."),
	INVALID_INPUT_VALUE("ERR0007", "유효하지 않은 입력값입니다.")
	;

	/**
	 * 응답 코드
	 */
	private String code;

	/**
	 * 응답 메세지
	 */
	private String message;

	/**
	 * 기본 생성자
	 *
	 * @param code - 응답 코드
	 * @param message - 응답 메세지
	 */
	ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 응답 코드 반환
	 *
	 * @return 응답 코드
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 응답 메세지 반환
	 *
	 * @return 응답 메세지
	 */
	public String getMessage() {
		return message;
	}

	public static ResponseCode getResponseCodeByCode(String code) {
		ResponseCode[] values = ResponseCode.values();
		
		for (ResponseCode responseCode : values) {
			if(responseCode.getCode().equals(code)) {
				return responseCode;
			}
		}
		
		return null;
	}
}
