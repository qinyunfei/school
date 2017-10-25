package sgg.qin.dao.others;

import java.util.List;
import java.util.Map;

import sgg.qin.domain.Dept;
import sgg.qin.domain.Page;
import sgg.qin.util.PageData;

/**
 * 
 * @Description: 部门管理dao接口
 * @author: Qin YunFei
 * @date: 2017年10月13日 下午6:25:59
 * @version V1.0
 */
public interface DeptDao {

	/**
	 * 
	 * Description : 获取部门分页信息 <br>
	 * PageDataKeys : <br>
	 * return : List<PageData<String,Object>>
	 */
	List<PageData<String, Object>> queryDeptlist(Page page);

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
	
	
	void ccgc(Map<String, Object> map);

}
