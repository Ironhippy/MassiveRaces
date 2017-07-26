package net.graystone.java.races.engine;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.collections.MassiveMap;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.RaceTrait;
import net.graystone.java.races.entity.MConf;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.event.LightChangeEvent;

public class TraitEngine extends Engine
{
	
	private Map<String, Integer> scheduledTasks = new MassiveMap<String, Integer>();
	
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
		if (!playerRace.containsTrait(RaceTrait.FREEZE_DARKNESS) && !playerRace.containsTrait(RaceTrait.BURN_SUNLIGHT) &&
			!playerRace.containsTrait(RaceTrait.FEED_DARKNESS) && !playerRace.containsTrait(RaceTrait.FEED_SUNLIGHT) &&
			!playerRace.containsTrait(RaceTrait.HEAL_DARKNESS) && !playerRace.containsTrait(RaceTrait.HEAL_SUNLIGHT)) return;
		
		Player player = event.getPlayer().getPlayer();
		
		// Feed darkness
		if (playerRace.containsTrait(RaceTrait.FEED_DARKNESS) && !isLight(event.getLight()))
		{
			long hungerPeriodRemaining = (20-player.getFoodLevel())*20L;
			
			if (hungerPeriodRemaining == 20) return;
			
			Bukkit.getScheduler().scheduleSyncRepeatingTask(MassiveRaces.get(), new Runnable()
			{
			    public void run()
			    {
			    	player.setFoodLevel(player.getFoodLevel()+1);
			    }
			}, 20L, hungerPeriodRemaining);
		}
		
		// Feed sunlight
		else if (playerRace.containsTrait(RaceTrait.FEED_SUNLIGHT) && isLight(event.getLight()))
		{
			long hungerPeriodRemaining = (20-player.getFoodLevel())*20L;
			
			if (hungerPeriodRemaining == 20) return;
			
			Bukkit.getScheduler().scheduleSyncRepeatingTask(MassiveRaces.get(), new Runnable()
			{
			    public void run()
			    {
			    	player.setFoodLevel(player.getFoodLevel()+1);
			    }
			}, 20L, hungerPeriodRemaining);
		}
		
		// Heal in darkness
		if (playerRace.containsTrait(RaceTrait.HEAL_DARKNESS) && !isLight(event.getLight()))
		{
			long healthPeriodRemaining = (long) ((player.getHealthScale()-player.getHealth())*20L);
			
			Bukkit.getScheduler().scheduleSyncRepeatingTask(MassiveRaces.get(), new Runnable()
			{
			    public void run()
			    {
			    	player.setHealth(player.getHealth()+1);
			    }
			}, 20L, healthPeriodRemaining);
		}
		
		// Heal in sunlight
		else if (playerRace.containsTrait(RaceTrait.HEAL_SUNLIGHT) && isLight(event.getLight()))
		{
			long healthPeriodRemaining = (long) ((player.getHealthScale()-player.getHealth())*20L);
			
			Bukkit.getScheduler().scheduleSyncRepeatingTask(MassiveRaces.get(), new Runnable()
			{
			    public void run()
			    {
			    	player.setHealth(player.getHealth()+1);
			    }
			}, 20L, healthPeriodRemaining);
		}
		
		// Freeze in darkness
		if (playerRace.containsTrait(RaceTrait.FREEZE_DARKNESS) && !isLight(event.getLight()))
		{
			Bukkit.getScheduler().scheduleSyncRepeatingTask(MassiveRaces.get(), new Runnable()
			{
				public void run()
				{
					if (isLight(event.getLight()))
					{
						int cancelled = scheduledTasks.get(event.getPlayer().getId());
						Bukkit.getScheduler().cancelTask(cancelled);
						return;
					}
					player.damage(1D);
				}
			}, 20L, 0L);
		}
		
		// Burn in sunlight
		else if (playerRace.containsTrait(RaceTrait.BURN_SUNLIGHT) && isLight(event.getLight()))
		{
			Bukkit.getScheduler().scheduleSyncRepeatingTask(MassiveRaces.get(), new Runnable()
			{
				public void run()
				{
					if (!isLight(event.getLight()))
					{
						int cancelled = scheduledTasks.get(event.getPlayer().getId());
						Bukkit.getScheduler().cancelTask(cancelled);
						return;
					}
					player.setFireTicks(20);
				}
			}, 20L, 0L);
		}
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void lightUpdateEvent(PlayerMoveEvent event)
	{
		float lightFrom = event.getFrom().getBlock().getLightFromSky();
		float lightTo = event.getTo().getBlock().getLightFromSky();
		
		if (lightTo == lightFrom) return;
		
		LightChangeEvent calledEvent = new LightChangeEvent(event.getTo(), MPlayer.get(event.getPlayer()));
		calledEvent.run();
	}
	
	private boolean isLight(float light)
	{
		if (light >= MConf.get().burnAt()) return true;
		
		return false;
	}
	
}
