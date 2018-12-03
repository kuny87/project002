package com.next.sample.search.type;

/**
 * 검색 유형
 */
public interface SType {
	/**
	 * 뷰에 뿌릴 이름(한글)
	 *
	 * @return
	 */
	String getVName();

	/**
	 * 타입 이름(enum.name())
	 *
	 * @return
	 */
	String getName();

	/**
	 * DB로 가져갈 값
	 *
	 * @return
	 */
	String getSValue();
}
