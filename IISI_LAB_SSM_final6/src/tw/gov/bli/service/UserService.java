package tw.gov.bli.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.bli.dao.UserDao;
import tw.gov.bli.domain.User;
import tw.gov.bli.dto.UserCase;

@Service
public class UserService {
	
	//因為applicationContext.xml的注入
	@Autowired
	private UserDao userdao;
	//就可以用DAO的方法
	
//------------------------------------------------------------------------------------------------------------------//
	
	//查所有帳戶
	public List<UserCase> findAll(){
		List<User> users = userdao.findAll();
		System.out.println(users.size());
		List<UserCase> cases = new ArrayList<UserCase>();
		for(User user : users) {
			UserCase userCase = new UserCase();
		    BeanUtils.copyProperties(user,userCase);
		    cases.add(userCase);
		}
		return cases;
	};
	
//------------------------------------------------------------------------------------------------------------------//
	//新增
	public void insert(UserCase userCase) {
		userdao.insert(userCaseToUser(userCase));
		System.out.println("業務層Service 新增成功");
	};
//------------------------------------------------------------------------------------------------------------------//
	//修改
	public void update(UserCase userCase) {
		userdao.update(userCaseToUser(userCase));
		System.out.println("業務層Service 修改");
	};
//------------------------------------------------------------------------------------------------------------------//
	//刪除
	public void delete(String uno) {//usercase的uno
		//轉成user的uno
		User user= new User();
		user.setUno(uno);//usercase的uno
		userdao.delete(user.getUno());
		System.out.println("業務層Service 刪除");
	};
//------------------------------------------------------------------------------------------------------------------//
	//根據條件查詢
    public List<UserCase> findUserByCondition(UserCase userCase){

    	//userCase轉user下去查
		List<User> users = userdao.findUserByCondition(userCaseToUser(userCase));//根據的條件
		System.out.println("模糊查詢");
		System.out.println(users.size());
		List<UserCase> cases = new ArrayList<UserCase>();
		for(User user : users) {
			UserCase c = new UserCase();
		    BeanUtils.copyProperties(user,c);
		    cases.add(c);
		}
		System.out.println(cases.size());
    	System.out.println("業務層Service 根據條件查詢");
		return cases;
    };
//------------------------------------------------------------------------------------------------------------------//
    //根據編號查一個
    public UserCase findByPrimaryKey(String uno) {
    	
    	User user = new User();
    	user = userdao.findByPrimaryKey(uno);
    	
    	System.out.println("業務層Service 根據編號一個");
		return userDomainToCase(user);
    };	
//------------------------------------------------------------------------------------------------------------------//
    //domain把值往前丟dto 後到前 select
    public UserCase userDomainToCase(User user) {
    	
    	UserCase userCase = new UserCase();
    	userCase.setUno(user.getUno());
    	userCase.setUsername(user.getUsername());
    	userCase.setUserid(user.getUserid());
    	userCase.setSex(user.getSex());
    	userCase.setAddress(user.getAddress());
    	userCase.setPhone(user.getPhone());
    	userCase.setBirthday(user.getBirthday());
    	userCase.setJoindate(user.getJoindate());
    	
		return userCase;//回傳一個usercase
    }
    
    //dto把值傳到domain 前到後 insert update delete 不用回傳VOID
    public User userCaseToUser(UserCase userCase) {
    	
    	User user = new User();
    	user.setUno(userCase.getUno());
    	user.setUsername(userCase.getUsername());
    	user.setUserid(userCase.getUserid());
    	user.setSex(userCase.getSex());
    	user.setAddress(userCase.getAddress());
    	user.setPhone(userCase.getPhone());
    	user.setBirthday(userCase.getBirthday());
    	user.setJoindate(userCase.getJoindate());
    	
		return user;//回傳一個user
    }
}
