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
 * ImpuestoSegundaCategoria entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ImpuestoSegundaCategoria
 * @author MyEclipse Persistence Tools
 */

public class ImpuestoSegundaCategoriaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ImpuestoSegundaCategoriaDAO.class);
	// property constants
	public static final String DESDE = "desde";
	public static final String HASTA = "hasta";
	public static final String FACTOR = "factor";
	public static final String REBAJA = "rebaja";

	public void save(ImpuestoSegundaCategoria transientInstance) {
		log.debug("saving ImpuestoSegundaCategoria instance");
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

	public void delete(ImpuestoSegundaCategoria persistentInstance) {
		log.debug("deleting ImpuestoSegundaCategoria instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ImpuestoSegundaCategoria findById(java.lang.Integer id) {
		log.debug("getting ImpuestoSegundaCategoria instance with id: " + id);
		try {
			ImpuestoSegundaCategoria instance = (ImpuestoSegundaCategoria) getSession()
					.get("org.remusystem.persistencia.ImpuestoSegundaCategoria", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ImpuestoSegundaCategoria> findByExample(
			ImpuestoSegundaCategoria instance) {
		log.debug("finding ImpuestoSegundaCategoria instance by example");
		try {
			List<ImpuestoSegundaCategoria> results = (List<ImpuestoSegundaCategoria>) getSession()
					.createCriteria("org.remusystem.persistencia.ImpuestoSegundaCategoria")
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
		log.debug("finding ImpuestoSegundaCategoria instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ImpuestoSegundaCategoria as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ImpuestoSegundaCategoria> findByDesde(Object desde) {
		return findByProperty(DESDE, desde);
	}

	public List<ImpuestoSegundaCategoria> findByHasta(Object hasta) {
		return findByProperty(HASTA, hasta);
	}

	public List<ImpuestoSegundaCategoria> findByFactor(Object factor) {
		return findByProperty(FACTOR, factor);
	}

	public List<ImpuestoSegundaCategoria> findByRebaja(Object rebaja) {
		return findByProperty(REBAJA, rebaja);
	}

	public List findAll() {
		log.debug("finding all ImpuestoSegundaCategoria instances");
		try {
			String queryString = "from ImpuestoSegundaCategoria";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ImpuestoSegundaCategoria merge(
			ImpuestoSegundaCategoria detachedInstance) {
		log.debug("merging ImpuestoSegundaCategoria instance");
		try {
			ImpuestoSegundaCategoria result = (ImpuestoSegundaCategoria) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ImpuestoSegundaCategoria instance) {
		log.debug("attaching dirty ImpuestoSegundaCategoria instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ImpuestoSegundaCategoria instance) {
		log.debug("attaching clean ImpuestoSegundaCategoria instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void update(ImpuestoSegundaCategoria transientInstance) {
		log.debug("saving ImpuestoSegundaCategoria instance");
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

	public List<ImpuestoSegundaCategoria> findAllGRID() {
		log.debug("finding all ImpuestoSegundaCategoria instances");
		try {
			Transaction tx= getSession().beginTransaction();
			String queryString = "from ImpuestoSegundaCategoria";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();		
			List lista=queryObject.list();
			return lista;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	
}
