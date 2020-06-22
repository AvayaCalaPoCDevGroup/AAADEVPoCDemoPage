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

/**
 *
 * @author umansilla
 */
public class ComponentsJpaController implements Serializable {

    public ComponentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Components create(Components components) {
        EntityManager em = null;
        Components resp = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(components);
            em.flush();
            em.getTransaction().commit();
            System.out.println("el id del nuevo componente es: " + components.getId());
            resp = components;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return resp;
    }

    public void edit(Components components) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            components = em.merge(components);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = components.getId();
                if (findComponents(id) == null) {
                    throw new NonexistentEntityException("The components with id " + id + " no longer exists.");
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
            Components components;
            try {
                components = em.getReference(Components.class, id);
                components.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The components with id " + id + " no longer exists.", enfe);
            }
            em.remove(components);
            em.getTransaction().commit();
            resp = true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return resp;
    }

    public List<Components> findComponentsEntities() {
        return findComponentsEntities(true, -1, -1);
    }

    public List<Components> findComponentsEntities(int maxResults, int firstResult) {
        return findComponentsEntities(false, maxResults, firstResult);
    }

    private List<Components> findComponentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Components.class));
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

    public Components findComponents(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Components.class, id);
        } finally {
            em.close();
        }
    }

    public int getComponentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Components> rt = cq.from(Components.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Components> obtenerComponentsPorIdDemo(String idDemo) {
        EntityManager em = getEntityManager();
        TypedQuery<Components> namedQuery = em.createNamedQuery("Components.findByIddemo", Components.class);
        namedQuery.setParameter("iddemo", idDemo);
        List<Components> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

}
