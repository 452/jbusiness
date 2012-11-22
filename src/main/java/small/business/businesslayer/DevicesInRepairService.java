package small.business.businesslayer;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import small.business.dao.DevicesInRepairDAO;
import small.business.dao.entity.DeviceInRepair;
import small.business.dao.entity.PartsForRepairDevices;

/**
 *
 * @author 452
 */
@Service
public class DevicesInRepairService {

    static Logger log = Logger.getLogger(DevicesInRepairService.class.getName());
    @Autowired
    private DevicesInRepairDAO devicesInRepairDAO;
    @Resource
    private HistoryService historyService;
    private DeviceInRepair currentElement;
    private DeviceInRepair selectedElement;
    private PartsForRepairDevices currentParts;
    private boolean canSave = false;
    private boolean canRemove = false;
    private boolean canMove = false;
    private boolean canEdit = false;
    private boolean canRemoveParts = false;
    private GroupSelector groupSelector = new GroupSelector();
    private boolean canSelect = false;
    private boolean selected = false;
    private String selectType;
 
    public List<DeviceInRepair> getDataList() {
        return devicesInRepairDAO.getHierarchicalDataList(groupSelector.getNavigationGroupId());
    }

    public void saveOrUpdate() {
        currentElement.setParentId(groupSelector.getNavigationGroupId());
        try {
            if (currentElement.getId() == null) {
                currentElement = devicesInRepairDAO.saveOrUpdate(currentElement);
                historyService.saveActionOfAdd(HistoryService.COUNTERPARTIES, currentElement.getTitle() + " №" + currentElement.getId().toString());
            } else {
                currentElement = devicesInRepairDAO.saveOrUpdate(currentElement);
                historyService.saveActionOfChange(HistoryService.COUNTERPARTIES, currentElement.getTitle() + " №" + currentElement.getId().toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void move() {
        if (selectedElement != null && isSelected()) {
            currentElement.setParentId(selectedElement.getId());
            try {
                devicesInRepairDAO.saveOrUpdate(currentElement);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            setSelected(false);
        }
    }

    public void removeCurrentElement(DeviceInRepair selectedObject) {
        devicesInRepairDAO.remove(selectedObject);
    }

    public void validate() {
        setCanSave(false);
        setCanEdit(false);
        setCanMove(false);
        setCanRemove(false);
        setCanSelect(false);
        setCanRemoveParts(false);
        if ((currentElement != null) && (currentElement.getId() != null) && (!currentElement.getId().equals(groupSelector.getNavigationGroupId()))) {
            setCanEdit(true);
            setCanMove(true);
            //setCanRemove(true);
        }
        if ((currentElement != null) && (currentElement.getTitle() != null) && (currentElement.getInfo() != null) && (currentElement.getTitle().length() > 0) && !currentElement.getTitle().endsWith(" ") && !currentElement.getInfo().endsWith(" ")) {
            setCanSave(true);
        }
        if (selectedElement != null && selectedElement.isGroup().equals(getSelectType())) {
            setCanSelect(true);
            if (currentElement != null && getSelectType().equals(DeviceInRepair.GROUP) && selectedElement.getId().equals(currentElement.getId())) {
                setCanSelect(false);
            }
        }
        if (currentParts != null){
            setCanRemoveParts(true);
        }
    }

    public long getCurrentCategoryId() {
        return groupSelector.getNavigationGroupId();
    }

    public void setCurrentCategory(DeviceInRepair currentCategory) {
        if (currentCategory.isGroup().equals(DeviceInRepair.GROUP) && (currentCategory.getId() != null)) {
            groupSelector.setСurrentCategory(currentCategory);
        }
    }

    public DeviceInRepair getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(DeviceInRepair currentElement) {
        this.currentElement = currentElement;
    }

    public DeviceInRepair getValidateElement() {
        if (currentElement == null) {
            currentElement = new DeviceInRepair();
        }
        return currentElement;
    }

    public DeviceInRepair getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(DeviceInRepair selectedElement) {
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

    /**
     * @return the currentParts
     */
    public PartsForRepairDevices getCurrentParts() {
        return currentParts;
    }

    /**
     * @param currentParts the currentParts to set
     */
    public void setCurrentParts(PartsForRepairDevices currentParts) {
        this.currentParts = currentParts;
    }

    /**
     * @return the CanRemoveParts
     */
    public boolean isCanRemoveParts() {
        return canRemoveParts;
    }

    /**
     * @param CanRemoveParts the CanRemoveParts to set
     */
    private void setCanRemoveParts(boolean canRemoveParts) {
        this.canRemoveParts = canRemoveParts;
    }
}