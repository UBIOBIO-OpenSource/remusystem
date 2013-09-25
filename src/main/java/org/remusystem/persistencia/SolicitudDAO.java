package org.remusystem.persistencia;

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
 * Solicitud entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Solicitud
 * @author MyEclipse Persistence Tools
 */

public class SolicitudDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SolicitudDAO.class);

	// property constants

	public void save(Solicitud transientInstance) {
		log.debug("saving Solicitud instance");
		try {
			Session session=HibernateSessionFactory.getSession();
			Transaction tx= session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(Solicitud persistentInstance) {
		log.debug("deleting Solicitud instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*public void delete(Solicitud persistentInstance) {
		log.debug("deleting Solicitud instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}*/

	public Solicitud findById(java.lang.Integer id) {
		log.debug("getting Solicitud instance with id: " + id);
		try {
			Solicitud instance = (Solicitud) getSession().get(
					"org.remusystem.persistencia.Solicitud", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Solicitud> findByExample(Solicitud instance) {
		log.debug("finding Solicitud instance by example");
		try {
			List<Solicitud> results = (List<Solicitud>) getSession()
					.createCriteria("org.remusystem.persistencia.Solicitud").add(
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
		log.debug("finding Solicitud instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Solicitud as model where model."
					+ propertyName + "='"+value+"'";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Solicitud instances");
		try {
			String queryString = "from Solicitud";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Solicitud merge(Solicitud detachedInstance) {
		log.debug("merging Solicitud instance");
		try {
			Solicitud result = (Solicitud) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Solicitud instance) {
		log.debug("attaching dirty Solicitud instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Solicitud instance) {
		log.debug("attaching clean Solicitud instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	

	public List<Solicitud> findByIDrel(Integer id_rel) {
			try {
				String queryString = "from Solicitud as model where model.relacionLaboral='"+id_rel+"'";
				Query queryObject = getSession().createQuery(queryString);
				List lista=queryObject.list();
				return (List<Solicitud>) (lista.size() == 0 ? null : lista);
				} catch (RuntimeException re) {
					log.error("find by property name failed", re);
					throw re;
				}
		}

	public Solicitud findByIdAnticipo(int id_anticipo) {
		try {
			String queryString = "from Solicitud u where u.otrosDescuentos = '" + id_anticipo+ "'";
			Query queryObject = getSession().createQuery(queryString);
			List l = queryObject.list();
			return (Solicitud) (l.size() == 0 ? null : l.get(0));
		} catch (RuntimeException re) {

			throw re;
		}
	}
	
	
}
