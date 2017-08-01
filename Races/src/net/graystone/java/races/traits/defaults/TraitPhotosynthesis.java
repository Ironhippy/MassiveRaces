package net.graystone.java.races.traits.defaults;

import java.util.Map;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.massivecraft.massivecore.collections.MassiveMap;

import net.graystone.java.races.MassiveRaces;
import net.graystone.java.races.entity.MConf;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.event.LightChangeEvent;
import net.graystone.java.races.traits.TraitAbstract;

public class TraitPhotosynthesis extends TraitAbstract
{
	
	private static TraitPhotosynthesis i = new TraitPhotosynthesis();
	public static TraitPhotosynthesis get() { return TraitPhotosynthesis.i; }
	
	public TraitPhotosynthesis()
	{
		super("photosynthesis");
	}
	
	private Map<String, Integer> registeredTasks = new MassiveMap<String, Integer>();
	
	@Override
	public boolean meetsRequirementsInner(MPlayer player)
	{
		if (player.getLocation().getWorld().hasStorm()) return false;
		
		if (isDay(player.getLocation().getWorld())
			&& player.getLocation().getBlock().getLightFromSky()>MConf.get().burnAt()
			&& player.getPlayer().getFoodLevel()<18
			&& !registeredTasks.containsKey(player.getId())) return true;
		
		return false;
	}
	
	@EventHandler
	public void lightAbsorb(LightChangeEvent event)
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
	
	protected boolean isDay(World targetWorld)
	{
		long time = targetWorld.getTime();
		
		if (time > 13500) return false;
		
		return true;
	}
}
