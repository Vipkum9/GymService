package com.paug.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.paug.entity.User;
import com.paug.entity.repo.UserRepo;
import com.paug.requestDto.UserRequest;

public class UserMapper {
	@Autowired
	private UserRepo userRepository;

	public User mapUserObject(UserRequest userInfo) {
		User user = new User();;
		try {
			
			user.setEmail(userInfo.getEmail());
			user.setMobile(userInfo.getMobile());
			user.setName(userInfo.getName());
			user.setPassword(userInfo.getPassword());
			user.setUsername(userInfo.getUsername());
			userRepository.save(user);
		} catch (Exception e) {
		throw e;
		}
		return user;
	}

}
