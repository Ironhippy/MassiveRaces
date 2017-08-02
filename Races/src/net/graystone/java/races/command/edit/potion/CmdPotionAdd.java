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

public class CmdPotionAdd extends RaceCommand
{
	
	public CmdPotionAdd()
	{
		this.setDesc("add a potion effect");
		
		this.addRequirements(RequirementHasPerm.get(Perm.PEFFECT_ADD.toString()));
		
		this.addParameter(TypePotionEffectType.get(), "potionEffect");
		this.addParameter(TypeRace.get(), "targetRace");
	}
	
	@Override
	public void perform() throws MassiveException
	{
		PotionEffectType potionType = this.readArg();
		MRace race = this.readArg();
		
		race.addPotionEffect(potionType);
		
		message(Txt.parse("<i>You have successfully added the <pink>"+potionType.getName()
		       +" <i>to the <pink>"+race.getName()+" race<i>."));
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("add");
	}
	
}
