package com.next.sample.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.next.sample.model.BoardReply;
import com.next.sample.rest.common.ResponseCode;
import com.next.sample.rest.common.RestResponse;
import com.next.sample.service.BoardReplyService;
import com.next.sample.validator.BoardReplyValidator;

@RestController
@RequestMapping("/rest")
public class BoardReplyRestController {

	@Autowired
	private BoardReplyService boardReplyService;
	
	@Autowired
	BoardReplyValidator boardReplyValidator;
	
	@InitBinder("boardReply")
	public void initRegEquipBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(boardReplyValidator);
	}
	
	@RequestMapping(value = "/board_reply_regist", method = RequestMethod.GET, produces = "application/json")
	public RestResponse boardReplyRegist(
			@ModelAttribute("boardReply") @Valid BoardReply boardReply,
			BindingResult boardReplyBindingResult) {

		RestResponse response = boardReplyValidator.bindingError(boardReplyBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			boardReplyService.registBoardReply(boardReply);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/board_reply_modify", method = RequestMethod.GET, produces = "application/json")
	public RestResponse boardReplyModify(
			@ModelAttribute("boardReply") @Valid BoardReply boardReply,
			BindingResult boardReplyBindingResult) {

		RestResponse response = boardReplyValidator.bindingError(boardReplyBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			boardReplyService.modifyBoardReply(boardReply);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/board_reply_remove", method = RequestMethod.GET, produces = "application/json")
	public RestResponse boardReplyRemove(
			@ModelAttribute BoardReply boardReply) {

		RestResponse response = new RestResponse();
		response.setSuccess(true);
		
		try {
			boardReplyService.removeBoardReply(boardReply);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
}