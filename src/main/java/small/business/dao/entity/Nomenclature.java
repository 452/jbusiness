package small.business.dao.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import small.business.domainmodel.interfaces.IElement;
import small.business.domainmodel.interfaces.IGroup;

/**
 *
 * @author root
 */
@Entity
@Table(name = "nomenclature", catalog = "", schema = "")
@Cache(type = CacheType.FULL, size = 10000)
//@Cacheable
@NamedQueries({
    @NamedQuery(name = "Nomenclature.findAll", query = "SELECT n FROM Nomenclature n"),
    @NamedQuery(name = "Nomenclature.findById", query = "SELECT n FROM Nomenclature n WHERE n.id = :id"),
    @NamedQuery(name = "Nomenclature.findByIdAndParentId", query = "SELECT n FROM Nomenclature n WHERE n.id = :id OR n.parentid = :parentid"),
    @NamedQuery(name = "Nomenclature.findByGroupAndParentIdAndId", query = "SELECT n FROM Nomenclature n WHERE n.id = :id OR n.parentid = :parentid AND n.isgroup = :isgroup"),
    @NamedQuery(name = "Nomenclature.findByIsgroup", query = "SELECT n FROM Nomenclature n WHERE n.isgroup = :isgroup"),
    @NamedQuery(name = "Nomenclature.findByParentid", query = "SELECT n FROM Nomenclature n WHERE n.parentid = :parentid"),
    @NamedQuery(name = "Nomenclature.findByTitle", query = "SELECT n FROM Nomenclature n WHERE n.title = :title"),
    @NamedQuery(name = "Nomenclature.findByArticleofgoods", query = "SELECT n FROM Nomenclature n WHERE n.articleofgoods = :articleofgoods"),
    @NamedQuery(name = "Nomenclature.findByArticleinside", query = "SELECT n FROM Nomenclature n WHERE n.articleinside = :articleinside"),
    @NamedQuery(name = "Nomenclature.findByPrice", query = "SELECT n FROM Nomenclature n WHERE n.price = :price"),
    @NamedQuery(name = "Nomenclature.findByPriceretail", query = "SELECT n FROM Nomenclature n WHERE n.priceretail = :priceretail"),
    @NamedQuery(name = "Nomenclature.findByPricesmallwholesale", query = "SELECT n FROM Nomenclature n WHERE n.pricesmallwholesale = :pricesmallwholesale"),
    @NamedQuery(name = "Nomenclature.findByPricebigwholesale", query = "SELECT n FROM Nomenclature n WHERE n.pricebigwholesale = :pricebigwholesale"),
    @NamedQuery(name = "Nomenclature.findByQuantity", query = "SELECT n FROM Nomenclature n WHERE n.quantity = :quantity"),
    @NamedQuery(name = "Nomenclature.findByQuantitymin", query = "SELECT n FROM Nomenclature n WHERE n.quantitymin = :quantitymin")})
public class Nomenclature implements Serializable, IElement<Nomenclature>, IGroup {

