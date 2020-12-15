package demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import demo.view.entityview.GroupView;
import demo.view.entityview.UserView;

@Entity
@Table(name = "group")
public class Group extends BaseEntity {
	@Column(name = "name")
	private String name;

	@Column(name = "short_desc")
	private String shortDesc;
	
	@Column(name = "long_desc")
	private String longDesc;

	public Group() {
		super();
	}

	public Group(GroupView groupV) {
		super();
		if(groupV.getId()==null) {
			this.setStatus(1);
			this.setCreateDate(new Date());
			
			//TODO à supprimer
			this.setUpdateDate(new Date());
		}else {
			//TODO à faire correctement ces deux attributes
			this.setCreateDate(new Date());
			this.setStatus(1);
			
			this.setId(groupV.getId());
			this.setUpdateDate(new Date());
		}
		this.name = groupV.getName();
		this.shortDesc = groupV.getShortDesc();
		this.longDesc = groupV.getLongDesc();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

}
