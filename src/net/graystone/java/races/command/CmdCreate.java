package net.graystone.java.races.command;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanTrue;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.Perm;
import net.graystone.java.races.entity.MRaceColl;

public class CmdCreate extends RaceCommand
{
	
	public CmdCreate()
	{
		this.addAliases("create");
		this.setDesc("create a playable race");
		this.addRequirements(RequirementHasPerm.get(Perm.RACE_CREATE.toString()));
		this.addParameter(TypeString.get(), "raceName");
		this.addParameter(false, TypeBooleanTrue.get(), "permissionRequired");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		String raceName = this.readArg();
		boolean permissionRequired = this.readArg();
		
		if (MRaceColl.get().containsRace(raceName)) { message(Txt.parse("<red>Error: <rose>This race already exists.")); return; }
		
		MRaceColl.get().create()
		               .setName(raceName)
		               .setPermissionRequired(permissionRequired);
		
		message(Txt.parse("<i>Successfully <pink>created <i>a new playable race titled: <pink>"+raceName+"<i>."));
	}
	
}
