package net.detectivekaktus;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.detectivekaktus.attach.DotcAttachments;
import net.detectivekaktus.attribute.DotcAttributeModifiers;
import net.detectivekaktus.block.DotcBlocks;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.event.DotcEvents;
import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.sound.DotcSounds;
import net.detectivekaktus.tag.DotcEntityTypeTags;
import net.detectivekaktus.worldgen.DotcWorldgen;

public class DefenseOfTheCraft implements ModInitializer {
	public static final String MOD_ID = "defense-of-the-craft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		DotcAttachments.initialize();
		DotcAttributeModifiers.initialize();
		DotcBlocks.initialize();
		DotcComponents.initialize();
		DotcDamageTypes.initialize();
		DotcEntityTypeTags.initialize();
		DotcItems.initialize();
		DotcSounds.initialize();
		DotcWorldgen.initialize();
		DotcEvents.initialize();
		LOGGER.info("Defense of the Craft has been successfully initialized!");
	}
}