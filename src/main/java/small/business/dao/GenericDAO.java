package small.business.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import small.business.domainmodel.interfaces.IElement;

/**
 *
 * @author root
 */
public abstract class GenericDAO<E extends IElement<?>> {

    static Logger log = Logger.getLogger(GenericDAO.class.getName());
    private Class<E> entityClass;
    @Resource(type = EntityManagerFactory.class)
    private EntityManagerFactory entityManagerFactory;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public E getCurrentElement(Long currentElement) {
        // long start = System.currentTimeMillis();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        E element = null;
        try {
            element = entityManager.find(entityClass, currentElement);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            // long end = System.currentTimeMillis();
            // log.debug("Time getCurrentElement: " + (end - start) + " ms");
        }
        return element;
    }

    public boolean isHaveRecordsHierarchicalData(Long parentid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<E> result = Collections.emptyList();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> criteriaQuery = cb.createQuery(entityClass);
            Root<E> from = criteriaQuery.from(entityClass);
            criteriaQuery.where(cb.equal(from.get("parentid"), parentid));
            result = entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return result.isEmpty() ? false : true;
    }

    public List<E> getHierarchicalDataList(Long parentid) {
        long start = System.currentTimeMillis();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<E> result = Collections.emptyList();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> criteriaQuery = cb.createQuery(entityClass);
            Root<E> from = criteriaQuery.from(entityClass);
            criteriaQuery.where(cb.or(cb.equal(from.get("id"), parentid), cb.equal(from.get("parentid"), parentid)));
            result = entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            long end = System.currentTimeMillis();
            log.debug("Time " + entityClass.getSimpleName() + " : " + (end - start) + " ms");
        }
        return result;
    }

    /**
     *
     * @param id
     * @param title
     * @param factoryserialArticul
     * @param additionalArticul
     * @return list of found nomenclature
     */
    public List<E> getNomenclatureSearchDataList(long id, String title, String factorySerialArticul, String additionalArticul) {
        long start = System.currentTimeMillis();
        List<E> result = Collections.emptyList();
        if (id != 0 || !title.isEmpty() || !factorySerialArticul.isEmpty() || !additionalArticul.isEmpty()) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<E> criteriaQuery = cb.createQuery(entityClass);
                Root<E> from = criteriaQuery.from(entityClass);

                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (id != 0) {
                    predicateList.add(cb.equal(from.get("id"), id));
                }
                if (!title.isEmpty()) {
                    predicateList.add(cb.like(cb.upper(from.<String>get("title")), "%" + title + "%"));
                }
                if (!factorySerialArticul.isEmpty()) {
                    predicateList.add(cb.like(cb.upper(from.<String>get("articleofgoods")), "%" + factorySerialArticul + "%"));
                }
                if (!additionalArticul.isEmpty()) {
                    predicateList.add(cb.like(cb.upper(from.<String>get("articleinside")), "%" + additionalArticul + "%"));
                }
                criteriaQuery.where(predicateList.toArray(new Predicate[0]));
                result = entityManager.createQuery(criteriaQuery).getResultList();
            } catch (Exception e) {
            	log.error(e.getMessage(), e);
            } finally {
                if (entityManager.isOpen()) {
                    entityManager.close();
                }
            }
        }
        long end = System.currentTimeMillis();
        log.debug("Time " + entityClass.getSimpleName() + " : " + (end - start) + " ms");
        return result;
    }

    public List<E> getInvoicesList() {
        long start = System.currentTimeMillis();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<E> result = Collections.emptyList();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> criteriaQuery = cb.createQuery(entityClass);
            Root<E> from = criteriaQuery.from(entityClass);
            criteriaQuery.orderBy(cb.desc(from.get("id")));
            result = entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            long end = System.currentTimeMillis();
            log.debug("Time getInvoicesList: " + (end - start) + " ms");
        }
        return result;
    }

    public E saveOrUpdate(E currentElement) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        E result = null;
        try {
            entityManager.getTransaction().begin();
            result = entityManager.merge(currentElement);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return result;
    }

    public void remove(E currentElement) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            E element = entityManager.find(entityClass, currentElement.getId());
            entityManager.refresh(element);
            entityManager.remove(element);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}