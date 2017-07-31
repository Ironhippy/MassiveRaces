package net.graystone.java.races.engine;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.entity.MConf;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.entity.MRaceColl;

public class LoginEngine extends Engine
{
	
	private static LoginEngine i = new LoginEngine();
	public static LoginEngine get() { return LoginEngine.i; }
	
	@EventHandler(priority=EventPriority.HIGH)
	public void messageLogin(PlayerJoinEvent event)
	{
		MPlayer player = MPlayer.get(event.getPlayer());
		
		event.getPlayer().setAllowFlight(true);
		
		if (player.getRace()==null) { player.setRace(MRaceColl.get().get(MConf.get().getDefaultRace())); }
		
		if (player.getRace().getId().equalsIgnoreCase(MConf.get().getDefaultRace())) return;
		
		MRace race = player.getRace();
		
		player.message(Txt.parse("<i>You are currently a(n) <pink>"+race.getName()+"<i>."));
	}
}
