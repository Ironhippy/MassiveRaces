package net.graystone.java.races.command.test;

import java.util.List;

import org.bukkit.entity.Player;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.sender.TypePlayer;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.command.RaceCommand;

public class CmdStarve extends RaceCommand
{
	
	private static CmdStarve i = new CmdStarve();
	public static CmdStarve get() { return CmdStarve.i; }
	
	public CmdStarve()
	{
		this.setDesc("starve a player");
		
		this.addParameter(TypePlayer.get(), "playerName");
	}
	
	@Override
	public void perform() 
			throws MassiveException
	{
		Player target = this.readArg(player.getPlayer());
		
		target.setFoodLevel(0);
		message(Txt.parse("<i>Successfully <pink>starved <i>the player <pink>"+target.getName()+"<i>."));
	}
	
	@Override
	public List<String> getAliases()
	{
		return MUtil.list("starve");
	}
	
}
