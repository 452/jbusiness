package small.business.dao.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.xml.bind.annotation.XmlRootElement;
import small.business.domainmodel.interfaces.IElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h ORDER BY h.date DESC"),
    @NamedQuery(name = "History.findById", query = "SELECT h FROM History h WHERE h.id = :id"),
    //@NamedQuery(name = "History.findByDate", query = "SELECT h FROM History h WHERE h.date = :date"),
    @NamedQuery(name = "History.findByTitle", query = "SELECT h FROM History h WHERE h.title = :title"),
    @NamedQuery(name = "History.findByInfo", query = "SELECT h FROM History h WHERE h.info = :info")})
public class History implements Serializable, IElement<History> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator(name = "history", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "history", strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "title")
    private String title;
    @Column(name = "info")
    private String info;

    public History() {
    }

    public History(Long id) {
        this.id = id;
    }

    public History(String title, String info) {
        this.title = title;
        this.info = info;
        setDate(new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = null;
        try {
            if (date != null) {
                result = df.parse(date);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ComingsInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void setDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = df.format(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Long.valueOf(id).toString();
    }
}