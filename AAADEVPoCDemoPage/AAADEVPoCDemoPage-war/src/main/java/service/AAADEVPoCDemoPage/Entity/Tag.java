package service.AAADEVPoCDemoPage.Entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name="tags")
@NamedQueries({
	@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t"),
	@NamedQuery(name="Tag.findById", query="SELECT t FROM Tag t WHERE t.id = :id"),
	@NamedQuery(name="Tag.findByIddemo", query="SELECT t FROM Tag t WHERE t.iddemo = :iddemo")	
})
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String iddemo;

	private String name;

	public Tag() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIddemo() {
		return this.iddemo;
	}

	public void setIddemo(String iddemo) {
		this.iddemo = iddemo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}