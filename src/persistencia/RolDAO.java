package persistencia;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Rol
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see persistencia.Rol
 * @author MyEclipse Persistence Tools
 */

public class RolDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RolDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String DESCRIPCION = "descripcion";

	public void save(Rol transientInstance) {
		log.debug("saving Rol instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rol persistentInstance) {
		log.debug("deleting Rol instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rol findById(java.lang.Integer id) {
		log.debug("getting Rol instance with id: " + id);
		try {
			Rol instance = (Rol) getSession().get("persistencia.Rol", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Rol> findByExample(Rol instance) {
		log.debug("finding Rol instance by example");
		try {
			List<Rol> results = (List<Rol>) getSession()
					.createCriteria("persistencia.Rol").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Rol instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rol as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Rol> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Rol> findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List findAll() {
		log.debug("finding all Rol instances");
		try {
			String queryString = "from Rol";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Rol merge(Rol detachedInstance) {
		log.debug("merging Rol instance");
		try {
			Rol result = (Rol) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Rol instance) {
		log.debug("attaching dirty Rol instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rol instance) {
		log.debug("attaching clean Rol instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}