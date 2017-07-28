package net.graystone.java.races.event;

import org.bukkit.event.HandlerList;

import com.massivecraft.massivecore.event.EventMassiveCore;

import net.graystone.java.races.entity.MPlayer;

public class WaterMoveEvent extends EventMassiveCore
{
	
	public static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() { return handlers; }
	@Override public HandlerList getHandlers() { return handlers; }
	
	private boolean stepsWater = false;
	private MPlayer player;
	
	public WaterMoveEvent(boolean fromLand, MPlayer player)
	{	
		this.stepsWater = fromLand;
		this.player = player;
	}
	
	public MPlayer getPlayer() { return this.player; }
	public boolean fromLand() { return this.stepsWater; }
}
