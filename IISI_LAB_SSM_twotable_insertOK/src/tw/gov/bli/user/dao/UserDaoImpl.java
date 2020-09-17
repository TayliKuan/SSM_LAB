package tw.gov.bli.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tw.gov.bli.baseDao.BaseDao;
import tw.gov.bli.user.domain.User;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao{
	
	//查詢全部
	@Override
	public List<User> findAll() {
		List<User> list = getSqlSession().selectList("tw.gov.bli.user.dao.UserDao.findAll");
		return list;
	}
	
	//新增
	@Override
	public void insert(User user) {
		getSqlSession().insert("tw.gov.bli.user.dao.UserDao.insert",user);
	}
	
	//修改
	@Override
	public void update(User user) {
		getSqlSession().update("tw.gov.bli.user.dao.UserDao.update",user);
		
	}
	
	//刪除(單一)
	@Override
	public void delete(String uno) {
		getSqlSession().delete("tw.gov.bli.user.dao.UserDao.delete",uno);
	}
	
	//複合查詢
	@Override
	public List<User> findUserByCondition(User user) {
		List<User>list = getSqlSession().selectList("tw.gov.bli.user.dao.UserDao.findUserByCondition",user);
		return list;
	}
	
	//查詢一筆
	@Override
	public User findByPrimaryKey(String uno) {
		User user=getSqlSession().selectOne("tw.gov.bli.user.dao.UserDao.findByPrimaryKey",uno);
		return user;
	}

}
