package net.graystone.java.races.command;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

@Deprecated
public class CmdInfo extends RaceCommand
{
	// 53
	public CmdInfo()
	{
		this.addAliases("info");
		this.setDesc("information on races");
		this.addParameter(senderRace, TypeRace.get(), "races");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		MRace targetRace = this.readArg();
		
		
		
		String info = Txt.parse("<gold>________.[ <green>"+targetRace.getName()+" Info <gold>].________\n"
				               +"Lore: <i>"+targetRace.getDesc()+"<orange>\n"
				               +"Effects: <lime>");
	}
}
