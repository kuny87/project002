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

import com.next.sample.model.Board;
import com.next.sample.rest.common.ResponseCode;
import com.next.sample.rest.common.RestResponse;
import com.next.sample.service.BoardService;
import com.next.sample.validator.BoardValidator;

@RestController
@RequestMapping("/rest")
public class BoardRestController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	BoardValidator boardValidator;
	
	@InitBinder("board")
	public void initRegEquipBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(boardValidator);
	}
	
	@RequestMapping(value = "/board_regist", method = RequestMethod.GET, produces = "application/json")
	public RestResponse boardRegist(
			@ModelAttribute("board") @Valid Board board,
			BindingResult boardBindingResult) {

		RestResponse response = boardValidator.bindingError(boardBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			boardService.registBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/board_modify", method = RequestMethod.GET, produces = "application/json")
	public RestResponse boardModify(
			@ModelAttribute("board") @Valid Board board,
			BindingResult boardBindingResult) {

		RestResponse response = boardValidator.bindingError(boardBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			boardService.modifyBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/board_remove", method = RequestMethod.GET, produces = "application/json")
	public RestResponse boardRemove(
			@ModelAttribute Board board) {

		RestResponse response = new RestResponse();
		response.setSuccess(true);
		
		try {
			boardService.removeBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
}