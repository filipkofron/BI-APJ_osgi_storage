package cz.kofron.storage.model.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable
{
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 7177486465762706316L;
	protected final int id;
	
	public AbstractEntity(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
}
