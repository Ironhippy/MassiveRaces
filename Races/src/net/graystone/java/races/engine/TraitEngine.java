package net.graystone.java.races.engine;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.massivecraft.massivecore.Engine;


import net.graystone.java.races.RaceTrait;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.event.LightChangeEvent;

public class TraitEngine extends Engine
{
	
	private static TraitEngine i = new TraitEngine();
	public static TraitEngine get() { return TraitEngine.i; }
	
	@EventHandler
	public void canFly(PlayerJoinEvent event)
	{
		MRace playerRace = MPlayer.get(event.getPlayer()).getRace();
		
		if (!playerRace.containsTrait(RaceTrait.FLY)) return;
		
		event.getPlayer().setFlying(true);
	}
	
	@EventHandler
	public void movedSunlight(LightChangeEvent event)
	{
		MPlayer target = event.getPlayer();
		MRace playerRace = target.getRace();
		
		if (!playerRace.containsTrait(RaceTrait.BURN_SUNLIGHT) && !playerRace.containsTrait(RaceTrait.FEED_SUNLIGHT)) return;
		
		Player player = event.getPlayer().getPlayer();
		if (!hasSun(player)) return;
		
		if (playerRace.containsTrait(RaceTrait.BURN_SUNLIGHT))
		{
			
		}
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void lightUpdateEvent(PlayerMoveEvent event)
	{
		float lightFrom = event.getFrom().getBlock().getLightLevel();
		float lightTo = event.getTo().getBlock().getLightLevel();
		
		if (lightTo == lightFrom) return;
		
		LightChangeEvent calledEvent = new LightChangeEvent(event.getTo(), MPlayer.get(event.getPlayer()));
		calledEvent.run();
	}
	
	private boolean hasSun(Player target)
	{
		float lightLevel = target.getLocation().getBlock().getLightFromSky();
		World occupantWorld = target.getLocation().getWorld();
		
		if (occupantWorld.getTime()>12500 || lightLevel<11) return false;
		
		return true;
	}
}
