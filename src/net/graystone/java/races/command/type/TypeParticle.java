package net.graystone.java.races.command.type;

import java.util.Collection;
import java.util.List;

import org.bukkit.Particle;
import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.command.type.TypeAbstract;

public class TypeParticle extends TypeAbstract<Particle>
{
	
	private static TypeParticle i = new TypeParticle();
	public static TypeParticle get() { return TypeParticle.i; }
	
	public TypeParticle()
	{
		super(Particle.class);
	}
	
	@Override
	public Particle read(String arg, CommandSender sender)
			throws MassiveException
	{	
		return Particle.valueOf(arg);
	}
	
	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		List<String> retTabList = new MassiveList<String>();
		
		for (Particle all : Particle.values())
		{
			retTabList.add(all.toString());
		}
			
		return retTabList;
	}
}
