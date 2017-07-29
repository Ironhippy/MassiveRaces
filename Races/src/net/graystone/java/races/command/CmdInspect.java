package net.graystone.java.races.command;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.Perm;
import net.graystone.java.races.command.req.HasRaceSelected;
import net.graystone.java.races.command.type.TypeMPlayer;
import net.graystone.java.races.entity.MPlayer;

public class CmdInspect extends RaceCommand
{
	
	public CmdInspect()
	{
		this.addAliases("inspect", "i");
		this.setDesc("inspect a player's race");
		this.addRequirements(RequirementHasPerm.get(Perm.RACE_INSPECT.toString()), HasRaceSelected.get());
		
		this.addParameter(TypeMPlayer.get(), "playerName");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		MPlayer player = this.readArg();
		
		if (player.getRace()==null) { message(Txt.parse("<red>Error: <rose>The server returned a <pink>NullPointerException <rose>executing this command. Please report this to an administrator.")); return; }
		
		String name = player.getRace().getName();
		
		message(Txt.parse("<i>"+player.getName()+" is a: <pink>"+name+"<i>."));
	}
}
