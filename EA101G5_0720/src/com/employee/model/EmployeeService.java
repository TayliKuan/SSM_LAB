package com.employee.model;

import java.sql.Date;
import java.util.List;
import java.util.Random;

public class EmployeeService {
	
	private EmployeeDAO_interface empdao ;
	
	public EmployeeService() {
		empdao = new EmployeeDAO();
	}
	
	public EmployeeVO addEmp (String ename , String eacc , String epsw ,String email, Date edate, byte[]epic, String esta) {
		
		EmployeeVO empVO = new EmployeeVO();
		empVO.setEname(ename);
		empVO.setEacc(eacc);
		empVO.setEpsw(epsw);
		empVO.setEmail(email);
		empVO.setEdate(edate);
		empVO.setEpic(epic);
		empVO.setEsta(esta);
		
		empdao.insertEmp(empVO);
//		MailService mSvc = new MailService();
//		mSvc.getNewPsw(empVO);
		
		return empVO ;
	}
	
	public EmployeeVO alterEmp (String ename , String eacc , String epsw , String email, Date edate, byte[]epic, String esta , String empno) {
		EmployeeVO empVO = new EmployeeVO();
		empVO.setEname(ename);
		empVO.setEacc(eacc);
		empVO.setEpsw(epsw);
		empVO.setEmail(email);
		empVO.setEdate(edate);
		empVO.setEpic(epic);
		empVO.setEsta(esta);
		empVO.setEmpno(empno);
		empdao.modifyEmpSta(empVO);
		return empVO ;
	}
	
	public void byeEmp (String empno) {
		empdao.deleteEmp(empno);
	}
	
	public EmployeeVO getOneEmp(String empno) {
		return empdao.selectByEmpno(empno);
	}
	
	public List<EmployeeVO> getAllEmp(){
		return empdao.selectAllEmp();
	}
	
	public EmployeeVO forgetPsw (String eacc , String email) {
//		MailService mSvc = new MailService();
//		mSvc.forgetPsw(empdao.forgetPsw(eacc, email));
		return empdao.forgetPsw(eacc, email);	//JDBC測試成功,這包VO有ename email epsw
	}
	
	public EmployeeVO loginCheck (String eacc) {
		return empdao.selectByEacc(eacc);
	}
	
//	public String getRandomString() {
//		String str="abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
//		Random random=new Random();
//		StringBuffer sf=new StringBuffer();
//		for(int i = 0 ; i <10 ; i++) {
//			int number=random.nextInt(62);
//			sf.append(str.charAt(number));
//		}
//		return sf.toString();
//	}
}
