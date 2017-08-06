package net.graystone.java.races.engine;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.massivecore.Engine;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.event.DryMoveEvent;
import net.graystone.java.races.event.LightChangeEvent;
import net.graystone.java.races.event.RaceUpdateEvent;
import net.graystone.java.races.event.WaterMoveEvent;
import net.graystone.java.races.traits.TraitAbstract;

public class VanillaEngine extends Engine
{
	
	private static VanillaEngine i = new VanillaEngine();
	public static VanillaEngine get() { return VanillaEngine.i; }
	
	@EventHandler
	public void particleEvent(PlayerMoveEvent event)
	{
		if (!isValid(event.getFrom(), event.getTo())) return;
		MPlayer player = MPlayer.get(event.getPlayer());
		
		if (!player.areParticlesToggled()) return;
		
		MRace playerRace = player.getRace();
		
		if (!playerRace.hasParticleEffect()) return;
		
		Location targetLocation = event.getTo();
		
		targetLocation.getWorld().spawnParticle(playerRace.getParticleEffect(), targetLocation.getX(), targetLocation.getY(), targetLocation.getZ(), 12);
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void lightChange(PlayerMoveEvent event)
	{
		if (!isValid(event.getFrom(), event.getTo())) return;
		
		Location from = event.getFrom();
		Location to = event.getTo();
		
		if (from.getBlock().getLightFromSky()==to.getBlock().getLightFromSky()) return;
		
		LightChangeEvent eventCalled = new LightChangeEvent(to, MPlayer.get(event.getPlayer()));
		eventCalled.run();
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void swimEvent(PlayerMoveEvent event)
	{
		if (!isValid(event.getFrom(), event.getTo())) return;
		
		Location from = event.getFrom();
		Location to = event.getTo();
		
		if (!to.getBlock().getType().equals(Material.WATER) && !to.getBlock().getType().equals(Material.STATIONARY_WATER))
		{
			if (event.getPlayer().isFlying() && event.getPlayer().getGameMode()!=GameMode.CREATIVE) event.getPlayer().setFlying(false);
			
			boolean fromWater = false;
			if (to.getBlock().getType().equals(Material.STATIONARY_WATER) || to.getBlock().getType().equals(Material.WATER)
			 || to.getBlock().getType().equals(Material.STATIONARY_LAVA) || to.getBlock().getType().equals(Material.LAVA)) fromWater = true;
			
			DryMoveEvent calledEvent = new DryMoveEvent(fromWater, MPlayer.get(event.getPlayer()));
			calledEvent.run();
			
			return;
		}
		
		boolean fromLand = false;
		
		if (!from.getBlock().getType().equals(Material.WATER) && !from.getBlock().getType().equals(Material.STATIONARY_WATER)) fromLand = true;
		
		WaterMoveEvent calledEvent = new WaterMoveEvent(fromLand, MPlayer.get(event.getPlayer()));
		calledEvent.run();
	}
	
	@EventHandler
	public void runEvents(RaceUpdateEvent event)
	{
		for (TraitAbstract traits : event.getRace().getTraits())
		{
			traits.fireEvents(event.getPlayer().getPlayer());
		}
	}
	
	public boolean isValid(Location firstLocation, Location secondLocation)
	{
		if (firstLocation.getX()==secondLocation.getX() && firstLocation.getZ()==secondLocation.getZ() && firstLocation.getY()==secondLocation.getY())
		{
			return false;
		}
		
		return true;
	}
	
	public void potionRun()
	{
		new BukkitRunnable()
		{
			public void run()
			{
				for (Player players : Bukkit.getOnlinePlayers())
				{
					MPlayer player = MPlayer.get(players);
					
					if (players.getGameMode()==GameMode.CREATIVE) return;
					
					MRace playerRace = player.getRace();
					
					for (PotionEffectType types : playerRace.getPotionEffects())
					{
						PotionEffect playedEffect = new PotionEffect(types, 200, 0);
						
						if (players.hasPotionEffect(types)) continue;
						
						players.addPotionEffect(playedEffect, true);
					}
				}
			}
		}.runTaskTimer(MassiveRaces.get(), 30L, 0L);
	}
	
	protected boolean isSubmerged(Location to)
	{
		if (to.getBlock().getType().equals(Material.STATIONARY_WATER) ||
			to.getBlock().getType().equals(Material.WATER)) return true;
		
		return false;
	}
	
	@Override
	public void setActiveInner(boolean set)
	{
		super.setActiveInner(set);
		
		this.potionRun();
	}
}
