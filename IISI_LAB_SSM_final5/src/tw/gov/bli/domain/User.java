package tw.gov.bli.domain;

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
public class User implements Serializable{
/*@NotNull 和 @NotEmpty 和@NotBlank 區別
@NotEmpty 用在集合類上面
@NotBlank 用在String上面
@NotNull 用在基本型別上
參考:https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/303168/
*/
	private static final long serialVersionUID = -3913416983685030136L;
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
