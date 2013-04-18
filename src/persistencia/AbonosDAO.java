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
 * Abonos entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see persistencia.Abonos
 * @author MyEclipse Persistence Tools
 */

public class AbonosDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AbonosDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String NUMERO_CUOTAS = "numeroCuotas";
	public static final String MONTO = "monto";
	public static final String TIPO_ABONO = "tipoAbono";

	public void save(Abonos transientInstance) {
		log.debug("saving Abonos instance");
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

	public void delete(Abonos persistentInstance) {
		log.debug("deleting Abonos instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void update(Abonos transientInstance) {
		log.debug("saving Abonos instance");
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


	public Abonos findById(java.lang.Integer id) {
		log.debug("getting Abonos instance with id: " + id);
		try {
			Abonos instance = (Abonos) getSession().get("persistencia.Abonos",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Abonos> findByExample(Abonos instance) {
		log.debug("finding Abonos instance by example");
		try {
			List<Abonos> results = (List<Abonos>) getSession().createCriteria(
					"persistencia.Abonos").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Abonos instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Abonos as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Abonos> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Abonos> findByNumeroCuotas(Object numeroCuotas) {
		return findByProperty(NUMERO_CUOTAS, numeroCuotas);
	}

	public List<Abonos> findByMonto(Object monto) {
		return findByProperty(MONTO, monto);
	}

	public List<Abonos> findByTipoAbono(Object tipoAbono) {
		return findByProperty(TIPO_ABONO, tipoAbono);
	}

	public List findAll() {
		log.debug("finding all Abonos instances");
		try {
			String queryString = "from Abonos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Abonos merge(Abonos detachedInstance) {
		log.debug("merging Abonos instance");
		try {
			Abonos result = (Abonos) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Abonos instance) {
		log.debug("attaching dirty Abonos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Abonos instance) {
		log.debug("attaching clean Abonos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}