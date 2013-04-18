package persistencia;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Privilegio entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see persistencia.Privilegio
 * @author MyEclipse Persistence Tools
 */

public class PrivilegioDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PrivilegioDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String DESCRIPCION = "descripcion";

	public void save(Privilegio transientInstance) {
		log.debug("saving Privilegio instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Privilegio persistentInstance) {
		log.debug("deleting Privilegio instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Privilegio findById(java.lang.Integer id) {
		log.debug("getting Privilegio instance with id: " + id);
		try {
			Privilegio instance = (Privilegio) getSession().get(
					"persistencia.Privilegio", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Privilegio> findByExample(Privilegio instance) {
		log.debug("finding Privilegio instance by example");
		try {
			List<Privilegio> results = (List<Privilegio>) getSession()
					.createCriteria("persistencia.Privilegio")
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
		log.debug("finding Privilegio instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Privilegio as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Privilegio> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Privilegio> findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List findAll() {
		log.debug("finding all Privilegio instances");
		try {
			String queryString = "from Privilegio";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Privilegio merge(Privilegio detachedInstance) {
		log.debug("merging Privilegio instance");
		try {
			Privilegio result = (Privilegio) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Privilegio instance) {
		log.debug("attaching dirty Privilegio instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Privilegio instance) {
		log.debug("attaching clean Privilegio instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}