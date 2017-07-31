package net.graystone.java.races.command.edit.particle;

import org.bukkit.Particle;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeParticle;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

public class CmdParticleSet extends RaceCommand
{
	
	public CmdParticleSet()
	{
		this.addAliases("set");
		this.setDesc("set a race's visisble particle effect");
		
		this.addParameter(TypeRace.get(), "raceName");
		this.addParameter(TypeParticle.get(), "particleEffect");
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MRace targetRace = this.readArg();
		Particle targetEffect = this.readArg();
		
		targetRace.setParticleEffect(targetEffect);
		
		message(Txt.parse("<i>The <pink>"+targetRace.getName()+"'s particle effect <i>has been assigned to <pink>"+targetEffect.name()+"<i>."));
	}
}
