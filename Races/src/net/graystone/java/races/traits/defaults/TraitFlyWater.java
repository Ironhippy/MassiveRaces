package net.graystone.java.races.traits.defaults;

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
	public boolean meetsRequirements(MPlayer player)
	{
		if (!player.getLocation().asBukkitBlock().getType().equals(Material.WATER)
		 || !containsTrait(player.getRace())) return false;
		
		return true;
	}
	
	@EventHandler
	public void swimEvent(WaterMoveEvent event)
	{
		if (!meetsRequirements(event.getPlayer())) return;
		
		MPlayer player = event.getPlayer();
		
		if (!event.fromLand())
		{
			player.getPlayer().setFlying(false);
			
			return;
		}
		
		player.getPlayer().setFlying(true);
	}
}
