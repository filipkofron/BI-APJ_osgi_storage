package cz.kofron.storage.model.entity;

import java.io.Serializable;

public class ItemGroup extends AbstractEntity implements Serializable
{
	public ItemGroup(int id)
	{
		super(id);
	}
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 3029754257727308776L;
	private String name;
	private String description;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
