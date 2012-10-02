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
        	log.error(e.getMessage(), e);
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
        	log.error(e.getMessage(), e);
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
        	log.error(e.getMessage(), e);
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
    
	public boolean goodsExist(GoodsOnStoreHouses goodsOnStoreHouses) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		int count = 0;
		try {
			Query q = entityManager.createNamedQuery("GoodsOnStoreHouses.findByNomenclatureAndStoreHouse");
			q.setParameter("nomenclatureid", goodsOnStoreHouses.getNomenclature());
			q.setParameter("storehouseid", goodsOnStoreHouses.getStorehouseid());
			count = q.getResultList().size();
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return count > 0 ? true : false;
	}
}