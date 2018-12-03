package com.next.sample.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.sample.exception.IdDuplicatedException;
import com.next.sample.exception.IdNoMatchException;
import com.next.sample.exception.PasswordNoMatchException;
import com.next.sample.mapper.UserMapper;
import com.next.sample.model.User;
import com.next.sample.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public static final int PAGESIZE = 10; // 게시판 하단에 보이게 될 페이지 개수 ==> (1 2 3 다음) 이런식으로, 글 게시판이든 사진 게시판이든 똑같이 10개로 고정

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean registUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, IdDuplicatedException {
		
		User userConfirm = userMapper.selectUser(user);
				
		if(userConfirm != null) {
			throw new IdDuplicatedException();
		}
		
		boolean result = false;
		
        // SHA-256 MessageDigest의 생성
        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
        // 문자열 바이트로 메시지 다이제스트를 갱신
        mdSHA256.update(user.getPw().getBytes("UTF-8"));
        // 해시 계산 반환값은 바이트 배열
        byte[] sha256Hash = mdSHA256.digest();
        // 바이트배열을 16진수 문자열로 변환하여 표시
        StringBuilder hexSHA256hash = new StringBuilder();
        for(byte b : sha256Hash) {
            String hexString = String.format("%02x", b);
            hexSHA256hash.append(hexString);
        }
        
        user.setPw(hexSHA256hash.toString());

        result = userMapper.insertUser(user) == 1;
		
		return result;
	}

	@Override
	public boolean modifyUser(User user) {
		return false;
	}

	@Override
	public boolean removeUser(User user) {
		return userMapper.deleteUser(user) == 1;
	}

	@Override
	public User getUser(User user) {
		
		User userConfirm = userMapper.selectUser(user);
		user.setName(userConfirm.getName());
		user.setUserSeq(userConfirm.getUserSeq());
		
		return user;
	}

	
	@Override
	public void getUserCheck(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException {
		
		User userConfirm = userMapper.selectUser(user);
		
		checkId(userConfirm);
		checkPw(user, userConfirm);
	}
	
	
	public void checkId(User user) throws IdNoMatchException, PasswordNoMatchException {
		User userConfirm = userMapper.selectUser(user);
		
		if(userConfirm == null) {
			throw new IdNoMatchException();
		}else if(! user.getPw().equals(userConfirm.getPw())) {
    		throw new PasswordNoMatchException();
    	}
	}
	
	public void checkPw(User user, User userConfirm) throws NoSuchAlgorithmException, UnsupportedEncodingException, PasswordNoMatchException {
		
//		// SHA-256 MessageDigest의 생성
//        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
//        // 문자열 바이트로 메시지 다이제스트를 갱신
//        mdSHA256.update(user.getPw().getBytes("UTF-8"));
//        // 해시 계산 반환값은 바이트 배열
//        byte[] sha256Hash = mdSHA256.digest();
//        // 바이트배열을 16진수 문자열로 변환하여 표시
//        StringBuilder hexSHA256hash = new StringBuilder();
//        for(byte b : sha256Hash) {
//            String hexString = String.format("%02x", b);
//            hexSHA256hash.append(hexString);
//        }
//        
//        user.setPw(hexSHA256hash.toString());
        
    	if(! user.getPw().equals(userConfirm.getPw())) {
    		throw new PasswordNoMatchException();
    	}
	}
	
}
