/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author ihor
 */
@Entity
@Table(name = "comingsgoods")
@NamedQueries({
    @NamedQuery(name = "ComingsGoods.findAll", query = "SELECT c FROM ComingsGoods c"),
    @NamedQuery(name = "ComingsGoods.findById", query = "SELECT c FROM ComingsGoods c WHERE c.id = :id"),
    @NamedQuery(name = "ComingsGoods.findByInvoiceid", query = "SELECT c FROM ComingsGoods c WHERE c.invoiceid = :invoiceid"),
    //@NamedQuery(name = "ComingsGoods.findByNomenclatureid", query = "SELECT c FROM ComingsGoods c WHERE c.nomenclatureid = :nomenclatureid"),
    @NamedQuery(name = "ComingsGoods.findByQuantity", query = "SELECT c FROM ComingsGoods c WHERE c.quantity = :quantity"),
    @NamedQuery(name = "ComingsGoods.findByPrice", query = "SELECT c FROM ComingsGoods c WHERE c.price = :price")})
@PrimaryKeyJoinColumn(name = "invoiceid")
public class ComingsGoods implements Serializable,IGoods {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @TableGenerator(name = "comingsgoods", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "comingsgoods", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Basic(optional = false)
    @MapsId("id")
    @Column(name = "invoiceid", nullable = false)
    private Long invoiceid;
    @JoinColumn(name = "nomenclatureid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Nomenclature nomenclature;
    @Basic(optional = false)
    @Column(name = "quantity")
    private Integer quantity;
    @Basic(optional = false)
    @Column(name = "price")
    private Double price;

    public ComingsGoods() {
    }

    public ComingsGoods(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(Long invoiceid) {
        this.invoiceid = invoiceid;
    }

    public Integer getQuantity() {
        if (quantity == null) {
            quantity = 1;
        }
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        if (price == null) {
            price = 0.01;
        }
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        if (!(object instanceof ComingsGoods)) {
            return false;
        }
        ComingsGoods other = (ComingsGoods) object;
        if ((this.nomenclature.getId() == null && other.getNomenclature().getId() != null) || (this.getNomenclature().getId() != null && !this.getNomenclature().getId().equals(other.getNomenclature().getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomenclature == null ? null : nomenclature.getTitle();
    }

    public Double getSum() {
        return getQuantity() * getPrice();
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }
}
