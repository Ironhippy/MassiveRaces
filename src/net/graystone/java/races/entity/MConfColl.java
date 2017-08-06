package net.graystone.java.races.entity;

import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.MStore;

import net.graystone.java.races.MassiveRaces;

public class MConfColl extends Coll<MConf>
{
	
	private static MConfColl i = new MConfColl();
	public static MConfColl get() { return MConfColl.i; }
	
	public MConfColl()
	{
		super("massiveraces_mconf", MConf.class, MStore.getDb(), MassiveRaces.get());
	}
	
	@Override
	public void setActive(boolean set)
	{
		super.setActive(set);
		if (! set) return;
		MConf.i = get().get("config", true);
	}
}
