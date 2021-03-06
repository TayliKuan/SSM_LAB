package tw.gov.bli.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;


import tw.gov.bli.domain.Dept;
import tw.gov.bli.domain.User;

//因為applicationContext.xml的注入
@Repository
public interface DeptDao {
	//查所有帳戶
	public List<Dept> findAll();
	//新增
	public void insert(Dept dept);
	//修改
	public void update(Dept dept);
	//刪除
	public void delete(String deptno);
	//查一個
    public Dept findByPrimaryKey(String deptno);
    //查詢某部門的員工(一對多)(回傳 Set)
    public List<User> getUsersByDeptno(String deptno);
}
