package net.detectivekaktus;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.detectivekaktus.block.DotcBlocks;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.sound.DotcSounds;
import net.detectivekaktus.worldgen.DotcWorldgen;

public class DefenseOfTheCraft implements ModInitializer {
	public static final String MOD_ID = "defense-of-the-craft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		DotcComponents.initialize();
		DotcBlocks.initialize();
		DotcItems.initialize();
		DotcSounds.initialize();
		DotcWorldgen.initialize();
		LOGGER.info("Defense of the Craft has been successfully initialized!");
	}
}