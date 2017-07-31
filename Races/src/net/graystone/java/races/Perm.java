package net.graystone.java.races;

import net.graystone.java.races.entity.MRace;

public enum Perm
{
	RACE_BECOME("default.become"),
	RACE_INFO("default.info"),
	RACE_INSPECT("default.inspect"),
	RACE_SCREAM("default.scream"),
	
	RACE_CREATE("admin.create"),
	RACE_REMOVE("admin.remove"),
	
	EDIT_DEFAULT("edit.default"),
	EDIT_NAME("edit.name"),
	EDIT_SOUND("edit.sound"),
	
	PARTICLE_SET("particle.set"),
	PARTICLE_RESET("particle.reset"),
	PARTICLE_TOGGLE("particle.toggle"),
	
	TRAIT_ADD("traits.add"),
	TRAIT_REMOVE("traits.remove"),
	TRAIT_LIST("traits.list"),
	
	TEST_LIGHT("test.light"),
	TEST_STARVE("test.starve");
	
	
	private String node = "massiveraces.";
	
	Perm(String node)
	{
		this.node+=node;
	}
	
	@Override
	public String toString()
	{
		return this.node;
	}
	
	public String racePerm(MRace arg0)
	{
		return node+"become."+arg0.getName();
	}
	
}
