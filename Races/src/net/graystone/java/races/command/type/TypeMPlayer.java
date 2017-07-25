package net.graystone.java.races.command.type;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.TypeAbstract;

import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MPlayerColl;

public class TypeMPlayer extends TypeAbstract<MPlayer>
{
	private static TypeMPlayer i = new TypeMPlayer();
	public static TypeMPlayer get() { return TypeMPlayer.i; }
	
	public TypeMPlayer()
	{
		super(MPlayer.class);
	}

	@Override
	public MPlayer read(String arg, CommandSender sender)
			throws MassiveException
	{
		return MPlayerColl.get().getByName(arg);
	}
	
	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return MPlayerColl.get().getPlayerNames();
	}
	
}
