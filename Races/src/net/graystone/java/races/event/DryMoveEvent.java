package net.graystone.java.races.event;

import org.bukkit.event.HandlerList;

import com.massivecraft.massivecore.event.EventMassiveCore;

import net.graystone.java.races.entity.MPlayer;

public class DryMoveEvent extends EventMassiveCore
{
	
	public static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() { return handlers; }
	public HandlerList getHandlers() { return handlers; }
	
	private MPlayer player;
	private boolean fromWater = false;
	
	public DryMoveEvent(boolean fromWater, MPlayer player)
	{
		this.player = player;
		this.fromWater = fromWater;
	}
	
	public MPlayer getPlayer() { return this.player; }
	public boolean fromWater() { return this.fromWater; }
	
}
