package com.next.sample.rest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.next.sample.exception.IdDuplicatedException;
import com.next.sample.exception.IdNoMatchException;
import com.next.sample.exception.PasswordNoMatchException;
import com.next.sample.model.User;
import com.next.sample.rest.common.ResponseCode;
import com.next.sample.rest.common.RestResponse;
import com.next.sample.service.UserService;
import com.next.sample.validator.UserValidator;

@RestController
@RequestMapping("/rest")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	UserValidator userValidator;
	
	@InitBinder("user")
	public void initRegEquipBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(userValidator);
	}
	
	@RequestMapping(value = "/user_regist", method = RequestMethod.GET, produces = "application/json")
	public RestResponse userRegist(
			@ModelAttribute("user") @Valid User user,
			BindingResult userBindingResult
			) throws UnsupportedEncodingException, NoSuchAlgorithmException, IdDuplicatedException {

		RestResponse response = userValidator.bindingError(userBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
	
		try {
			userService.registUser(user);
		} catch (IdDuplicatedException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.ID_DUPLICATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/user_pwCheck", method = RequestMethod.GET, produces = "application/json")
	public RestResponse userPwCheck(
			@ModelAttribute User user) {

		RestResponse response = new RestResponse();
		response.setSuccess(true);
		
		try {
			userService.getUserCheck(user);
		} catch (IdNoMatchException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.ID_NO_MATCH);
		} catch (PasswordNoMatchException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.PASSWORD_NO_MATCH);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/user_modify", method = RequestMethod.GET, produces = "application/json")
	public RestResponse userModify(
			@ModelAttribute("user") @Valid User user,
			BindingResult userBindingResult) {

		RestResponse response = userValidator.bindingError(userBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			userService.modifyUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/user_remove", method = RequestMethod.GET, produces = "application/json")
	public RestResponse userRemove(
			@ModelAttribute User user) {

		RestResponse response = new RestResponse();
		response.setSuccess(true);
		
		try {
			userService.removeUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/user_login", method = RequestMethod.GET, produces = "application/json")
	public RestResponse userLogin(
			@ModelAttribute("user") @Valid User user,
			BindingResult userBindingResult,
			HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException {
		
		RestResponse response = userValidator.bindingError(userBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			userService.getUserCheck(user);
			user = userService.getUser(user);
		} catch (IdNoMatchException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.ID_NO_MATCH);
		} catch (PasswordNoMatchException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.PASSWORD_NO_MATCH);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		return response;
	}
	
}