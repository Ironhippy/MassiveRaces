package net.graystone.java.races.command;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.Visibility;

import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MPlayerColl;

public class RaceCommand extends MassiveCommand
{
	
	protected MPlayer player;
	
	public RaceCommand()
	{
		this.setVisibility(Visibility.SECRET);
	}
	
	@Override
	public void senderFields(boolean set)
	{
		super.senderFields(set);
		
		this.player = set ? MPlayerColl.get().get(sender) : null;
	}
}
