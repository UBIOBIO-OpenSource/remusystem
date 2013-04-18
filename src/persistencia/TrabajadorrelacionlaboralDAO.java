package persistencia;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Trabajadorrelacionlaboral entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see persistencia.Trabajadorrelacionlaboral
 * @author MyEclipse Persistence Tools
 */

public class TrabajadorrelacionlaboralDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TrabajadorrelacionlaboralDAO.class);

	// property constants

	public void save(Trabajadorrelacionlaboral transientInstance) {
		log.debug("saving Trabajadorrelacionlaboral instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Trabajadorrelacionlaboral persistentInstance) {
		log.debug("deleting Trabajadorrelacionlaboral instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Trabajadorrelacionlaboral findById(
			persistencia.TrabajadorrelacionlaboralId id) {
		log.debug("getting Trabajadorrelacionlaboral instance with id: " + id);
		try {
			Trabajadorrelacionlaboral instance = (Trabajadorrelacionlaboral) getSession()
					.get("persistencia.Trabajadorrelacionlaboral", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Trabajadorrelacionlaboral> findByExample(
			Trabajadorrelacionlaboral instance) {
		log.debug("finding Trabajadorrelacionlaboral instance by example");
		try {
			List<Trabajadorrelacionlaboral> results = (List<Trabajadorrelacionlaboral>) getSession()
					.createCriteria("persistencia.Trabajadorrelacionlaboral")
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
		log.debug("finding Trabajadorrelacionlaboral instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Trabajadorrelacionlaboral as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Trabajadorrelacionlaboral instances");
		try {
			String queryString = "from Trabajadorrelacionlaboral";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Trabajadorrelacionlaboral merge(
			Trabajadorrelacionlaboral detachedInstance) {
		log.debug("merging Trabajadorrelacionlaboral instance");
		try {
			Trabajadorrelacionlaboral result = (Trabajadorrelacionlaboral) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Trabajadorrelacionlaboral instance) {
		log.debug("attaching dirty Trabajadorrelacionlaboral instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Trabajadorrelacionlaboral instance) {
		log.debug("attaching clean Trabajadorrelacionlaboral instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<Trabajadorrelacionlaboral> findAllIdEmp(Integer id) {
		try {
			String queryString = "from TrabajadorrelacionlaboralId as model where model.idEmpresa='"+id+"' and model.estado='1'";
			Query queryObject = getSession().createQuery(queryString);
			List lista=queryObject.list();
			return (List<Trabajadorrelacionlaboral>)(lista.size() == 0 ? null : lista.get(0));
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
	}

	
}