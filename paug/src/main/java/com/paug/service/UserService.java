package com.paug.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paug.entity.User;
import com.paug.entity.repo.UserRepo;
import com.paug.requestDto.UserRequest;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepository;

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	 private static final String PHONE_REGEX = "^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[-\\s]?\\d{3}[-\\s]?\\d{4}$";

	public String addUser(UserRequest userInfo) {

		try {
			boolean emailAndPhoneValidation = validateEmailAndPhone(userInfo.getEmail(),userInfo.getMobile());
			boolean passwordValidation = validatePassword(userInfo.getPassword(), userInfo.getConfirmPassword());
			boolean emailExists = validateEmailInDB(userInfo.getEmail());
			
			if (emailAndPhoneValidation && passwordValidation && emailExists) {
				User user = mapUserObject(userInfo);
				if (user != null) {
					userRepository.save(user);
					return "User Added Successfully ";
				}
			} else {
				return "Validation Failed";
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	private boolean validateEmailInDB(String email) {
		boolean user = userRepository.findByEmail(email).isPresent();
		if (user) {
			return false;
		}
		return true;
	}

	private boolean validatePassword(String password, String confirmPassword) {
		if ((password == null) && (confirmPassword == null)) {
			return false;
		}
		if (password.equals(confirmPassword)) {
			return true;
		}
		return false;
	}

	private boolean validateEmailAndPhone(String email, Integer phoneNo) {
		if (email == null && phoneNo !=0) {
			return false;
		}

		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}

	public User mapUserObject(UserRequest userInfo) {
		User user = new User();
	
		user.setEmail(userInfo.getEmail());
		user.setMobile(userInfo.getMobile());
		user.setName(userInfo.getName());
		user.setPassword(userInfo.getPassword());
		user.setUsername(userInfo.getUsername());

		return user;
	}

}
