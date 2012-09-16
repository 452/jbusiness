package small.business.businesslayer;

import java.util.List;
import java.util.logging.Level;
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
    private CounterParties validateElement;
    private boolean canSave = false;
    private boolean canRemove = false;
    private boolean canMove = false;
    private boolean canEdit = false;
    private GroupSelector groupSelector = new GroupSelector();
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
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    public void move() {
        log.debug("move");
        if (selectedElement != null) {
            log.debug(currentElement.getTitle() + " selectedCategory=" + selectedElement.getId());
            currentElement.setParentid(selectedElement.getId());
            try {
                counterPartiesDAO.saveOrUpdate(currentElement);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(CounterPartiesService.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        if ((validateElement != null) && (validateElement.getId() != null) && (!validateElement.getId().equals(groupSelector.getNavigationGroupId()))) {
            setCanEdit(true);
            // setCanMove(true);
            setCanRemove(true);
        }
        if ((validateElement != null) && (validateElement.getTitle() != null) && (validateElement.getInfo() != null) && (validateElement.getTitle().length() > 0) && !validateElement.getTitle().endsWith(" ") && !validateElement.getInfo().endsWith(" ")) {
            setCanSave(true);
        }
        setValidateElement(null);
    }

    public void setCurrentCategory(CounterParties currentCategory) {
        if (currentCategory.getIsgroup().equals(CounterParties.GROUP) && (currentCategory.getId() != null)) {
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
        if (validateElement == null) {
            validateElement = new CounterParties();
        }

        return validateElement;
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

    public void setValidateElement(CounterParties validateElement) {
        this.validateElement = validateElement;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String g) {
        this.selectType = g;
    }
}