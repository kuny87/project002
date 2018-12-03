package com.next.sample.search;

import com.next.sample.search.type.EBoardType;
import com.next.sample.search.type.SBoardSearchType;

public class BoardSearch {
	
//	public static final int BOARDSIZE = 30; // 글 게시판에 한 페이지 당 보이게 될 게시글 수는 30개로 픽스

	private int pageNo; // 현재 페이지 번호
	private int startRow; // 화면에 보이게 될 게시글이 디비에서 몇번째인지
	private SBoardSearchType searchType; // 어떤 기준으로 검색할지 (검색 유형)
	private String keyword; // 검색어
	private int boardSize; // 한 화면에 보이게 될 게시글 개수
	private String startDate;
	private String endDate;
	private EBoardType type;

	public BoardSearch() {
		this.setPageNo(1);
		this.setKeyword("");
		this.setSearchType(SBoardSearchType.TITLE);
		this.boardSize = 20;
		
		setStartRow(pageNo);
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		setStartRow(pageNo);
	}

	public int getStartRow() {
		return startRow;
	}
	
	public void setStartRow(int pageNo) { // 디비에서 가져올 게시물의 시작 위치, boardSize가 정해질 때 자동으로 정해진다.
		this.startRow = (pageNo-1) * boardSize;
	}
	
	public SBoardSearchType getSearchType() {
		return searchType;
	}
	
	public void setSearchType(SBoardSearchType searchType) {
		this.searchType = searchType;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getBoardSize() {
		return boardSize;
	}
	
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
		setStartRow(boardSize); // boardSize가 정해질 때 StartRow도 정해진다.
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate + " 00:00:00";
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate + " 23:59:59";
	}

	public EBoardType getType() {
		return type;
	}

	public void setType(EBoardType type) {
		this.type = type;
	}
	
}
