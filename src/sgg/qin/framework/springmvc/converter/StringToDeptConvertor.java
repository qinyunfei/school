package sgg.qin.framework.springmvc.converter;

import org.springframework.core.convert.converter.Converter;

import sgg.qin.domain.Dept;
import sgg.qin.util.Tools;

public class StringToDeptConvertor implements Converter<String, Dept>{
	
	//编写自定义转换逻辑
	@Override
	public Dept convert(String deptstr) {
		// TODO Auto-generated method stub
		//deptstr:市场部&&2017-09-10
		Dept dept=new Dept();
		if (Tools.notEmpty(deptstr)) {
			String[] split = deptstr.split("&&");
			dept=new Dept(split[0],Tools.strToDate("yyyy-MM-dd", split[1]));
			
		}
		return dept;
	}

}
