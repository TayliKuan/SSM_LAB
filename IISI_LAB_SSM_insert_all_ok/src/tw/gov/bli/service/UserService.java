package tw.gov.bli.service;

import java.util.List;

import tw.gov.bli.domain.User;

public interface UserService {
	
		public List<User> findAll();
	
		public void insert(User user);
		
}
