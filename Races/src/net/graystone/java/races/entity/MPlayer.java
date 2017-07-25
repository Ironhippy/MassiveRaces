package net.graystone.java.races.entity;

import com.massivecraft.massivecore.store.SenderEntity;
import com.massivecraft.massivecore.util.TimeUnit;

import net.graystone.java.races.MassiveRaces;

public class MPlayer extends SenderEntity<MPlayer>
{
	
	public static MPlayer get(Object oid) { return MPlayerColl.get().get(oid); }
	
	private String raceId;
	public boolean setRace(MRace arg0) { if (!canSwitch()) return false; this.raceId = arg0.getId(); this.changed(); return true; }
	public MRace getRace()
	{
		if (raceId==null) { System.out.println("The player <pink>"+this.getName()+"'s <rose>data is corrupted."); return null; }
		MRace ret = MRaceColl.get().get(raceId);
		if (ret==null) {  System.out.println("There was an error retrieving the race <pink>"+raceId+"<rose>."); return null; }
		
		return ret;
	}
	
	private long lastSwitch = 0;
	public void setSwitch(long switchTime) { this.lastSwitch = switchTime; this.changed(); }
	public boolean canSwitch() { if (System.currentTimeMillis()-MassiveRaces.HOUR >= lastSwitch || lastSwitch == 0) return true; return false; }
	public long getNextSwitchTime() { return lastSwitch+TimeUnit.MILLIS_PER_HOUR; }
	
}
