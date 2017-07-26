package net.graystone.java.races.command.edit;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

public class CmdEditDefault extends RaceCommand
{
	
	public CmdEditDefault()
	{
		this.addAliases("default");
		this.setDesc("set the default race");
		
		this.addParameter(TypeRace.get(), "raceName");
	}
	
	@Override
	public void perform()
			throws MassiveException
	{
		MRace race = this.readArg();
		race.setDefault(true);
		
		message(Txt.parse("<i>The <pink>"+race.getName()+" race <i>is now the <pink>default <i>race."));
	}
}
