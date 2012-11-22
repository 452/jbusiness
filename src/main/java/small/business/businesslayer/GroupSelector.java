package small.business.businesslayer;

import small.business.domainmodel.interfaces.IGroup;

/**
 *
 * @author 452
 */
public class GroupSelector {

    //static Logger log = Logger.getLogger(GroupSelector.class.getName());
    private Long lastGroupNo = 0L;
    private Long currentGroupId = 0L;

    public Long getNavigationGroupId() {
        return currentGroupId;
    }

    public void setСurrentCategory(IGroup сurrentCategory) {
        if (сurrentCategory.getId().equals(lastGroupNo)) {
            currentGroupId = сurrentCategory.getParentId();
            lastGroupNo = сurrentCategory.getParentId();
        } else {
            if (!сurrentCategory.getId().equals(сurrentCategory.getParentId())) {
                currentGroupId = сurrentCategory.getId();
                lastGroupNo = сurrentCategory.getId();
            }
        }
    }
}