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
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Entity
@Table(name = "ReturnedGoods", catalog = "", schema = "")
public class ReturnedGoods implements Serializable, IGoods {

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "ReturnedGoods", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "ReturnedGoods", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "returnsInvoice", unique = true, nullable = false)
    private Long returnsInvoice;
    @Column(name = "quantity", unique = true, nullable = false)
    private Integer quantity;
    @Column(name = "price", unique = true, nullable = false)
    private Double price = 0.01;
    @JoinColumn(name = "nomenclature")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Nomenclature nomenclature;
    @Transient
    private int initialQuantity = 0;

    public ReturnedGoods() {
        if (quantity == null) {
            this.initialQuantity = 0;
            this.quantity = 1;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getNomenclature().getId() != null ? this.getNomenclature().getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof ReturnedGoods)) {
            return false;
        }
        ReturnedGoods other = (ReturnedGoods) object;
        if ((this.nomenclature.getId() == null && other.getNomenclature().getId() != null) || (this.getNomenclature().getId() != null && !this.getNomenclature().getId().equals(other.getNomenclature().getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomenclature == null ? null : nomenclature.getTitle();
    }

    public Long getReturnsInvoice() {
        return returnsInvoice;
    }

    public void setReturnsInvoice(Long returnsInvoice) {
        this.returnsInvoice = returnsInvoice;
    }

    @Override
    public Integer getQuantity() {
        if (quantity == null) {
            quantity = 1;
        }
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Double getSum() {
        return price * quantity;
    }

    @Override
    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    @Override
    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    @Override
    public int getInitialQuantity() {
        return initialQuantity;
    }

    @Override
    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public String getTitle() {
        return nomenclature == null ? null : nomenclature.getTitle();
    }

    @PostLoad
    public void PostLoad() {
        if (quantity == null) {
            this.initialQuantity = 0;
            this.quantity = 1;
        } else {
            this.initialQuantity = this.quantity;
        }
        if (this.id == null) {
            this.quantity = 1;
        }
    }

    @PostUpdate
    public void PostUpdate() {
        this.initialQuantity = this.quantity;
    }
}
