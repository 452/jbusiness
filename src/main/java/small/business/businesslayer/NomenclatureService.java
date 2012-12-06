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

    static Logger log = Logger.getLogger(NomenclatureService.class.getName());
    @Autowired
    private NomenclatureDAO nomenclatureDAO;
    @Resource
    private HistoryService historyService;
    private Nomenclature currentElement;
    private Nomenclature selectedElement;
    private GroupSelector groupSelector = new GroupSelector();
    private Pictures currentPicture;
    private boolean canSave = false;
    private boolean canSaveGroup = false;
    private boolean canSavePicture = false;
    private boolean canRemove = false;
    private boolean canMove = false;
    private boolean canEdit = false;
    private boolean canEditPicture = false;
    private boolean canRemovePicture = false;
    private boolean canSelect = false;
    private boolean selected = false;
    private String selectType;

    public List<Nomenclature> getDataList() {
        return nomenclatureDAO.getHierarchicalDataList(groupSelector.getNavigationGroupId());
    }

    public List<Nomenclature> getSearchDataList(Long id, String title, String factorySerialArticul, String additionalArticul) {
        return nomenclatureDAO.getNomenclatureSearchDataList(id, title, factorySerialArticul, additionalArticul);
    }

    public void saveUpdate() {
        try {
            if (currentElement.getId() == null) {
                currentElement.setParentid(groupSelector.getNavigationGroupId());
                currentElement = nomenclatureDAO.saveOrUpdate(currentElement);
                historyService.saveActionOfAdd(HistoryService.NOMENCLATURE, currentElement.getTitle() + " №" + currentElement.getId().toString());
            } else {
                currentElement = nomenclatureDAO.saveOrUpdate(currentElement);
                historyService.saveActionOfChange(HistoryService.NOMENCLATURE, currentElement.getTitle() + " №" + currentElement.getId().toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
            } catch (Exception e) {
                log.error(e.getMessage(), e);
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
                //setCanRemove(true);
            }
            if ((currentElement.getTitle() != null) && (currentElement.getInfo() != null) && (currentElement.getTitle().length() > 0) && !currentElement.getTitle().endsWith(" ") && !currentElement.getInfo().endsWith(" ") && (currentElement.getArticleofgoods() != null) && (currentElement.getArticleinside() != null) && !currentElement.getArticleofgoods().endsWith(" ") && !currentElement.getArticleinside().endsWith(" ")) {
                setCanSave(true);
            }
            if ((currentElement.getTitle() != null) && (currentElement.getTitle().length() > 0) && !currentElement.getTitle().endsWith(" ")) {
                setCanSaveGroup(true);
            }
        }
        if (selectedElement != null && selectedElement.isGroup().equals(getSelectType())) {
            setCanSelect(true);
            if (currentElement != null && getSelectType().equals(Nomenclature.GROUP) && selectedElement.getId().equals(currentElement.getId())) {
                setCanSelect(false);
            }
        }
        if (currentPicture != null) {
            setCanEditPicture(true);
            setCanRemovePicture(true);
            if (currentPicture.getData() != null) {
                setCanSavePicture(true);
            }
        }
    }

    public long getCurrentCategoryId() {
        return groupSelector.getNavigationGroupId();
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

    public boolean isCanSave() {
        return canSave;
    }

    private void setCanSave(boolean canSave) {
        this.canSave = canSave;
    }

    public boolean isCanRemove() {
        return canRemove;
    }

    private void setCanRemove(boolean canRemove) {
        this.canRemove = canRemove;
    }

    public boolean isCanMove() {
        return canMove;
    }

    private void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    private void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isCanSaveGroup() {
        return canSaveGroup;
    }

    private void setCanSaveGroup(boolean canSaveGroup) {
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

    public boolean isCanEditPicture() {
        return canEditPicture;
    }

    private void setCanEditPicture(boolean canEditPicture) {
        this.canEditPicture = canEditPicture;
    }

    public boolean isCanRemovePicture() {
        return canRemovePicture;
    }

    private void setCanRemovePicture(boolean canRemovePicture) {
        this.canRemovePicture = canRemovePicture;
    }

    public boolean isCanSavePicture() {
        return canSavePicture;
    }

    public void setCanSavePicture(boolean canSavePicture) {
        this.canSavePicture = canSavePicture;
    }

    public boolean isCanSelect() {
        return canSelect;
    }

    private void setCanSelect(boolean canSelect) {
        this.canSelect = canSelect;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}