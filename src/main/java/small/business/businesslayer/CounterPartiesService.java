package small.business.businesslayer;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import small.business.dao.CounterPartiesDAO;
import small.business.dao.entity.CounterParties;

/**
 *
 * @author 452
 */
@Service
public class CounterPartiesService {

    static Logger log = Logger.getLogger(CounterPartiesService.class.getName());
    @Autowired
    private CounterPartiesDAO counterPartiesDAO;
    @Resource
    private HistoryService historyService;
    private CounterParties currentElement;
    private CounterParties selectedElement;
    private boolean canSave = false;
    private boolean canRemove = false;
    private boolean canMove = false;
    private boolean canEdit = false;
    private GroupSelector groupSelector = new GroupSelector();
    private boolean canSelect = false;
    private boolean selected = false;
    private String selectType;

    public List<CounterParties> getDataList() {
        return counterPartiesDAO.getHierarchicalDataList(groupSelector.getNavigationGroupId());
    }

    public void saveOrUpdate() {
        currentElement.setParentid(groupSelector.getNavigationGroupId());
        try {
            if (currentElement.getId() == null) {
                currentElement = counterPartiesDAO.saveOrUpdate(currentElement);
                historyService.saveActionOfAdd(HistoryService.COUNTERPARTIES, currentElement.getTitle() + " №" + currentElement.getId().toString());
            } else {
                currentElement = counterPartiesDAO.saveOrUpdate(currentElement);
                historyService.saveActionOfChange(HistoryService.COUNTERPARTIES, currentElement.getTitle() + " №" + currentElement.getId().toString());
            }
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    }

    public void move() {
        if (selectedElement != null && isSelected()) {
            currentElement.setParentid(selectedElement.getId());
            try {
                counterPartiesDAO.saveOrUpdate(currentElement);
            } catch (Exception e) {
            	log.error(e.getMessage(), e);
            }
            setSelected(false);
        }
    }

    public void removeCurrentElement(CounterParties selectedObject) {
        counterPartiesDAO.remove(selectedObject);
    }

    public void validate() {
        setCanSave(false);
        setCanEdit(false);
        setCanMove(false);
        setCanRemove(false);
        setCanSelect(false);
        if ((currentElement != null) && (currentElement.getId() != null) && (!currentElement.getId().equals(groupSelector.getNavigationGroupId()))) {
            setCanEdit(true);
            setCanMove(true);
            //setCanRemove(true);
        }
        if ((currentElement != null) && (currentElement.getTitle() != null) && (currentElement.getInfo() != null) && (currentElement.getTitle().length() > 0) && !currentElement.getTitle().endsWith(" ") && !currentElement.getInfo().endsWith(" ")) {
            setCanSave(true);
        }
        if (selectedElement != null && currentElement != null && selectedElement.isGroup().equals(getSelectType()) && !selectedElement.getId().equals(currentElement.getId())) {
            setCanSelect(true);
        }
    }

    public void setCurrentCategory(CounterParties currentCategory) {
        if (currentCategory.isGroup().equals(CounterParties.GROUP) && (currentCategory.getId() != null)) {
            groupSelector.setСurrentCategory(currentCategory);
        }
    }

    public CounterParties getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(CounterParties currentElement) {
        this.currentElement = currentElement;
    }

    public CounterParties getValidateElement() {
        if (currentElement == null) {
        	currentElement = new CounterParties();
        }

        return currentElement;
    }

    public CounterParties getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(CounterParties selectedElement) {
        this.selectedElement = selectedElement;
    }

    public boolean getCanSave() {
        return canSave;
    }

    protected void setCanSave(boolean canSave) {
        this.canSave = canSave;
    }

    public boolean getCanRemove() {
        return canRemove;
    }

    public void setCanRemove(boolean canRemove) {
        this.canRemove = canRemove;
    }

    public boolean getCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String g) {
        this.selectType = g;
    }

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isCanSelect() {
		return canSelect;
	}

	public void setCanSelect(boolean canSelect) {
		this.canSelect = canSelect;
	}
}