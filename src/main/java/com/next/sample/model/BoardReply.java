package com.next.sample.model;

import com.next.sample.search.type.EBoardType;

public class BoardReply {

	private Integer boardReplySeq;
	private EBoardType boardType;
	private Integer boardSeq;
	private Integer regSeq;
	private String regName;
	private String replyContent;
	private String regDate;
	private String delYn;
	
	private Integer groupSeq;
	private Integer reparent;
	private Integer redepth;
	private Integer reorder;
	
	public Integer getBoardReplySeq() {
		return boardReplySeq;
	}
	public void setBoardReplySeq(Integer boardReplySeq) {
		this.boardReplySeq = boardReplySeq;
	}
	public Integer getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(Integer boardSeq) {
		this.boardSeq = boardSeq;
	}
	public Integer getRegSeq() {
		return regSeq;
	}
	public void setRegSeq(Integer regSeq) {
		this.regSeq = regSeq;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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
	public EBoardType getBoardType() {
		return boardType;
	}
	public void setBoardType(EBoardType boardType) {
		this.boardType = boardType;
	}
	public Integer getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(Integer groupSeq) {
		this.groupSeq = groupSeq;
	}
	public Integer getReparent() {
		return reparent;
	}
	public void setReparent(Integer reparent) {
		this.reparent = reparent;
	}
	public Integer getRedepth() {
		return redepth;
	}
	public void setRedepth(Integer redepth) {
		this.redepth = redepth;
	}
	public Integer getReorder() {
		return reorder;
	}
	public void setReorder(Integer reorder) {
		this.reorder = reorder;
	}
}
