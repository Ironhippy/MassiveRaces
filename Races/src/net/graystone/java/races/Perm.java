package net.graystone.java.races;

import net.graystone.java.races.entity.MRace;

public enum Perm
{
	ADD_TRAIT("traits.add"),
	REMOVE_TRAIT("traits.remove"),
	EDIT_NAME("admin.name"),
	CREATE("admin.create"),
	REMOVE("admin.remove"),
	BE("default.become"),
	INSPECT("default.inspect"),
	INFO("default.info");
	
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
