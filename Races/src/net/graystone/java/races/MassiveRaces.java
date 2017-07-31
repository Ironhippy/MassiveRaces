package net.graystone.java.races;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.potion.PotionEffectType;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.collections.MassiveMap;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.CmdRace;
import net.graystone.java.races.command.test.CmdLight;
import net.graystone.java.races.command.test.CmdStarve;
import net.graystone.java.races.engine.LoginEngine;
import net.graystone.java.races.engine.VanillaEngine;
import net.graystone.java.races.entity.MConf;
import net.graystone.java.races.entity.MConfColl;
import net.graystone.java.races.entity.MPlayerColl;
import net.graystone.java.races.entity.MRaceColl;
import net.graystone.java.races.traits.TraitAbstract;
import net.graystone.java.races.traits.defaults.TraitFlyWater;

public class MassiveRaces extends MassivePlugin
{
	
	private static MassiveRaces i;
	public MassiveRaces() { MassiveRaces.i = this; }
	public static MassiveRaces get() { return MassiveRaces.i; }
	
	public static long HOUR = 360000;
	
	private Map<String, TraitAbstract> traitMap = new MassiveMap<String, TraitAbstract>();
	
	@Override
	public void onEnableInner()
	{
		this.activate(MConfColl.get(),
				      MRaceColl.get(),
				      MPlayerColl.get(),
				      
				      LoginEngine.get(),
				      VanillaEngine.get(),
				      
				      CmdRace.get(),
				      
				      CmdLight.get(),
				      CmdStarve.get());
		
		this.attach(TraitFlyWater.get());
		
		MRaceColl.get().get(MConf.get().getDefaultRace()).addPotionEffect(PotionEffectType.FAST_DIGGING);
	}
	
	public void attach(TraitAbstract... args)
	{
		for (TraitAbstract arg : args)
		{
			this.attach(arg.getId(), arg);
		}
	}
	
	public void attach(String arg0, TraitAbstract arg1)
	{
		if (this.traitMap.containsKey(arg0)) return;
		this.traitMap.put(arg0, arg1);
		
		this.activate(arg1);
		
		this.log(Level.INFO, Txt.parse("Successfully integrated trait type <pink>"+arg0+"<i>!"));
	}
	
	public TraitAbstract getTrait(String arg0) { return this.traitMap.get(arg0); }
	
	public Set<String> getTraits() { return this.traitMap.keySet(); }
	
}
