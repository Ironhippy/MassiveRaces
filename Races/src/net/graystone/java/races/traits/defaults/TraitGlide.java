package net.graystone.java.races.traits.defaults;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.traits.TraitAbstract;

public class TraitGlide extends TraitAbstract
{
	
	private static TraitGlide i = new TraitGlide();
	public static TraitGlide get() { return TraitGlide.i; }
	
	
	private ItemStack glideWings = new ItemStack(Material.ELYTRA, 1);
	
	public TraitGlide()
	{
		super("glide", true);
	}
	
	@Override
	public boolean meetsRequirementsInner(MPlayer player)
	{
		return true;
	}
	
	@EventHandler
	public void alwaysWings(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		if (!meetsRequirements(MPlayer.get(player))) return;
		
		if (event.isCancelled()) return;
		if (!(event.getInventory() instanceof PlayerInventory)) return;
		
		PlayerInventory privInventory = (PlayerInventory) event.getInventory();
		
		if (privInventory.getChestplate()!=null || privInventory.getChestplate().getType().equals(Material.AIR))
		{
			replaceChestplate(player);
			return;
		}
		
		privInventory.setChestplate(glideWings);
	}
	
	@Override
	public void fireEvents(Player player)
	{
		replaceChestplate(player);
	}
	
	protected void replaceChestplate(Player player)
	{
		PlayerInventory playerInventory = player.getInventory();
		ItemStack chestPiece = null;
		
		if (playerInventory.getChestplate()!=null)
		{
			chestPiece = player.getInventory().getChestplate();
		}
		
		if (chestPiece!=null) playerInventory.remove(chestPiece);
		
		playerInventory.setChestplate(this.glideWings);
	}
}
