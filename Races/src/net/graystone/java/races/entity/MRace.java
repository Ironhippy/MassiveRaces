package net.graystone.java.races.entity;

import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.particleeffect.ParticleEffect;
import com.massivecraft.massivecore.store.Entity;

public class MRace extends Entity<MRace>
{
	
	private String displayName;
	public MRace setName(String arg0) { this.displayName = arg0; this.changed(); return this; }
	public String getName() { return this.displayName; }
	
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
	
	
	private List<String> potionEffectTypes = new MassiveList<String>();
	public boolean addPotionEffect(PotionEffectType arg0) { if (containsPotionEffect(arg0)) return false; this.potionEffectTypes.add( arg0.getName() ); this.changed(); return true; }
	public boolean removePotionEffect(PotionEffectType arg0) { if (!containsPotionEffect(arg0)) return false; this.potionEffectTypes.remove( arg0.getName() ); this.changed(); return true; }
	public boolean containsPotionEffect(PotionEffectType arg0) { return this.potionEffectTypes.contains( PotionEffectType.getByName(arg0.getName()) ); }
	
	private String particleEffect;
	public void setParticleEffect(ParticleEffect arg0) { this.particleEffect = arg0.getName(); this.changed(); }
	public ParticleEffect getParticleEffect() { return ParticleEffect.fromName(id); }
	public boolean hasParticleEffect(ParticleEffect arg0) { if(particleEffect == null) return false; if (arg0.getName().equalsIgnoreCase(particleEffect)) return true; return false; }
	
	public boolean equals(MRace race) { if (this.getId().equalsIgnoreCase(race.getId())) return true; return false; }
	
	private String raceDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac molestie magna, semper elementum est. Mauris quis hendrerit odio. Curabitur sollicitudin, velit sit amet bibendum iaculis, sem quam ullamcorper mauris, ac rhoncus mauris augue non tortor. Quisque in enim vulputate nibh scelerisque mollis. Phasellus quis elementum quam. Sed a malesuada velit. Curabitur vel rutrum lectus. Duis porttitor elit et purus volutpat, sit amet aliquam neque volutpat. Suspendisse ligula mauris, finibus sit amet metus sagittis, ornare blandit purus. Aliquam a felis ac lacus porttitor dapibus sed id ligula. In at molestie ipsum. Aenean mollis eros dui, feugiat convallis felis egestas at.";
	public MRace setDesc(String arg0) { this.raceDescription = arg0; this.changed(); return this; }
	public String getDesc() { return this.raceDescription; }
	
	private boolean permissionRequired = false;
	public MRace setPermissionRequired(boolean set) { this.permissionRequired = set; this.changed(); return this; }
	public boolean isPermissionRequired() { return this.permissionRequired; }
	
}
