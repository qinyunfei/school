package sgg.qin.framework.shiro.session;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

public class MySessionDAO  extends CachingSessionDAO{

	@Override
	protected void doDelete(Session arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doUpdate(Session arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Serializable doCreate(Session arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Session doReadSession(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
