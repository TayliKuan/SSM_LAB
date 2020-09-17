package tw.gov.bli.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;



import lombok.Data;
@Data
public class Dept implements Serializable{

	private String deptno;
	
	@NotEmpty(message = "單位名稱 請勿空白")
	private String dname;
	
	@NotEmpty(message = "地點 請勿空白")
	private String loc;
	/**
	 * 一對多
	 */
	@Valid
	private List<User> users= new ArrayList<User>();
}
