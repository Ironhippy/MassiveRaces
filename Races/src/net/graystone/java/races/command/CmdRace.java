package net.graystone.java.races.command;

import java.util.List;

import com.massivecraft.massivecore.command.MassiveCommandHelp;
import com.massivecraft.massivecore.command.MassiveCommandVersion;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.command.edit.CmdEdit;
import net.graystone.java.races.entity.MConf;

public class CmdRace extends RaceCommand
{
	
	private static CmdRace i = new CmdRace();
	public static CmdRace get() { return CmdRace.i; }
	
	public CmdRace()
	{
		this.addChild(new MassiveCommandHelp());
		
		this.addChild(new CmdBe());
		this.addChild(new CmdInspect());
		
		this.addChild(new CmdCreate());
		this.addChild(new CmdRemove());
		
		this.addChild(new CmdEdit());
		
		this.addChild(new MassiveCommandVersion(MassiveRaces.get()));
	}
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().getAliases();
	}
	
}
