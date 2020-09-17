package com.emp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.MybatisUtil.MyBaitsUtil;

public class EmpDAO implements EmpDAO_interface{


	@Override
	public EmpVO findByPrimaryKey(Integer empno) {
		//1.�ھ�factory�o��SqlSession
		SqlSession session = MyBaitsUtil.getSessionFactory().openSession();
		//2.��SqlSession�d�ߦC��  ��selectOne
		EmpVO empVO = session.selectOne("com.emp.model.EmpDAO_interface.findByPrimaryKey",empno);//EmpDao.xml��namespace(KEY)
		//3.����귽
		session.close();
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		//1.�ھ�factory�o��SqlSession
		SqlSession session = MyBaitsUtil.getSessionFactory().openSession();
		//2.��SqlSession�d�ߦC��  ��selectList
		List<EmpVO> emps = session.selectList("com.emp.model.EmpDAO_interface.getAll");//EmpDao.xml��namespace(KEY)
		//3.����귽
		session.close();
		return emps;
	}

	@Override
	public List<EmpVO> findByDeptno(Integer deptno) {
		//1.�ھ�factory�o��SqlSession
		SqlSession session = MyBaitsUtil.getSessionFactory().openSession();
		//2.��SqlSession�d�ߦC��  ��selectList
		List<EmpVO> emps = session.selectList("com.emp.model.EmpDAO_interface.findByDeptno",deptno);//EmpDao.xml��namespace(KEY)
		//3.����귽
		session.close();
		return emps;
	}

	@Override
	public List<EmpVO> findByName(String ename) {
		//1.�ھ�factory�o��SqlSession
		SqlSession session = MyBaitsUtil.getSessionFactory().openSession();
		//2.��SqlSession�d�ߦC��  ��selectList
		List<EmpVO> emps = session.selectList("com.emp.model.EmpDAO_interface.findByName",ename);//EmpDao.xml��namespace(KEY)
		//3.����귽
		session.close();
		return emps;
	}


	@Override
	public List<EmpVO> findEmpVOByVO(QueryVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
