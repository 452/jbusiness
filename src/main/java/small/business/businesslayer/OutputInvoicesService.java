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
import org.springframework.transaction.annotation.Transactional;

import small.business.dao.OutputInvoicesDAO;
import small.business.dao.StoreHousesDAO;
import small.business.dao.entity.CounterParties;
import small.business.dao.entity.GoodsOnStoreHouses;
import small.business.dao.entity.OutputGoods;
import small.business.dao.entity.OutputInvoice;
import small.business.dao.entity.StoreHouse;

/**
 * 
 * @author root
 */
@Service
public class OutputInvoicesService {

	static Logger				log						= Logger.getLogger(OutputInvoicesService.class.getName());
	@Autowired
	private OutputInvoicesDAO	outputInvoicesDAO;
	@Autowired
	private StoreHousesDAO		storeHouseDAO;
	@Resource
	private HistoryService		historyService;
	private OutputInvoice		currentElement;
	private OutputGoods			currentGoodsElement;
	private Boolean				canSave					= false;
	private Boolean				canPrint				= false;
	private Boolean				canAddGoods				= false;
	private Boolean				canRemoveGoods			= false;
	private Boolean				canRemoveInvoice		= false;
	private Boolean				canEditInvoice			= false;
	private Boolean				canEditGoods			= false;
	private Boolean				canSelectCounterParty	= false;
	private Boolean				canSelectStoreHouse		= false;
	private Set<OutputGoods>	outputGoodsToRemove		= new HashSet<OutputGoods>();

	public List<OutputInvoice> getDataList() {
		return outputInvoicesDAO.getInvoicesList();
	}

	public void validate() {
		setCanAddGoods(true);
		setCanEditInvoice(false);
		setCanEditGoods(false);
		setCanRemoveInvoice(false);
		setCanRemoveGoods(true);
		setCanSave(false);
		setCanPrint(false);
		setCanSelectCounterParty(false);
		setCanSelectStoreHouse(false);
		if (currentElement != null) {
			if (currentElement.getId() == null) {
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
				setCanRemoveInvoice(true);
				setCanPrint(true);
			}
		}
	}

	@Transactional
	public OutputInvoice saveOrUpdate() throws Exception {
		try {
			if (currentElement != null) {
				if (currentElement.getId() == null) {
					currentElement = outputInvoicesDAO.saveOrUpdate(currentElement);
					updateGoodsQuantityOnStoreHouses();
					historyService.saveActionOfAdd(HistoryService.OUTPUT_INVOICE, HistoryService.NEW_INVOICE + " №" + currentElement.getId().toString());
				} else {
					if (outputGoodsToRemove.size() > 0) {
						for (OutputGoods goods : outputGoodsToRemove) {
							if (goods.getId() != null) {
								GoodsOnStoreHouses goodsOnStoreHouses = storeHouseDAO.getGoodsFromStoreHouse(goods, currentElement.getStoreHouse());
								if (goods.getInitialQuantity() == null) {
									goodsOnStoreHouses.setQuantity(goodsOnStoreHouses.getQuantity() + (goods.getQuantity()));
									goodsOnStoreHouses.getNomenclature().setQuantity(goodsOnStoreHouses.getNomenclature().getQuantity() + (goods.getQuantity()));
								} else {
									goodsOnStoreHouses.setQuantity(goodsOnStoreHouses.getQuantity() + (goods.getInitialQuantity() - goods.getQuantity()));
									goodsOnStoreHouses.getNomenclature().setQuantity(goodsOnStoreHouses.getNomenclature().getQuantity() + (goods.getInitialQuantity() - goods.getQuantity()));
								}
								storeHouseDAO.saveOrUpdate(goodsOnStoreHouses);
								historyService.saveActionOfRemoval(HistoryService.OUTPUT_INVOICE, "Накладна №" + currentElement.getId().toString() + " " + goods.getNomenclature().getTitle());
							}
						}
					}
					// /////////////////
					OutputInvoice tmpCurrentElement = outputInvoicesDAO.saveOrUpdate(currentElement);
					for (OutputGoods goods : currentElement.getGoods()) {
						Integer quantity = null;
						Integer nomenclatureQuantity = null;
						if (goods.getInitialQuantity() != null) {
							if (!goods.getQuantity().equals(goods.getInitialQuantity())) {
								GoodsOnStoreHouses goodsOnStoreHouses = storeHouseDAO.getGoodsFromStoreHouse(goods, currentElement.getStoreHouse());
								if (goods.getQuantity() > goods.getInitialQuantity()) {
									quantity = goodsOnStoreHouses.getQuantity() - (goods.getQuantity() - goods.getInitialQuantity());
									nomenclatureQuantity = goodsOnStoreHouses.getNomenclature().getQuantity() - (goods.getQuantity() - goods.getInitialQuantity());
								}
								if (goods.getQuantity() < goods.getInitialQuantity()) {
									quantity = goodsOnStoreHouses.getQuantity() + (goods.getInitialQuantity() - goods.getQuantity());
									nomenclatureQuantity = goodsOnStoreHouses.getNomenclature().getQuantity() + (goods.getInitialQuantity() - goods.getQuantity());
								}
								goods.setInitialQuantity(null);
								goodsOnStoreHouses.setQuantity(quantity);
								goodsOnStoreHouses.getNomenclature().setQuantity(nomenclatureQuantity);
								storeHouseDAO.saveOrUpdate(goodsOnStoreHouses);
							}
						}
					}
					currentElement = tmpCurrentElement;
					historyService.saveActionOfChange(HistoryService.OUTPUT_INVOICE, " №" + currentElement.getId().toString());
				}
			}
			return currentElement;
		}
		catch (Exception e) {
			log.error("Can't save Output Invoice", e);
			throw new Exception(e);
		}
	}

