package small.business.dao.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import small.business.domainmodel.interfaces.IElement;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Entity
@Table(name = "ReturnsOfGoodsInvoice", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "ReturnsOfGoodsInvoice.findAll", query = "SELECT r FROM ReturnsOfGoodsInvoice r ORDER BY r.returningDate DESC")})
public class ReturnsOfGoodsInvoice implements Serializable, IElement<ReturnsOfGoodsInvoice> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator(name = "ReturnsOfGoodsInvoice", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "ReturnsOfGoodsInvoice", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private Integer typeOfReturns = 0;
    @Column(nullable = false)
    private String returningDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = true)
    private String info;
    @JoinColumn(name = "counterParty")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CounterParties counterParty;
    @JoinColumn(name = "storeHouse")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StoreHouse storeHouse = new StoreHouse(1L, "Основний склад");
    @JoinColumn(name = "returnsInvoice", referencedColumnName = "id", insertable = true, updatable = true)
    @OneToMany(targetEntity = ReturnedGoods.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<IGoods> goods = new HashSet<IGoods>();

    public void setReturningDate(String returningDate) {
        this.returningDate = returningDate;
    }

    public CounterParties getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(CounterParties counterParty) {
        this.counterParty = counterParty;
    }

    public StoreHouse getStoreHouse() {
        return storeHouse;
    }

    public void setStoreHouse(StoreHouse storeHouse) {
        this.storeHouse = storeHouse;
    }

    public Set<IGoods> getGoods() {
        return goods;
    }

    public void setGoods(Set<IGoods> goods) {
        this.goods = goods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof ReturnsOfGoodsInvoice)) {
            return false;
        }
        ReturnsOfGoodsInvoice other = (ReturnsOfGoodsInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Long.toString(id);
    }

    public Integer getTypeOfReturns() {
        return typeOfReturns;
    }

    public void setTypeOfReturns(Integer typeOfReturns) {
        this.typeOfReturns = typeOfReturns;
    }

    public Date getReturningDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = null;
        try {
            if (returningDate != null) {
                result = df.parse(returningDate);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ReturnsOfGoodsInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void setReturningDate(Date returningDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.returningDate = df.format(returningDate);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
