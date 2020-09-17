//package tw.gov.bli.dao;
//
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//
//import tw.gov.bli.MybatisUtil.*;
//import tw.gov.bli.domain.User;
//
//public class UserDaoImpl implements UserDao{
//
//	@Override
//	public List<User> findAll() {
//		//1.根據factory得到SqlSession
//		SqlSession session = MyBaitsUtil.getSessionFactory().openSession();
//		//2.用SqlSession查詢列表  用selectList
//		List<User> users = session.selectList("tw.gov.bli.dao.UserDao.findAll");//User.xml的namespace(KEY) tw.gov.bli.dao.UserDao
//		//3.釋放資源
//		session.close();
//		return users;
//	}
//
//	@Override
//	public void insert(User user) {
//		//1.根據factory得到SqlSession
//		SqlSession session = MyBaitsUtil.getSessionFactory().openSession();
//		//2.用SqlSession查詢列表 用insert
//		session.insert("tw.gov.bli.dao.UserDao.insert",user);
//		//3.commit
//		session.commit();
//		//4.釋放資源
//		session.close();
//		
//	}
//
//}
