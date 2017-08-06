package net.graystone.java.races.command.type;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.TypeAbstract;

import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.entity.MRaceColl;

public class TypeRace extends TypeAbstract<MRace>
{
	
	private static TypeRace i = new TypeRace();
	public static TypeRace get() { return TypeRace.i; }
	
	public TypeRace()
	{
		super(MRace.class);
	}

	@Override
	public MRace read(String arg, CommandSender sender) 
			throws MassiveException
	{
		return MRaceColl.get().getByName(arg);
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return MRaceColl.get().getRaceNames();
	}
	
}
