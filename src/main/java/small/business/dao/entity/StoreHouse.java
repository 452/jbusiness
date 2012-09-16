package small.business.dao.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

/**
 *
 * @author root
 */
@Entity
@Table(name = "storehouses", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "StoreHouse.findAll", query = "SELECT s FROM StoreHouse s"),
    @NamedQuery(name = "StoreHouse.findById", query = "SELECT s FROM StoreHouse s WHERE s.id = :id"),
    @NamedQuery(name = "StoreHouse.findByTitle", query = "SELECT s FROM StoreHouse s WHERE s.title = :title")})
public class StoreHouse implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator(name = "storehouses", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "storehouses", strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, updatable = true)
    private String title;

    public StoreHouse() {
    }

    public StoreHouse(Long id) {
        this.id = id;
    }

    public StoreHouse(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        changeSupport.firePropertyChange("title", oldTitle, title);
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
        if (!(object instanceof StoreHouse)) {
            return false;
        }
        StoreHouse other = (StoreHouse) object;
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
}