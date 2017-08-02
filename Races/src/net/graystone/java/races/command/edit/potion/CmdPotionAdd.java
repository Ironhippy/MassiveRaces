package net.graystone.java.races.command.edit.potion;

import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.TypePotionEffectType;
import com.massivecraft.massivecore.util.MUtil;

import net.graystone.java.races.Perm;
import net.graystone.java.races.command.RaceCommand;

public class CmdPotionAdd extends RaceCommand
{
	
	public CmdPotionAdd()
	{
		this.setDesc("add a potion effect");
		
		this.addRequirements(RequirementHasPerm.get(Perm.PEFFECT_ADD.toString()));
		
		this.addParameter(TypePotionEffectType.get(), "potionEffect");
	}
	
	@Override
	public void perform() throws MassiveException
	{
		PotionEffectType potiontype = this.readArg();
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("add");
	}
	
}
