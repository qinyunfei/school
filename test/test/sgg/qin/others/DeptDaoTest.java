package test.sgg.qin.others;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import sgg.qin.dao.others.DeptDao;
import sgg.qin.domain.Dept;
import sgg.qin.domain.Page;
import sgg.qin.util.PageData;
import test.sgg.qin.supTest;


public class DeptDaoTest extends supTest{
	
	@Autowired
	private DeptDao deptDao;
	
	@Test
	public void testQueryDeptlist() {
		List<PageData<String,Object>> list = deptDao.queryDeptlist(new Page());
		System.out.println(list);
	}

	@Test
	public void testUpadteDeptByDeptno() {
		List<PageData<String,Object>> list = deptDao.queryDeptlist(new Page());
		Dept dept = list.get(0).getbean(Dept.class);
		dept.setDname("大数据部");
		deptDao.upadteDeptByDeptno(dept);

	}

	@Test
	public void testSaveDept() {
		List<PageData<String,Object>> list = deptDao.queryDeptlist(new Page());
		Dept dept = list.get(0).getbean(Dept.class);
		dept.setDname("研发部");
		deptDao.saveDept(dept);
	}

	@Test
	public void testDeleteDeptByDeptno() {
		deptDao.deleteDeptByDeptno(4);
	}

}
