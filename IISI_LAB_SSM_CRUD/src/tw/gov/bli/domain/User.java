package tw.gov.bli.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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
	
	 @NotNull(message = "日期請勿空白")
	 @Past(message = "日期格式錯誤")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 @Temporal(TemporalType.DATE)
	private Date birthday;
	 
	 @NotNull(message = "日期請勿空白")
	 @Past(message = "日期格式錯誤")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 @Temporal(TemporalType.DATE)
	private Date joindate;
	
}
