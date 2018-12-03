package com.next.sample.service;

import com.next.sample.model.BoardReply;

public interface BoardReplyService {

	boolean registBoardReply(BoardReply boardReply);
	
	boolean modifyBoardReply(BoardReply boardReply);
	
	boolean removeBoardReply(BoardReply boardReply);

//	Paging<Board> getBoardList(BoardSearch boardSearch);
//	
//	Board getBoard(Board board);
	
}
