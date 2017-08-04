package net.graystone.java.races.event;

import org.bukkit.event.HandlerList;

import com.massivecraft.massivecore.event.EventMassiveCore;

import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MRace;

public class RaceUpdateEvent extends EventMassiveCore
{
	
	public static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandelerList() { return handlers; }
	public HandlerList getHandlers() { return handlers; }
	
	private MPlayer player;
	private MRace race;
	
	public RaceUpdateEvent(MPlayer player, MRace race)
	{
		this.player = player;
		this.race = race;
	}
	
	public MPlayer getPlayer() { return this.player; }
	
	public MRace getRace() { return this.race; } 
	
}
