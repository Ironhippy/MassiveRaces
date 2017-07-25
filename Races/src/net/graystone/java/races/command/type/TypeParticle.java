package net.graystone.java.races.command.type;

import org.bukkit.Particle;

import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;

public class TypeParticle extends TypeEnum<Particle>
{
	
	private static TypeParticle i = new TypeParticle();
	public static TypeParticle get() { return TypeParticle.i; }
	
	public TypeParticle()
	{
		super(Particle.class);
	}
	
}
