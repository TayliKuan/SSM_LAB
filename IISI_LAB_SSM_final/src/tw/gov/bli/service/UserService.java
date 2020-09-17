package tw.gov.bli.service;

import java.util.List;

import tw.gov.bli.domain.User;

public interface UserService {
	
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
    //批次刪除
//    public void deleteMany(String[] unoArray);
}
