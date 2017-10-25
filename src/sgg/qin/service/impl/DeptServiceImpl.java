package sgg.qin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sgg.qin.dao.others.DeptDao;
import sgg.qin.domain.Dept;
import sgg.qin.domain.Page;
import sgg.qin.service.DeptService;

/**
 * 
 * @Description: 部门管理service实现类
 * @author: Qin YunFei
 * @date: 2017年10月13日 下午8:33:41
 * @version V1.0
 */
@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;

	/**
	 * 
	 * Description : 获取部门分页信息 <br>
	 * PageDataKeys : <br>
	 * return : List<PageData<String,Object>>
	 */
	@Override
	public Page queryDeptlist(Page page) {
		page.setRetlist(deptDao.queryDeptlist(page));
		return page;
	};

	/**
	 * 
	 * Description : 根据部门id更新部门信息 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	@Override
	public void upadteDeptByDeptno(Dept dept) {
		deptDao.upadteDeptByDeptno(dept);

	};

	/**
	 * 
	 * Description : 添加部门 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	@Override
	public void saveDept(Dept dept) {
		deptDao.saveDept(dept);
	};

	/**
	 * 
	 * Description : 根据部门id删除部门 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	@Override
	public void deleteDeptByDeptno(int deptno) {
		deptDao.deleteDeptByDeptno(deptno);
	};

}
