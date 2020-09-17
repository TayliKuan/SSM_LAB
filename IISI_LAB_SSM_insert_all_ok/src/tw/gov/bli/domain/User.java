package tw.gov.bli.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class User implements Serializable{
	private String uno;
	private String username;
	private String userid;
	private String sex;
	private String address;
	private String phone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joindate;
	
}
