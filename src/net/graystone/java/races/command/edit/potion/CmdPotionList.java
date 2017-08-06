package net.graystone.java.races.command.edit.potion;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.Perm;
import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

public class CmdPotionList extends RaceCommand
{
	
	public CmdPotionList()
	{
		this.setDesc("lists potion effects");
		
		this.addRequirements(RequirementHasPerm.get(Perm.PEFFECT_LIST.toString()));
		
		this.addParameter(TypeRace.get(), "raceName");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		MRace targetRace = this.readArg();
		
		List<String> potionEffects = targetRace.getPotionEffectsAsStrings();
		
		String complete = StringUtils.join(potionEffects, ", <aqua><i>");
		
		message(Txt.parse("<i>The <pink>"+targetRace.getName()+" race <i>contains the following potion effects: "+complete+"."));
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("list");
	}
	
}
