package com.hello.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Account implements Serializable{

	private String username;
	private String password;
	private Double money;
	
//	private User user;
	
	private List<User> list;
	private Map<String,User> map;
}
