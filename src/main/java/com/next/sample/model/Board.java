package com.next.sample.model;

import com.next.sample.search.type.EBoardType;

public class Board {

	private Integer boardSeq;
	private EBoardType type;
	private String title;
	private String content;
	private String name;
	private Integer cnt;
	private String regDate;
	private String delYn;

	public Integer getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(Integer boardSeq) {
		this.boardSeq = boardSeq;
	}

	public EBoardType getType() {
		return type;
	}

	public void setType(EBoardType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}
