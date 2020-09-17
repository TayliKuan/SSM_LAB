package tw.gov.bli.dto;

import java.util.List;

import tw.gov.bli.domain.User;

public interface UserDto {
	
	public List<User> findAll();
	
	public void saveUser(User user);
	
	
}
