package net.graystone.java.races;

import java.util.List;

import com.massivecraft.massivecore.collections.MassiveList;

public enum RaceTrait
{
	
	FLY_WATER("flyWater", 0),
	FLY("flySky", 0),
	GLIDE("glideSky", 0),
	FEED_WATER("feedWater", 0),
	FEED_SUNLIGHT("feedSunlight", 0),
	FEED_DARKNESS("feedDarkness", 0),
	HEAL_DARKNESS("healDarkness", 0),
	HEAL_SUNLIGHT("healSunlight", 0),
	HEAL_WATER("healWater", 0),
	FIRE_IMMUNITY("immuneFire", 0),
	LAVA_IMMUNITY("immuneLava", 0),
	BURN_SUNLIGHT("burnSunlight", 1),
	FREEZE_DARKNESS("freezeDarkness", 1),
	WEAKNESS_SUN("weaknessSun", 1);
	
	private String id;
	private int positive;
	RaceTrait(String id, int positive)
	{
		this.id = id;
		this.positive = positive;
	}
	
	public String toString() { return this.id; }
	public boolean isPositive() { if (this.positive==0) return true; return false; }
	
	public static RaceTrait fromString(String arg0)
	{
		RaceTrait ret = null;
		for (RaceTrait all : RaceTrait.values())
		{
			if (!all.id.equalsIgnoreCase(arg0)) continue;
			
			ret = all;
			break;
		}
		
		return ret;
	}
	
	public static List<String> getArguments()
	{
		List<String> ret = new MassiveList<String>();
		for (RaceTrait all : RaceTrait.values())
		{
			ret.add(all.id);
		}
		
		return ret;
	}
	
}
