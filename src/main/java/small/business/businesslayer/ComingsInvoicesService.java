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

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import small.business.dao.ComingInvoicesDAO;
import small.business.dao.StoreHousesDAO;
import small.business.dao.entity.ComingsInvoice;
import small.business.domainmodel.interfaces.IGoods;

/**
 * 
 * @author root
 */
@Service
public class ComingsInvoicesService {

	static Logger				log						= Logger.getLogger(ComingsInvoicesService.class.getName());
	@Autowired
	private ComingInvoicesDAO	comingInvoicesDAO;
	@Autowired
	private StoreHousesDAO		storeHousesDAO;
	@Resource
	private HistoryService		historyService;
	private ComingsInvoice		currentElement;
	private IGoods				CurrentGoodsElement;
	private Boolean				canSave					= false;
	private Boolean				canAddGoods				= false;
	private Boolean				canRemove				= false;
	private Boolean				canEditInvoice			= false;
	private Boolean				canEditGoods			= false;
	private Boolean				canSelectCounterParty	= false;
	private Boolean				canSelectStoreHouse		= false;

	public List<ComingsInvoice> getDataList() {
		return comingInvoicesDAO.getInvoicesList();
	}

	public void validate() {
		setCanRemove(false);
		setCanAddGoods(false);
		setCanEditInvoice(false);
		setCanSave(false);
		setCanSelectCounterParty(false);
		setCanSelectStoreHouse(false);
		if (currentElement != null) {
			if (currentElement.getId() == null) {
				setCanAddGoods(true);
			}
			if (((currentElement.getId() == null) && (currentElement.getGoods() != null) && (currentElement.getGoods().size() > 0))) {
				setCanEditGoods(true);
				setCanRemove(true);
			}
			if (currentElement.getCounterParty() == null) {
				setCanSelectCounterParty(true);
			}
			if (currentElement.getStoreHouse() == null) {
				setCanSelectStoreHouse(true);
			}
			if ((currentElement.getStoreHouse() != null) && (currentElement.getCounterParty() != null) && (currentElement.getGoods() != null) && (currentElement.getGoods().size() > 0)) {
				setCanEditInvoice(true);
				setCanSave(true);
			}
		}
	}

	public void saveOrUpdate() throws Exception {
		try {
			if (currentElement != null && currentElement.getId() == null) {
				currentElement = comingInvoicesDAO.saveOrUpdate(currentElement);
				updateGoodsQuantityOnStoreHouses();
				historyService.saveActionOfAdd(HistoryService.COMINGS_INVOICE, HistoryService.NEW_INVOICE + " №" + currentElement.getId().toString());
			} else {
				currentElement = comingInvoicesDAO.saveOrUpdate(currentElement);
				historyService.saveActionOfChange(HistoryService.COMINGS_INVOICE, " №" + currentElement.getId().toString());
			}
		}
		catch (Exception e) {
			log.error("Can't save Incoming Invoice", e);
			throw new Exception(e);
		}
	}

	private void updateGoodsQuantityOnStoreHouses() {
		for (IGoods goods : currentElement.getGoods()) {
			storeHousesDAO.updateQuantityIncrease(goods, currentElement.getStoreHouse());
		}
	}

	public void removeCurrentElement(ComingsInvoice selectedObject) {
		comingInvoicesDAO.remove(selectedObject);
	}

	public ComingsInvoice getCurrentElement() {
		return this.currentElement;
	}

	public void setCurrentElement(ComingsInvoice currentElement) {
		this.currentElement = currentElement;
	}

	public Boolean isCanRemove() {
		return canRemove;
	}

	private void setCanRemove(Boolean canRemove) {
		this.canRemove = canRemove;
	}

	public Boolean isCanEditInvoice() {
		return canEditInvoice;
	}

	private void setCanEditInvoice(Boolean canEditInvoice) {
		this.canEditInvoice = canEditInvoice;
	}

	public Boolean isCanEditGoods() {
		return canEditGoods;
	}

	private void setCanEditGoods(Boolean canEditGoods) {
		this.canEditGoods = canEditGoods;
	}

	public Boolean isCanSelectCounterParty() {
		return canSelectCounterParty;
	}

	private void setCanSelectCounterParty(Boolean canSelectCounterParty) {
		this.canSelectCounterParty = canSelectCounterParty;
	}

	public Boolean isCanSelectStoreHouse() {
		return canSelectStoreHouse;
	}

	private void setCanSelectStoreHouse(Boolean canSelectStoreHouse) {
		this.canSelectStoreHouse = canSelectStoreHouse;
	}

	public Boolean isCanSave() {
		return canSave;
	}

	private void setCanSave(Boolean canSave) {
		this.canSave = canSave;
	}

	public IGoods getCurrentGoodsElement() {
		return CurrentGoodsElement;
	}

	public void setCurrentGoodsElement(IGoods CurrentGoodsElement) {
		this.CurrentGoodsElement = CurrentGoodsElement;
	}

	public Boolean isCanAddGoods() {
		return canAddGoods;
	}

	private void setCanAddGoods(Boolean canAddGoods) {
		this.canAddGoods = canAddGoods;
	}
}