package small.business.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import small.business.dao.entity.Settings;

/**
 *
 * @author root
 */
@Repository
public class SettingsDAO {

    static Logger log = Logger.getLogger(SettingsDAO.class.getName());
    @Resource
    EntityManagerFactory entityManagerFactory;

    public Map<String, Settings> getSettings() {
        Map<String, Settings> resultsMap = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            resultsMap = new HashMap<String, Settings>();
            @SuppressWarnings("unchecked")
            List<Settings> settings = entityManager.createNamedQuery("Settings.findAll").getResultList();
            for (Settings s : settings) {
                resultsMap.put(s.getName(), s);
            }
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return resultsMap;
    }

    public void saveSetting(String name, String value) {
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
        Settings setting = (Settings) getSettings().get(name);
        setting.setValue(value);
        try {
        	entityManager.getTransaction().begin();
            entityManager.merge(setting);
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