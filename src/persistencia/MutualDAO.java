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
 * Mutual entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see persistencia.Mutual
 * @author MyEclipse Persistence Tools
 */

public class MutualDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MutualDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String COMISION = "comision";
	public static final String PORCENTAJE_DESCUENTO = "porcentajeDescuento";

	public void save(Mutual transientInstance) {
		log.debug("saving Mutual instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Mutual persistentInstance) {
		log.debug("deleting Mutual instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Mutual findById(java.lang.Integer id) {
		log.debug("getting Mutual instance with id: " + id);
		try {
			Mutual instance = (Mutual) getSession().get("persistencia.Mutual",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Mutual> findByExample(Mutual instance) {
		log.debug("finding Mutual instance by example");
		try {
			List<Mutual> results = (List<Mutual>) getSession()
					.createCriteria("persistencia.Mutual")
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
		log.debug("finding Mutual instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Mutual as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Mutual> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Mutual> findByComision(Object comision) {
		return findByProperty(COMISION, comision);
	}

	public List<Mutual> findByPorcentajeDescuento(Object porcentajeDescuento) {
		return findByProperty(PORCENTAJE_DESCUENTO, porcentajeDescuento);
	}

	public List findAll() {
		log.debug("finding all Mutual instances");
		try {
			String queryString = "from Mutual";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Mutual merge(Mutual detachedInstance) {
		log.debug("merging Mutual instance");
		try {
			Mutual result = (Mutual) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Mutual instance) {
		log.debug("attaching dirty Mutual instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Mutual instance) {
		log.debug("attaching clean Mutual instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}