package net.graystone.java.races.command.req;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementAbstract;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.entity.MPlayer;

public class HasRaceSelected extends RequirementAbstract
{
	private static final long serialVersionUID = 1L;
	
	private static HasRaceSelected i = new HasRaceSelected();
	public static HasRaceSelected get() { return HasRaceSelected.i; }
	
	@Override
	public boolean apply(CommandSender sender, MassiveCommand command)
	{
		if (MPlayer.get(sender).getRace()==null || MPlayer.get(sender).getRace().isDefault()) return false;
		
		return true;
	}

	@Override
	public String createErrorMessage(CommandSender sender, MassiveCommand command)
	{
		return Txt.parse("<rose>You have not yet <pink>selected <rose>a race.");
	}
}
