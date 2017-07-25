package net.graystone.java.races.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.massivecore.store.MStore;
import com.massivecraft.massivecore.store.SenderColl;

import net.graystone.java.races.MassiveRaces;

public class MPlayerColl extends SenderColl<MPlayer>
{
	
	private static MPlayerColl i = new MPlayerColl();
	public static MPlayerColl get() { return MPlayerColl.i; }
	
	public MPlayerColl()
	{
		super("massiveraces_mplayers", MPlayer.class, MStore.getDb(), MassiveRaces.get());
	}
	
	public Collection<String> getPlayerNames()
	{
		List<String> collNames = new ArrayList<String>();
		
		for (MPlayer players : get().getAll())
		{
			collNames.add(players.getName());
		}
		
		return collNames;
	}
	
}
