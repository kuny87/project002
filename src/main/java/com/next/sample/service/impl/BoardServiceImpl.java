package com.next.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.sample.mapper.BoardMapper;
import com.next.sample.model.Board;
import com.next.sample.page.Paging;
import com.next.sample.search.BoardSearch;
import com.next.sample.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	public static final int PAGESIZE = 10; // 게시판 하단에 보이게 될 페이지 개수 ==> (1 2 3 다음) 이런식으로, 글 게시판이든 사진 게시판이든 똑같이 10개로 고정
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean registBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}
	
	@Override
	public boolean modifyBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	public boolean removeBoard(Board board) {
		return boardMapper.deleteBoard(board) == 1;
	}

	@Override
	public Paging<Board> getBoardList(BoardSearch boardSearch) {
		
		int totalBoard = boardMapper.selectBoardCount(boardSearch);
		List<Board> boardList = boardMapper.selectBoardList(boardSearch);
		
		Paging<Board> paging = new Paging<Board>(PAGESIZE, boardSearch.getBoardSize(), boardSearch.getPageNo(), totalBoard, boardList);
		
		return paging;
	}

	@Override
	public Board getBoard(Board board) {
		
		boardMapper.addCount(board);
		
		return boardMapper.selectBoard(board);
	}

}
