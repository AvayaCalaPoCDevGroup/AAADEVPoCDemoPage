package service.AAADEVPoCDemoPage.Dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import service.AAADEVPoCDemoPage.Dao.Exceptions.NonexistentEntityException;
import service.AAADEVPoCDemoPage.Entity.Collaterals;

/**
 *
 * @author umansilla
 */
public class CollateralsJpaController implements Serializable {

	private static final long serialVersionUID = 1L;

	public CollateralsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Collaterals create(Collaterals collaterals) {
        EntityManager em = null;
        Collaterals resp = null;
//        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(collaterals);
            em.flush();
            resp = collaterals;
            em.getTransaction().commit();
//        } finally {
            if (em != null) {
                em.close();
            }
//        }
        
        return resp;
    }

    public void edit(Collaterals collaterals) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            collaterals = em.merge(collaterals);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = collaterals.getId();
                if (findCollaterals(id) == null) {
                    throw new NonexistentEntityException("The collaterals with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        boolean resp = false;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collaterals collaterals;
            try {
                collaterals = em.getReference(Collaterals.class, id);
                collaterals.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The collaterals with id " + id + " no longer exists.", enfe);
            }
            em.remove(collaterals);
            em.getTransaction().commit();
            resp =  true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return resp;
    }

    public List<Collaterals> findCollateralsEntities() {
        return findCollateralsEntities(true, -1, -1);
    }

    public List<Collaterals> findCollateralsEntities(int maxResults, int firstResult) {
        return findCollateralsEntities(false, maxResults, firstResult);
    }

    private List<Collaterals> findCollateralsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Collaterals.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Collaterals findCollaterals(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Collaterals.class, id);
        } finally {
            em.close();
        }
    }

    public int getCollateralsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Collaterals> rt = cq.from(Collaterals.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Collaterals> obtenerCollateralsPorIdDemo(String idDemo) {
        EntityManager em = getEntityManager();
        List<Collaterals> result = em.createNativeQuery("SELECT c.id, c.type, c.title, c.updatedtime, c.iddemo, null as file, c.filetype FROM Collaterals c WHERE c.iddemo = '"+idDemo+"'", Collaterals.class).getResultList();
        
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }
    
    public Collaterals obtenerCollateralsPorIdEIdDemo(Long id) {
        EntityManager em = getEntityManager();
        Collaterals collateral = null;
        try {
        	TypedQuery<Collaterals> namedquery = em.createNamedQuery("Collaterals.findById", Collaterals.class);
        	namedquery.setParameter("id", id);
        	List<Collaterals> result = namedquery.getResultList();
        	if (!result.isEmpty()) {
                collateral = result.get(0);
            }
        } catch (NoResultException ex) {
			System.out.println("repositorio create or update, Sin resultados");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("repositorio create or update, exception: " + e.getMessage());
			
		}
        return collateral;
    }
}
