package net.graystone.java.races.traits.defaults;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.event.WaterMoveEvent;
import net.graystone.java.races.traits.TraitAbstract;

public class TraitFlyWater extends TraitAbstract
{
	
	private static TraitFlyWater i = new TraitFlyWater();
	public static TraitFlyWater get() { return TraitFlyWater.i; }
	
	public TraitFlyWater()
	{
		super("flyWater");
	}
	
	@Override
	public boolean meetsRequirementsInner(MPlayer player)
	{
		if (!containsTrait(player.getRace())
		 || !isSubmerged(player.getLocation()))
		{
			if (player.getPlayer().isFlying()) player.getPlayer().setFlying(false);
			
			return false;
		}
		
		if (player.getPlayer().isFlying()
		 && !isSubmerged(player.getPlayer().getLocation())) return false;
		
		return true;
	}
	
	@EventHandler
	public void swimEvent(WaterMoveEvent event)
	{
		if (!meetsRequirements(event.getPlayer())) return;
		MPlayer player = event.getPlayer();
		
		player.getPlayer().setFlying(true);
	}
	
	protected boolean isSubmerged(Location to)
	{
		if (to.getBlock().getType().equals(Material.STATIONARY_WATER) ||
			to.getBlock().getType().equals(Material.WATER)) return true;
		
		return false;
	}
}
