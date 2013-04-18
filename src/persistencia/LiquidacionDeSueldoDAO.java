package persistencia;

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
 * LiquidacionDeSueldo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see persistencia.LiquidacionDeSueldo
 * @author MyEclipse Persistence Tools
 */

public class LiquidacionDeSueldoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LiquidacionDeSueldoDAO.class);
	// property constants
	public static final String ANIO = "anio";
	public static final String MES = "mes";

	public void save(LiquidacionDeSueldo transientInstance) {
		log.debug("saving LiquidacionDeSueldo instance");
		try {
			Session session=HibernateSessionFactory.getSession();
			Transaction tx= session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			HibernateSessionFactory.closeSession();
			log.debug("save successful");
		} catch (RuntimeException re) {
			HibernateSessionFactory.closeSession();
			log.error("save failed", re);
			throw re;
		}
	}
	

	public void update(LiquidacionDeSueldo transientInstance) {
			log.debug("saving LiquidacionDeSueldo instance");
			try{
				System.out.println("en update");
				Session session = HibernateSessionFactory.getSession();
				Transaction tx = session.beginTransaction();
				session.merge(transientInstance);
				tx.commit();
				session.close();
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
			
		}

	public void delete(LiquidacionDeSueldo persistentInstance) {
		log.debug("deleting LiquidacionDeSueldo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LiquidacionDeSueldo findById(java.lang.Integer id) {
		log.debug("getting LiquidacionDeSueldo instance with id: " + id);
		try {
			LiquidacionDeSueldo instance = (LiquidacionDeSueldo) getSession()
					.get("persistencia.LiquidacionDeSueldo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<LiquidacionDeSueldo> findByExample(LiquidacionDeSueldo instance) {
		log.debug("finding LiquidacionDeSueldo instance by example");
		try {
			List<LiquidacionDeSueldo> results = (List<LiquidacionDeSueldo>) getSession()
					.createCriteria("persistencia.LiquidacionDeSueldo").add(
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
		log.debug("finding LiquidacionDeSueldo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LiquidacionDeSueldo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<LiquidacionDeSueldo> findByAnio(Object anio) {
		return findByProperty(ANIO, anio);
	}

	public List<LiquidacionDeSueldo> findByMes(Object mes) {
		return findByProperty(MES, mes);
	}

	public List findAll() {
		log.debug("finding all LiquidacionDeSueldo instances");
		try {
			String queryString = "from LiquidacionDeSueldo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LiquidacionDeSueldo merge(LiquidacionDeSueldo detachedInstance) {
		log.debug("merging LiquidacionDeSueldo instance");
		try {
			LiquidacionDeSueldo result = (LiquidacionDeSueldo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LiquidacionDeSueldo instance) {
		log.debug("attaching dirty LiquidacionDeSueldo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LiquidacionDeSueldo instance) {
		log.debug("attaching clean LiquidacionDeSueldo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	public LiquidacionDeSueldo findBYIDrelAnioMes(Integer id_rel, Integer numAnio, String mes2) {
		try {
			String queryString = "from LiquidacionDeSueldo u where u.relacionLaboral = '" + id_rel
					+ "' and u.anio = '" + numAnio + "' and u.mes = '" + mes2 + "'";
			Query queryObject = getSession().createQuery(queryString);
			List l = queryObject.list();
			return (LiquidacionDeSueldo) (l.size() == 0 ? null : l.get(0));
		} catch (RuntimeException re) {

			throw re;
		}
	}


	public List<LiquidacionDeSueldo> findByIdRel(Integer id_rel) {
		try {
			String queryString = "from LiquidacionDeSueldo u where u.relacionLaboral = '" + id_rel+ "'";
			Query queryObject = getSession().createQuery(queryString);
			List l = queryObject.list();
			return (List<LiquidacionDeSueldo>) (l.size() == 0 ? null : l);
		} catch (RuntimeException re) {

			throw re;
		}
	}

	
}