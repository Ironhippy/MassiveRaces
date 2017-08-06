package net.graystone.java.races.command;

import java.util.List;

import com.massivecraft.massivecore.command.MassiveCommandHelp;
import com.massivecraft.massivecore.command.MassiveCommandVersion;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.command.edit.CmdEditDefault;
import net.graystone.java.races.command.edit.CmdEditName;
import net.graystone.java.races.command.edit.CmdEditSound;
import net.graystone.java.races.command.edit.particle.CmdParticle;
import net.graystone.java.races.command.edit.potion.CmdPotion;
import net.graystone.java.races.command.edit.trait.CmdTrait;
import net.graystone.java.races.entity.MConf;

public class CmdRace extends RaceCommand
{
	
	private static CmdRace i = new CmdRace();
	public static CmdRace get() { return CmdRace.i; }
	
	public CmdRace()
	{
		this.addChild(new MassiveCommandHelp());
		
		this.addChild(new CmdBe());
		this.addChild(CmdScream.get());
		this.addChild(new CmdInspect());
		
		this.addChild(new CmdCreate());
		this.addChild(new CmdRemove());
		
		this.addChild(new CmdEditDefault());
		this.addChild(new CmdEditName());
		this.addChild(new CmdEditSound());
		
		this.addChild(new CmdParticle());
		this.addChild(new CmdPotion());
		this.addChild(new CmdTrait());
		
		this.addChild(new MassiveCommandVersion(MassiveRaces.get()));
	}
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().getAliases();
	}
	
}
