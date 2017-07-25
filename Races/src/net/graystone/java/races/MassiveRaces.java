package net.graystone.java.races;

import com.massivecraft.massivecore.MassivePlugin;

import net.graystone.java.races.command.CmdRace;
import net.graystone.java.races.engine.LoginEngine;
import net.graystone.java.races.engine.VanillaEngine;
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
				      VanillaEngine.get(),
				      
				      CmdRace.get());
	}
	
}
