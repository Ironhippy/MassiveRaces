package net.graystone.java.races.traits;

import com.massivecraft.massivecore.Engine;

public abstract class TraitAbstract extends Engine
{
	
	private String id;
	public String getId() { return this.id; } 
	
	public TraitAbstract(String id)
	{
		this.id = id;
	}
	
}
