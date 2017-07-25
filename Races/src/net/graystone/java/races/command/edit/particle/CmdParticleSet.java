package net.graystone.java.races.command.edit.particle;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.enumeration.TypeParticleEffect;
import com.massivecraft.massivecore.particleeffect.ParticleEffect;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

public class CmdParticleSet extends RaceCommand
{
	
	public CmdParticleSet()
	{
		this.addAliases("add", "append");
		this.setDesc("set a race's visisble particle effect");
		
		this.addParameter(TypeRace.get(), "raceName");
		this.addParameter(TypeParticleEffect.get(), "particleEffect");
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MRace targetRace = this.readArg();
		ParticleEffect targetEffect = this.readArg();
		
		targetRace.setParticleEffect(targetEffect);
		
		message(Txt.parse("<i>The <pink>"+targetRace.getName()+"'s particle effect <i>has been assigned to <pink>"+targetEffect.getName()+"<i>."));
	}
}
