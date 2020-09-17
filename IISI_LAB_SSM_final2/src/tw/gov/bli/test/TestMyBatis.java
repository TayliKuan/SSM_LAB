//package tw.gov.bli.test;
//
//import java.sql.Date;
//import java.util.List;
//
//import org.junit.Test;
//
//import tw.gov.bli.dao.UserDaoImpl;
//import tw.gov.bli.domain.User;
//
//public class TestMyBatis {
//	UserDaoImpl dao = new UserDaoImpl();
//	
//	//查全部
//		@Test
//		public void testFindAll() {
//			List<User> users = dao.findAll();
//			for (User u : users) {
//				System.out.println(u);
//			}
//		}
//		
//	//新增員工
////		@Test
////		public void testInsert() {
////			User user = new User();
////			user.setUsername("tayli");
////			user.setUserid("A123456789");
////			user.setSex("女");
////			user.setAddress("桃園市");
////			user.setPhone("0900123456");
////			user.setBirthday(java.sql.Date.valueOf("2005-01-01"));
////			user.setJoindate(new Date());
////			
////			dao.insert(user);
////		}
//}
