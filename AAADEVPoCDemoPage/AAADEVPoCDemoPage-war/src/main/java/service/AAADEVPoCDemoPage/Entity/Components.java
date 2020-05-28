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
@Table(name = "components", catalog = "aaadevpocdemopage", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Components.findAll", query = "SELECT c FROM Components c")
    , @NamedQuery(name = "Components.findById", query = "SELECT c FROM Components c WHERE c.id = :id")
    , @NamedQuery(name = "Components.findByName", query = "SELECT c FROM Components c WHERE c.name = :name")
    , @NamedQuery(name = "Components.findByIddemo", query = "SELECT c FROM Components c WHERE c.iddemo = :iddemo")})
public class Components implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "iddemo")
    private String iddemo;

    public Components() {
    }

    public Components(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Components)) {
            return false;
        }
        Components other = (Components) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.aaadevpocdemopage.entity.Components[ id=" + id + " ]";
    }
    
}
