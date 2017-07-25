package net.graystone.java.races.command;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.Visibility;

import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MPlayerColl;
import net.graystone.java.races.entity.MRace;

public class RaceCommand extends MassiveCommand
{
	
	protected MPlayer player;
	protected MRace senderRace;
	
	public RaceCommand()
	{
		this.setVisibility(Visibility.SECRET);
	}
	
	@Override
	public void senderFields(boolean set)
	{
		super.senderFields(set);
		
		this.player = set ? MPlayerColl.get().get(sender) : null;
		this.senderRace = set ? player.getRace() : null;
	}
}
