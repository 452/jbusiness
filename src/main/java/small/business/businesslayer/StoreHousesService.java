package small.business.businesslayer;

import java.util.List;
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
}
