package net.graystone.java.races.command;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.Perm;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MConf;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MPlayerColl;
import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.entity.MRaceColl;

public class CmdRemove extends RaceCommand
{
	
	public CmdRemove()
	{
		this.addAliases("remove");
		this.setDesc("remove a race from the playable list");
		this.addRequirements(RequirementHasPerm.get(Perm.REMOVE.toString()));
		
		this.addParameter(TypeRace.get(), "raceName");
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MRace target = this.readArg();
		
		if (MConf.get().getDefaultRace().equalsIgnoreCase(target.getName())) { message(Txt.parse("<red>Error: <rose>You must set another race as the <pink>default <rose>before removing this race.")); return; }
		
		MRace def = MRaceColl.get().getByName(MConf.get().getDefaultRace());
		
		for (MPlayer players : MPlayerColl.get().getAll())
		{
			if (!players.getRace().equals(target)) continue;
			
			players.setRace(def);
			
			if (!players.isOnline()) continue;
			
			players.message(Txt.parse("<i>The race <pink>"+target.getName()+" <i>has been removed. Your race has been set to <pink>"+def.getName()+"<i>."));
		}
		
		target.detach();
	}
}
