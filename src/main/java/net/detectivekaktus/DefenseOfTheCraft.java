package net.detectivekaktus;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefenseOfTheCraft implements ModInitializer {
	public static final String MOD_ID = "defense-of-the-craft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Defense of the Craft has been successfully initialized!");
	}
}