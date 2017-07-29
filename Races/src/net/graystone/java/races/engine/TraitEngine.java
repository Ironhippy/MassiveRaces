package net.graystone.java.races.engine;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.massivecore.Engine;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.RaceTrait;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.event.LightChangeEvent;
import net.graystone.java.races.event.WaterMoveEvent;
import net.minecraft.server.v1_12_R1.Material;

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
			new BukkitRunnable()
			{
				public void run()
				{
					if (!hasSun(player) || !playerRace.containsTrait(RaceTrait.BURN_SUNLIGHT))
					{
						this.cancel();
						return;
					}
					
					player.setFireTicks(19);
				}
			}.runTaskTimer(MassiveRaces.get(), 20L, 0L);
		}
		
		if (playerRace.containsTrait(RaceTrait.FEED_SUNLIGHT))
		{
			new BukkitRunnable()
			{
				public void run()
				{
					if (!hasSun(player) || !playerRace.containsTrait(RaceTrait.FEED_SUNLIGHT))
					{
						this.cancel();
						return;
					}
					
					if (player.getFoodLevel()==20) return;
					
					player.setFoodLevel(player.getFoodLevel()+1);
				}
			}.runTaskTimer(MassiveRaces.get(), 80L, 0L);
		}
	}
	
	@EventHandler
	public void moveDarkness(LightChangeEvent event)
	{
		MPlayer target = event.getPlayer();
		MRace targetRace = target.getRace();
		
		if (!targetRace.containsTrait(RaceTrait.FEED_DARKNESS) && !targetRace.containsTrait(RaceTrait.FREEZE_DARKNESS)) return;
		
		Player player = target.getPlayer();
		
		if (hasSun(player)) return;
		
		if (targetRace.containsTrait(RaceTrait.FEED_DARKNESS))
		{
			new BukkitRunnable()
			{
				public void run()
				{
					if (hasSun(player) || !targetRace.containsTrait(RaceTrait.FEED_DARKNESS))
					{
						this.cancel();
						return;
					}
					
					if (player.getFoodLevel()==20) return;
					player.setFoodLevel(player.getFoodLevel()+1);
				}
			}.runTaskTimer(MassiveRaces.get(), 80L, 0L);
		}
		
		if (targetRace.containsTrait(RaceTrait.FREEZE_DARKNESS))
		{
			new BukkitRunnable()
			{
				public void run()
				{
					if (hasSun(player) || !targetRace.containsTrait(RaceTrait.FREEZE_DARKNESS))
					{
						this.cancel();
						return;
					}
					
					if (player.getHealth()==1) return;
					player.damage(1);
				}
			}.runTaskTimer(MassiveRaces.get(), 80L, 0L);
		}
	}
	
	@EventHandler
	public void ontoWaterEvent(WaterMoveEvent event)
	{
		// TODO Write breathable abilities as well as flyWater
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void lightUpdateEvent(PlayerMoveEvent event)
	{
		if (!VanillaEngine.get().isValid(event.getFrom(), event.getTo())) return;
		
		float lightFrom = event.getFrom().getBlock().getLightLevel();
		float lightTo = event.getTo().getBlock().getLightLevel();
		
		if (lightTo == lightFrom) return;
		
		LightChangeEvent calledEvent = new LightChangeEvent(event.getTo(), MPlayer.get(event.getPlayer()));
		calledEvent.run();
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void lightUpdateEvent(PlayerJoinEvent event)
	{
		LightChangeEvent calledEvent = new LightChangeEvent(event.getPlayer().getLocation(), MPlayer.get(event.getPlayer()));
		calledEvent.run();
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void waterMoveEvent(PlayerMoveEvent event)
	{
		if (!VanillaEngine.get().isValid(event.getFrom(), event.getTo())) return;
		
		if (!event.getTo().getBlock().getType().equals(Material.WATER))
		{
			WaterMoveEvent calledEvent = new WaterMoveEvent(false, MPlayer.get(event.getPlayer()));
			calledEvent.run();
			
			return;
		}
		
		WaterMoveEvent calledEvent = new WaterMoveEvent(true, MPlayer.get(event.getPlayer()));
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
