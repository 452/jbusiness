package small.business.dao.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "cashregister")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cashregister.findAll", query = "SELECT c FROM Cashregister c"),
    @NamedQuery(name = "Cashregister.findById", query = "SELECT c FROM Cashregister c WHERE c.id = :id"),
    @NamedQuery(name = "Cashregister.findByDateoperation", query = "SELECT c FROM Cashregister c WHERE c.dateoperation = :dateoperation"),
    @NamedQuery(name = "Cashregister.findByTypeoperation", query = "SELECT c FROM Cashregister c WHERE c.typeoperation = :typeoperation"),
    @NamedQuery(name = "Cashregister.findByOrderid", query = "SELECT c FROM Cashregister c WHERE c.orderid = :orderid"),
    @NamedQuery(name = "Cashregister.findByExchangerate", query = "SELECT c FROM Cashregister c WHERE c.exchangerate = :exchangerate"),
    @NamedQuery(name = "Cashregister.findByPrice", query = "SELECT c FROM Cashregister c WHERE c.price = :price"),
    @NamedQuery(name = "Cashregister.findByInfo", query = "SELECT c FROM Cashregister c WHERE c.info = :info")})
public class Cashregister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateoperation")
    private String dateoperation;
    @Column(name = "typeoperation")
    private Integer typeoperation;
    @Column(name = "orderid")
    private Integer orderid;
    @Column(name = "exchangerate")
    private String exchangerate;
    @Column(name = "price")
    private String price;
    @Column(name = "info")
    private String info;

    public Cashregister() {
    }

    public Cashregister(Integer id) {
        this.id = id;
    }

    public Cashregister(Integer id, String dateoperation) {
        this.id = id;
        this.dateoperation = dateoperation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateoperation() {
        return dateoperation;
    }

    public void setDateoperation(String dateoperation) {
        this.dateoperation = dateoperation;
    }

    public Integer getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(Integer typeoperation) {
        this.typeoperation = typeoperation;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(String exchangerate) {
        this.exchangerate = exchangerate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        if (!(object instanceof Cashregister)) {
            return false;
        }
        Cashregister other = (Cashregister) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "small.business.swing.domain.Cashregister[ id=" + id + " ]";
    }
}