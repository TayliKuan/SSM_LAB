package tw.gov.bli.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.bli.dept.domain.Dept;
import tw.gov.bli.log.logTest;
import tw.gov.bli.user.dao.UserDao;
import tw.gov.bli.user.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
	
	//因為applicationContext.xml的注入
	@Autowired
	private UserDao userdao;
	//就可以用DAO的方法
	private static Logger logger = LoggerFactory.getLogger(logTest.class);
//------------------------------------------------------------------------------------------------------------------//
//	@Transactional(readOnly = true)
//	@Transactional(readOnly = false , propagation = Propagation.REQUIRED )
	//查所有帳戶
	public List<User> findAll(){
		List<User> users = userdao.findAll();
		return users;
	};
	
//------------------------------------------------------------------------------------------------------------------//
	//新增
	public void insert(User user) {
		userdao.insert(user);
		logger.debug("業務層Service 新增成功");
	};
//------------------------------------------------------------------------------------------------------------------//
	//修改
	public void update(User user) {
		userdao.update(user);
		logger.debug("業務層Service 修改");
	};
//------------------------------------------------------------------------------------------------------------------//
	//刪除
	public void delete(String uno) {
		userdao.delete(uno);
		logger.debug("業務層Service 刪除");
	};
//------------------------------------------------------------------------------------------------------------------//
	//根據條件查詢
    public List<User> findUserByCondition(User user){

    	//userCase轉user下去查
		List<User> users = userdao.findUserByCondition(user);//根據的條件
		logger.debug("模糊查詢");
    	logger.debug("業務層Service 根據條件查詢");
		return users;
    };
//------------------------------------------------------------------------------------------------------------------//
    //根據編號查一個
    public User findByPrimaryKey(String uno) {
    	
    	User user = new User();
    	user = userdao.findByPrimaryKey(uno);
    	logger.debug("業務層Service 根據編號一個");
		return user;
    };	

}
