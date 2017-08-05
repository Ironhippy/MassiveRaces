package net.graystone.java.races.traits;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.massivecraft.massivecore.Engine;

import net.graystone.java.races.entity.MPlayer;
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
	
	public boolean meetsRequirements(MPlayer player)
	{
		if (player.getPlayer().getGameMode().equals(GameMode.CREATIVE)
		 || !containsTrait(player.getRace())) return false;
		
		return this.meetsRequirementsInner(player);
	}
	
	public void fireEvents(Player player)
	{
		return;
	}
	
}
