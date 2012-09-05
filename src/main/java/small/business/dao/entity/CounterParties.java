package small.business.dao.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import org.eclipse.persistence.annotations.Cache;
import small.business.domainmodel.interfaces.IElement;
import small.business.domainmodel.interfaces.IGroup;

/**
 *
 * @author ihor
 */
@Entity
@Cache
@Table(name = "counterparties")
@NamedQueries({
    @NamedQuery(name = "Counterparties.findAll", query = "SELECT c FROM CounterParties c"),
    @NamedQuery(name = "Counterparties.findById", query = "SELECT c FROM CounterParties c WHERE c.id = :id"),
    @NamedQuery(name = "Counterparties.findByTitle", query = "SELECT c FROM CounterParties c WHERE c.title = :title"),
    @NamedQuery(name = "Counterparties.findByIsgroup", query = "SELECT c FROM CounterParties c WHERE c.isgroup = :isgroup"),
    @NamedQuery(name = "Counterparties.findByParentid", query = "SELECT c FROM CounterParties c WHERE c.parentid = :parentid"),
    @NamedQuery(name = "Counterparties.findByIdAndParentId", query = "SELECT c FROM CounterParties c WHERE c.id = :id OR c.parentid = :parentid"),
    @NamedQuery(name = "Counterparties.findByGroupAndParentId", query = "SELECT c FROM CounterParties c WHERE c.parentid = :parentid AND c.isgroup = :isgroup"),
    @NamedQuery(name = "Counterparties.findByGroupAndParentIdAndId", query = "SELECT c FROM CounterParties c WHERE c.id = :id OR c.parentid = :parentid AND c.isgroup = :isgroup"),
    @NamedQuery(name = "Counterparties.findByInfo", query = "SELECT c FROM CounterParties c WHERE c.info = :info")})
public class CounterParties implements Serializable, IElement<CounterParties>, IGroup {

    @Transient
    public static final String GROUP = "Y";
    @Transient
    public static final String COUNTERPARTY = "N";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator(name = "counterparties", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "counterparties", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "title", nullable = false)
    private String title;
    @Basic(optional = false)
    @Column(name = "isgroup", nullable = false)
    private String isgroup;
    @Basic(optional = false)
    @Column(name = "parentid", nullable = false)
    private Long parentid;
    @Column(name = "info")
    private String info;

    public CounterParties() {
    }

    public CounterParties(Long id) {
        this.id = id;
    }

    public CounterParties(Long id, String title, String isgroup, Long parentid) {
        this.id = id;
        this.title = title;
        this.isgroup = isgroup;
        this.parentid = parentid;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsgroup() {
        return isgroup;
    }

    public void setIsgroup(String isgroup) {
        this.isgroup = isgroup;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CounterParties)) {
            return false;
        }
        CounterParties other = (CounterParties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getGeneralInformation() {
        return title + "\n" + (info == null ? "" : info);
    }
}