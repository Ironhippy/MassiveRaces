package net.graystone.java.races;

import org.bukkit.potion.PotionEffectType;

import com.massivecraft.massivecore.MassivePlugin;

import net.graystone.java.races.command.CmdRace;
import net.graystone.java.races.command.test.CmdLight;
import net.graystone.java.races.command.test.CmdStarve;
import net.graystone.java.races.engine.LoginEngine;
import net.graystone.java.races.engine.TraitEngine;
import net.graystone.java.races.engine.VanillaEngine;
import net.graystone.java.races.entity.MConf;
import net.graystone.java.races.entity.MConfColl;
import net.graystone.java.races.entity.MPlayerColl;
import net.graystone.java.races.entity.MRaceColl;

public class MassiveRaces extends MassivePlugin
{
	
	private static MassiveRaces i;
	public MassiveRaces() { MassiveRaces.i = this; }
	public static MassiveRaces get() { return MassiveRaces.i; }
	
	public static long HOUR = 360000;
	
	@Override
	public void onEnableInner()
	{
		this.activate(MConfColl.get(),
				      MRaceColl.get(),
				      MPlayerColl.get(),
				      
				      LoginEngine.get(),
				      TraitEngine.get(),
				      VanillaEngine.get(),
				      
				      CmdRace.get(),
				      
				      CmdLight.get(),
				      CmdStarve.get());
		
		MRaceColl.get().get(MConf.get().getDefaultRace()).addPotionEffect(PotionEffectType.FAST_DIGGING);
	}
	
}
