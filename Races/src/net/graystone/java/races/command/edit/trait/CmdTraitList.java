package net.graystone.java.races.command.edit.trait;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MRace;

public class CmdTraitList extends RaceCommand
{
	
	public CmdTraitList()
	{
		this.addAliases("list");
		this.setDesc("list traits applicable to a race");
		
		this.addParameter(TypeRace.get(), "raceName");
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MRace targetRace = this.readArg();
		
		List<String> traits = targetRace.getTraits();
		
		String sepVar = StringUtils.join(traits, "<i>, <aqua>");
		
		message(Txt.parse("<i>The <pink>"+targetRace.getName()+" race <i>has the following traits: <aqua>"+sepVar+"<i>."));
	}
}
