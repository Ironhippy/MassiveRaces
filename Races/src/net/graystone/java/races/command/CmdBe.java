package net.graystone.java.races.command;

import java.util.LinkedHashMap;
import java.util.TreeSet;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;

import net.graystone.java.races.Perm;
import net.graystone.java.races.command.type.TypeMPlayer;
import net.graystone.java.races.command.type.TypeRace;
import net.graystone.java.races.entity.MPlayer;
import net.graystone.java.races.entity.MRace;

public class CmdBe extends RaceCommand
{
	
	public CmdBe()
	{
		this.addAliases("be");
		this.setDesc("become a race");
		this.addParameter(TypeRace.get(), "raceType");
		this.addParameter(player, TypeMPlayer.get(), "playerName");
		
		this.addRequirements(RequirementHasPerm.get(Perm.BE.toString()));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MRace targetRace = this.readArg();
		
		if (player.getRace().equals(targetRace)) { message(Txt.parse("<rose>You already belong to the <pink>"+targetRace.getName()+"<rose> race.")); return; }
		MPlayer player = this.readArg(this.player);
		
		long p1 = (player.getNextSwitchTime()-player.getLastSwitchTime())*10L;
		TreeSet<TimeUnit> timeUnit = TimeUnit.getAllButMillis();
		
		LinkedHashMap<TimeUnit, Long> cooldownTimeRaw = TimeDiffUtil.unitcounts(p1, timeUnit);
		
		String cooldownTime = TimeDiffUtil.formatedVerboose(cooldownTimeRaw, "<i>");
		
		if (!player.canSwitch()) { message(Txt.parse("<i>You have changed races already within the <pink>past day<i>.\nYou may change races again in "+cooldownTime+"<i>.")); return; }
		
		player.setRace(targetRace);
		player.setSwitch(System.currentTimeMillis());
		
		message(Txt.parse("<pink>Your character <i>has become a: <pink>"+targetRace.getName()+"<i>."));
	}
}
