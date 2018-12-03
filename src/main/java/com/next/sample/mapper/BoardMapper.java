package com.next.sample.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.next.sample.model.Board;
import com.next.sample.model.BoardReply;
import com.next.sample.search.BoardSearch;

@Repository
public interface BoardMapper {

	// 게시판
	int insertBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoard(Board board);
	
	Board selectBoard(Board board);
	
	int selectBoardCount(BoardSearch boardSearch);
	
	List<Board> selectBoardList(BoardSearch boardSearch);
	
	void addCount(Board board);
	
	// 댓글
	int insertBoardReply(BoardReply boardReply);
	
}
