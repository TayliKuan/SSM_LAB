package com.hello.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class User  implements Serializable{
	private String uname;
	private Integer age;
	
	private Date date;

}
