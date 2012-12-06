package small.business.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "PartsForRepairDevices")
@TableGenerator(name = "PartsForRepairDevices", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
public class PartsForRepairDevices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "PartsForRepairDevices", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @JoinColumn(name = "deviceInRepair")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DeviceInRepair deviceInRepair;
    @JoinColumn(name = "nomenclature")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Nomenclature nomenclature;

    public PartsForRepairDevices() {
    }

    public PartsForRepairDevices(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceInRepair getDeviceInRepair() {
        return deviceInRepair;
    }

    public void setDeviceInRepair(DeviceInRepair deviceInRepair) {
        this.deviceInRepair = deviceInRepair;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getNomenclature().getId() != null ? this.getNomenclature().getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PartsForRepairDevices)) {
            return false;
        }
        PartsForRepairDevices other = (PartsForRepairDevices) object;
        if (!this.getNomenclature().getId().equals(other.getNomenclature().getId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id == null ? "" : String.valueOf(id);
    }
}