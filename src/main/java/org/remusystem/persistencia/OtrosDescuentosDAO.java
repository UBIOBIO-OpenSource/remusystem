package org.remusystem.persistencia;

import java.util.Date;
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
 * OtrosDescuentos entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see OtrosDescuentos
 * @author MyEclipse Persistence Tools
 */

public class OtrosDescuentosDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OtrosDescuentosDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String NUMER_CUOTAS = "numerCuotas";
	public static final String MONTO = "monto";

	public void save(OtrosDescuentos transientInstance) {
		log.debug("saving OtrosDescuentos instance");
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
	}

	public void delete(OtrosDescuentos persistentInstance) {
		log.debug("deleting OtrosDescuentos instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	/*public void delete(OtrosDescuentos persistentInstance) {
		log.debug("deleting OtrosDescuentos instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}*/
	
	public void update(OtrosDescuentos transientInstance) {
		log.debug("saving OtrosDescuentos instance");
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

	public OtrosDescuentos findById(java.lang.Integer id) {
		log.debug("getting OtrosDescuentos instance with id: " + id);
		try {
			OtrosDescuentos instance = (OtrosDescuentos) getSession().get(
					"org.remusystem.persistencia.OtrosDescuentos", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<OtrosDescuentos> findByExample(OtrosDescuentos instance) {
		log.debug("finding OtrosDescuentos instance by example");
		try {
			List<OtrosDescuentos> results = (List<OtrosDescuentos>) getSession()
					.createCriteria("org.remusystem.persistencia.OtrosDescuentos").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding OtrosDescuentos instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtrosDescuentos as model where model."
					+ propertyName + "='"+value+"'";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<OtrosDescuentos> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<OtrosDescuentos> findByNumerCuotas(Object numerCuotas) {
		return findByProperty(NUMER_CUOTAS, numerCuotas);
	}

	public List<OtrosDescuentos> findByMonto(Object monto) {
		return findByProperty(MONTO, monto);
	}

	public List findAll() {
		log.debug("finding all OtrosDescuentos instances");
		try {
			String queryString = "from OtrosDescuentos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtrosDescuentos merge(OtrosDescuentos detachedInstance) {
		log.debug("merging OtrosDescuentos instance");
		try {
			OtrosDescuentos result = (OtrosDescuentos) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtrosDescuentos instance) {
		log.debug("attaching dirty OtrosDescuentos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtrosDescuentos instance) {
		log.debug("attaching clean OtrosDescuentos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
