package net.graystone.java.races.command.edit;

import java.util.List;

import com.massivecraft.massivecore.util.MUtil;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.edit.particle.CmdParticle;

public class CmdEdit extends RaceCommand
{
	
	public CmdEdit()
	{
		this.setDesc("edit a race's settings and values");
		
		this.addChild(new CmdEditName());
		
		this.addChild(new CmdParticle());
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("edit");
	}
	
}
