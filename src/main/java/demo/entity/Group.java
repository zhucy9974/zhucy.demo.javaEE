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

	@Column(name = "description")
	private String description;

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
		this.description = groupV.getDescription();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
