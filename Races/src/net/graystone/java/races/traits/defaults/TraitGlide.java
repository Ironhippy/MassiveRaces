package net.graystone.java.races.traits.defaults;

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
	
	public TraitGlide()
	{
		super("glide");
	}
	
	@Override
	public boolean meetsRequirementsInner(MPlayer player)
	{
		return true;
	}
	
	@EventHandler
	public void alwaysWings(InventoryClickEvent event)
	{
		
	}
	
	protected void replaceChest(Player player)
	{
		PlayerInventory playerInventory = player.getInventory();
		ItemStack chestPiece = null;
		
		if (playerInventory.getChestplate()!=null)
		{
			chestPiece = player.getInventory().getChestplate();
		}
		
		if (chestPiece!=null) playerInventory.remove(chestPiece);
		
	}
}
