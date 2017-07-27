package net.graystone.java.races.command.edit.trait;

import java.util.List;

import com.massivecraft.massivecore.util.MUtil;

import net.graystone.java.races.command.RaceCommand;

public class CmdTrait extends RaceCommand
{
	
	public CmdTrait()
	{
		this.setDesc("edit a race's trait settings");
		
		this.addChild(new CmdTraitList());
		
		this.addChild(new CmdTraitAdd());
		this.addChild(new CmdTraitRemove());
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("trait");
	}
	
}
