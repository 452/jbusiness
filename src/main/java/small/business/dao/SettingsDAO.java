package small.business.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import small.business.dao.entity.Settings;

/**
 *
 * @author root
 */
@Repository
@Transactional
public class SettingsDAO {

    static Logger log = Logger.getLogger(SettingsDAO.class.getName());
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    public Map<String, Settings> getSettings() {
        Map<String, Settings> resultsMap = null;
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

    public void setSetting(String name, String value) {
        Settings setting = (Settings) getSettings().get(name);
        setting.setValue(value);
        try {
            entityManager.merge(setting);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}