package org.remusystem.persistencia;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * CajaCompensacion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see CajaCompensacion
 * @author MyEclipse Persistence Tools
 */

public class CajaCompensacionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CajaCompensacionDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String COMISION = "comision";
	public static final String PORCENTAJE_DESCUENTO = "porcentajeDescuento";

	public void save(CajaCompensacion transientInstance) {
		log.debug("saving CajaCompensacion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CajaCompensacion persistentInstance) {
		log.debug("deleting CajaCompensacion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CajaCompensacion findById(java.lang.Integer id) {
		log.debug("getting CajaCompensacion instance with id: " + id);
		try {
			CajaCompensacion instance = (CajaCompensacion) getSession().get(
					"org.remusystem.persistencia.CajaCompensacion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CajaCompensacion> findByExample(CajaCompensacion instance) {
		log.debug("finding CajaCompensacion instance by example");
		try {
			List<CajaCompensacion> results = (List<CajaCompensacion>) getSession()
					.createCriteria("org.remusystem.persistencia.CajaCompensacion")
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
		log.debug("finding CajaCompensacion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CajaCompensacion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CajaCompensacion> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<CajaCompensacion> findByComision(Object comision) {
		return findByProperty(COMISION, comision);
	}

	public List<CajaCompensacion> findByPorcentajeDescuento(
			Object porcentajeDescuento) {
		return findByProperty(PORCENTAJE_DESCUENTO, porcentajeDescuento);
	}

	public List findAll() {
		log.debug("finding all CajaCompensacion instances");
		try {
			String queryString = "from CajaCompensacion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CajaCompensacion merge(CajaCompensacion detachedInstance) {
		log.debug("merging CajaCompensacion instance");
		try {
			CajaCompensacion result = (CajaCompensacion) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CajaCompensacion instance) {
		log.debug("attaching dirty CajaCompensacion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CajaCompensacion instance) {
		log.debug("attaching clean CajaCompensacion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
