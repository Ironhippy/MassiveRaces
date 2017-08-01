package net.graystone.java.races.traits.defaults;

import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.massivecraft.massivecore.collections.MassiveMap;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.event.LightChangeEvent;
import net.graystone.java.races.traits.TraitAbstract;

public class TraitFeedDarkness extends TraitAbstract
{
	
	private static TraitFeedDarkness i = new TraitFeedDarkness();
	public static TraitFeedDarkness get() { return TraitFeedDarkness.i; }
	
	private Map<String, Integer> registeredTasks = new MassiveMap<String, Integer>();
	
	public TraitFeedDarkness()
	{
		super("feedDarkness");
	}

	@Override
	public boolean meetsRequirementsInner(MPlayer player)
	{
		if (player.getLocation().getBlock().getLightLevel()<10
			&& !this.registeredTasks.containsKey(player.getId()))
		{
			return true;
		}
		
		return false;
	}
	
	@EventHandler
	public void darkFeed(LightChangeEvent event)
	{
		if (!meetsRequirements(event.getPlayer())) return;
		
		BukkitTask task = new BukkitRunnable()
		{
			public void run()
			{
				if (!meetsRequirements(event.getPlayer()))
				{
					this.cancel();
					registeredTasks.remove(event.getPlayer().getName());
					return;
				}
				
				int foodLevel = event.getPlayer().getPlayer().getFoodLevel()+1;
				
				event.getPlayer().getPlayer().setFoodLevel(foodLevel);
			}
		}.runTaskTimer(MassiveRaces.get(), 0L, 360L);
		
		registeredTasks.put(event.getPlayer().getName(), task.getTaskId());
	}
}
