package actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class End extends ActionSupport implements SessionAware {
	
	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	private Map session;
	
	public String execute(){
		session.clear();
		return SUCCESS;
	}
	

}
