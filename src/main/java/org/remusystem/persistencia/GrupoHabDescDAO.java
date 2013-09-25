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
 * GrupoHabDesc entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see GrupoHabDesc
 * @author MyEclipse Persistence Tools
 */

public class GrupoHabDescDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(GrupoHabDescDAO.class);
	// property constants
	public static final String CARGO = "cargo";

	public void save(GrupoHabDesc transientInstance) {
		log.debug("saving GrupoHabDesc instance");
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

	public void delete(GrupoHabDesc persistentInstance) {
		log.debug("deleting GrupoHabDesc instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GrupoHabDesc findById(java.lang.Integer id) {
		log.debug("getting GrupoHabDesc instance with id: " + id);
		try {
			GrupoHabDesc instance = (GrupoHabDesc) getSession().get(
					"org.remusystem.persistencia.GrupoHabDesc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GrupoHabDesc> findByExample(GrupoHabDesc instance) {
		log.debug("finding GrupoHabDesc instance by example");
		try {
			List<GrupoHabDesc> results = (List<GrupoHabDesc>) getSession()
					.createCriteria("org.remusystem.persistencia.GrupoHabDesc")
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
		log.debug("finding GrupoHabDesc instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from GrupoHabDesc as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GrupoHabDesc> findByCargo(Object cargo) {
		return findByProperty(CARGO, cargo);
	}

	public List findAll() {
		log.debug("finding all GrupoHabDesc instances");
		try {
			String queryString = "from GrupoHabDesc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GrupoHabDesc merge(GrupoHabDesc detachedInstance) {
		log.debug("merging GrupoHabDesc instance");
		try {
			GrupoHabDesc result = (GrupoHabDesc) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GrupoHabDesc instance) {
		log.debug("attaching dirty GrupoHabDesc instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GrupoHabDesc instance) {
		log.debug("attaching clean GrupoHabDesc instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public GrupoHabDesc findBynombre(String nombre_cargo) {	
		try {
			String queryString = "from GrupoHabDesc as model where model.cargo='"+nombre_cargo+"'";
			Query queryObject = getSession().createQuery(queryString);
			List lista=queryObject.list();
			return (GrupoHabDesc)(lista.size() == 0 ? null : lista.get(0));
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
		}
}
