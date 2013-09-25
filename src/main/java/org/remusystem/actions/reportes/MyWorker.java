package org.remusystem.actions.reportes;

import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: miguel
 * Date: 29-08-13
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyWorker implements Work {
    private Connection conn=null;
    @Override
    public void execute(Connection connection) throws SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
        conn=connection;
    }
    public Connection getConnection(){
        return conn;
    }
}
