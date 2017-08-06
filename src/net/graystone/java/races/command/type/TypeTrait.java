package net.graystone.java.races.command.type;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.TypeAbstract;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.traits.TraitAbstract;

public class TypeTrait extends TypeAbstract<TraitAbstract>
{
	
	private static TypeTrait i = new TypeTrait();
	public static TypeTrait get() { return TypeTrait.i; }
	
	public TypeTrait()
	{
		super(TraitAbstract.class);
	}

	@Override
	public TraitAbstract read(String arg, CommandSender sender) throws MassiveException
	{
		return MassiveRaces.get().getTrait(arg);
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return MassiveRaces.get().getTraits();
	}
}
