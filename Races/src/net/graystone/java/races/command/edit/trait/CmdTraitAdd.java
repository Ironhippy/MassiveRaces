package net.graystone.java.races.command.edit.trait;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.command.type.TypeTrait;
import net.graystone.java.races.entity.MRace;
import net.graystone.java.races.traits.TraitAbstract;

public class CmdTraitAdd extends RaceCommand
{
	
	public CmdTraitAdd()
	{
		this.addAliases("add");
		this.setDesc("add a race trait");
		
		this.addParameter(TypeRace.get(), "raceName");
		this.addParameter(TypeTrait.get(), "traitId");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		MRace targetRace = this.readArg();
		TraitAbstract trait = this.readArg();
		
		if (targetRace.containsTrait(trait)) { message(Txt.parse("<rose>The <pink> "+targetRace.getName()+" race <rose>already has the <pink>"+trait.getId()+" trait<rose>.")); return; }
		
		targetRace.addTrait(trait);
		
		message(Txt.parse("<i>Successfully added the <pink>"+trait.toString()+" trait <i>to the <pink>"+targetRace.getName()+" race<i>."));
	}
}
