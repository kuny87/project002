package com.next.sample.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.next.sample.model.BoardReply;

@Repository
public interface BoardReplyMapper {

	int insertBoardReply(BoardReply boardReply);
	
	int insertBoardReReply(BoardReply boardReply);
	
	int updateBoardReply(BoardReply boardReply);
	
	int deleteBoardReply(BoardReply boardReply);
	
	int updateReplyReorderAdd(BoardReply boardReply);
	
	BoardReply selectBoardReply(BoardReply boardReply);
	
	int selectReplyReorder(BoardReply boardReply);
	
	int selectReReplyReorder(BoardReply boardReply);
	
	int selectGroupDepthCheck(BoardReply boardReply);
	
	BoardReply selectReparentGroupSeq(BoardReply boardReply);
	
	BoardReply selectMaxReply();
	
	int updateGroupSeq(BoardReply boardReply);
	
	int updateGroupSeqChilde(BoardReply boardReply);
	
	List<BoardReply> selectBoardList(BoardReply boardReply);
	
}
