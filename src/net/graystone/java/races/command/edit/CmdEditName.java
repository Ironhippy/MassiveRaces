package net.graystone.java.races.command.edit;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

public class CmdEditName extends RaceCommand
{
	
	public CmdEditName()
	{
		this.addAliases("name");
		this.setDesc("change the visible name of a race");
		
		this.addParameter(TypeRace.get(), "targetRace");
		this.addParameter(TypeString.get(), "newName");
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MRace targetRace = this.readArg();
		String newName = this.readArg();
		
		targetRace.setName(newName);
		
		message(Txt.parse("<i>The <pink>"+targetRace.getName()+" race <i>has been renamed to the <pink>"+targetRace.getName()+" race<i>."));
	}
}
