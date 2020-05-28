package service.AAADEVPoCDemoPage.Entity;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author umansilla
 */
@Entity
@Table(name = "collaterals", catalog = "aaadevpocdemopage", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Collaterals.findAll", query = "SELECT c FROM Collaterals c")
    , @NamedQuery(name = "Collaterals.findById", query = "SELECT c FROM Collaterals c WHERE c.id = :id")
    , @NamedQuery(name = "Collaterals.findByType", query = "SELECT c FROM Collaterals c WHERE c.type = :type")
    , @NamedQuery(name = "Collaterals.findByTitle", query = "SELECT c FROM Collaterals c WHERE c.title = :title")
    , @NamedQuery(name = "Collaterals.findByLink", query = "SELECT c FROM Collaterals c WHERE c.link = :link")
    , @NamedQuery(name = "Collaterals.findByUpdatedtime", query = "SELECT c FROM Collaterals c WHERE c.updatedtime = :updatedtime")
    , @NamedQuery(name = "Collaterals.findByIddemo", query = "SELECT c FROM Collaterals c WHERE c.iddemo = :iddemo")})
public class Collaterals implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;
    @Size(max = 2147483647)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "link")
    private String link;
    @Size(max = 2147483647)
    @Column(name = "updatedtime")
    private String updatedtime;
    @Size(max = 2147483647)
    @Column(name = "iddemo")
    private String iddemo;

    public Collaterals() {
    }

    public Collaterals(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }

    public String getIddemo() {
        return iddemo;
    }

    public void setIddemo(String iddemo) {
        this.iddemo = iddemo;
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
        if (!(object instanceof Collaterals)) {
            return false;
        }
        Collaterals other = (Collaterals) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.aaadevpocdemopage.entity.Collaterals[ id=" + id + " ]";
    }
    
}
