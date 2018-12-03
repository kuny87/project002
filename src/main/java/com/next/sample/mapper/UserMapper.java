package com.next.sample.mapper;

import org.springframework.stereotype.Repository;

import com.next.sample.model.User;


@Repository
public interface UserMapper {

	int insertUser(User user);
	
	int updateUser(User user);
	
	int deleteUser(User user);
	
	User selectUser(User user);
	
}
