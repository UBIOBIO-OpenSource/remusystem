package persistencia;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Topes
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see persistencia.Topes
 * @author MyEclipse Persistence Tools
 */

public class TopesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TopesDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String MONTO_UF = "montoUf";

	public void save(Topes transientInstance) {
		log.debug("saving Topes instance");
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

	public void delete(Topes persistentInstance) {
		log.debug("deleting Topes instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Topes findById(java.lang.Integer id) {
		log.debug("getting Topes instance with id: " + id);
		try {
			Topes instance = (Topes) getSession().get("persistencia.Topes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Topes> findByExample(Topes instance) {
		log.debug("finding Topes instance by example");
		try {
			List<Topes> results = (List<Topes>) getSession().createCriteria(
					"persistencia.Topes").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Topes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Topes as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Topes> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Topes> findByMontoUf(Object montoUf) {
		return findByProperty(MONTO_UF, montoUf);
	}

	public List findAll() {
		log.debug("finding all Topes instances");
		try {
			String queryString = "from Topes";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Topes merge(Topes detachedInstance) {
		log.debug("merging Topes instance");
		try {
			Topes result = (Topes) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Topes instance) {
		log.debug("attaching dirty Topes instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Topes instance) {
		log.debug("attaching clean Topes instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<Topes> findAllGRID() {
		log.debug("finding all Topes instances");
		try {
			Transaction tx= getSession().beginTransaction();
			String queryString = "from Topes";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();		
			List lista=queryObject.list();
			getSession().close();
			return lista;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void update(Topes transientInstance) {
		log.debug("saving Topes instance");
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
}