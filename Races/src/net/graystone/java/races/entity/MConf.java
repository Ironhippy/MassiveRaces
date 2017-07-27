package net.graystone.java.races.entity;

import java.util.List;

import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

public class MConf extends Entity<MConf>
{
	
	protected static transient MConf i;
	public static MConf get() { return MConf.i; }
	
	private String defaultRace = "Default";
	public String getDefaultRace() { return this.defaultRace; }
	
	private List<String> commandAliases = MUtil.list("races", "race", "r");
	public List<String> getAliases() { return this.commandAliases; }
	
	private boolean loginGui = true;
	public boolean useGui() { return this.loginGui; }
	
	private float burnLightTemp = 13F;
	public float burnAt() { return this.burnLightTemp; }
	
	
	@Override
	public MConf load(MConf that)
	{
		super.load(that);
		return this;
	}
	
}
