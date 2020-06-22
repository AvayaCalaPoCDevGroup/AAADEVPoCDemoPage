package service.AAADEVPoCDemoPage.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONPropertyIgnore;
import org.json.JSONPropertyName;

/**
 *
 * @author umansilla
 */
@Entity
@Table(name = "demo", catalog = "aaadevpocdemopage", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demo.findAll", query = "SELECT d FROM Demo d order by d.updatedtime desc")
    , @NamedQuery(name = "Demo.findById", query = "SELECT d FROM Demo d WHERE d.id = :id")
    , @NamedQuery(name = "Demo.findByTitle", query = "SELECT d FROM Demo d WHERE d.title = :title")
    , @NamedQuery(name = "Demo.findByDescription", query = "SELECT d FROM Demo d WHERE d.description = :description")
    , @NamedQuery(name = "Demo.findByLink", query = "SELECT d FROM Demo d WHERE d.link = :link")
    , @NamedQuery(name = "Demo.findByUpdatedtime", query = "SELECT d FROM Demo d WHERE d.updatedtime = :updatedtime")})
public class Demo implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 2147483647)
    @Column(name = "link")
    private String link;
    @Size(max = 2147483647)
    @Column(name = "updatedtime")
    private String updatedtime;
    @Column(name = "available")
    private String available;

    public Demo() {
    }

	public Demo(Long id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    
    public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Demo)) {
//            return false;
//        }
//        Demo other = (Demo) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "service.aaadevpocdemopage.entity.Demo[ id=" + id + " ]";
//    }
    
}
