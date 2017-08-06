package net.graystone.java.races.traits.defaults;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.engine.VanillaEngine;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.traits.TraitAbstract;

public class TraitBurnRain extends TraitAbstract
{
	
	private static TraitBurnRain i = new TraitBurnRain();
	public static TraitBurnRain get() { return TraitBurnRain.i; }
	
	public TraitBurnRain()
	{
		super("burnRain", false);
	}
	
	@Override
	public boolean meetsRequirementsInner(MPlayer player)
	{
		int x = player.getLocation().getBlockX();
		int y = player.getLocation().getBlockY();
		int z = player.getLocation().getBlockZ();
		
		World hostWorld = player.getPlayer().getWorld();
		Block highestBlock = hostWorld.getHighestBlockAt(x, z);
		
		if (y>=highestBlock.getY() && hostWorld.hasStorm()) return true;
		
		if (player.getLocation().getBlock().getType()==Material.STATIONARY_WATER ||
			player.getLocation().getBlock().getType()==Material.WATER) return true;
		
		return false;
	}
	
	@EventHandler
	public void moveRain(PlayerMoveEvent event)
	{
		if (!VanillaEngine.get().isValid(event.getFrom(), event.getTo())) return;
		
		MPlayer player = MPlayer.get(event.getPlayer());
		
		if (!meetsRequirements( player )) return;
		
		new BukkitRunnable()
		{
			public void run()
			{
				if (event.getPlayer().isDead() || !meetsRequirements(player)) { this.cancel(); return; }
				
				event.getPlayer().damage(1);
			}
		}.runTaskTimer(MassiveRaces.get(), 60L, 0L);
	}
}
