package demo.view.entityview;

import java.io.Serializable;

import demo.entity.Group;

public class GroupView extends BaseEntityView implements Serializable {

	private static final long serialVersionUID = 2538079483292512055L;
	private String name;
	private String description;

	public GroupView() {

	}

	public GroupView(Group group) {
		this.setId(group.getId());
		this.setStatus(group.getStatus());
		this.setCreateDate(group.getCreateDate());
		this.setUpdateDate(group.getUpdateDate());

		this.name = group.getName();
		this.description = group.getDescription();
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
