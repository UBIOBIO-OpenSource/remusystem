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
 * A data access object (DAO) providing persistence and search support for
 * Valores entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see persistencia.Valores
 * @author MyEclipse Persistence Tools
 */

public class ValoresDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ValoresDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String MONTO = "monto";

	public void save(Valores transientInstance) {
		log.debug("saving Valores instance");
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

	public void delete(Valores persistentInstance) {
		log.debug("deleting Valores instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Valores findById(java.lang.Integer id) {
		log.debug("getting Valores instance with id: " + id);
		try {
			Valores instance = (Valores) getSession().get(
					"persistencia.Valores", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Valores> findByExample(Valores instance) {
		log.debug("finding Valores instance by example");
		try {
			List<Valores> results = (List<Valores>) getSession()
					.createCriteria("persistencia.Valores").add(
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
		log.debug("finding Valores instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Valores as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Valores> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Valores> findByMonto(Object monto) {
		return findByProperty(MONTO, monto);
	}

	public List findAll() {
		log.debug("finding all Valores instances");
		try {
			String queryString = "from Valores";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Valores merge(Valores detachedInstance) {
		log.debug("merging Valores instance");
		try {
			Valores result = (Valores) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Valores instance) {
		log.debug("attaching dirty Valores instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Valores instance) {
		log.debug("attaching clean Valores instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<Valores> findAllGRID() {
		log.debug("finding all Valores instances");
		try {
			Transaction tx= getSession().beginTransaction();
			String queryString = "from Valores";
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

	public void update(Valores transientInstance) {
		log.debug("saving Valores instance");
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