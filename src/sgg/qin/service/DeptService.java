package sgg.qin.service;

import sgg.qin.domain.Dept;
import sgg.qin.domain.Page;

/**
 * 
 * @Description: 部门管理serice接口
 * @author: Qin YunFei
 * @date: 2017年10月13日 下午8:34:50
 * @version V1.0
 */
public interface DeptService {

	/**
	 * 
	 * Description : 获取部门分页信息 <br>
	 * PageDataKeys : <br>
	 * return : List<PageData<String,Object>>
	 */
	Page queryDeptlist(Page page);

	/**
	 * 
	 * Description : 根据部门id更新部门信息 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	void upadteDeptByDeptno(Dept dept);

	/**
	 * 
	 * Description : 添加部门 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	void saveDept(Dept dept);

	/**
	 * 
	 * Description : 根据部门id删除部门 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	void deleteDeptByDeptno(int deptno);

}