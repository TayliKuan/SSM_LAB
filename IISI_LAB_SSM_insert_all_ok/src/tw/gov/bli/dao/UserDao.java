package tw.gov.bli.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tw.gov.bli.domain.User;

//因為applicationContext.xml的注入
@Repository
public interface UserDao {
	//查所有帳戶
	public List<User> findAll();
	//新增
	public void insert(User user);
	
	
}
