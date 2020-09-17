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
	//修改
	public void update(User user);
	//刪除
	public void delete(Integer uno);
	//根據條件查詢
    public List<User> findUserByCondition(User user);
    //根據編號查一個
    public User findByPrimaryKey(Integer uno);
}
