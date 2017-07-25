package net.graystone.java.races.engine;

import com.massivecraft.massivecore.Engine;

public class TraitEngine extends Engine
{
	
	private static TraitEngine i = new TraitEngine();
	public static TraitEngine get() { return TraitEngine.i; }
	
}
