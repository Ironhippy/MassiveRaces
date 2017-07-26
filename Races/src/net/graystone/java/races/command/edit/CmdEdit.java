package net.graystone.java.races.command.edit;

import java.util.List;

import com.massivecraft.massivecore.util.MUtil;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.edit.particle.CmdParticle;
import net.graystone.java.races.command.edit.trait.CmdTrait;

public class CmdEdit extends RaceCommand
{
	
	public CmdEdit()
	{
		this.setDesc("edit a race's settings and values");
		
		this.addChild(new CmdEditName());
		this.addChild(new CmdEditDefault());
		
		this.addChild(new CmdParticle());
		this.addChild(new CmdTrait());
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("edit");
	}
	
}
