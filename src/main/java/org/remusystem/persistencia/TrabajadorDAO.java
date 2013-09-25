package org.remusystem.persistencia;

import java.util.Date;
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
 * Trabajador entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Trabajador
 * @author MyEclipse Persistence Tools
 */

public class TrabajadorDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TrabajadorDAO.class);
	// property constants
	public static final String RUT = "rut";
	public static final String NOMBRE = "nombre";
	public static final String APELLIDO_PATERNO = "apellidoPaterno";
	public static final String APELLIDO_MATERNO = "apellidoMaterno";
	public static final String NACIONALIDAD = "nacionalidad";
	public static final String SEXO = "sexo";
	public static final String DIRECCION = "direccion";
	public static final String TELEFONO_FIJO = "telefonoFijo";
	public static final String CELULAR = "celular";
	public static final String EMAIL = "email";
	public static final String NUMERO_CARGAS = "numeroCargas";

	public void save(Trabajador transientInstance) {
		log.debug("saving Trabajador instance");
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
	
	public void update(Trabajador transientInstance) {
		log.debug("saving Trabajador instance");
		try{
			System.out.println("en update");
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(transientInstance);
			tx.commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	public void delete(Trabajador persistentInstance) {
		log.debug("deleting Trabajador instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Trabajador findById(java.lang.Integer id) {
		log.debug("getting Trabajador instance with id: " + id);
		try {
			Trabajador instance = (Trabajador) getSession().get(
					"org.remusystem.persistencia.Trabajador", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Trabajador> findByExample(Trabajador instance) {
		log.debug("finding Trabajador instance by example");
		try {
			List<Trabajador> results = (List<Trabajador>) getSession()
					.createCriteria("org.remusystem.persistencia.Trabajador")
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
		log.debug("finding Trabajador instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Trabajador as model where model."
					+ propertyName + "="+value;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Trabajador> findByRut(Object rut) {
		return findByProperty(RUT, rut);
	}

	public List<Trabajador> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Trabajador> findByApellidoPaterno(Object apellidoPaterno) {
		return findByProperty(APELLIDO_PATERNO, apellidoPaterno);
	}

	public List<Trabajador> findByApellidoMaterno(Object apellidoMaterno) {
		return findByProperty(APELLIDO_MATERNO, apellidoMaterno);
	}

	public List<Trabajador> findByNacionalidad(Object nacionalidad) {
		return findByProperty(NACIONALIDAD, nacionalidad);
	}

	public List<Trabajador> findBySexo(Object sexo) {
		return findByProperty(SEXO, sexo);
	}

	public List<Trabajador> findByDireccion(Object direccion) {
		return findByProperty(DIRECCION, direccion);
	}

	public List<Trabajador> findByTelefonoFijo(Object telefonoFijo) {
		return findByProperty(TELEFONO_FIJO, telefonoFijo);
	}

	public List<Trabajador> findByCelular(Object celular) {
		return findByProperty(CELULAR, celular);
	}

	public List<Trabajador> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<Trabajador> findByNumeroCargas(Object numeroCargas) {
		return findByProperty(NUMERO_CARGAS, numeroCargas);
	}

	public List findAll() {
		log.debug("finding all Trabajador instances");
		try {
			String queryString = "from Trabajador";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Trabajador merge(Trabajador detachedInstance) {
		log.debug("merging Trabajador instance");
		try {
			Trabajador result = (Trabajador) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Trabajador instance) {
		log.debug("attaching dirty Trabajador instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Trabajador instance) {
		log.debug("attaching clean Trabajador instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Trabajador findByRUT(String rutTrabajador) {	
		try {
		String queryString = "from Trabajador as model where model.rut='"+rutTrabajador+"'";
		Query queryObject = getSession().createQuery(queryString);
		List lista=queryObject.list();
		return (Trabajador)(lista.size() == 0 ? null : lista.get(0));
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Trabajador> findByRUTtodos(String rutTrabajador) {	
		try {
		String queryString = "from Trabajador as model where model.rut='"+rutTrabajador+"'";
		Query queryObject = getSession().createQuery(queryString);
		List lista=queryObject.list();
		return (List<Trabajador>)(lista.size() == 0 ? null : lista);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	
}
