package org.remusystem.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.views.jasperreports.ValueStackDataSource;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: miguel
 * Date: 24-09-13
 * Time: 04:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CloseConectionReport implements Interceptor {

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //before action...
        String result=actionInvocation.invoke();
        //after action...
        //aquí se cierra la conección,
        ValueStack stack = actionInvocation.getStack();
        Connection conn = (Connection) stack.findValue("conexion");
        if(conn!=null && !conn.isClosed()){
            conn.close();
        }
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
