package com.next.sample.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.next.sample.exception.IdDuplicatedException;
import com.next.sample.exception.IdNoMatchException;
import com.next.sample.exception.PasswordNoMatchException;
import com.next.sample.model.User;

public interface UserService {

	boolean registUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, IdDuplicatedException;
	
	boolean modifyUser(User user);
	
	boolean removeUser(User user);

	User getUser(User user);

	void getUserCheck(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException;
}
