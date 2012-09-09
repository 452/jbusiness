package small.business.dao;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import small.business.dao.entity.GoodsOnStoreHouses;
import small.business.dao.entity.StoreHouse;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Repository
public class StoreHousesDAO {

    static Logger log = Logger.getLogger(StoreHousesDAO.class.getName());
    @Resource
    EntityManagerFactory entityManagerFactory;

    /**
     * Збільшення товару на складах
     *
     * @param goods
     * @param store House
     */
    public void updateQuantityIncrease(IGoods element, StoreHouse storeHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query q = entityManager.createNamedQuery("GoodsOnStoreHouses.findByNomenclatureAndStoreHouse");
            q.setParameter("nomenclatureid", element.getNomenclature());
            q.setParameter("storehouseid", storeHouse.getId());
            GoodsOnStoreHouses goods;
            try {
                goods = (GoodsOnStoreHouses) q.getSingleResult();
                entityManager.getTransaction().begin();
                goods.getNomenclature().setQuantity(goods.getNomenclature().getQuantity() + element.getQuantity());
                goods.setQuantity(goods.getQuantity() + element.getQuantity());
                entityManager.merge(goods);
                entityManager.flush();
                entityManager.getTransaction().commit();
            } catch (NoResultException e) {
                log.debug(e);
                goods = new GoodsOnStoreHouses();
                entityManager.getTransaction().begin();
                goods.setNomenclature(element.getNomenclature());
                goods.getNomenclature().setQuantity(goods.getNomenclature().getQuantity() + element.getQuantity());
                goods.setQuantity(element.getQuantity());
                goods.setStorehouseid(storeHouse.getId());
                entityManager.merge(goods);
                entityManager.flush();
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                log.error(e);
            }

        } catch (Exception e) {
            log.error(e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    /**
     * Зменшення товару на складах
     *
     * @param goods
     * @param store House
     */
    public void updateQuantityReduce(IGoods element, StoreHouse storeHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query q = entityManager.createNamedQuery("GoodsOnStoreHouses.findByNomenclatureAndStoreHouse");
            q.setParameter("nomenclatureid", element.getNomenclature());
            q.setParameter("storehouseid", storeHouse.getId());
            GoodsOnStoreHouses goods;
            try {
                goods = (GoodsOnStoreHouses) q.getSingleResult();
                goods.getNomenclature().setQuantity(goods.getNomenclature().getQuantity() - element.getQuantity());
                goods.setQuantity(goods.getQuantity() - element.getQuantity());
                entityManager.merge(goods);
            } catch (NoResultException e) {
                log.debug(e);
                goods = new GoodsOnStoreHouses();
                goods.setNomenclature(element.getNomenclature());
                goods.getNomenclature().setQuantity(goods.getNomenclature().getQuantity() - element.getQuantity());
                goods.setQuantity(element.getQuantity());
                goods.setStorehouseid(storeHouse.getId());
                entityManager.persist(goods);
            } catch (Exception e) {
                log.error(e);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public GoodsOnStoreHouses getGoodsFromStoreHouse(IGoods element, StoreHouse storeHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GoodsOnStoreHouses goods = null;
        try {
            Query q = entityManager.createNamedQuery("GoodsOnStoreHouses.findByNomenclatureAndStoreHouse");
            q.setParameter("nomenclatureid", element.getNomenclature());
            q.setParameter("storehouseid", storeHouse.getId());
            try {
                goods = (GoodsOnStoreHouses) q.getSingleResult();
            } catch (NoResultException e) {
                log.debug(e);
                entityManager.getTransaction().begin();
                goods = new GoodsOnStoreHouses();
                goods.setNomenclature(element.getNomenclature());
                goods.setStorehouseid(storeHouse.getId());
                entityManager.persist(goods);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return goods;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<StoreHouse> getStoreHousesList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List result = Collections.emptyList();
        try {
            result = entityManager.createNamedQuery("StoreHouse.findAll").getResultList();
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<GoodsOnStoreHouses> getGoodsOnStoreHouseList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List result = Collections.emptyList();
        try {
            result = entityManager.createNamedQuery("GoodsOnStoreHouses.findByStorehouseid").setParameter("storehouseid", 1).getResultList();
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return result;
    }

    public GoodsOnStoreHouses saveOrUpdate(GoodsOnStoreHouses currentElement) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GoodsOnStoreHouses result = null;
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
}