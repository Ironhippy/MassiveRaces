package net.graystone.java.races.command.test;

import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;

public class CmdLight extends RaceCommand
{
	
	private static CmdLight i = new CmdLight();
	public static CmdLight get() { return CmdLight.i; }
	
	public CmdLight()
	{
		this.setDesc("see the light");
	}
	
	@Override
	public void perform() 
	throws MassiveException
	{
		float light = player.getPlayer().getLocation().getBlock().getLightFromSky();
		
		message(Txt.parse("<i>Your local light level is <pink>"+light+" <aqua>rays<i>."));
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("light");
	}
	
}
