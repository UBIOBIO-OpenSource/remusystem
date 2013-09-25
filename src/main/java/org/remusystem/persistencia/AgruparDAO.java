package org.remusystem.persistencia;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Agrupar entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Agrupar
 * @author MyEclipse Persistence Tools
 */

public class AgruparDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AgruparDAO.class);

	// property constants

	public void save(Agrupar transientInstance) {
		log.debug("saving Agrupar instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Agrupar persistentInstance) {
		log.debug("deleting Agrupar instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Agrupar findById(java.lang.Integer id) {
		log.debug("getting Agrupar instance with id: " + id);
		try {
			Agrupar instance = (Agrupar) getSession().get(
					"org.remusystem.persistencia.Agrupar", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Agrupar> findByExample(Agrupar instance) {
		log.debug("finding Agrupar instance by example");
		try {
			List<Agrupar> results = (List<Agrupar>) getSession()
					.createCriteria("org.remusystem.persistencia.Agrupar")
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
		log.debug("finding Agrupar instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Agrupar as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Agrupar instances");
		try {
			String queryString = "from Agrupar";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Agrupar merge(Agrupar detachedInstance) {
		log.debug("merging Agrupar instance");
		try {
			Agrupar result = (Agrupar) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Agrupar instance) {
		log.debug("attaching dirty Agrupar instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Agrupar instance) {
		log.debug("attaching clean Agrupar instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
