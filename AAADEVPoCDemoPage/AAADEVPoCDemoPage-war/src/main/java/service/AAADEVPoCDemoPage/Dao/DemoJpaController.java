package service.AAADEVPoCDemoPage.Dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import service.AAADEVPoCDemoPage.Dao.Exceptions.NonexistentEntityException;
import service.AAADEVPoCDemoPage.Entity.Components;
import service.AAADEVPoCDemoPage.Entity.Demo;

/**
 *
 * @author umansilla
 */
public class DemoJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DemoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Demo demo) {
        EntityManager em = null;
//        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(demo);
            em.flush();
            em.getTransaction().commit();
//        } finally {
            if (em != null) {
                em.close();
            }
//        }
    }

    public void edit(Demo demo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            demo = em.merge(demo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = demo.getId();
                if (findDemo(id) == null) {
                    throw new NonexistentEntityException("The demo with id " + id + " no longer exists.");
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
            Demo demo;
            try {
                demo = em.getReference(Demo.class, id);
                demo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The demo with id " + id + " no longer exists.", enfe);
            }
            em.remove(demo);
            em.getTransaction().commit();
            resp = true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return resp;
    }

    public List<Demo> findDemoEntities() {
        return findDemoEntities(true, -1, -1);
    }

    public List<Demo> findDemoEntities(int maxResults, int firstResult) {
        return findDemoEntities(false, maxResults, firstResult);
    }

    private List<Demo> findDemoEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Demo.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
    	EntityManager em = getEntityManager();
        TypedQuery<Demo> namedQuery = em.createNamedQuery("Demo.findAll", Demo.class);
        List<Demo> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public Demo findDemo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Demo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDemoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Demo> rt = cq.from(Demo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
