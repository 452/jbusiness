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
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Cache;
import small.business.domainmodel.interfaces.IElement;
import small.business.domainmodel.interfaces.IGoods;

/**
 *
 * @author root
 */
@Entity
@Cache
@Table(name = "comingsofinvoicesongoods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComingsInvoice.findAll", query = "SELECT c FROM ComingsInvoice c ORDER BY c.createdDate DESC"),
    @NamedQuery(name = "ComingsInvoice.findById", query = "SELECT c FROM ComingsInvoice c WHERE c.id = :id"),
    @NamedQuery(name = "ComingsInvoice.findByInvoiceid", query = "SELECT c FROM ComingsInvoice c WHERE c.invoiceid = :invoiceid")})
public class ComingsInvoice implements Serializable, IElement<ComingsInvoice> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator(name = "comingsofinvoicesongoods", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "comingsofinvoicesongoods", strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    // @Temporal(TemporalType.DATE)
    private String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    @Column(name = "statusofpayment")
    private Integer statusOfPayment;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "paidamount")
    private Double paidAmount = 0.0;
    @Column(name = "invoiceid")
    private String invoiceid;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "info")
    private String info;
    @JoinColumn(name = "counterpartyid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CounterParties counterParty;
    @JoinColumn(name = "storehouse")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StoreHouse storeHouse = new StoreHouse(1L, "Основний склад");
    @JoinColumn(name = "invoiceid", referencedColumnName = "id", insertable = true, updatable = true)
    @OneToMany(targetEntity = ComingsGoods.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // @MapsId("invoiceid")
    // @OneToMany(targetEntity = ComingsGoods.class,mappedBy="invoiceid")
    // @OrderBy("quantity ASC")
    private Set<IGoods> goods = new HashSet<IGoods>();

    public CounterParties getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(CounterParties counterParty) {
        this.counterParty = counterParty;
    }

    public ComingsInvoice() {
    }

    public ComingsInvoice(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = null;
        try {
            if (createdDate != null) {
                result = df.parse(createdDate);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ComingsInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void setCreatedDate(Date createdDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdDate = df.format(createdDate);
    }

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof ComingsInvoice)) {
            return false;
        }
        ComingsInvoice other = (ComingsInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getStatusOfPayment() {
        if (statusOfPayment == null) {
            statusOfPayment = 0;
        }
        return statusOfPayment;
    }

    public void setStatusOfPayment(Integer statusOfPayment) {
        this.statusOfPayment = statusOfPayment;
    }

    public Double getPaidAmount() {
        if (paidAmount == null) {
            paidAmount = 0.0;
        }
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public boolean addGoods(IGoods goods) {
        return this.goods.add(goods);
    }
}