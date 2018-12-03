package com.next.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.sample.mapper.BoardReplyMapper;
import com.next.sample.model.BoardReply;
import com.next.sample.service.BoardReplyService;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {

	@Autowired
	private BoardReplyMapper boardReplyMapper;

	@Override
	public boolean registBoardReply(BoardReply boardReply) {
		
		boolean result = false;
		
		if(boardReply.getReparent() == null) {		// 최상위 댓글일 경우
			// 현재 부모 댓글중 가장 큰 reorder 값을 가져와서 +1 한다
			boardReply.setReorder(boardReplyMapper.selectReplyReorder(boardReply));
			// 댓글을 등록한다
			result = boardReplyMapper.insertBoardReply(boardReply) == 1;
			// 방금 등록한 댓글 데이터를 불러온다
			boardReply = boardReplyMapper.selectMaxReply();
			// 방금 등록한 댓글의 그룹Seq 값을 셋팅한다
			result = boardReplyMapper.updateGroupSeq(boardReply) == 1;
		
		}else {		// 하위 댓글일 경우
			BoardReply temp = boardReplyMapper.selectReparentGroupSeq(boardReply);
			// 해당 댓글의 부모댓글의 그룹Seq를 해당 댓글에 셋팅한다
			boardReply.setGroupSeq(temp.getGroupSeq());
			// 해당 댓글의 부모댓글의 Seq를 해당 댓글에 BoardReplySeq에 셋팅한다
			boardReply.setBoardReplySeq(temp.getBoardReplySeq());
			// 해당 댓글의 depth를 체크하여 조건에 만족하는 reorder 값을 가져온다
			int reorder = boardReplyMapper.selectGroupDepthCheck(boardReply);
			// 등록할 글의 reorder 를 셋팅한다
			if(boardReply.getRedepth() == null || reorder == 0){
				boardReply.setReorder(boardReplyMapper.selectReReplyReorder(boardReply));
			}else {
				boardReply.setReorder(boardReplyMapper.selectGroupDepthCheck(boardReply));
			}
			// 대댓글을 등록한다
			result = boardReplyMapper.insertBoardReReply(boardReply) == 1;
			if(result){
				// 등록한 글을 제외한 나머지 글들의 순서를 update 한다
				boardReply = boardReplyMapper.selectMaxReply();
				result = boardReplyMapper.updateReplyReorderAdd(boardReply) == 1;
				boardReply.setGroupSeq(boardReplyMapper.selectReparentGroupSeq(boardReply).getGroupSeq());
				result = boardReplyMapper.updateGroupSeqChilde(boardReply) == 1;
			}
		}
		return result;
	}

	@Override
	public boolean modifyBoardReply(BoardReply boardReply) {
		return boardReplyMapper.updateBoardReply(boardReply) == 1;
	}

	@Override
	public boolean removeBoardReply(BoardReply boardReply) {
		return boardReplyMapper.deleteBoardReply(boardReply) == 1;
	}

}
