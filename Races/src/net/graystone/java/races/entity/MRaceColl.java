package net.graystone.java.races.entity;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.MStore;
import com.massivecraft.massivecore.util.Txt;

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
		super.setActive(set);
		
		if (!set) return;
		
		this.defaultRace();
	}
	
	protected MRace defaultRace()
	{
		MRace defRace = get().get(MConf.get().getDefaultRace());
		
		if (defRace != null) return defRace;
		
		defRace = get().create(MConf.get().getDefaultRace());
		
		MassiveRaces.get().log(Txt.parse("<i>Beginning the creation of the <pink>"+defRace.getId()+" race<i>..."));
		
		defRace.setName(MConf.get().getDefaultRace());
		defRace.setDefault(true);
		
		MassiveRaces.get().log(Txt.parse("<i>...Done!"));
		
		return defRace;
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
		
		if (ret == null) { MassiveRaces.get().log(Level.ERROR, Txt.parse("There was an error finding the race <pink>"+arg0+"<rose>.")); return null; }
		
		return ret;
	}
	
	@Override
	public MRace get(Object oid)
	{
		MRace ret = super.get(oid);
		if (ret == null) { MassiveRaces.get().log(Level.ERROR, Txt.parse("There was an error finding the race <pink>"+oid+"<rose>.")); return null; }
		
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
