package persistencia;

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
 * InstitucionPrevision entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see persistencia.InstitucionPrevision
 * @author MyEclipse Persistence Tools
 */

public class InstitucionPrevisionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InstitucionPrevisionDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String COMISION = "comision";
	public static final String PORCENTAJE_DESCUENTO = "porcentajeDescuento";

	public void save(InstitucionPrevision transientInstance) {
		log.debug("saving InstitucionPrevision instance");
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
	
	public void update(InstitucionPrevision transientInstance) {
		log.debug("saving InstitucionPrevision instance");
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

	public void delete(InstitucionPrevision persistentInstance) {
		log.debug("deleting InstitucionPrevision instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InstitucionPrevision findById(java.lang.Integer id) {
		log.debug("getting InstitucionPrevision instance with id: " + id);
		try {
			InstitucionPrevision instance = (InstitucionPrevision) getSession()
					.get("persistencia.InstitucionPrevision", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<InstitucionPrevision> findByExample(
			InstitucionPrevision instance) {
		log.debug("finding InstitucionPrevision instance by example");
		try {
			List<InstitucionPrevision> results = (List<InstitucionPrevision>) getSession()
					.createCriteria("persistencia.InstitucionPrevision").add(
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
		log.debug("finding InstitucionPrevision instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InstitucionPrevision as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<InstitucionPrevision> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<InstitucionPrevision> findByComision(Object comision) {
		return findByProperty(COMISION, comision);
	}

	public List<InstitucionPrevision> findByPorcentajeDescuento(
			Object porcentajeDescuento) {
		return findByProperty(PORCENTAJE_DESCUENTO, porcentajeDescuento);
	}

	public List findAll() {
		log.debug("finding all InstitucionPrevision instances");
		try {
			String queryString = "from InstitucionPrevision";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InstitucionPrevision merge(InstitucionPrevision detachedInstance) {
		log.debug("merging InstitucionPrevision instance");
		try {
			InstitucionPrevision result = (InstitucionPrevision) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InstitucionPrevision instance) {
		log.debug("attaching dirty InstitucionPrevision instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<InstitucionPrevision> findAllGRID() {
		log.debug("finding all InstitucionPrevision instances");
		try {
			Transaction tx= getSession().beginTransaction();
			String queryString = "from InstitucionPrevision";
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
}