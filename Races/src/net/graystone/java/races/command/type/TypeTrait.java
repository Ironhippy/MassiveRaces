package net.graystone.java.races.command.type;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.TypeAbstract;

import net.graystone.java.races.RaceTrait;

public class TypeTrait extends TypeAbstract<RaceTrait>
{
	
	private static TypeTrait i = new TypeTrait();
	public static TypeTrait get() { return TypeTrait.i; }
	
	public TypeTrait()
	{
		super(RaceTrait.class);
	}

	@Override
	public RaceTrait read(String arg, CommandSender sender) throws MassiveException
	{
		return RaceTrait.fromString(arg);
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return RaceTrait.getArguments();
	}
}
