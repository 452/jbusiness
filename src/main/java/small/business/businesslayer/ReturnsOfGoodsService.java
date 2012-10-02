package small.business.businesslayer;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import small.business.dao.ReturnsOfGoodsDAO;
import small.business.dao.entity.ReturnedGoods;
import small.business.dao.entity.ReturnsOfGoodsInvoice;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Service
public class ReturnsOfGoodsService extends Invoices<ReturnsOfGoodsService> {

    static Logger log = Logger.getLogger(ReturnsOfGoodsService.class.getName());
    @Autowired
    private ReturnsOfGoodsDAO returnsOfGoodsDAO;
    private ReturnsOfGoodsInvoice currentElement;
    private ReturnedGoods currentGoodsElement;
    private boolean canChangeReturnsType = false;

    public List<ReturnsOfGoodsInvoice> getDataList() {
        return returnsOfGoodsDAO.getInvoicesList();
    }

    public void validate() {
        setCanAddGoods(false);
        setCanEditInvoice(false);
        setCanRemoveInvoice(false);
        setCanEditGoods(false);
        setCanRemoveGoods(false);
        setCanSave(false);
        setCanSaveGoods(false);
        setCanPrint(false);
        setCanChangeNomenclature(false);
        setCanSelectCounterParty(false);
        setCanSelectStoreHouse(false);
        setCanChangeReturnsType(true);
        if (currentElement != null) {
            if (currentElement.getTypeOfReturns() != -1) {
                setCanChangeReturnsType(false);
                setCanAddGoods(true);
            }
            if (currentElement.getId() == null) {
                setCanChangeNomenclature(true);
                setCanSelectCounterParty(true);
                setCanSelectStoreHouse(true);
                if (((currentElement.getId() == null) && (currentElement.getGoods() != null) && (currentElement.getGoods().size() > 0))) {
                    setCanEditGoods(true);
                    setCanRemoveGoods(true);
                }
            }
            if ((currentElement.getStoreHouse() != null) && (currentElement.getCounterParty() != null) && (currentElement.getGoods() != null) && (currentElement.getGoods().size() > 0)) {
                setCanEditInvoice(true);
                setCanEditGoods(true);
                setCanSave(true);
                setCanRemoveGoods(true);
                setCanRemoveInvoice(true);
                setCanPrint(true);
                if (currentGoodsElement != null && currentGoodsElement.getId() == null) {
                    setCanChangeNomenclature(true);
                }
            }
            if (currentGoodsElement != null && currentGoodsElement.getNomenclature() != null) {
                if (getValidateQuantityOfGoods() > 0) {
                    if (currentElement.getTypeOfReturns() == 0) {
                        if (currentGoodsElement.getNomenclature().getQuantity() != 0) {
                            int q = currentGoodsElement.getNomenclature().getQuantity() - (getValidateQuantityOfGoods() - currentGoodsElement.getInitialQuantity());
                            if (q >= 0) {
                                setCanSaveGoods(true);
                            }
                        } else {
                            if (getValidateQuantityOfGoods() <= currentGoodsElement.getInitialQuantity()) {
                                setCanSaveGoods(true);
                            }
                        }
                    } else {
                        if (getValidateQuantityOfGoods() >= (currentGoodsElement.getInitialQuantity() - currentGoodsElement.getNomenclature().getQuantity())) {
                            setCanSaveGoods(true);
                        }
                    }
                }
            }
        }
    }

