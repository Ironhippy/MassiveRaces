package net.graystone.java.races.event;

import org.bukkit.Location;
import org.bukkit.event.HandlerList;

import com.massivecraft.massivecore.event.EventMassiveCore;

import net.graystone.java.races.entity.MPlayer;

public class LightChangeEvent extends EventMassiveCore
{
	
	public static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() { return handlers; } 
	public HandlerList getHandlers() { return handlers; }
	
	private Location lightLocation;
	private MPlayer player;
	private byte lightLevel;
	
	public LightChangeEvent(Location lightLocation, MPlayer player)
	{
		this.lightLocation = lightLocation;
		this.lightLevel = lightLocation.getBlock().getLightFromSky();
	}
	
	public Location getLocation() { return this.lightLocation; }
	public MPlayer getPlayer() { return this.player; }
	public float getLight() { return this.lightLevel; }
}
