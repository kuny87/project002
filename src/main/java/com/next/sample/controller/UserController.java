package com.next.sample.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.next.sample.exception.IdNoMatchException;
import com.next.sample.exception.PasswordNoMatchException;
import com.next.sample.model.User;

@Controller
@SessionAttributes("user")
public class UserController {

	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(
			@ModelAttribute("user") User user,
			HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException {
		
		ModelAndView mav = new ModelAndView("index/main");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/user_logout", method = RequestMethod.GET)
	public ModelAndView userLogout(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("index/login");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return mav;
	}
	
	
	@RequestMapping(value = "/user_regist", method = RequestMethod.GET)
	public ModelAndView userRegist() {
		
		ModelAndView mav = new ModelAndView("user/regist");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/user_mypage", method = RequestMethod.GET)
	public ModelAndView userMypage(
			@ModelAttribute("user") User user,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("user/get");
		
		mav.addObject("user", user);
		
		return mav;
	}
	
}
