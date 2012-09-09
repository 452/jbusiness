package small.business.dao.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author root
 */
@Entity
@Cache
@Table(name = "nomenclatureonstorehouses", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GoodsOnStoreHouses.findAll", query = "SELECT g FROM GoodsOnStoreHouses g"),
    @NamedQuery(name = "GoodsOnStoreHouses.findById", query = "SELECT g FROM GoodsOnStoreHouses g WHERE g.id = :id"),
    @NamedQuery(name = "GoodsOnStoreHouses.findByStorehouseid", query = "SELECT g FROM GoodsOnStoreHouses g WHERE g.storehouseid = :storehouseid"),
    @NamedQuery(name = "GoodsOnStoreHouses.findByNomenclatureAndStoreHouse", query = "SELECT g FROM GoodsOnStoreHouses g WHERE g.nomenclatureid = :nomenclatureid AND g.storehouseid = :storehouseid"),
    @NamedQuery(name = "GoodsOnStoreHouses.findByQuantity", query = "SELECT g FROM GoodsOnStoreHouses g WHERE g.quantity = :quantity")})
public class GoodsOnStoreHouses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "nomenclatureonstorehouses", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "nomenclatureonstorehouses", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "storehouseid", nullable = false)
    private Long storehouseid = 1L;
    @JoinColumn(name = "nomenclatureid", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Nomenclature nomenclatureid;
    @Column(name = "quantity", nullable = false)
    private Integer quantity = 0;
    @Transient
    private Integer initialQuantity = null;

    public GoodsOnStoreHouses() {
    }

    public GoodsOnStoreHouses(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStorehouseid() {
        return storehouseid;
    }

    public void setStorehouseid(Long storehouseid) {
        this.storehouseid = storehouseid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (initialQuantity == null) {
            if (id == null) {
                setInitialQuantity(0);
            } else {
                setInitialQuantity(this.quantity);
            }
        }
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GoodsOnStoreHouses)) {
            return false;
        }
        GoodsOnStoreHouses other = (GoodsOnStoreHouses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (nomenclatureid.getTitle() == null) ? null : nomenclatureid.getTitle();
    }

    public Nomenclature getNomenclature() {
        return nomenclatureid;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclatureid = nomenclature;
    }

    /**
     * @return Initial value quantity of goods
     */
    public Integer getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity = initialQuantity;
    }
}
