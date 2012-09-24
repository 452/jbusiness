/*
 * Copyright 2012 <a href="mailto:sp.titan@gmail.com">Emetemunoy</a>
 * 
 * Licensed under the GNU Lesser General Public License, Version 3 (the
 * "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.gnu.org/copyleft/lesser.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package small.business.businesslayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import small.business.dao.ComingInvoicesDAO;
import small.business.dao.entity.ComingsInvoice;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Service
public class ComingsInvoicesService extends Invoices<ComingsInvoicesService> {

    @Autowired
    private ComingInvoicesDAO comingInvoicesDAO;
    private ComingsInvoice currentElement;
    private IGoods currentGoodsElement;

    public List<ComingsInvoice> getDataList() {
        return comingInvoicesDAO.getInvoicesList();
    }

    public void validate() {
        setCanAddGoods(true);
        setCanEditGoods(false);
        setCanEditInvoice(false);
        setCanRemoveInvoice(false);
        setCanRemoveGoods(false);
        setCanSave(false);
        setCanSaveGoods(false);
        setCanChangeNomenclature(false);
        setCanSelectCounterParty(false);
        setCanSelectStoreHouse(false);
        if (currentElement != null) {
            if (currentElement.getId() == null) {
                setCanChangeNomenclature(true);
                setCanSelectCounterParty(true);
                setCanSelectStoreHouse(true);
                if (((currentElement.getId() == null) && (currentElement.getGoods() != null) && (currentElement.getGoods().size() > 0))) {
                    setCanEditGoods(true);
                    setCanRemoveInvoice(true);
                }
            }
            if ((currentElement.getStoreHouse() != null) && (currentElement.getCounterParty() != null) && (currentElement.getGoods() != null) && (currentElement.getGoods().size() > 0)) {
                setCanEditInvoice(true);
                setCanEditGoods(true);
                setCanRemoveInvoice(true);
                setCanRemoveGoods(true);
                setCanSave(true);
                if (currentGoodsElement != null && currentGoodsElement.getId() == null) {
                    setCanChangeNomenclature(true);
                }
            }
			if (currentGoodsElement != null && currentGoodsElement.getNomenclature() != null) {
				if (getValidateQuantityOfGoods() > 0) {
					//  5
					// 15
					// 16
					if (getValidateQuantityOfGoods() >= (currentGoodsElement.getInitialQuantity()-currentGoodsElement.getNomenclature().getQuantity())) {
						setCanSaveGoods(true);
					}
				}
			}
        }
    }

    public ComingsInvoice saveOrUpdate() throws Exception {
        try {
            if (currentElement != null) {
                if (currentElement.getId() == null) {
                    ComingsInvoice tmpCurrentElement = comingInvoicesDAO.saveOrUpdate(currentElement);
                    for (IGoods goods : currentElement.getGoods()) {
                        storeHousesService.increaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                    }
                    currentElement = tmpCurrentElement;
                    historyService.saveActionOfAdd(HistoryService.COMINGS_INVOICE, HistoryService.NEW_INVOICE + " №" + currentElement.getId().toString());
                } else {
                    removeGoodsIfMarked();
                    ComingsInvoice tmpCurrentElement = comingInvoicesDAO.saveOrUpdate(currentElement);
                    for (IGoods goods : currentElement.getGoods()) {
                        storeHousesService.changeIncreaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                    }
                    currentElement = tmpCurrentElement;
                    historyService.saveActionOfChange(HistoryService.COMINGS_INVOICE, " №" + currentElement.getId().toString());
                }
            }
            return currentElement;
        } catch (Exception e) {
            log.error("Can't save Incoming Invoice", e);
            throw new Exception(e);
        }
    }

    private void removeGoodsIfMarked() {
        if (goodsToRemove.size() > 0) {
            for (IGoods goods : goodsToRemove) {
                if (goods.getId() != null) {
                    storeHousesService.reduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                    historyService.saveActionOfRemoval(HistoryService.COMINGS_INVOICE, "Накладна №" + currentElement.getId().toString() + " " + goods.getNomenclature().getTitle());
                }
            }
            goodsToRemove.clear();
        }
    }

    public void removeGoods(IGoods selectedObject) {
        goodsToRemove.add(selectedObject);
        currentElement.getGoods().remove(selectedObject);
    }

    public void removeCurrentElement(ComingsInvoice selectedObject, boolean alsoChangeQuantityOfGoods) {
        comingInvoicesDAO.remove(selectedObject);
        if (alsoChangeQuantityOfGoods) {
            updateGoodsQuantityOnStoreHousesRemoval();
        }
        historyService.saveActionOfRemoval(HistoryService.COMINGS_INVOICE, currentElement.getId().toString());
    }

    private void updateGoodsQuantityOnStoreHousesRemoval() {
        for (IGoods goods : currentElement.getGoods()) {
            storeHousesService.reduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
        }
    }

    public ComingsInvoice getCurrentElement() {
        return this.currentElement;
    }

    public void setCurrentElement(ComingsInvoice currentElement) {
        this.currentElement = currentElement;
    }

    public IGoods getCurrentGoodsElement() {
        return this.currentGoodsElement;
    }

    public void setCurrentGoodsElement(IGoods currentGoodsElement) {
        this.currentGoodsElement = currentGoodsElement;
    }
}