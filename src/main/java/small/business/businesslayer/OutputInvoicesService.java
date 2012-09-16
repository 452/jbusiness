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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import small.business.dao.OutputInvoicesDAO;
import small.business.dao.entity.CounterParties;
import small.business.dao.entity.OutputGoods;
import small.business.dao.entity.OutputInvoice;
import small.business.dao.entity.StoreHouse;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Service
public class OutputInvoicesService {

    static Logger log = Logger.getLogger(OutputInvoicesService.class.getName());
    @Autowired
    private OutputInvoicesDAO outputInvoicesDAO;
    @Resource
    private HistoryService historyService;
    @Resource
    private StoreHousesService storeHousesService;
    private OutputInvoice currentElement;
    private OutputGoods currentGoodsElement;
    private boolean canSave = false;
    private boolean canPrint = false;
    private boolean canAddGoods = false;
    private boolean canRemoveGoods = false;
    private boolean canRemoveInvoice = false;
    private boolean canEditInvoice = false;
    private boolean canEditGoods = false;
    private boolean canChangeNomenclature = false;
    private boolean canSelectCounterParty = false;
    private boolean canSelectStoreHouse = false;
    private Set<OutputGoods> goodsToRemove = new HashSet<OutputGoods>();

    public List<OutputInvoice> getDataList() {
        return outputInvoicesDAO.getInvoicesList();
    }

    public void validate() {
        setCanAddGoods(true);
        setCanEditInvoice(false);
        setCanEditGoods(false);
        setCanRemoveInvoice(false);
        setCanRemoveGoods(false);
        setCanSave(false);
        setCanPrint(false);
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
        }
    }

    public OutputInvoice saveOrUpdate() throws Exception {
        try {
            if (currentElement != null) {
                if (currentElement.getId() == null) {
                    OutputInvoice tmpCurrentElement = outputInvoicesDAO.saveOrUpdate(currentElement);
                    for (OutputGoods goods : currentElement.getGoods()) {
                        storeHousesService.reduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                    }
                    currentElement = tmpCurrentElement;
                    historyService.saveActionOfAdd(HistoryService.OUTPUT_INVOICE, HistoryService.NEW_INVOICE + " №" + currentElement.getId().toString());
                } else {
                    removeGoodsIfMarked();
                    OutputInvoice tmpCurrentElement = outputInvoicesDAO.saveOrUpdate(currentElement);
                    for (OutputGoods goods : currentElement.getGoods()) {
                        storeHousesService.changeReduceGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                    }
                    currentElement = tmpCurrentElement;
                    historyService.saveActionOfChange(HistoryService.OUTPUT_INVOICE, " №" + currentElement.getId().toString());
                }
            }
            return currentElement;
        } catch (Exception e) {
            log.error("Can't save Output Invoice", e);
            throw new Exception(e);
        }
    }

    private void removeGoodsIfMarked() {
        if (goodsToRemove.size() > 0) {
            for (OutputGoods goods : goodsToRemove) {
                if (goods.getId() != null) {
                    storeHousesService.increaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
                    historyService.saveActionOfRemoval(HistoryService.OUTPUT_INVOICE, "Накладна №" + currentElement.getId().toString() + " " + goods.getNomenclature().getTitle());
                }
            }
            goodsToRemove.clear();
        }
    }

    public void removeCurrentElement(OutputInvoice selectedObject, boolean alsoChangeQuantityOfGoods) {
        outputInvoicesDAO.remove(selectedObject);
        if (alsoChangeQuantityOfGoods) {
            updateGoodsQuantityOnStoreHousesRemoval();
        }
        historyService.saveActionOfRemoval(HistoryService.OUTPUT_INVOICE, currentElement.getId().toString());
    }

    private void updateGoodsQuantityOnStoreHousesRemoval() {
        for (IGoods goods : currentElement.getGoods()) {
            storeHousesService.increaseGoodsQuantityOnStorehouse(goods, currentElement.getStoreHouse());
        }
    }

    public void removeGoods(OutputGoods selectedObject) {
        goodsToRemove.add(selectedObject);
        currentElement.getGoods().remove(selectedObject);
    }

    public OutputInvoice getCurrentElement() {
        return this.currentElement;
    }

    public void setCurrentElement(OutputInvoice currentElement) {
        this.currentElement = currentElement;
    }

    public boolean isCanRemoveInvoice() {
        return canRemoveInvoice;
    }

    private void setCanRemoveInvoice(boolean canRemove) {
        this.canRemoveInvoice = canRemove;
    }

    public boolean isCanRemoveGoods() {
        return canRemoveGoods;
    }

    private void setCanRemoveGoods(boolean canRemove) {
        this.canRemoveGoods = canRemove;
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

    public boolean isCanSave() {
        return canSave;
    }

    private void setCanSave(boolean canSave) {
        this.canSave = canSave;
    }

    public OutputGoods getCurrentGoodsElement() {
        return currentGoodsElement;
    }

    public void setCurrentGoodsElement(OutputGoods currentGoodsElement) {
        this.currentGoodsElement = currentGoodsElement;
    }

    public boolean isCanAddGoods() {
        return canAddGoods;
    }

    private void setCanAddGoods(boolean canAddGoods) {
        this.canAddGoods = canAddGoods;
    }

    public boolean isCanPrint() {
        return canPrint;
    }

    private void setCanPrint(boolean canPrint) {
        this.canPrint = canPrint;
    }

    public String getCounterPartyTitle() {
        if (currentElement.getCounterParty() != null) {
            return currentElement.getCounterParty().getTitle();
        }
        return null;
    }

    public void setCounterParty(CounterParties counterParty) {
        currentElement.setCounterParty(counterParty);
    }

    public String getStoreHouseTitle() {
        if (currentElement.getStoreHouse() != null) {
            return currentElement.getStoreHouse().getTitle();
        }
        return null;
    }

    public void setStoreHouse(StoreHouse storeHouse) {
        currentElement.setStoreHouse(storeHouse);
    }

    public boolean isCanChangeNomenclature() {
        return canChangeNomenclature;
    }

    private void setCanChangeNomenclature(boolean canChangeNomenclature) {
        this.canChangeNomenclature = canChangeNomenclature;
    }
}