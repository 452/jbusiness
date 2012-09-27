package small.business.businesslayer;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import small.business.dao.StoreHousesDAO;
import small.business.dao.entity.GoodsOnStoreHouses;
import small.business.dao.entity.StoreHouse;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Service
public class StoreHousesService {

    static Logger log = Logger.getLogger(StoreHousesService.class.getName());
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
				if (!storeHousesDAO.goodsExist(currentElement)) {
					currentElement = updateGoodsQuantityOnStorehouse(currentElement);
					if (currentElement != null) {
						historyService.saveActionOfAdd(HistoryService.STOREHOUSE, currentElement.getNomenclature().getTitle() + " №" + currentElement.getId().toString());
					}
				}
			} else {
				currentElement = updateGoodsQuantityOnStorehouse(currentElement);
				if (currentElement != null) {
					historyService.saveActionOfChange(HistoryService.STOREHOUSE, " №" + currentElement.getId().toString() + " " + currentElement.getNomenclature().getTitle());
				}
			}
		}
	}

    public IGoods changeIncreaseGoodsQuantityOnStorehouse(IGoods goods, StoreHouse storeHouse) {
        GoodsOnStoreHouses goodsOnStoreHouses = storeHousesDAO.getGoodsFromStoreHouse(goods, storeHouse);
        if (goods.getInitialQuantity() != 0) {
            if (!goods.getQuantity().equals(goods.getInitialQuantity())) {
                goodsOnStoreHouses.setQuantity(goodsOnStoreHouses.getQuantity() + (goods.getQuantity() - goods.getInitialQuantity()));
                goods.setInitialQuantity(0);
                updateGoodsQuantityOnStorehouse(goodsOnStoreHouses);
            }
        }
        return goods;
    }

    public IGoods increaseGoodsQuantityOnStorehouse(IGoods goods, StoreHouse storeHouse) {
        GoodsOnStoreHouses goodsOnStoreHouses = storeHousesDAO.getGoodsFromStoreHouse(goods, storeHouse);
        goodsOnStoreHouses.setQuantity(goodsOnStoreHouses.getQuantity() + goods.getQuantity());
        goods.setInitialQuantity(0);
        updateGoodsQuantityOnStorehouse(goodsOnStoreHouses);
        return goods;
    }

    public IGoods changeReduceGoodsQuantityOnStorehouse(IGoods goods, StoreHouse storeHouse) {
        GoodsOnStoreHouses goodsOnStoreHouses = storeHousesDAO.getGoodsFromStoreHouse(goods, storeHouse);
        if (goods.getInitialQuantity() != 0) {
            if (!goods.getQuantity().equals(goods.getInitialQuantity())) {
                goodsOnStoreHouses.setQuantity(goodsOnStoreHouses.getQuantity() + (goods.getInitialQuantity() - goods.getQuantity()));
                goods.setInitialQuantity(0);
                updateGoodsQuantityOnStorehouse(goodsOnStoreHouses);
            }
        }
        return goods;
    }

    public IGoods reduceGoodsQuantityOnStorehouse(IGoods goods, StoreHouse storeHouse) {
        GoodsOnStoreHouses goodsOnStoreHouses = storeHousesDAO.getGoodsFromStoreHouse(goods, storeHouse);
        goodsOnStoreHouses.setQuantity(goodsOnStoreHouses.getQuantity() - goods.getQuantity());
        goods.setInitialQuantity(0);
        updateGoodsQuantityOnStorehouse(goodsOnStoreHouses);
        return goods;
    }

    public GoodsOnStoreHouses updateGoodsQuantityOnStorehouse(GoodsOnStoreHouses goodsOnStoreHouse) {
        GoodsOnStoreHouses result = null;
        if (goodsOnStoreHouse.getId() == null) {
            goodsOnStoreHouse.getNomenclature().setQuantity(goodsOnStoreHouse.getNomenclature().getQuantity() + goodsOnStoreHouse.getQuantity());
        } else {
            if (!goodsOnStoreHouse.getQuantity().equals(goodsOnStoreHouse.getInitialQuantity())) {
                int nomenclatureQuantity = goodsOnStoreHouse.getNomenclature().getQuantity();
                if (goodsOnStoreHouse.getQuantity() > goodsOnStoreHouse.getInitialQuantity()) {
                    nomenclatureQuantity += goodsOnStoreHouse.getQuantity() - goodsOnStoreHouse.getInitialQuantity();
                }
                if (goodsOnStoreHouse.getQuantity() < goodsOnStoreHouse.getInitialQuantity()) {
                    nomenclatureQuantity -= (goodsOnStoreHouse.getInitialQuantity() - goodsOnStoreHouse.getQuantity());
                }
                goodsOnStoreHouse.setInitialQuantity(0);
                goodsOnStoreHouse.getNomenclature().setQuantity(nomenclatureQuantity);
            }
        }
        try {
            result = storeHousesDAO.saveOrUpdate(goodsOnStoreHouse);
        } catch (Exception ex) {
            log.error(ex);
        }
        return result;
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