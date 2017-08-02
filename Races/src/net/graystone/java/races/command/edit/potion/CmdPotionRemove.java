package net.graystone.java.races.command.edit.potion;

import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.TypePotionEffectType;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.Perm;
import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

public class CmdPotionRemove extends RaceCommand
{
	
	public CmdPotionRemove()
	{
		this.setDesc("remove potion effect");
		
		this.addRequirements(RequirementHasPerm.get(Perm.PEFFECT_REMOVE.toString()));
		
		this.addParameter(TypePotionEffectType.get(), "potionEffect");
		this.addParameter(TypeRace.get(), "raceName");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		PotionEffectType potionEffect = this.readArg();
		MRace targetRace = this.readArg();
		
		if (!targetRace.containsPotionEffect(potionEffect))
		{
			message(Txt.parse("<i>The race <pink>"+targetRace.getName()+" <i>does not have the <aqua>"+potionEffect.getName()+" effect<i>."));
			return;
		}
		
		targetRace.removePotionEffect(potionEffect);
		message(Txt.parse("<i>The race <pink>"+targetRace.getName()+" <i>no longer has the <aqua>"+potionEffect.getName()+" effect<i>."));
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("remove");
	}
	
}
