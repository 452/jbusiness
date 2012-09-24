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
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import small.business.domainmodel.interfaces.IGoods;

public abstract class Invoices<E> {

    static Logger log = Logger.getLogger(Invoices.class.getName());
    @Resource
    protected HistoryService historyService;
    @Resource
    protected StoreHousesService storeHousesService;
    private boolean canSave = false;
    private boolean canPrint = false;
    private boolean canAddGoods = false;
    private boolean canSaveGoods = false;
    private boolean canRemoveGoods = false;
    private boolean canRemoveInvoice = false;
    private boolean canEditInvoice = false;
    private boolean canEditGoods = false;
    private boolean canChangeNomenclature = false;
    private boolean canSelectCounterParty = false;
    private boolean canSelectStoreHouse = false;
    protected Set<IGoods> goodsToRemove = new HashSet<IGoods>();
    private int validateQuantityOfGoods = 0;

    public boolean isCanRemoveInvoice() {
        return canRemoveInvoice;
    }

    protected void setCanRemoveInvoice(boolean canRemove) {
        this.canRemoveInvoice = canRemove;
    }

    public boolean isCanRemoveGoods() {
        return canRemoveGoods;
    }

    protected void setCanRemoveGoods(boolean canRemove) {
        this.canRemoveGoods = canRemove;
    }

    public boolean isCanEditInvoice() {
        return canEditInvoice;
    }

    protected void setCanEditInvoice(boolean canEditInvoice) {
        this.canEditInvoice = canEditInvoice;
    }

    public boolean isCanEditGoods() {
        return canEditGoods;
    }

    protected void setCanEditGoods(boolean canEditGoods) {
        this.canEditGoods = canEditGoods;
    }

    public boolean isCanSelectCounterParty() {
        return canSelectCounterParty;
    }

    protected void setCanSelectCounterParty(boolean canSelectCounterParty) {
        this.canSelectCounterParty = canSelectCounterParty;
    }

    public boolean isCanSelectStoreHouse() {
        return canSelectStoreHouse;
    }

    protected void setCanSelectStoreHouse(boolean canSelectStoreHouse) {
        this.canSelectStoreHouse = canSelectStoreHouse;
    }

    public boolean isCanSave() {
        return canSave;
    }

    protected void setCanSave(boolean canSave) {
        this.canSave = canSave;
    }

    public boolean isCanAddGoods() {
        return canAddGoods;
    }

    protected void setCanAddGoods(boolean canAddGoods) {
        this.canAddGoods = canAddGoods;
    }

    public boolean isCanPrint() {
        return canPrint;
    }

    protected void setCanPrint(boolean canPrint) {
        this.canPrint = canPrint;
    }

    public boolean isCanChangeNomenclature() {
        return canChangeNomenclature;
    }

    protected void setCanChangeNomenclature(boolean canChangeNomenclature) {
        this.canChangeNomenclature = canChangeNomenclature;
    }

    public boolean isCanSaveGoods() {
        return canSaveGoods;
    }

    protected void setCanSaveGoods(boolean canSaveGoods) {
        this.canSaveGoods = canSaveGoods;
    }

    public int getValidateQuantityOfGoods() {
        return validateQuantityOfGoods;
    }

    public void setValidateQuantityOfGoods(int validateQuantityOfGoods) {
        this.validateQuantityOfGoods = validateQuantityOfGoods;
    }
}