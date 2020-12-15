package demo.view.entityview;

import java.io.Serializable;

import javax.persistence.Column;

import demo.entity.Group;

public class GroupView extends BaseEntityView implements Serializable {

	private static final long serialVersionUID = 2538079483292512055L;
	private String name;
	private String shortDesc;
	private String longDesc;

	public GroupView() {

	}

	public GroupView(Group group) {
		this.setId(group.getId());
		this.setStatus(group.getStatus());
		this.setCreateDate(group.getCreateDate());
		this.setUpdateDate(group.getUpdateDate());

		this.name = group.getName();
		this.shortDesc = group.getShortDesc();
		this.longDesc = group.getLongDesc();
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
