package net.graystone.java.races.command.edit.particle;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

public class CmdParticleReset extends RaceCommand
{	
	public CmdParticleReset()
	{
		this.addAliases("reset");
		this.setDesc("reset a race's particle effect");
		
		this.addParameter(TypeRace.get(), "raceName");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		MRace targetRace = this.readArg();
		
		targetRace.setParticleEffect(null);
		
		message(Txt.parse("<i>The <pink>"+targetRace.getName()+" race <i>now has <pink>no <i>particle effect."));
	}
}