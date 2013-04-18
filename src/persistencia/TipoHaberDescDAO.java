package persistencia;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TipoHaberDesc entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see persistencia.TipoHaberDesc
 * @author MyEclipse Persistence Tools
 */

public class TipoHaberDescDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TipoHaberDescDAO.class);
	// property constants
	public static final String TIPO = "tipo";
	public static final String NOMBRE = "nombre";
	public static final String POSICION = "posicion";

	public void save(TipoHaberDesc transientInstance) {
		log.debug("saving TipoHaberDesc instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TipoHaberDesc persistentInstance) {
		log.debug("deleting TipoHaberDesc instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TipoHaberDesc findById(java.lang.Integer id) {
		log.debug("getting TipoHaberDesc instance with id: " + id);
		try {
			TipoHaberDesc instance = (TipoHaberDesc) getSession().get(
					"persistencia.TipoHaberDesc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TipoHaberDesc> findByExample(TipoHaberDesc instance) {
		log.debug("finding TipoHaberDesc instance by example");
		try {
			List<TipoHaberDesc> results = (List<TipoHaberDesc>) getSession()
					.createCriteria("persistencia.TipoHaberDesc").add(
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
		log.debug("finding TipoHaberDesc instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TipoHaberDesc as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TipoHaberDesc> findByTipo(Object tipo) {
		return findByProperty(TIPO, tipo);
	}

	public List<TipoHaberDesc> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<TipoHaberDesc> findByPosicion(Object posicion) {
		return findByProperty(POSICION, posicion);
	}

	public List findAll() {
		log.debug("finding all TipoHaberDesc instances");
		try {
			String queryString = "from TipoHaberDesc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TipoHaberDesc merge(TipoHaberDesc detachedInstance) {
		log.debug("merging TipoHaberDesc instance");
		try {
			TipoHaberDesc result = (TipoHaberDesc) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TipoHaberDesc instance) {
		log.debug("attaching dirty TipoHaberDesc instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TipoHaberDesc instance) {
		log.debug("attaching clean TipoHaberDesc instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}