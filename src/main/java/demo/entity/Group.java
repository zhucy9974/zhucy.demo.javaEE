package demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import demo.view.entityview.GroupView;
import demo.view.entityview.UserView;

@Entity
@Table(name = "group_app")
public class Group extends BaseEntity {
	@Column(name = "name")
	private String name;

	@Column(name = "short_desc")
	private String shortDesc;

	@Column(name = "long_desc")
	private String longDesc;

	@ManyToMany
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	public Group() {
		super();
	}

	public Group(GroupView groupV) {
		super();
		if (groupV.getId() == null) {
			this.setStatus(1);
			this.setDateCreate(new Date());

			// TODO à supprimer
			this.setDateUpdate(new Date());
		} else {
			// TODO à faire correctement ces deux attributes
			this.setDateCreate(new Date());
			this.setStatus(1);

			this.setId(groupV.getId());
			this.setDateUpdate(new Date());
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
