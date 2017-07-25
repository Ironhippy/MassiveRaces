package net.graystone.java.races.command.type;

import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;

import net.graystone.java.races.RaceTrait;

public class TypeTrait extends TypeEnum<RaceTrait>
{
	
	private static TypeTrait i = new TypeTrait();
	public static TypeTrait get() { return TypeTrait.i; }
	
	public TypeTrait()
	{
		super(RaceTrait.class);
	}
}
