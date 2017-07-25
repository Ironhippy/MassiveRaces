package net.graystone.java.races.command.edit.particle;

import java.util.List;

import com.massivecraft.massivecore.util.MUtil;

import net.graystone.java.races.command.RaceCommand;

public class CmdParticle extends RaceCommand
{
	
	public CmdParticle()
	{
		this.addAliases("edit race particle effect");
		this.setDesc("edit a race's particle settings");
		
		this.addChild(new CmdParticleSet());
		this.addChild(new CmdParticleReset());
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("particle");
	}
	
}
