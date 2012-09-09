package small.business.businesslayer;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import small.business.dao.HistoryDAO;
import small.business.dao.entity.History;

/**
 *
 * @author root
 */
@Service
public class HistoryService {

    final static String SETTINGS = "Налаштування";
    final static String NOMENCLATURE = "Номенклатура";
    final static String COUNTERPARTIES = "Контрагенти";
    final static String COMINGS_INVOICE = "Прихідна накладна";
    final static String OUTPUT_INVOICE = "Видаткова накладна";
    final static String RETURN_TO_PURVEYOR_INVOICE = "Повернення постачальнику";
    final static String RETURN_FROM_CLIENT_INVOICE = "Повернення від клієнта";
    final static String STOREHOUSE = "Склад";
    final static String NEW_INVOICE = "Нова накладна";
    final static String NEW_GOODS = "Новий товар";
    final static String REMOVE_GOODS = "Видалення товару";
    static Logger log = Logger.getLogger(HistoryService.class.getName());
    @Autowired
    private HistoryDAO historyDAO;

    public List<History> getDataList() {
        return historyDAO.getInvoicesList();
    }

    public void saveActionOfAdd(String place, String value) {
        try {
            historyDAO.saveOrUpdate(new History(place, "Створення елементу: " + value));
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    public void saveActionOfChange(String place, String value) {
        try {
            historyDAO.saveOrUpdate(new History(place, "Зміна елементу: " + value));
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    public void saveActionOfRemoval(String place, String value) {
        try {
            historyDAO.saveOrUpdate(new History(place, "Видалення елементу: " + value));
        } catch (Exception ex) {
            log.error(ex);
        }
    }
}