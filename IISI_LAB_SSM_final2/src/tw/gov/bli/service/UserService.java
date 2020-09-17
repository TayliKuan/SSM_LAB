package tw.gov.bli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.bli.dao.UserDao;
import tw.gov.bli.domain.User;

@Service
public class UserService {
	
	//因為applicationContext.xml的注入
	@Autowired
	private UserDao userdao;
	
	//查所有帳戶
	public List<User> findAll(){
		System.out.println("業務層Service 查所有");
		//就可以用DAO的方法
		return userdao.findAll();
	};
	//新增
	public void insert(User user) {
		userdao.insert(user);
		System.out.println("業務層Service 新增成功");
	};
	//修改
	public void update(User user) {
		userdao.update(user);
		System.out.println("業務層Service 修改");
	};
	//刪除
	public void delete(Integer uno) {
		userdao.delete(uno);
		System.out.println("業務層Service 刪除");
	};
	//根據條件查詢
    public List<User> findUserByCondition(User user){
    	System.out.println("業務層Service 根據條件查詢");
		return userdao.findUserByCondition(user);
    };
    //根據編號查一個
    public User findByPrimaryKey(Integer uno) {
    	System.out.println("業務層Service 根據編號一個");
		return userdao.findByPrimaryKey(uno);
    };	
}
