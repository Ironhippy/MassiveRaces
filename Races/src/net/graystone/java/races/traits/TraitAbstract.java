package net.graystone.java.races.traits;

import com.massivecraft.massivecore.Engine;

import net.graystone.java.races.entity.MRace;

public abstract class TraitAbstract extends Engine implements Trait
{
	
	private String id;
	public String getId() { return this.id; }
	
	public TraitAbstract(String id)
	{
		this.id = id;
	}
	
	protected boolean containsTrait(MRace race)
	{
		if (!race.containsTrait(this)) return false;
		
		return true;
	}
	
}
