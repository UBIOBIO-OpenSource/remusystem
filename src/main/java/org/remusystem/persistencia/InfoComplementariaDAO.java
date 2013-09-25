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
 * InfoComplementaria entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see InfoComplementaria
 * @author MyEclipse Persistence Tools
 */

public class InfoComplementariaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InfoComplementariaDAO.class);
	// property constants
	public static final String BASE_DE_CALCULO = "baseDeCalculo";
	public static final String FACTOR = "factor";

	public void save(InfoComplementaria transientInstance) {
		log.debug("saving InfoComplementaria instance");
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


public void update(InfoComplementaria transientInstance) {
		log.debug("saving InfoComplementaria instance");
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

	public void delete(InfoComplementaria persistentInstance) {
		log.debug("deleting InfoComplementaria instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InfoComplementaria findById(java.lang.Integer id) {
		log.debug("getting InfoComplementaria instance with id: " + id);
		try {
			InfoComplementaria instance = (InfoComplementaria) getSession()
					.get("org.remusystem.persistencia.InfoComplementaria", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<InfoComplementaria> findByExample(InfoComplementaria instance) {
		log.debug("finding InfoComplementaria instance by example");
		try {
			List<InfoComplementaria> results = (List<InfoComplementaria>) getSession()
					.createCriteria("org.remusystem.persistencia.InfoComplementaria").add(
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
		log.debug("finding InfoComplementaria instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InfoComplementaria as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<InfoComplementaria> findByBaseDeCalculo(Object baseDeCalculo) {
		return findByProperty(BASE_DE_CALCULO, baseDeCalculo);
	}

	public List<InfoComplementaria> findByFactor(Object factor) {
		return findByProperty(FACTOR, factor);
	}

	public List findAll() {
		log.debug("finding all InfoComplementaria instances");
		try {
			String queryString = "from InfoComplementaria";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InfoComplementaria merge(InfoComplementaria detachedInstance) {
		log.debug("merging InfoComplementaria instance");
		try {
			InfoComplementaria result = (InfoComplementaria) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InfoComplementaria instance) {
		log.debug("attaching dirty InfoComplementaria instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InfoComplementaria instance) {
		log.debug("attaching clean InfoComplementaria instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	public InfoComplementaria findByidDetalle(Integer id){
		try {
			String queryString = "from InfoComplementaria as model where model.detalleLiquidacion='"+id+"'";
			Query queryObject = getSession().createQuery(queryString);
			List lista=queryObject.list();
			return (InfoComplementaria) (lista.size() == 0 ? null : lista.get(0));
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
	}
}
