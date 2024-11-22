package com.paug.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paug.requestDto.UserRequest;
import com.paug.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserRequest userInfo) {
        return userService.addUser(userInfo);
    }

}
