package com.next.sample.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 페이징 공통 클래스
 *
 * @param <T> - 페이징 데이터 타입
 */
public class Paging<T> {

	/**
	 * 한 화면에 보이는 페이지네이션 크기
	 */
	private int pageSize;

	/**
	 * 한 화면에 보일 게시글 개수
	 */
	private int boardSize;

	/**
	 * 현재 페이지 번호
	 */
	private int pageNo;

	/**
	 * 총 게시물 수
	 */
	private int totalBoard;

	/**
	 * 총 페이지 수
	 */
	private int totalPage;

	/**
	 * 현재 화면에 보이는 맨 처음 페이지 번호
	 */
	private int startPage;

	/**
	 * 현재 화면에 보이는 맨 끝 페이지 번호
	 */
	private int endPage;

	/**
	 * 노출 번호 시작 번호
	 */
	private int startSeq;

	/**
	 * 페이징 데이터 목록
	 */
	private List<T> list;

	private Map<String, Object> extraData;

	public Paging(final List<T> list) {
		this(1, 1, 1, 1, list);
	}

	/**
	 * 기본 생성자
	 *
	 * @param pageSize - 한 화면에 보이는 페이징 수
	 * @param boardSize - 한 화면에 보일 게시글 수
	 * @param pageNo - 현재 페이지 번호
	 * @param totalBoard - 전체 게시글 수
	 * @param list - 페이징 데이터 목록
	 */
	public Paging(int pageSize, int boardSize, int pageNo, int totalBoard, List<T> list) {
		this.pageSize = pageSize;
		this.boardSize = boardSize;
		this.pageNo = pageNo;
		this.totalBoard = totalBoard;
		setPageInfo (pageSize, boardSize, pageNo, totalBoard);
		this.list = list;
		this.extraData = new HashMap<String, Object>();
	}

	/**
	 * 노출 번호 시작 번호 반환
	 *
	 * @return 노출 번호 시작 번호
	 */
	public int getStartSeq() {
		return startSeq;
	}

	/**
	 * 노출 번호 시작 번호 설정
	 *
	 * @param startSeq - 노출 번호 시작 번호
	 */
	public void setStartSeq(int startSeq) {
		this.startSeq = (startSeq-1)*boardSize;
	}

	/**
	 * 한 화면에 보이는 페이징 수 반환
	 *
	 * @return 한 화면에 보이는 페이징 수
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 한 화면에 보이는 페이징 수 설정
	 *
	 * @param pageSize - 한 화면에 보이는 페이징 수
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 한 화면에 보이는 게시글 수 반환
	 *
	 * @return 한 화면에 보이는 게시글 수
	 */
	public int getBoardSize() {
		return boardSize;
	}

	/**
	 * 한 화면에 보이는 게시글 수 설정
	 *
	 * @param boardSize - 한 화면에 보이는 게시글 수
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	/**
	 * 현재 페이지 번호 반환
	 *
	 * @return 현재 페이지 번호
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 현재 페이지 번호 설정
	 *
	 * @param pageNo - 현재 페이지 번호
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 전체 게시글 수 반환
	 *
	 * @return 전체 게시글 수
	 */
	public int getTotalBoard() {
		return totalBoard;
	}

	/**
	 * 전체 게시글 수 설정
	 *
	 * @param totalBoard - 전체 게시글 수
	 */
	public void setTotalBoard(int totalBoard) {
		this.totalBoard = totalBoard;
	}

	/**
	 * 전체 페이지 수 반환
	 *
	 * @return 전체 페이지 수
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 시작 페이지 번호 반환
	 *
	 * @return 시작 페이지 번호
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * 끝 페이지 번호 반환
	 *
	 * @return 끝 페이지 번호
	 */
	public int getEndPage() {
		return endPage;
	}

	/**
	 * 페이지 정보 설정
	 *
	 * @param pageSize - 한 화면에 보일 페이지 수
	 * @param boardSize - 한 화면에 보일 게시글 수
	 * @param pageNo - 현재 페이지 번호
	 * @param totalBoard - 전체 게시글 수
	 */
	public void setPageInfo (int pageSize, int boardSize, int pageNo, int totalBoard) {
		int nmg = totalBoard%boardSize;
		if (nmg != 0) {
			this.totalPage = (totalBoard / boardSize) + 1;
		} else {
			this.totalPage = totalBoard / boardSize;
		}
		
		this.startPage = pageNo - ((pageNo-1)%pageSize);
		
		int end = startPage + pageSize - 1;
		if (end > this.totalPage) {
			end = this.totalPage;
		}
		this.endPage = end;
	}

	/**
	 * 페이지 데이터 목록 반환
	 *
	 * @return 페이지 데이터 목록
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 페이지 데이터 목록 설정
	 *
	 * @param list - 페이지 데이터 목록
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	public Map<String, Object> getExtraData() {
		return extraData;
	}

	public void addExtraData(final String key, final Object value) {
		if (extraData == null) {
			extraData = new HashMap<String, Object>();
		}

		extraData.put(key, value);
	}
}
