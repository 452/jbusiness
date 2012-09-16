package small.business.dao.entity;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.apache.log4j.Logger;

/**
 *
 * @author ihor
 */
@Entity
@Table(name = "pictures")
@NamedQueries({
    @NamedQuery(name = "Pictures.findAll", query = "SELECT p FROM Pictures p"),
    @NamedQuery(name = "Pictures.findById", query = "SELECT p FROM Pictures p WHERE p.id = :id"),
    @NamedQuery(name = "Pictures.findByNomenclatureid", query = "SELECT p FROM Pictures p WHERE p.nomenclatureid = :nomenclatureid"),
    @NamedQuery(name = "Pictures.findByData", query = "SELECT p FROM Pictures p WHERE p.data = :data")})
public class Pictures implements Serializable {

    static Logger log = Logger.getLogger(Pictures.class.getName());
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "pictures", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(generator = "pictures", strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "nomenclatureid", nullable = false)
    private Long nomenclatureid;
    @Column(name = "data", nullable = false)
    @Lob
    private byte[] data;
    @Column(name = "info", nullable = true)
    private String info;

    public Pictures(byte[] data) {
        this.data = data;
    }

    public Pictures() {
    }

    public Pictures(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomenclatureid() {
        return nomenclatureid;
    }

    public void setNomenclatureid(Long nomenclatureid) {
        this.nomenclatureid = nomenclatureid;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (data != null ? Arrays.hashCode(data) : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pictures)) {
            return false;
        }
        Pictures other = (Pictures) object;
        if (this.data != null && other.data != null && !Arrays.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id == null ? "" : String.valueOf(id);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}