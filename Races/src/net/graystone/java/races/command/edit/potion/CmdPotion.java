package net.graystone.java.races.command.edit.potion;

import java.util.List;

import com.massivecraft.massivecore.util.MUtil;

import net.graystone.java.races.command.RaceCommand;

public class CmdPotion extends RaceCommand
{
	
	public CmdPotion()
	{
		this.setDesc("edit potion effects of a race");
		
		this.addChild(new CmdPotionAdd());
		this.addChild(new CmdPotionRemove());
		
		this.addChild(new CmdPotionList());
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("potion");
	}
	
}
