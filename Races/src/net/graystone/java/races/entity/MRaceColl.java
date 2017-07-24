package net.graystone.java.races.entity;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.MStore;

import net.graystone.java.races.MassiveRaces;

public class MRaceColl extends Coll<MRace>
{
	
	private static MRaceColl i = new MRaceColl();
	public static MRaceColl get() { return MRaceColl.i; }
	
	public MRaceColl()
	{
		super("massiveraces_races", MRace.class, MStore.getDb(), MassiveRaces.get());
	}
	
	@Override
	public void setActive(boolean set)
	{
		if (! set) { super.setActive(set); return; }
		
		super.setActive(set);
		
		if (get().containsRace(MConf.get().getDefaultRace())) return;
		
		MRace defRace = get().create(MConf.get().getDefaultRace());
		
		defRace.setName(MConf.get().getDefaultRace());
	}
	
	public MRace getByName(String arg0)
	{
		MRace ret = null;
		
		for (MRace target : get().getAll())
		{
			if (! target.getName().equalsIgnoreCase(arg0)) continue;
			
			ret = target;
			break;
		}
		
		if (ret == null) { MassiveRaces.get().log(Level.ERROR, "There was an error finding the race <pink>"+arg0+"<rose>."); return null; }
		
		return ret;
	}
	
	@Override
	public MRace get(Object oid)
	{
		MRace ret = super.get(oid);
		if (ret == null) { MassiveRaces.get().log(Level.ERROR, "There was an error finding the race <pink>"+oid+"<rose>."); return null; }
		
		return ret;
	}
	
	public boolean containsRace(String id)
	{
		boolean ret = false;
		
		for (MRace races : get().getAll())
		{
			if (! races.getName().equalsIgnoreCase(id)) continue;
			
			ret = true;
			break;
		}
		
		return ret;
	}
	
	public Collection<String> getRaceNames()
	{
		List<String> coll = new MassiveList<String>();
		
		for (MRace all : get().getAll())
		{
			if (all.isParent()) continue;
			
			coll.add(all.getName());
		}
		
		return coll;
	}
	
}
