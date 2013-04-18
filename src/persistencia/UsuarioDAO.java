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
 * Usuario entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see persistencia.Usuario
 * @author MyEclipse Persistence Tools
 */

public class UsuarioDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UsuarioDAO.class);
	// property constants
	public static final String RUT = "rut";
	public static final String CONTRASENA = "contrasena";
	public static final String TIPO = "tipo";
	public static final String NOMBRES = "nombres";
	public static final String APELLIDO_PATERNO = "apellidoPaterno";
	public static final String APELLIDO_MATERNO = "apellidoMaterno";
	public static final String EMAIL = "email";

	public void save(Usuario transientInstance) {
		log.debug("saving Usuario instance");
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
	
	public void update(Usuario transientInstance) {
		log.debug("saving Usuario instance");
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

	public void delete(Usuario persistentInstance) {
		log.debug("deleting Usuario instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usuario findById(java.lang.Integer id) {
		log.debug("getting Usuario instance with id: " + id);
		try {
			Usuario instance = (Usuario) getSession().get(
					"persistencia.Usuario", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Usuario> findByExample(Usuario instance) {
		log.debug("finding Usuario instance by example");
		try {
			List<Usuario> results = (List<Usuario>) getSession()
					.createCriteria("persistencia.Usuario")
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
		log.debug("finding Usuario instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usuario as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Usuario> findByRut(Object rut) {
		return findByProperty(RUT, rut);
	}

	public List<Usuario> findByContrasena(Object contrasena) {
		return findByProperty(CONTRASENA, contrasena);
	}

	public List<Usuario> findByTipo(Object tipo) {
		return findByProperty(TIPO, tipo);
	}

	public List<Usuario> findByNombres(Object nombres) {
		return findByProperty(NOMBRES, nombres);
	}

	public List<Usuario> findByApellidoPaterno(Object apellidoPaterno) {
		return findByProperty(APELLIDO_PATERNO, apellidoPaterno);
	}

	public List<Usuario> findByApellidoMaterno(Object apellidoMaterno) {
		return findByProperty(APELLIDO_MATERNO, apellidoMaterno);
	}

	public List<Usuario> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findAll() {
		log.debug("finding all Usuario instances");
		try {
			String queryString = "from Usuario";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usuario merge(Usuario detachedInstance) {
		log.debug("merging Usuario instance");
		try {
			Usuario result = (Usuario) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuario instance) {
		log.debug("attaching dirty Usuario instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuario instance) {
		log.debug("attaching clean Usuario instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Usuario findByUserAndPass(String rut2, String pass) {
		try {
			String queryString = "from Usuario u where u.rut = '" + rut2
					+ "' and u.contrasena = '" + pass + "'";
			Query queryObject = getSession().createQuery(queryString);
			List l = queryObject.list();
			return (Usuario) (l.size() == 0 ? null : l.get(0));
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public Usuario findByRutString(String rut2) {
		try {
			String queryString = "from Usuario u where u.rut = '" + rut2
					+ "'";
			Query queryObject = getSession().createQuery(queryString);
			List l = queryObject.list();
			return (Usuario) (l.size() == 0 ? null : l.get(0));
		} catch (RuntimeException re) {

			throw re;
		}
	}

	
}