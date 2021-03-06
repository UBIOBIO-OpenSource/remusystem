package org.remusystem.actions.reportes;
import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.Action;
import org.hibernate.jdbc.Work;
import org.remusystem.persistencia.HibernateSessionFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.remusystem.persistencia.HibernateSessionFactory.getSession;

public class ReportConector{
	public static String getDriver(){
		return "com.mysql.jdbc.Driver";
	}
  	public static String getURL(){
		return "jdbc:mysql://localhost:3306/remusystem";
	}
	public static String getUSR(){
		return "remusystem";
	}
	public static String getPWD(){
		return "miPWDprivada";
	}

    public static Connection getConnection() throws Exception{
       /* Aquí se puede obtener la sessión, el problema es como devolverla al pool.
        //obteniendo la conección a partir de una sessión de hibernate
        MyWorker worker=new MyWorker();
        getSession().doWork(worker);
        return (Connection) worker.getConnection();
        */
        //Coneccion BD

       Class.forName(getDriver());
       return (Connection) DriverManager.getConnection(getURL(), getUSR(),getPWD());

    }
}
