package net.graystone.java.races.command.edit.particle;

import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanOn;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.Perm;
import net.graystone.java.races.command.RaceCommand;

public class CmdParticleToggle extends RaceCommand
{
	
	public CmdParticleToggle()
	{
		this.setDesc("toggle particle effects");
		
		this.addRequirements(RequirementIsPlayer.get(), RequirementHasPerm.get(Perm.PARTICLE_TOGGLE.toString()));
		
		this.addParameter(TypeBooleanOn.get(), "on/off");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		boolean on = this.readArg();
		
		player.toggleParticles(on);
		
		message(Txt.parse("<i>You have successfully toggled particles <pink>"+on+"<i>."));
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("toggle");
	}
	
}
