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
 * Empresa entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see persistencia.Empresa
 * @author MyEclipse Persistence Tools
 */

public class EmpresaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(EmpresaDAO.class);
	// property constants
	public static final String RUT = "rut";
	public static final String NOMBRE = "nombre";
	public static final String GIRO = "giro";
	public static final String DIRECCION = "direccion";
	public static final String TELEFONO = "telefono";
	public static final String FAX = "fax";
	public static final String EMAIL = "email";

	public void save(Empresa transientInstance) {
		log.debug("saving Empresa instance");
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
	

	public void update(Empresa transientInstance) {
		log.debug("saving Empresa instance");
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

	public void delete(Empresa persistentInstance) {
		log.debug("deleting Empresa instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Empresa findById(java.lang.Integer id) {
		log.debug("getting Empresa instance with id: " + id);
		try {
			Empresa instance = (Empresa) getSession().get(
					"persistencia.Empresa", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Empresa> findByExample(Empresa instance) {
		log.debug("finding Empresa instance by example");
		try {
			List<Empresa> results = (List<Empresa>) getSession()
					.createCriteria("persistencia.Empresa")
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
		log.debug("finding Empresa instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Empresa as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Empresa> findByRut(Object rut) {
		return findByProperty(RUT, rut);
	}

	public List<Empresa> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Empresa> findByGiro(Object giro) {
		return findByProperty(GIRO, giro);
	}

	public List<Empresa> findByDireccion(Object direccion) {
		return findByProperty(DIRECCION, direccion);
	}

	public List<Empresa> findByTelefono(Object telefono) {
		return findByProperty(TELEFONO, telefono);
	}

	public List<Empresa> findByFax(Object fax) {
		return findByProperty(FAX, fax);
	}

	public List<Empresa> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findAll() {
		log.debug("finding all Empresa instances");
		try {
			String queryString = "from Empresa";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Empresa merge(Empresa detachedInstance) {
		log.debug("merging Empresa instance");
		try {
			Empresa result = (Empresa) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Empresa instance) {
		log.debug("attaching dirty Empresa instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Empresa instance) {
		log.debug("attaching clean Empresa instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Empresa findByRUT(String rutEmpresa) {
		try {
			String queryString = "from Empresa as model where model.rut='"+rutEmpresa+"'";
			Query queryObject = getSession().createQuery(queryString);
			List lista=queryObject.list();
			return (Empresa)(lista.size() == 0 ? null : lista.get(0));
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
		}

}