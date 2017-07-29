package net.graystone.java.races.engine;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.massivecraft.massivecore.Engine;

import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MRace;

public class VanillaEngine extends Engine
{
	
	private static VanillaEngine i = new VanillaEngine();
	public static VanillaEngine get() { return VanillaEngine.i; }
	
	@EventHandler
	public void walkEvent(PlayerMoveEvent event)
	{
		if (!isValid(event.getFrom(), event.getTo())) return;
		
		MRace playerRace = MPlayer.get(event.getPlayer()).getRace();
		
		if (!playerRace.hasParticleEffect()) return;
		
		Location targetLocation = event.getTo();
		
		targetLocation.getWorld().spawnParticle(playerRace.getParticleEffect(), targetLocation.getX(), targetLocation.getY(), targetLocation.getZ(), 12);
	}
	
	public boolean isValid(Location firstLocation, Location secondLocation)
	{
		if (firstLocation.getX()==secondLocation.getX() && firstLocation.getZ()==secondLocation.getZ() && firstLocation.getY()==secondLocation.getY())
		{
			return false;
		}
		
		return true;
	}
}
