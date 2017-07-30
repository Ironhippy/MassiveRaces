package net.graystone.java.races.command.edit.trait;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.command.type.TypeTrait;
import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.traits.TraitAbstract;

public class CmdTraitRemove extends RaceCommand
{
	public CmdTraitRemove()
	{
		this.addAliases("remove");
		this.setDesc("remove a race trait");
		
		this.addParameter(TypeRace.get(), "raceName");
		this.addParameter(TypeTrait.get(), "traitId");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		MRace targetRace = this.readArg();
		TraitAbstract trait = this.readArg();
		
		if (!targetRace.containsTrait(trait)) { message(Txt.parse("<rose>The <pink> "+targetRace.getName()+" race <rose>doesn not have the <pink>"+trait.getId()+" trait<rose>.")); return; }
		
		targetRace.removeTrait(trait);
		
		message(Txt.parse("<i>Successfully removed the <pink>"+trait.toString()+" trait <i>from the <pink>"+targetRace.getName()+" race<i>."));
	}
}
