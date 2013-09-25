package org.remusystem.persistencia;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * SeguroCesantia entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see SeguroCesantia
 * @author MyEclipse Persistence Tools
 */

public class SeguroCesantiaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SeguroCesantiaDAO.class);
	// property constants
	public static final String TIPO_CONTRATO = "tipoContrato";
	public static final String EMPLEADOR = "empleador";
	public static final String TRABAJADOR = "trabajador";

	public void save(SeguroCesantia transientInstance) {
		log.debug("saving SeguroCesantia instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SeguroCesantia persistentInstance) {
		log.debug("deleting SeguroCesantia instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SeguroCesantia findById(java.lang.Integer id) {
		log.debug("getting SeguroCesantia instance with id: " + id);
		try {
			SeguroCesantia instance = (SeguroCesantia) getSession().get(
					"org.remusystem.persistencia.SeguroCesantia", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SeguroCesantia> findByExample(SeguroCesantia instance) {
		log.debug("finding SeguroCesantia instance by example");
		try {
			List<SeguroCesantia> results = (List<SeguroCesantia>) getSession()
					.createCriteria("org.remusystem.persistencia.SeguroCesantia").add(
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
		log.debug("finding SeguroCesantia instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SeguroCesantia as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SeguroCesantia> findByTipoContrato(Object tipoContrato) {
		return findByProperty(TIPO_CONTRATO, tipoContrato);
	}

	public List<SeguroCesantia> findByEmpleador(Object empleador) {
		return findByProperty(EMPLEADOR, empleador);
	}

	public List<SeguroCesantia> findByTrabajador(Object trabajador) {
		return findByProperty(TRABAJADOR, trabajador);
	}

	public List findAll() {
		log.debug("finding all SeguroCesantia instances");
		try {
			String queryString = "from SeguroCesantia";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SeguroCesantia merge(SeguroCesantia detachedInstance) {
		log.debug("merging SeguroCesantia instance");
		try {
			SeguroCesantia result = (SeguroCesantia) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SeguroCesantia instance) {
		log.debug("attaching dirty SeguroCesantia instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SeguroCesantia instance) {
		log.debug("attaching clean SeguroCesantia instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