    public void saveOrUpdate() throws Exception {
        try {
            if (currentElement != null) {
                if (currentElement.getId() == null) {
                    ReturnsOfGoodsInvoice tmpCurrentElement = returnsOfGoodsDAO.saveOrUpdate(currentElement);
                    if (currentElement.getTypeOfReturns() == 0) {
                        for (IGoods goods : currentElement.getGoods()) {
                            storeHousesService.reduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                        }
                        currentElement = tmpCurrentElement;
                        historyService.saveActionOfAdd(HistoryService.RETURN_TO_PURVEYOR_INVOICE, HistoryService.NEW_INVOICE + " №" + currentElement.getId().toString());
                    } else {
                        for (IGoods goods : currentElement.getGoods()) {
                            storeHousesService.increaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                        }
                        currentElement = tmpCurrentElement;
                        historyService.saveActionOfAdd(HistoryService.RETURN_FROM_CLIENT_INVOICE, HistoryService.NEW_INVOICE + " №" + currentElement.getId().toString());
                    }
                } else {
                    removeGoodsIfMarked();
                    ReturnsOfGoodsInvoice tmpCurrentElement = returnsOfGoodsDAO.saveOrUpdate(currentElement);
                    if (currentElement.getTypeOfReturns() == 0) {
                        for (IGoods goods : currentElement.getGoods()) {
                            if (goods.getId() == null) {
                                storeHousesService.reduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                            } else {
                                storeHousesService.changeReduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                            }
                        }
                        currentElement = tmpCurrentElement;
                        historyService.saveActionOfChange(HistoryService.RETURN_TO_PURVEYOR_INVOICE, " №" + currentElement.getId().toString());
                    } else {
                        for (IGoods goods : currentElement.getGoods()) {
                            if (goods.getId() == null) {
                                storeHousesService.increaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                            } else {
                                storeHousesService.changeIncreaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                            }
                        }
                        currentElement = tmpCurrentElement;
                        historyService.saveActionOfChange(HistoryService.RETURN_FROM_CLIENT_INVOICE, " №" + currentElement.getId().toString());
                    }
                }
            }
        } catch (Exception e) {
            log.error("Can't save Returns Invoice", e);
            throw new Exception(e);
        }
    }

    private void removeGoodsIfMarked() {
        if (goodsToRemove.size() > 0) {
            for (IGoods goods : goodsToRemove) {
                if (goods.getId() != null) {
                    if (currentElement.getTypeOfReturns() == 0) {
                        storeHousesService.increaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                        historyService.saveActionOfRemoval(HistoryService.RETURN_TO_PURVEYOR_INVOICE, "Накладна №" + currentElement.getId().toString() + " " + goods.getNomenclature().getTitle());
                    } else {
                        storeHousesService.reduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                        historyService.saveActionOfRemoval(HistoryService.RETURN_FROM_CLIENT_INVOICE, "Накладна №" + currentElement.getId().toString() + " " + goods.getNomenclature().getTitle());
                    }
                }
            }
            goodsToRemove.clear();
        }
    }

    private void updateGoodsQuantityFromClientOnStoreHouses() {
        for (IGoods goods : currentElement.getGoods()) {
            if (goods.getId() == null) {
                log.debug("DDD");
                storeHousesService.changeIncreaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
            } else {
                log.debug("AAA");
                storeHousesService.increaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
            }
        }
    }

    private void updateGoodsQuantityToPurveyorOnStoreHouses() {
        for (IGoods goods : currentElement.getGoods()) {
            if (goods.getId() == null) {
                log.debug("CCC");
                storeHousesService.changeReduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
            } else {
                log.debug("BBB");
                storeHousesService.reduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
            }
        }
    }

    public void removeCurrentElement(ReturnsOfGoodsInvoice selectedObject, boolean alsoChangeQuantityOfGoods) {
        returnsOfGoodsDAO.remove(selectedObject);
        if (currentElement.getTypeOfReturns() == 0) {
            if (alsoChangeQuantityOfGoods) {
                for (IGoods goods : currentElement.getGoods()) {
                    storeHousesService.increaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                }
            }
            historyService.saveActionOfRemoval(HistoryService.RETURN_TO_PURVEYOR_INVOICE, currentElement.getId().toString());
        } else {
            if (alsoChangeQuantityOfGoods) {
                for (IGoods goods : currentElement.getGoods()) {
                    storeHousesService.reduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                }
            }
            historyService.saveActionOfRemoval(HistoryService.RETURN_FROM_CLIENT_INVOICE, currentElement.getId().toString());
        }
    }

    public void removeGoods(IGoods selectedObject) {
        goodsToRemove.add(selectedObject);
        currentElement.getGoods().remove(selectedObject);
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

    public boolean isCanChangeReturnsType() {
        return canChangeReturnsType;
    }

    private void setCanChangeReturnsType(boolean canChangeReturnsType) {
        this.canChangeReturnsType = canChangeReturnsType;
    }
}