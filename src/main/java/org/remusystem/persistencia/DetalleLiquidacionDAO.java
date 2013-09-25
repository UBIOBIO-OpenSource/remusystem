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
 * DetalleLiquidacion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see DetalleLiquidacion
 * @author MyEclipse Persistence Tools
 */

public class DetalleLiquidacionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(DetalleLiquidacionDAO.class);
	// property constants
	public static final String DESCRIPCION = "descripcion";
	public static final String MONTO = "monto";
	public static final String POSICION = "posicion";
	public static final String EN_PALABRAS = "enPalabras";

	public void save(DetalleLiquidacion transientInstance) {
		log.debug("saving DetalleLiquidacion instance");
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


	public void update(DetalleLiquidacion transientInstance) {
		log.debug("saving DetalleLiquidacion instance");
		try{
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(transientInstance);
			tx.commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	public List<DetalleLiquidacion> findByIdLiquidacion(Integer id) {
		try {
			String queryString = "from DetalleLiquidacion as model where model.liquidacionDeSueldo='"+id+"'";
			Query queryObject = getSession().createQuery(queryString);
			List lista=queryObject.list();
			return (List<DetalleLiquidacion>) (lista.size() == 0 ? null : lista);
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
	}


	public DetalleLiquidacion findByIdLiqandPos(Integer id, int i) {
		try {
			String queryString = "from DetalleLiquidacion as model where model.liquidacionDeSueldo='"+id+"' and model.posicion='"+i+"'";
			Query queryObject = getSession().createQuery(queryString);
			List lista=queryObject.list();
			return (DetalleLiquidacion) (lista.size() == 0 ? null : lista.get(0));
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
	}

	public void delete(DetalleLiquidacion persistentInstance) {
		log.debug("deleting DetalleLiquidacion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DetalleLiquidacion findById(java.lang.Integer id) {
		log.debug("getting DetalleLiquidacion instance with id: " + id);
		try {
			DetalleLiquidacion instance = (DetalleLiquidacion) getSession()
					.get("org.remusystem.persistencia.DetalleLiquidacion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DetalleLiquidacion> findByExample(DetalleLiquidacion instance) {
		log.debug("finding DetalleLiquidacion instance by example");
		try {
			List<DetalleLiquidacion> results = (List<DetalleLiquidacion>) getSession()
					.createCriteria("org.remusystem.persistencia.DetalleLiquidacion").add(
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
		log.debug("finding DetalleLiquidacion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DetalleLiquidacion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DetalleLiquidacion> findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List<DetalleLiquidacion> findByMonto(Object monto) {
		return findByProperty(MONTO, monto);
	}

	public List<DetalleLiquidacion> findByPosicion(Object posicion) {
		return findByProperty(POSICION, posicion);
	}

	public List<DetalleLiquidacion> findByEnPalabras(Object enPalabras) {
		return findByProperty(EN_PALABRAS, enPalabras);
	}

	public List findAll() {
		log.debug("finding all DetalleLiquidacion instances");
		try {
			String queryString = "from DetalleLiquidacion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DetalleLiquidacion merge(DetalleLiquidacion detachedInstance) {
		log.debug("merging DetalleLiquidacion instance");
		try {
			DetalleLiquidacion result = (DetalleLiquidacion) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DetalleLiquidacion instance) {
		log.debug("attaching dirty DetalleLiquidacion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DetalleLiquidacion instance) {
		log.debug("attaching clean DetalleLiquidacion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
