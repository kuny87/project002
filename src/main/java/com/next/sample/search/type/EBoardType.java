package com.next.sample.search.type;

/**
 * 게시판 구별을 위한 Enum 클래스
 * @author next
 *
 */
public enum EBoardType implements EType<String> {
	NORMAL("normal"), // 자유 게시판
	NOTICE("notice"), // 공지사항 게시판
	FAQ("faq"), // FAQ 게시판
	ETC("etc") // 기타 게시판
	;

	private String eValue;
	
	EBoardType(String eValue) {
		this.eValue = eValue;
	}
	
	public String getEValue() {
		return eValue;
	}
	
}
