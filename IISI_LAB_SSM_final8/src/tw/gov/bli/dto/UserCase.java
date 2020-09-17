package tw.gov.bli.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserCase implements Serializable{

	private static final long serialVersionUID = 5232057538209225723L;
private String uno;
	
	@NotEmpty(message = "姓名不可空白")
	private String username;
	
	@NotEmpty(message = "身分證號 不可空白")
	private String userid;
	
	@NotEmpty(message = "性別必選")
	private String sex;
	
	@NotEmpty(message = "地址 不可空白")
	private String address;
	
	@NotEmpty(message = "電話 不可空白")
	private String phone;
	
	 @NotNull(message = "生日必選")
	 @Past(message = "日期格式錯誤")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 @Temporal(TemporalType.DATE)
	private Date birthday;
	 
	 @NotNull(message = "加入日期  不可空白")
	 @Past(message = "日期格式錯誤")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 @Temporal(TemporalType.DATE)
	private Date joindate;
	 
}