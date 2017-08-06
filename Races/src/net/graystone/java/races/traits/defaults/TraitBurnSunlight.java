package net.graystone.java.races.traits.defaults;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.traits.TraitAbstract;

public class TraitBurnSunlight extends TraitAbstract
{
	
	private static TraitBurnSunlight i = new TraitBurnSunlight();
	public static TraitBurnSunlight get() { return TraitBurnSunlight.i; }
	
	public TraitBurnSunlight()
	{
		super("burnSunlight", false);
	}
	
	@Override
	public boolean meetsRequirementsInner(MPlayer player)
	{
		if (player.getLocation().getWorld().getHighestBlockAt(player.getLocation()).getY()
			<=player.getLocation().getBlockY() && isDay(player.getLocation().getWorld())) return true;
		
		return false;
	}
	
	@EventHandler
	public void sunlightMove(PlayerMoveEvent event)
	{
		MPlayer player = MPlayer.get(event.getPlayer());
		
		if (!meetsRequirements(player)) return;
		
		new BukkitRunnable()
		{
			public void run()
			{
				if (!meetsRequirements(player))
				{
					this.cancel();
					player.getPlayer().setFireTicks(0);
				}
				
				player.getPlayer().setFireTicks(20);
			}
		}.runTaskTimer(MassiveRaces.get(), 60L, 0L);
	}
	
	protected boolean isDay(World targetWorld)
	{
		long time = targetWorld.getTime();
		
		if (time > 13500) return false;
		
		return true;
	}
	
}
