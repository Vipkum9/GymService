package com.paug.requestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {
	  	private int id;
	    private String username;
	    private String password;
	    private String confirmPassword;
	    private String name;
	    private String email;
	    private int mobile;
}
