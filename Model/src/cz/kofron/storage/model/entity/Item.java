package cz.kofron.storage.model.entity;

import java.io.Serializable;

public class Item extends AbstractEntity implements Serializable
{

	public Item(int id)
	{
		super(id);
	}

	public Item(int id, long timeAdded, String info, int groupId, int addedBy)
	{
		super(id);
		this.timeAdded = timeAdded;
		this.info = info;
		this.groupId = groupId;
		this.addedBy = addedBy;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3029754257727308776L;
	private long timeAdded;
	private String info;
	private int groupId;
	private int addedBy;
	
	public long getTimeAdded()
	{
		return timeAdded;
	}
	public void setTimeAdded(long timeAdded)
	{
		this.timeAdded = timeAdded;
	}
	public String getInfo()
	{
		return info;
	}
	public void setInfo(String info)
	{
		this.info = info;
	}
	
	public int getGroupId()
	{
		return groupId;
	}
	
	public void setGroupId(int groupId)
	{
		this.groupId = groupId;
	}
	
	public int getAddedBy()
	{
		return addedBy;
	}
	
	public void setAddedBy(int addedBy)
	{
		this.addedBy = addedBy;
	}	
}
