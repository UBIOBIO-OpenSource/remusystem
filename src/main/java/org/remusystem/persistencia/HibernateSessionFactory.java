package org.remusystem.persistencia;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateSessionFactory {

    private HibernateSessionFactory() {

    }
	
    public static Session getSession() throws HibernateException {
        return HibernateUtil.getSessionFactory().openSession();
    }

	/**
     *  return session factory
     *
     */
	public static org.hibernate.SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

}