    @Transient
    public static final String GROUP = "Y";
    @Transient
    public static final String NOMENCLATURE = "N";
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator(name = "nomenclature", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "nomenclature", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "isgroup", nullable = false)
    private String isgroup;
    @Basic(optional = false)
    @Column(name = "parentid", nullable = false)
    private Long parentid;
    @Basic(optional = false)
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "articleofgoods", nullable = true)
    private String articleofgoods;
    @Column(name = "articleinside", nullable = true)
    private String articleinside;
    @Column(name = "price", nullable = true)//,precision=12, scale=2
    private Double price;
    @Column(name = "priceretail", nullable = true)
    private Integer priceretail;
    @Column(name = "pricesmallwholesale", nullable = true)
    private Integer pricesmallwholesale;
    @Column(name = "pricebigwholesale", nullable = true)
    private Integer pricebigwholesale;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = true)
    private Integer quantity;
    @Column(name = "quantitymin", nullable = true)
    private Integer quantitymin;
    @Basic(optional = false)
    @Column(name = "info", nullable = true)
    private String info;
    @JoinColumn(name = "nomenclatureid", referencedColumnName = "id", insertable = true, updatable = true)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Pictures> pictures = new HashSet<Pictures>();

    public Nomenclature() {
    }

    public Nomenclature(Long id) {
        this.id = id;
    }

    public Nomenclature(Long id, String isgroup) {
        this.id = id;
        this.isgroup = isgroup;
    }

    public Nomenclature(String isgroup) {
        this.isgroup = isgroup;
    }

    public Nomenclature(Long id, Long parentid, String title) {
        this.id = id;
        this.parentid = parentid;
        this.title = title;
    }

    public Nomenclature(String isgroup, Long parentid, String title) {
        this.isgroup = isgroup;
        this.parentid = parentid;
        this.title = title;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String isGroup() {
        return isgroup;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        Long oldParentid = this.parentid;
        this.parentid = parentid;
        changeSupport.firePropertyChange("parentid", oldParentid, parentid);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        changeSupport.firePropertyChange("title", oldTitle, title);
    }

    public String getArticleofgoods() {
        return articleofgoods;
    }

    public void setArticleofgoods(String articleofgoods) {
        String oldArticleofgoods = this.articleofgoods;
        this.articleofgoods = articleofgoods;
        changeSupport.firePropertyChange("articleofgoods", oldArticleofgoods, articleofgoods);
    }

    public String getArticleinside() {
        return articleinside;
    }

    public void setArticleinside(String articleinside) {
        String oldArticleinside = this.articleinside;
        this.articleinside = articleinside;
        changeSupport.firePropertyChange("articleinside", oldArticleinside, articleinside);
    }

    public Double getPrice() {
        if (price == null && isGroup().equals(NOMENCLATURE)) {
            price = 0.01;
        }
        return price;
    }

    public void setPrice(Double price) {
        Double oldPrice = this.price;
        this.price = price;
        changeSupport.firePropertyChange("price", oldPrice, price);
    }

    public Integer getPriceretail() {
        if (priceretail == null && isGroup().equals(NOMENCLATURE)) {
            priceretail = 0;
        }
        return priceretail;
    }

    public void setPriceretail(Integer priceretail) {
        Integer oldPriceretail = this.priceretail;
        this.priceretail = priceretail;
        changeSupport.firePropertyChange("priceretail", oldPriceretail, priceretail);
    }

    public Integer getPricesmallwholesale() {
        if (pricesmallwholesale == null && isGroup().equals(NOMENCLATURE)) {
            pricesmallwholesale = 0;
        }
        return pricesmallwholesale;
    }

    public void setPricesmallwholesale(Integer pricesmallwholesale) {
        Integer oldPricesmallwholesale = this.pricesmallwholesale;
        this.pricesmallwholesale = pricesmallwholesale;
        changeSupport.firePropertyChange("pricesmallwholesale", oldPricesmallwholesale, pricesmallwholesale);
    }

    public Integer getPricebigwholesale() {
        if (pricebigwholesale == null && isGroup().equals(NOMENCLATURE)) {
            pricebigwholesale = 0;
        }
        return pricebigwholesale;
    }

    public void setPricebigwholesale(Integer pricebigwholesale) {
        Integer oldPricebigwholesale = this.pricebigwholesale;
        this.pricebigwholesale = pricebigwholesale;
        changeSupport.firePropertyChange("pricebigwholesale", oldPricebigwholesale, pricebigwholesale);
    }

    public Integer getQuantity() {
        if (quantity == null) {// &&
            quantity = 0;
        }
        if (isGroup().equals(GROUP)) {
            quantity = null;
        }
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        Integer oldQuantity = this.quantity;
        this.quantity = quantity;
        changeSupport.firePropertyChange("quantity", oldQuantity, quantity);
    }

    public Integer getQuantitymin() {
        if (quantitymin == null && isGroup().equals(NOMENCLATURE)) {
            quantitymin = 0;
        }
        return quantitymin;
    }

    public void setQuantitymin(Integer quantitymin) {
        Integer oldQuantitymin = this.quantitymin;
        this.quantitymin = quantitymin;
        changeSupport.firePropertyChange("quantitymin", oldQuantitymin, quantitymin);
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
        if (!(object instanceof Nomenclature)) {
            return false;
        }
        Nomenclature other = (Nomenclature) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getRetailPrice() {
        Double result = null;
        if (isGroup().equals(NOMENCLATURE)) {
            result = price * ((getPriceretail() * 0.01) + 1);
            if (getPriceretail().equals(0)) {
                result = 0.0;
            }
        }
        return result;
    }

    public Double getSmallWholeSalePrice() {
        Double result = null;
        if (isGroup().equals(NOMENCLATURE)) {
            result = price * ((getPricesmallwholesale() * 0.01) + 1);
            if (getPricesmallwholesale().equals(0)) {
                result = 0.0;
            }
        }
        return result;
    }

    public Double getBigWholeSalePrice() {
        Double result = null;
        if (isGroup().equals(NOMENCLATURE)) {
            result = price * ((getPricebigwholesale() * 0.01) + 1);
            if (getPricebigwholesale().equals(0)) {
                result = 0.0;
            }
        }
        return result;
    }

    public Set<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Pictures> pictures) {
        this.pictures = pictures;
    }
}