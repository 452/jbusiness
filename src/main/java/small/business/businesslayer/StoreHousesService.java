package small.business.businesslayer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import small.business.dao.StoreHousesDAO;
import small.business.dao.entity.GoodsOnStoreHouses;
import small.business.dao.entity.StoreHouse;

/**
 *
 * @author root
 */
@Service
public class StoreHousesService {

    @Autowired
    private StoreHousesDAO storeHousesDAO;
    private StoreHouse SelectedElement;
    private GoodsOnStoreHouses currentElement;
    @Resource
    private HistoryService historyService;
    private boolean canAddGoods = false;
    private boolean canEditGoods = false;
    private boolean canSaveGoods = false;
    private boolean canRemoveGoods = false;
    private boolean canChangeNomenclature = false;

    public void saveOrUpdate() {
        if (currentElement != null) {
            if (currentElement.getId() == null) {
                try {
                    currentElement.getNomenclature().setQuantity(currentElement.getNomenclature().getQuantity() + currentElement.getQuantity());
                    currentElement = storeHousesDAO.saveOrUpdate(currentElement);
                    historyService.saveActionOfAdd(HistoryService.STOREHOUSE, currentElement.getNomenclature().getTitle() + " №" + currentElement.getId().toString());
                } catch (Exception ex) {
                    Logger.getLogger(StoreHousesService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (currentElement.getInitialQuantity() != null) {
                    if (!currentElement.getQuantity().equals(currentElement.getInitialQuantity())) {
                        Integer nomenclatureQuantity = currentElement.getNomenclature().getQuantity();

                        if (currentElement.getQuantity() > currentElement.getInitialQuantity()) {
                            nomenclatureQuantity -= (currentElement.getInitialQuantity() - currentElement.getQuantity());
                        }
                        if (currentElement.getQuantity() < currentElement.getInitialQuantity()) {
                            nomenclatureQuantity += (currentElement.getQuantity() - currentElement.getInitialQuantity());
                        }
                        currentElement.setInitialQuantity(null);
                        currentElement.getNomenclature().setQuantity(nomenclatureQuantity);
                        try {
                            currentElement = storeHousesDAO.saveOrUpdate(currentElement);
                        } catch (Exception ex) {
                            Logger.getLogger(StoreHousesService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        historyService.saveActionOfChange(HistoryService.STOREHOUSE, " №" + currentElement.getId().toString() + " " + currentElement.getNomenclature().getTitle());
                    }
                }
            }
        }
    }

    public void validate() {
        setCanAddGoods(true);
        setCanEditGoods(false);
        setCanSaveGoods(false);
        setCanRemoveGoods(false);
        setCanChangeNomenclature(false);
        if (currentElement != null) {
            setCanEditGoods(true);
            if (currentElement.getId() == null) {
                setCanChangeNomenclature(true);
            }
            if (currentElement.getNomenclature() != null) {
                setCanSaveGoods(true);
            }
        }
    }

    public List<StoreHouse> getDataList() {
        return storeHousesDAO.getStoreHousesList();
    }

    public StoreHouse getSelectedElement() {
        return SelectedElement;
    }

    public void setSelectedElement(StoreHouse SelectedElement) {
        this.SelectedElement = SelectedElement;
    }

    public List<GoodsOnStoreHouses> getGoods() {
        return storeHousesDAO.getGoodsOnStoreHouseList();
    }

    public boolean isCanEditGoods() {
        return canEditGoods;
    }

    private void setCanEditGoods(boolean canEditGoods) {
        this.canEditGoods = canEditGoods;
    }

    public GoodsOnStoreHouses getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(GoodsOnStoreHouses currentElement) {
        this.currentElement = currentElement;
    }

    public boolean isCanRemoveGoods() {
        return canRemoveGoods;
    }

    private void setCanRemoveGoods(boolean canRemoveGoods) {
        this.canRemoveGoods = canRemoveGoods;
    }

    public boolean isCanAddGoods() {
        return canAddGoods;
    }

    public void setCanAddGoods(boolean canAddGoods) {
        this.canAddGoods = canAddGoods;
    }

    public boolean isCanChangeNomenclature() {
        return canChangeNomenclature;
    }

    private void setCanChangeNomenclature(boolean canChangeNomenclature) {
        this.canChangeNomenclature = canChangeNomenclature;
    }

    public boolean isCanSaveGoods() {
        return canSaveGoods;
    }

    private void setCanSaveGoods(boolean canSaveGoods) {
        this.canSaveGoods = canSaveGoods;
    }
}