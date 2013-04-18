package actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;


public class LoadModEmp extends ActionSupport implements SessionAware {

	//Session
		private Map session;
		
	public String load(){
			
		if(session.get("relacion")!=null){
			return SUCCESS;
		}else{
		addActionError("debe realizar una busqueda primero");
		return ERROR;
		}
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

}
