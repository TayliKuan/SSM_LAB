package tw.gov.bli.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.bli.dao.DeptDao;
import tw.gov.bli.domain.Dept;
import tw.gov.bli.domain.User;
import tw.gov.bli.dto.DeptCase;
import tw.gov.bli.dto.UserCase;
import tw.gov.bli.log.logTest;


@Service
public class DeptService {
	
	//因為applicationContext.xml的注入
	@Autowired
	private DeptDao deptdao;
	//就可以用DAO的方法
	private static Logger logger = LoggerFactory.getLogger(logTest.class);
//------------------------------------------------------------------------------------------------------------------//
	
	//查所有帳戶
	public List<DeptCase> findAll(){
		List<Dept> depts = deptdao.findAll();
		List<DeptCase> cases = new ArrayList<DeptCase>();
		for(Dept dept : depts) {
			DeptCase deptCase = new DeptCase();
		    BeanUtils.copyProperties(dept,deptCase);
		    cases.add(deptCase);
		}
		return cases;
	};
	
//------------------------------------------------------------------------------------------------------------------//
	//新增
	public void insert(DeptCase deptCase) {
		deptdao.insert(deptCaseToDept(deptCase));
		logger.debug("業務層Service 新增成功");
	};
//------------------------------------------------------------------------------------------------------------------//
	//修改
	public void update(DeptCase deptCase) {
		deptdao.update(deptCaseToDept(deptCase));
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
    public List<UserCase> getUsersByDeptno(String deptno) {
		
    	
    	List<User> users =  deptdao.getUsersByDeptno(deptno);
    	List<UserCase> cases = new ArrayList<UserCase>();
		for(User user : users) {
			UserCase userCase = new UserCase();
		    BeanUtils.copyProperties(user,userCase);
		    cases.add(userCase);
		}
    	return cases;
    };	
//------------------------------------------------------------------------------------------------------------------//
    //domain把值往前丟dto 後到前 select
    public DeptCase deptDomainToCase(Dept dept) {
    	
    	DeptCase deptCase = new DeptCase();
    	deptCase.setDeptno(dept.getDeptno());
    	deptCase.setDname(dept.getDname());
    	deptCase.setLoc(dept.getLoc());
    	deptCase.setUsers(dept.getUsers());//list問題 	
		return deptCase;//回傳一個usercase
    }
    
    //dto把值傳到domain 前到後 insert update delete 不用回傳VOID
    public Dept deptCaseToDept(DeptCase deptCase) {
    	
    	Dept dept = new Dept();
    	dept.setDeptno(deptCase.getDeptno());
    	dept.setDname(deptCase.getDname());
    	dept.setLoc(deptCase.getLoc());
    	dept.setUsers(deptCase.getUsers());
    	
		return dept;//回傳一個dept
    }
}
