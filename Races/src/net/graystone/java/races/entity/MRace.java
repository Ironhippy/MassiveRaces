package net.graystone.java.races.entity;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.store.Entity;

import net.graystone.java.races.traits.TraitAbstract;

public class MRace extends Entity<MRace>
{
	
	private String displayName;
	public MRace setName(String arg0) { this.displayName = arg0; this.changed(); return this; }
	public String getName() { return this.displayName; }
	
	protected boolean isDefault = false;
	public boolean isDefault() { return this.isDefault; }
	public void setDefault(boolean set) { this.isDefault = set; this.changed(); }
	
	private List<String> subraceIds = new MassiveList<String>();
	public boolean addSubrace(MRace arg0) { if (containsSubrace(arg0)) return false; this.subraceIds.add(arg0.getId()); this.changed(); return true; }
	public boolean removeSubrace(MRace arg0) { if (!containsSubrace(arg0)) return false; this.subraceIds.remove(arg0.getId()); this.changed(); return true; }
	public boolean containsSubrace(MRace arg0) { return subraceIds.contains(arg0.getId()); }
	public List<MRace> getSubraces()
	{
		List<MRace> subraces = new MassiveList<MRace>();
		
		for (String all : this.subraceIds)
		{
			if (!MRaceColl.get().containsRace(all)) { this.subraceIds.remove(all); continue; }
			
			MRace race = MRaceColl.get().getByName(all);
			subraces.add(race);
		}
		
		this.changed();
		
		return subraces;
	}
	
	public boolean isParent() { if (subraceIds.size()>0) return true; return false; }
	
	private ItemStack raceMaterial = new ItemStack(Material.EMERALD, 1);
	public void setMaterial(Material arg0) { this.raceMaterial = new ItemStack(arg0, 1); this.changed(); }
	public ItemStack getMaterial() { return this.raceMaterial; }
	
	private String raceSound = Sound.ENTITY_CHICKEN_AMBIENT.name();
	public void setSound(Sound arg0) { this.raceSound = arg0.name(); this.changed(); }
	public Sound getSound() { return Sound.valueOf(raceSound); }
	
	private List<String> potionEffectTypes = new MassiveList<String>();
	public boolean addPotionEffect(PotionEffectType arg0) { if (containsPotionEffect(arg0)) return false; this.potionEffectTypes.add( arg0.getName() ); this.changed(); return true; }
	public boolean removePotionEffect(PotionEffectType arg0) { if (!containsPotionEffect(arg0)) return false; this.potionEffectTypes.remove( arg0.getName() ); this.changed(); return true; }
	public boolean containsPotionEffect(PotionEffectType arg0) { return this.potionEffectTypes.contains( arg0.getName() ); }
	public List<PotionEffectType> getPotionEffects()
	{
		List<PotionEffectType> potionEffects = new MassiveList<PotionEffectType>();
		for (String all : potionEffectTypes)
		{
			potionEffects.add(PotionEffectType.getByName(all));
		}
		
		return potionEffects;
	}
	
	private String particleEffect;
	public void setParticleEffect(Particle arg0) { this.particleEffect = arg0.name(); this.changed(); }
	public Particle getParticleEffect() { return Particle.valueOf(particleEffect); }
	public boolean hasParticleEffect() { if(particleEffect == null) return false; return true; }
	
	public boolean equals(MRace race) { if (this.getId().equalsIgnoreCase(race.getId())) return true; return false; }
	
	private List<String> raceTraits = new MassiveList<String>();
	public void addTrait(TraitAbstract arg0) { this.raceTraits.add(arg0.getId()); this.changed(); }
	public void removeTrait(TraitAbstract arg0) { this.raceTraits.remove(arg0.getId()); this.changed(); }
	public List<String> getTraits() { return this.raceTraits; }
	
	public boolean containsTrait(TraitAbstract arg0) { if (raceTraits.contains(arg0.getId())) return true; return false; }
	
	private String raceDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac molestie magna, semper elementum est. Mauris quis hendrerit odio. Curabitur sollicitudin, velit sit amet bibendum iaculis, sem quam ullamcorper mauris, ac rhoncus mauris augue non tortor. Quisque in enim vulputate nibh scelerisque mollis. Phasellus quis elementum quam. Sed a malesuada velit. Curabitur vel rutrum lectus. Duis porttitor elit et purus volutpat, sit amet aliquam neque volutpat. Suspendisse ligula mauris, finibus sit amet metus sagittis, ornare blandit purus. Aliquam a felis ac lacus porttitor dapibus sed id ligula. In at molestie ipsum. Aenean mollis eros dui, feugiat convallis felis egestas at.";
	public MRace setDesc(String arg0) { this.raceDescription = arg0; this.changed(); return this; }
	public String getDesc() { return this.raceDescription; }
	
	private boolean permissionRequired = false;
	public MRace setPermissionRequired(boolean set) { this.permissionRequired = set; this.changed(); return this; }
	public boolean isPermissionRequired() { return this.permissionRequired; }
	
}
