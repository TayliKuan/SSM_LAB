package tw.gov.bli.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.bli.dao.UserDao;
import tw.gov.bli.domain.User;
import tw.gov.bli.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{
	
	//因為applicationContext.xml的注入
	@Autowired
	private UserDao userdao;
	
	@Override
	public List<User> findAll() {
		System.out.println("業務層Service 查所有");
		//就可以用DAO的方法
		return userdao.findAll();
	}

	@Override
	public void insert(User user) {
		userdao.insert(user);
		System.out.println("業務層Service 新增成功");

	}

}
