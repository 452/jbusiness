package small.business.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import small.business.domainmodel.interfaces.IElement;
import small.business.domainmodel.interfaces.IGroup;

@Entity
@Table(name = "DeviceInRepair")
@TableGenerator(name = "DeviceInRepair", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
public class DeviceInRepair implements Serializable, IElement<DeviceInRepair>, IGroup {

    @Transient
    public static final String GROUP = "Y";
    @Transient
    public static final String DEVICE = "N";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "DeviceInRepair", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column
    private Long parentid;
    @Column
    private String isGroup;
    @Column
    @Lob
    private String title;
    @Column
    @Lob
    private String info;
    @JoinColumn(name = "deviceInRepair", referencedColumnName = "id", insertable = true, updatable = true)
    @OneToMany(targetEntity = PartsForRepairDevices.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PartsForRepairDevices> partsForRepairDevices = new HashSet<PartsForRepairDevices>();

    public DeviceInRepair() {
    }

    public DeviceInRepair(String isGroup) {
        this.isGroup = isGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getParentId() {
        return parentid;
    }

    public void setParentId(Long parentId) {
        this.parentid = parentId;
    }

    public String isGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<PartsForRepairDevices> getPartsForRepairDevices() {
        return partsForRepairDevices;
    }

    public void setPartsForRepairDevices(Set<PartsForRepairDevices> partsForRepairDevices) {
        this.partsForRepairDevices = partsForRepairDevices;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DeviceInRepair)) {
            return false;
        }
        DeviceInRepair other = (DeviceInRepair) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }
}