package com.majesty373.sorceresssgarden;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SorceresssGardenPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SorceresssGardenPlugin.class);
		RuneLite.main(args);
	}
}