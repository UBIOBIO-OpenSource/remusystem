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
 	* A data access object (DAO) providing persistence and search support for RelacionLaboral entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see RelacionLaboral
  * @author MyEclipse Persistence Tools 
 */

public class RelacionLaboralDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(RelacionLaboralDAO.class);
		//property constants
	public static final String TIPO_CONTRATO = "tipoContrato";
	public static final String SUELDO_BASE = "sueldoBase";
	public static final String RUTA_ARCHIVO_RESPALDO = "rutaArchivoRespaldo";
	public static final String ESTADO = "estado";
	public static final String VALOR_PLAN_ISAPRE = "valorPlanIsapre";



    
	public void save(RelacionLaboral transientInstance) {
		log.debug("saving RelacionLaboral instance");
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
	
	public void update(RelacionLaboral transientInstance) {
		log.debug("saving RelacionLaboral instance");
		try{
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(transientInstance);
			tx.commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

    
	public void delete(RelacionLaboral persistentInstance) {
        log.debug("deleting RelacionLaboral instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
	public RelacionLaboral findById(java.lang.Integer id) {
		log.debug("getting RelacionLaboral instance with id: " + id);
		try {
			RelacionLaboral instance = (RelacionLaboral) getSession().get(
					"org.remusystem.persistencia.RelacionLaboral", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
    
    public List<RelacionLaboral> findByExample(RelacionLaboral instance) {
        log.debug("finding RelacionLaboral instance by example");
        try {
            List<RelacionLaboral> results = (List<RelacionLaboral>) getSession()
                    .createCriteria("org.remusystem.persistencia.RelacionLaboral")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding RelacionLaboral instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from RelacionLaboral as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<RelacionLaboral> findByTipoContrato(Object tipoContrato
	) {
		return findByProperty(TIPO_CONTRATO, tipoContrato
		);
	}
	
	public List<RelacionLaboral> findBySueldoBase(Object sueldoBase
	) {
		return findByProperty(SUELDO_BASE, sueldoBase
		);
	}
	
	public List<RelacionLaboral> findByRutaArchivoRespaldo(Object rutaArchivoRespaldo
	) {
		return findByProperty(RUTA_ARCHIVO_RESPALDO, rutaArchivoRespaldo
		);
	}
	
	public List<RelacionLaboral> findByEstado(Object estado
	) {
		return findByProperty(ESTADO, estado
		);
	}
	
	public List<RelacionLaboral> findByValorPlanIsapre(Object valorPlanIsapre
	) {
		return findByProperty(VALOR_PLAN_ISAPRE, valorPlanIsapre
		);
	}
	

	public List findAll() {
		log.debug("finding all RelacionLaboral instances");
		try {
			String queryString = "from RelacionLaboral";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public RelacionLaboral merge(RelacionLaboral detachedInstance) {
        log.debug("merging RelacionLaboral instance");
        try {
            RelacionLaboral result = (RelacionLaboral) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(RelacionLaboral instance) {
        log.debug("attaching dirty RelacionLaboral instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(RelacionLaboral instance) {
        log.debug("attaching clean RelacionLaboral instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
        
        public RelacionLaboral findByIDtra(Integer id_tra) {
    		try {
    			String queryString = "from RelacionLaboral as model where model.trabajador='"+id_tra+"'";
    			Query queryObject = getSession().createQuery(queryString);
    			List lista=queryObject.list();
    			return (RelacionLaboral)(lista.size() == 0 ? null : lista.get(0));
    			} catch (RuntimeException re) {
    				log.error("find by property name failed", re);
    				throw re;
    			}
    		}

    	public List<RelacionLaboral> findBYrutempresa(Integer id_emp) {
    		try {
    			String queryString = "from RelacionLaboral as model where model.empresa='"+id_emp+"'";
    			Query queryObject = getSession().createQuery(queryString);
    			List lista=queryObject.list();
    			return (List<RelacionLaboral>) (lista.size() == 0 ? null : lista);
    			} catch (RuntimeException re) {
    				log.error("find by property name failed", re);
    				throw re;
    			}
    	}

    	public List<RelacionLaboral> findByIdEmpresaEstado(Integer id) {
    		try {
    			String queryString = "from RelacionLaboral as model where model.empresa='"+id+"' and model.estado='1'";
    			Query queryObject = getSession().createQuery(queryString);
    			List lista=queryObject.list();
    			return (List<RelacionLaboral>) (lista.size() == 0 ? null : lista);
    			} catch (RuntimeException re) {
    				log.error("find by property name failed", re);
    				throw re;
    			}
    	}

		public List<RelacionLaboral> findbyCargo(Integer carTra, Integer id) {
			try {
    			String queryString = "from RelacionLaboral as model where model.empresa='"+id+"' and model.grupoHabDesc='"+carTra+"' and model.estado='1'";
    			Query queryObject = getSession().createQuery(queryString);
    			List lista=queryObject.list();
    			return (List<RelacionLaboral>) (lista.size() == 0 ? null : lista);
    			} catch (RuntimeException re) {
    				log.error("find by property name failed", re);
    				throw re;
    			}
		}

		public List<RelacionLaboral> findByRangoSueldo(Integer id,
				Integer desde, Integer hasta) {
			try {
    			String queryString = "from RelacionLaboral as model where model.empresa='"+id+"' and model.sueldoBase>='"+desde+"' and model.sueldoBase<'"+hasta+"' and model.estado='1'";
    			Query queryObject = getSession().createQuery(queryString);
    			List lista=queryObject.list();
    			return (List<RelacionLaboral>) (lista.size() == 0 ? null : lista);
    			} catch (RuntimeException re) {
    				log.error("find by property name failed", re);
    				throw re;
    			}
		}
		
		public RelacionLaboral findByIdTrabajadorEstado(Integer id) {
    		try {
    			String queryString = "from RelacionLaboral as model where model.empresa='"+id+"' and model.estado='1'";
    			Query queryObject = getSession().createQuery(queryString);
    			List lista=queryObject.list();
    			return (RelacionLaboral) (lista.size() == 0 ? null : lista.get(0));
    			} catch (RuntimeException re) {
    				log.error("find by property name failed", re);
    				throw re;
    			}
    	}

		public RelacionLaboral findByIdTrabajadorEstadoEmpresa(Integer id, Integer id2) {
    		try {
    			String queryString = "from RelacionLaboral as model where model.empresa='"+id2+"' and model.estado='1' and model.trabajador='"+id+"'";
    			Query queryObject = getSession().createQuery(queryString);
    			List lista=queryObject.list();
    			return (RelacionLaboral) (lista.size() == 0 ? null : lista.get(0));
    			} catch (RuntimeException re) {
    				log.error("find by property name failed", re);
    				throw re;
    			}
    	}



	

    }