	private void updateGoodsQuantityOnStoreHouses() {
		for (OutputGoods goods : currentElement.getGoods()) {
			storeHouseDAO.updateQuantityReduce(goods, currentElement.getStoreHouse());
		}
	}

	private void updateGoodsQuantityOnStoreHousesRemoval() {
		for (OutputGoods goods : currentElement.getGoods()) {
			storeHouseDAO.updateQuantityIncrease(goods, currentElement.getStoreHouse());
		}
	}

	@Transactional
	public void removeCurrentElement(OutputInvoice selectedObject) {
		outputInvoicesDAO.remove(selectedObject);
		updateGoodsQuantityOnStoreHousesRemoval();
		historyService.saveActionOfRemoval(HistoryService.OUTPUT_INVOICE, currentElement.getId().toString());
	}

	public void removeGoods(OutputGoods selectedObject) {
		outputGoodsToRemove.add(selectedObject);
		currentElement.getGoods().remove(selectedObject);
	}

	public OutputInvoice getCurrentElement() {
		return this.currentElement;
	}

	public void setCurrentElement(OutputInvoice currentElement) {
		this.currentElement = currentElement;
	}

	public Boolean isCanRemoveInvoice() {
		return canRemoveInvoice;
	}

	private void setCanRemoveInvoice(Boolean canRemove) {
		this.canRemoveInvoice = canRemove;
	}

	public Boolean isCanRemoveGoods() {
		return canRemoveGoods;
	}

	private void setCanRemoveGoods(Boolean canRemove) {
		this.canRemoveGoods = canRemove;
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

	public OutputGoods getCurrentGoodsElement() {
		return currentGoodsElement;
	}

	public void setCurrentGoodsElement(OutputGoods currentGoodsElement) {
		this.currentGoodsElement = currentGoodsElement;
	}

	public Boolean isCanAddGoods() {
		return canAddGoods;
	}

	private void setCanAddGoods(Boolean canAddGoods) {
		this.canAddGoods = canAddGoods;
	}

	public Boolean isCanPrint() {
		return canPrint;
	}

	private void setCanPrint(Boolean canPrint) {
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
}