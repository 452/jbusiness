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

	static Logger					log						= Logger.getLogger(ReturnsOfGoodsService.class.getName());
	@Autowired
	private ReturnsOfGoodsDAO		returnsOfGoodsDAO;
	@Autowired
	private StoreHousesDAO			storeHouseDAO;
	@Resource
	private HistoryService			historyService;
	private ReturnsOfGoodsInvoice	currentElement;
	private ReturnedGoods			currentGoodsElement;
	private Boolean					canSave					= false;
	private Boolean					canPrint				= false;
	private Boolean					canAddGoods				= false;
	private Boolean					canRemove				= false;
	private Boolean					canEditInvoice			= false;
	private Boolean					canEditGoods			= false;
	private Boolean					canSelectCounterParty	= false;
	private Boolean					canSelectStoreHouse		= false;

	public List<ReturnsOfGoodsInvoice> getDataList() {
		return returnsOfGoodsDAO.getInvoicesList();
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
		}
		catch (Exception e) {
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

	public Boolean isCanSave() {
		return canSave;
	}

	private void setCanSave(Boolean canSave) {
		this.canSave = canSave;
	}

	public Boolean isCanPrint() {
		return canPrint;
	}

	private void setCanPrint(Boolean canPrint) {
		this.canPrint = canPrint;
	}

	public Boolean isCanAddGoods() {
		return canAddGoods;
	}

	private void setCanAddGoods(Boolean canAddGoods) {
		this.canAddGoods = canAddGoods;
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
}