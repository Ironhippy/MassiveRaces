package net.graystone.java.races.traits.defaults;

import org.bukkit.event.EventHandler;

import net.graystone.java.races.MassiveRaces;
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
		if (!containsTrait(player.getRace()))
		{
			if (player.getPlayer().isFlying()) player.getPlayer().setFlying(false);
			
			return false;
		}
		
		return true;
	}
	
	@EventHandler
	public void swimEvent(WaterMoveEvent event)
	{
		MassiveRaces.get().log("1");
		if (!meetsRequirements(event.getPlayer())) return;
		MassiveRaces.get().log("2");
		MPlayer player = event.getPlayer();
		
		if (!event.fromLand())
		{
			player.getPlayer().setFlying(false);
			
			return;
		}
		
		player.getPlayer().setFlying(true);
	}
}
