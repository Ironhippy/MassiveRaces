package net.graystone.java.races.command;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.req.HasRaceSelected;

public class CmdScream extends RaceCommand
{
	
	private static CmdScream i = new CmdScream();
	public static CmdScream get() { return CmdScream.i; } 
	
	public CmdScream()
	{
		this.setDesc("scream a racial sound");
		
		this.addRequirements(HasRaceSelected.get());
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		if (player.getRace().getSound()==null) { message(Txt.parse("<rose>Your race makes no distinguishable sounds.")); return; }
		
		Sound screamedSound = player.getRace().getSound();
		
		for (Entity nearbyEntities : player.getPlayer().getNearbyEntities(25, 15, 25))
		{
			Player rangedPlayer = (Player) nearbyEntities;
			
			rangedPlayer.playSound(rangedPlayer.getLocation(), screamedSound, 5, 5);
		}
		
		if (player.getRace().getParticleEffect()==null) return;
		
		Particle displayedParticle = player.getRace().getParticleEffect();
		
		Location particleLocation = player.getPlayer().getLocation();
		
		player.getPlayer().getWorld().spawnParticle(displayedParticle, particleLocation.getX(), particleLocation.getY(), particleLocation.getZ(), 4);
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("scream");
	}
	
}
