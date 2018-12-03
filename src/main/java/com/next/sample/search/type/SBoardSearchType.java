package com.next.sample.search.type;

/**
 * 모든 게시판에서 검색 유형을 관리하는 Enum 클래스
 * @author ohjic
 *
 */
public enum SBoardSearchType implements SType {
	// getName() (getVname(), getSvalue()) 형태
	TITLE("제목", "title"),
	NAME("작성자", "name")
	;
	
	String vName;
	String sValue;

	SBoardSearchType(String vName, String sValue) {
		this.vName = vName;
		this.sValue = sValue;
	}
	
	@Override
	public String getName() {
		return name(); // CONTENT
	}

	@Override
	public String getVName() {
		return vName; // "내용"
	}
	
	@Override
	public String getSValue() {
		return sValue; // "content"
	}
}
