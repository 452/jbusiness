package small.business.businesslayer;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import small.business.dao.NomenclatureDAO;
import small.business.dao.entity.Nomenclature;
import small.business.dao.entity.Pictures;

/**
 * 
 * @author ihor
 */
@Service
public class NomenclatureService {

	static Logger			log					= Logger.getLogger(NomenclatureService.class.getName());
	@Autowired
	private NomenclatureDAO	nomenclatureDAO;
	@Resource
	private HistoryService	historyService;
	private Nomenclature	currentElement;
	private Nomenclature	selectedElement;
	private GroupSelector	groupSelector		= new GroupSelector();
	private Pictures		currentPicture;
	private Boolean			canSave				= false;
	private Boolean			canSaveGroup		= false;
	private Boolean			canSavePicture		= false;
	private Boolean			canRemove			= false;
	private Boolean			canMove				= false;
	private Boolean			canEdit				= false;
	private Boolean			canEditPicture		= false;
	private Boolean			canRemovePicture	= false;
	private Boolean			canSelect			= false;
	private Boolean			selected			= false;
	private String			selectType;

	public List<Nomenclature> getDataList() {
		return nomenclatureDAO.getHierarchicalDataList(groupSelector.getNavigationGroupId());
	}

	public void saveUpdate() {
		currentElement.setParentid(groupSelector.getNavigationGroupId());
		try {
			if (currentElement.getId() == null) {
				currentElement = nomenclatureDAO.saveOrUpdate(currentElement);
				historyService.saveActionOfAdd(HistoryService.NOMENCLATURE, currentElement.getTitle() + " №" + currentElement.getId().toString());
			} else {
				currentElement = nomenclatureDAO.saveOrUpdate(currentElement);
				historyService.saveActionOfChange(HistoryService.NOMENCLATURE, currentElement.getTitle() + " №" + currentElement.getId().toString());
			}
		}
		catch (Exception ex) {
			log.error(ex);
		}
	}

	public void saveUpdateImage() {
		currentElement.getPictures().add(currentPicture);
	}

	public void move() {
		if (selectedElement != null && isSelected()) {
			currentElement.setParentid(selectedElement.getId());
			try {
				nomenclatureDAO.saveOrUpdate(currentElement);
			}
			catch (Exception ex) {
				log.error(ex);
			}
			setSelected(false);
		}
	}

	public void removeCurrentElement(Nomenclature selectedObject) {
		nomenclatureDAO.remove(selectedObject);
	}

	public void validate() {
		setCanSave(false);
		setCanSaveGroup(false);
		setCanEdit(false);
		setCanMove(false);
		setCanRemove(false);
		setCanEditPicture(false);
		setCanRemovePicture(false);
		setCanSavePicture(false);
		setCanSelect(false);
		if (currentElement != null) {
			if ((currentElement.getId() != null) && (!currentElement.getId().equals(groupSelector.getNavigationGroupId()))) {
				setCanEdit(true);
				setCanMove(true);
				setCanRemove(true);
			}
			if ((currentElement.getTitle() != null) && (currentElement.getInfo() != null) && (currentElement.getTitle().length() > 0) && !currentElement.getTitle().endsWith(" ") && !currentElement.getInfo().endsWith(" ") && (currentElement.getArticleofgoods() != null) && (currentElement.getArticleinside() != null) && !currentElement.getArticleofgoods().endsWith(" ") && !currentElement.getArticleinside().endsWith(" ")) {
				setCanSave(true);
			}
			if ((currentElement.getTitle() != null) && (currentElement.getTitle().length() > 0) && !currentElement.getTitle().endsWith(" ")) {
				setCanSaveGroup(true);
			}
		}
		// || (selectedElement != null && currentElement != null &&
		// !selectedElement.getId().equals(currentElement.getId()))
		if (selectedElement != null && selectedElement.isGroup().equals(getSelectType())) {
			setCanSelect(true);
		}
		if (currentPicture != null) {
			setCanEditPicture(true);
			setCanRemovePicture(true);
			if (currentPicture.getData() != null) {
				setCanSavePicture(true);
			}
		}
	}

	public void setCurrentCategory(Nomenclature currentCategory) {
		if (currentCategory.isGroup().equals(Nomenclature.GROUP) && (currentCategory.getId() != null)) {
			groupSelector.setСurrentCategory(currentCategory);
		}
	}

	public Nomenclature getCurrentElement() {
		return currentElement;
	}

	public void setCurrentElement(Nomenclature currentElement) {
		this.currentElement = currentElement;
	}

	public Nomenclature getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(Nomenclature selectedElement) {
		this.selectedElement = selectedElement;
	}

	public Boolean isCanSave() {
		return canSave;
	}

	private void setCanSave(Boolean canSave) {
		this.canSave = canSave;
	}

	public Boolean isCanRemove() {
		return canRemove;
	}

	private void setCanRemove(Boolean canRemove) {
		this.canRemove = canRemove;
	}

	public Boolean isCanMove() {
		return canMove;
	}

	private void setCanMove(Boolean canMove) {
		this.canMove = canMove;
	}

	public Boolean isCanEdit() {
		return canEdit;
	}

	private void setCanEdit(Boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Boolean isCanSaveGroup() {
		return canSaveGroup;
	}

	private void setCanSaveGroup(Boolean canSaveGroup) {
		this.canSaveGroup = canSaveGroup;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public Pictures getCurrentPicture() {
		return currentPicture;
	}

	public void setCurrentPicture(Pictures selectedPicture) {
		this.currentPicture = selectedPicture;
	}

	public Boolean isCanEditPicture() {
		return canEditPicture;
	}

	private void setCanEditPicture(Boolean canEditPicture) {
		this.canEditPicture = canEditPicture;
	}

	public Boolean isCanRemovePicture() {
		return canRemovePicture;
	}

	private void setCanRemovePicture(Boolean canRemovePicture) {
		this.canRemovePicture = canRemovePicture;
	}

	public Boolean isCanSavePicture() {
		return canSavePicture;
	}

	public void setCanSavePicture(Boolean canSavePicture) {
		this.canSavePicture = canSavePicture;
	}

	public Boolean isCanSelect() {
		return canSelect;
	}

	private void setCanSelect(Boolean canSelect) {
		this.canSelect = canSelect;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}