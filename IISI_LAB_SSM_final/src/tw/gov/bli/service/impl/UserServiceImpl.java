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

	@Override
	public void update(User user) {
		userdao.update(user);
		System.out.println("業務層Service 修改");
	}

	@Override
	public void delete(Integer uno) {
		userdao.delete(uno);
		System.out.println("業務層Service 刪除");
	}

	@Override
	public List<User> findUserByCondition(User user) {
		System.out.println("業務層Service 根據條件查詢");
		return userdao.findUserByCondition(user);
	}

	@Override
	public User findByPrimaryKey(Integer uno) {
		System.out.println("業務層Service 根據編號一個");
		return userdao.findByPrimaryKey(uno);
	}

//	@Override
//	public void deleteMany(String[] unoArray) {
//		System.out.println("業務層Service 批次刪除");
//		userdao.deleteMany(unoArray);
//	}

}
