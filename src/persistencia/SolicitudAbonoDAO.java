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
 * SolicitudAbono entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see persistencia.SolicitudAbono
 * @author MyEclipse Persistence Tools
 */

public class SolicitudAbonoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SolicitudAbonoDAO.class);

	// property constants

	public void save(SolicitudAbono transientInstance) {
		log.debug("saving SolicitudAbono instance");
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

	public void delete(SolicitudAbono persistentInstance) {
		log.debug("deleting SolicitudAbonos instance");
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

	public SolicitudAbono findById(java.lang.Integer id) {
		log.debug("getting SolicitudAbono instance with id: " + id);
		try {
			SolicitudAbono instance = (SolicitudAbono) getSession().get(
					"persistencia.SolicitudAbono", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SolicitudAbono> findByExample(SolicitudAbono instance) {
		log.debug("finding SolicitudAbono instance by example");
		try {
			List<SolicitudAbono> results = (List<SolicitudAbono>) getSession()
					.createCriteria("persistencia.SolicitudAbono").add(
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
		log.debug("finding SolicitudAbono instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SolicitudAbono as model where model."
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
		log.debug("finding all SolicitudAbono instances");
		try {
			String queryString = "from SolicitudAbono";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SolicitudAbono merge(SolicitudAbono detachedInstance) {
		log.debug("merging SolicitudAbono instance");
		try {
			SolicitudAbono result = (SolicitudAbono) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SolicitudAbono instance) {
		log.debug("attaching dirty SolicitudAbono instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SolicitudAbono instance) {
		log.debug("attaching clean SolicitudAbono instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<SolicitudAbono> findByIDrel(Integer id_rel) {
		try {
			String queryString = "from SolicitudAbono as model where model.relacionLaboral='"+id_rel+"'";
			Query queryObject = getSession().createQuery(queryString);
			List lista=queryObject.list();
			return (List<SolicitudAbono>) (lista.size() == 0 ? null : lista);
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
	}

	public SolicitudAbono findByIdAbono(int id_abono) {
		try {
			String queryString = "from SolicitudAbono u where u.abonos = '" + id_abono+ "'";
			Query queryObject = getSession().createQuery(queryString);
			List l = queryObject.list();
			return (SolicitudAbono) (l.size() == 0 ? null : l.get(0));
		} catch (RuntimeException re) {
			throw re;
		}
	}
}