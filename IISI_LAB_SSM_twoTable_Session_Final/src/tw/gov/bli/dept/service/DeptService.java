package tw.gov.bli.dept.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.bli.dept.dao.DeptDao;
import tw.gov.bli.dept.domain.Dept;
import tw.gov.bli.log.logTest;
import tw.gov.bli.user.domain.User;


@Service
public class DeptService {
	
	//因為applicationContext.xml的注入
	@Autowired
	private DeptDao deptdao;
	//就可以用DAO的方法
	private static Logger logger = LoggerFactory.getLogger(logTest.class);
//------------------------------------------------------------------------------------------------------------------//
	
	//查所有帳戶
	public List<Dept> findAll(){
		List<Dept> depts = deptdao.findAll();
		return depts;
	};
	
//------------------------------------------------------------------------------------------------------------------//
	//新增
	public void insert(Dept dept) {
		deptdao.insert(dept);
		logger.debug("業務層Service 新增成功");
	};
//------------------------------------------------------------------------------------------------------------------//
	//修改
	public void update(Dept dept) {
		deptdao.update(dept);
		logger.debug("業務層Service 修改");
	};
//------------------------------------------------------------------------------------------------------------------//
	//刪除
	public void delete(String deptno) {
		deptdao.delete(deptno);
		logger.debug("業務層Service 刪除");
	};
//------------------------------------------------------------------------------------------------------------------//
	
    //一方查多方
    public List<User> getUsersByDeptno(String deptno) {
		
    	List<User> users =  deptdao.getUsersByDeptno(deptno);
    	return users;
    };	

}
