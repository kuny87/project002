package com.next.sample.service;

import com.next.sample.model.Board;
import com.next.sample.page.Paging;
import com.next.sample.search.BoardSearch;

public interface BoardService {

	// 게시판
	boolean registBoard(Board board);
	
	boolean modifyBoard(Board board);
	
	boolean removeBoard(Board board);

	Paging<Board> getBoardList(BoardSearch boardSearch);
	
	Board getBoard(Board board);

}
