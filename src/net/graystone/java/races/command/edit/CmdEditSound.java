package net.graystone.java.races.command.edit;

import com.massivecraft.massivecore.command.type.enumeration.TypeSound;

import net.graystone.java.races.command.RaceCommand;

public class CmdEditSound extends RaceCommand
{
	
	public CmdEditSound()
	{
		this.addAliases("sound");
		this.setDesc("set race sound");
		
		this.addParameter(TypeSound.get(), "soundName");
	}
	
}
