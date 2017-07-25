package net.graystone.java.races;

public enum RaceTrait
{
	
	FLY_WATER("flyWater"),
	FLY("flySky"),
	GLIDE("glideSky"),
	FEED_WATER("feedWater"),
	FEED_SUNLIGHT("feedSunlight"),
	FEED_DARKNESS("feedDarkness"),
	HEAL_DARKNESS("healDarkness"),
	HEAL_SUNLIGHT("healSunlight"),
	HEAL_WATER("healWater"),
	FIRE_IMMUNITY("immuneFire"),
	LAVA_IMMUNITY("immuneLava"),
	BURN_SUNLIGHT("burnSunlight"),
	FREEZE_DARKNESS("freezeDarkness"),
	WEAKNESS_SUN("weaknessSun");
	
	private String id;
	RaceTrait(String id)
	{
		this.id = id;
	}
	
	public String toString() { return this.id; }
	
	public RaceTrait fromString(String arg0)
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
	
}
