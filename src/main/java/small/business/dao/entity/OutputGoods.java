package small.business.dao.entity;

import java.io.Serializable;

import javax.persistence.Basic;
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
@Table(name = "outputgoods")
public class OutputGoods implements Serializable, IGoods {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator(name = "outputgoods", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "outputgoods", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "invoiceid", nullable = false)
    private Long invoiceid;
    @JoinColumn(name = "nomenclatureid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Nomenclature nomenclature;
    @Column(name = "typeofprice", nullable = false)
    private Integer typeOfPrice = 1;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false, updatable = true)
    private Integer quantity = null;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private Double price = 0.01;
    @Transient
    private int initialQuantity = 0;

    public OutputGoods() {
        if (quantity == null) {
            this.initialQuantity = 0;
            this.quantity = 1;
        }
    }

    public OutputGoods(Long id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param invoiceid
     * @param nomenclature
     */
    public OutputGoods(Long invoiceid, Nomenclature nomenclature, Integer quantity) {
        // this.id = id;
        this.invoiceid = invoiceid;
        this.nomenclature = nomenclature;
        this.quantity = quantity;
        // this.initialQuantity = 0;
    }

    public OutputGoods(Long id, Long invoiceid, Nomenclature nomenclature, Integer quantity, int initialQuantity) {
        this.id = id;
        this.invoiceid = invoiceid;
        this.nomenclature = nomenclature;
        this.quantity = quantity;
        this.initialQuantity = initialQuantity;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeOfPrice() {
        if (typeOfPrice == null) {
            typeOfPrice = 1;
        }
        return typeOfPrice;
    }

    /**
     * 1 - retail 2 - small whole sale 3 - big whole sale
     *
     * @param typeofprice
     */
    public void setTypeOfPrice(Integer typeofprice) {
        this.typeOfPrice = typeofprice;
    }

    @Override
    public Integer getQuantity() {
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
        return getPrice() / getQuantity();
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
        if (!(object instanceof OutputGoods)) {
            return false;
        }
        OutputGoods other = (OutputGoods) object;
        if (!this.getNomenclature().getId().equals(other.getNomenclature().getId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomenclature == null ? null : nomenclature.getTitle();
    }

    public String getTitle() {
        return nomenclature == null ? null : nomenclature.getTitle();
    }

    @Override
    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    @Override
    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    /**
     * @return Initial value quantity of goods
     */
    @Override
    public int getInitialQuantity() {
        return initialQuantity;
    }

    @Override
    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
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

    public Long getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(Long invoiceid) {
        this.invoiceid = invoiceid;
    }
}