package small.business.businesslayer;

import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import small.business.dao.SettingsDAO;
import small.business.dao.entity.Settings;

/**
 *
 * @author root
 */
@Service
public class SettingsService {

    static Logger log = Logger.getLogger(SettingsService.class.getName());
    private Map<String, Settings> settings;
    @Autowired
    private SettingsDAO settingsDao;
    @Resource
    private HistoryService historyService;

    public Map<String, Settings> getSettings() {
        if (settings == null) {
            settings = settingsDao.getSettings();
        }
        return settings;
    }

    public Object getSetting(String name) {
        return getSettings().get(name);
    }

    public void setSetting(String name, String value) {
        settingsDao.saveSetting(name, value);
        historyService.saveActionOfChange(HistoryService.SETTINGS, name + " - " + value);
        settings = null;
    }

    public double getExchangeRate() {
        return Double.valueOf(getSetting("exchangerate").toString());
    }

    public void setExchangeRate(double exchangeRate) {
        setSetting("exchangerate", Double.valueOf(exchangeRate).toString());
    }

    public void setNameData(String value) {
        setSetting("NameData", value);
    }

    public String getNameData() {
        return getSetting("NameData").toString();
    }
}