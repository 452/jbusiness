package small.business.businesslayer;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import small.business.dao.ReturnsOfGoodsDAO;
import small.business.dao.StoreHousesDAO;
import small.business.dao.entity.ReturnedGoods;
import small.business.dao.entity.ReturnsOfGoodsInvoice;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Service
public class ReturnsOfGoodsService {

    static Logger log = Logger.getLogger(ReturnsOfGoodsService.class.getName());
    @Autowired
    private ReturnsOfGoodsDAO returnsOfGoodsDAO;
    @Autowired
    private StoreHousesDAO storeHouseDAO;
    @Resource
    private HistoryService historyService;
    private ReturnsOfGoodsInvoice currentElement;
    private ReturnedGoods currentGoodsElement;
    private boolean canSave = false;
    private boolean canPrint = false;
    private boolean canAddGoods = false;
    private boolean canRemove = false;
    private boolean canEditInvoice = false;
    private boolean canEditGoods = false;
    private boolean canSelectCounterParty = false;
    private boolean canSelectStoreHouse = false;

    public List<ReturnsOfGoodsInvoice> getDataList() {
        return returnsOfGoodsDAO.getInvoicesList();
    }

    public void validate() {
        setCanRemove(false);
        setCanAddGoods(false);
        setCanEditInvoice(false);
        setCanEditGoods(false);
        setCanSave(false);
        setCanPrint(false);
        setCanSelectCounterParty(false);
        setCanSelectStoreHouse(false);
        if (currentElement != null) {
            if (currentElement.getId() == null) {
                setCanSelectCounterParty(true);
                setCanSelectStoreHouse(true);
                setCanAddGoods(true);
            }
            if (((currentElement.getId() == null) && (currentElement.getGoods() != null) && (currentElement.getGoods().size() > 0))) {
                setCanEditGoods(true);
                setCanRemove(true);
            }
            if ((currentElement.getStoreHouse() != null) && (currentElement.getCounterParty() != null) && (currentElement.getGoods() != null) && (currentElement.getGoods().size() > 0)) {
                setCanEditInvoice(true);
                setCanSave(true);
            }
        }
    }

    public void saveOrUpdate() throws Exception {
        try {
            if (currentElement.getId() == null) {
                currentElement = returnsOfGoodsDAO.saveOrUpdate(currentElement);
                if (currentElement.getTypeOfReturns().equals(0)) {
                    updateGoodsQuantityToPurveyorOnStoreHouses();
                    historyService.saveActionOfAdd(HistoryService.RETURN_TO_PURVEYOR_INVOICE, HistoryService.NEW_INVOICE + " №" + currentElement.getId().toString());
                } else {
                    updateGoodsQuantityFromClientOnStoreHouses();
                    historyService.saveActionOfAdd(HistoryService.RETURN_TO_PURVEYOR_INVOICE, HistoryService.NEW_INVOICE + " №" + currentElement.getId().toString());
                }
            }
        } catch (Exception e) {
            log.error("Can't save Returns Invoice", e);
            throw new Exception(e);
        }
    }

    private void updateGoodsQuantityFromClientOnStoreHouses() {
        for (IGoods goods : currentElement.getGoods()) {
            storeHouseDAO.updateQuantityIncrease(goods, currentElement.getStoreHouse());
        }
    }

    private void updateGoodsQuantityToPurveyorOnStoreHouses() {
        for (IGoods goods : currentElement.getGoods()) {
            storeHouseDAO.updateQuantityReduce(goods, currentElement.getStoreHouse());
        }
    }

    public void removeCurrentElement(ReturnsOfGoodsInvoice selectedObject) {
        returnsOfGoodsDAO.remove(selectedObject);
    }

    public ReturnsOfGoodsInvoice getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(ReturnsOfGoodsInvoice currentElement) {
        this.currentElement = currentElement;
    }

    public ReturnedGoods getCurrentGoodsElement() {
        return currentGoodsElement;
    }

    public void setCurrentGoodsElement(ReturnedGoods currentGoodsElement) {
        this.currentGoodsElement = currentGoodsElement;
    }

    public boolean isCanSave() {
        return canSave;
    }

    private void setCanSave(boolean canSave) {
        this.canSave = canSave;
    }

    public boolean isCanPrint() {
        return canPrint;
    }

    private void setCanPrint(boolean canPrint) {
        this.canPrint = canPrint;
    }

    public boolean isCanAddGoods() {
        return canAddGoods;
    }

    private void setCanAddGoods(boolean canAddGoods) {
        this.canAddGoods = canAddGoods;
    }

    public boolean isCanRemove() {
        return canRemove;
    }

    private void setCanRemove(boolean canRemove) {
        this.canRemove = canRemove;
    }

    public boolean isCanEditInvoice() {
        return canEditInvoice;
    }

    private void setCanEditInvoice(boolean canEditInvoice) {
        this.canEditInvoice = canEditInvoice;
    }

    public boolean isCanEditGoods() {
        return canEditGoods;
    }

    private void setCanEditGoods(boolean canEditGoods) {
        this.canEditGoods = canEditGoods;
    }

    public boolean isCanSelectCounterParty() {
        return canSelectCounterParty;
    }

    private void setCanSelectCounterParty(boolean canSelectCounterParty) {
        this.canSelectCounterParty = canSelectCounterParty;
    }

    public boolean isCanSelectStoreHouse() {
        return canSelectStoreHouse;
    }

    private void setCanSelectStoreHouse(boolean canSelectStoreHouse) {
        this.canSelectStoreHouse = canSelectStoreHouse;
    }
}