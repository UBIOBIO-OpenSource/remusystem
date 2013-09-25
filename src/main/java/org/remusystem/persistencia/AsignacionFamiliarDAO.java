package org.remusystem.persistencia;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * AsignacionFamiliar entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see AsignacionFamiliar
 * @author MyEclipse Persistence Tools
 */

public class AsignacionFamiliarDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AsignacionFamiliarDAO.class);
	// property constants
	public static final String TRAMO = "tramo";
	public static final String MONTO = "monto";
	public static final String DESDE = "desde";
	public static final String HASTA = "hasta";

	public void save(AsignacionFamiliar transientInstance) {
			log.debug("saving AsignacionFamiliar instance");
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

	public void delete(AsignacionFamiliar persistentInstance) {
		log.debug("deleting AsignacionFamiliar instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AsignacionFamiliar findById(java.lang.Integer id) {
		log.debug("getting AsignacionFamiliar instance with id: " + id);
		try {
			AsignacionFamiliar instance = (AsignacionFamiliar) getSession()
					.get("org.remusystem.persistencia.AsignacionFamiliar", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AsignacionFamiliar> findByExample(AsignacionFamiliar instance) {
		log.debug("finding AsignacionFamiliar instance by example");
		try {
			List<AsignacionFamiliar> results = (List<AsignacionFamiliar>) getSession()
					.createCriteria("org.remusystem.persistencia.AsignacionFamiliar").add(
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
		log.debug("finding AsignacionFamiliar instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AsignacionFamiliar as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AsignacionFamiliar> findByTramo(Object tramo) {
		return findByProperty(TRAMO, tramo);
	}

	public List<AsignacionFamiliar> findByMonto(Object monto) {
		return findByProperty(MONTO, monto);
	}

	public List<AsignacionFamiliar> findByDesde(Object desde) {
		return findByProperty(DESDE, desde);
	}

	public List<AsignacionFamiliar> findByHasta(Object hasta) {
		return findByProperty(HASTA, hasta);
	}

	public List findAll() {
		log.debug("finding all AsignacionFamiliar instances");
		try {
			String queryString = "from AsignacionFamiliar";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AsignacionFamiliar merge(AsignacionFamiliar detachedInstance) {
		log.debug("merging AsignacionFamiliar instance");
		try {
			AsignacionFamiliar result = (AsignacionFamiliar) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AsignacionFamiliar instance) {
		log.debug("attaching dirty AsignacionFamiliar instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AsignacionFamiliar instance) {
		log.debug("attaching clean AsignacionFamiliar instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<AsignacionFamiliar> findAllGRID() {
		log.debug("finding all AsignacionFamiliar instances");
		try {
			Transaction tx= getSession().beginTransaction();
			String queryString = "from AsignacionFamiliar";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();		
			List lista=queryObject.list();
			return lista;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void update(AsignacionFamiliar transientInstance) {
		log.debug("saving AsignacionFamiliar instance");
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
}
