package org.remusystem.persistencia;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * InstitucionSalud entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see InstitucionSalud
 * @author MyEclipse Persistence Tools
 */

public class InstitucionSaludDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InstitucionSaludDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String COMISION = "comision";
	public static final String PORCENTAJE_DESCUENTO = "porcentajeDescuento";

	public void save(InstitucionSalud transientInstance) {
		log.debug("saving InstitucionSalud instance");
		try {
			Session session=HibernateSessionFactory.getSession();
			Transaction tx= session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		//original
		/*
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}*/
	}
	public void update(InstitucionSalud transientInstance) {
		log.debug("update InstitucionSalud instance");
		try{
			System.out.println("en update");
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(transientInstance);
			tx.commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}
	public void delete(InstitucionSalud persistentInstance) {
		log.debug("deleting InstitucionSalud instance");
		try {
			Session session=HibernateSessionFactory.getSession();
			Transaction tx= session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		/*try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}*/
	}

	public InstitucionSalud findById(java.lang.Integer id) {
		log.debug("getting InstitucionSalud instance with id: " + id);
		try {
			InstitucionSalud instance = (InstitucionSalud) getSession().get(
					"org.remusystem.persistencia.InstitucionSalud", id);
			return instance;
		} catch (RuntimeException re) {
			System.out.println("ha ocurrido una exepcion al buscar por ID una institución de salud"+re);
			log.error("get failed (InstitucionSalud class)", re);
			throw re;
		}
	}

	public List<InstitucionSalud> findByExample(InstitucionSalud instance) {
		log.debug("finding InstitucionSalud instance by example");
		try {
			List<InstitucionSalud> results = (List<InstitucionSalud>) getSession()
					.createCriteria("org.remusystem.persistencia.InstitucionSalud")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding InstitucionSalud instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InstitucionSalud as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<InstitucionSalud> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<InstitucionSalud> findByComision(Object comision) {
		return findByProperty(COMISION, comision);
	}

	public List<InstitucionSalud> findByPorcentajeDescuento(
			Object porcentajeDescuento) {
		return findByProperty(PORCENTAJE_DESCUENTO, porcentajeDescuento);
	}

	public List findAll() {
		log.debug("finding all InstitucionSalud instances");
		try {
			String queryString = "from InstitucionSalud";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InstitucionSalud merge(InstitucionSalud detachedInstance) {
		log.debug("merging InstitucionSalud instance");
		try {
			InstitucionSalud result = (InstitucionSalud) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InstitucionSalud instance) {
		log.debug("attaching dirty InstitucionSalud instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InstitucionSalud instance) {
		log.debug("attaching clean InstitucionSalud instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
