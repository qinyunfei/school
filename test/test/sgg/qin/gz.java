package test.sgg.qin;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import sgg.qin.dao.others.DeptDao;
import sgg.qin.dao.sys.ResourceDao;
import sgg.qin.domain.sys.Resource;

public class gz extends supTest{
	
	@Autowired
	private DeptDao deptDao;

	@Autowired
	private ResourceDao resourceDao;
	
	@Test
	public void testlist() throws Exception  {
		List<Resource> list = resourceDao.listSubResourceById(0);
		System.out.println(list);

	}

}
