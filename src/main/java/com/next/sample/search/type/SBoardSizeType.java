package com.next.sample.search.type;

/**
 * 紐⑤뱺 寃뚯떆�뙋�뿉�꽌 寃��깋 �쑀�삎�쓣 愿�由ы븯�뒗 Enum �겢�옒�뒪
 * @author ohjic
 *
 */
public enum SBoardSizeType implements SType {
	// getName() (getVname(), getSvalue()) �삎�깭
	이십개씩("20개씩", "20"),
	사십개씩("40개씩", "40"),
	육십개씩("60개씩", "60"),
	팔십개씩("80개씩", "80"),
	백개씩("100개씩", "100")
	;
	
	String vName;
	String sValue;

	SBoardSizeType(String vName, String sValue) {
		this.vName = vName;
		this.sValue = sValue;
	}
	
	@Override
	public String getName() {
		return name(); // CONTENT
	}

	@Override
	public String getVName() {
		return vName; // "�궡�슜"
	}
	
	@Override
	public String getSValue() {
		return sValue; // "content"
	}
}
