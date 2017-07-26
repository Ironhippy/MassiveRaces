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
		if (!MRaceColl.get().containsId(raceId) || raceId == null)
		{
			raceId = MConf.get().getDefaultRace();
			this.changed();
		}
		
		MRace ret = MRaceColl.get().get(raceId);
		
		return ret;
	}
	
	private long lastSwitch = 0;
	public long getLastSwitchTime() { return this.lastSwitch; }
	public void setSwitch(long switchTime) { this.lastSwitch = switchTime; this.changed(); }
	public boolean canSwitch() { if (this.getNextSwitchTime()-this.getLastSwitchTime()>TimeUnit.MILLIS_PER_HOUR || lastSwitch == 0) return true; return false; }
	public long getNextSwitchTime() { return lastSwitch+MassiveRaces.HOUR; }
	
}
