package net.detectivekaktus.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.detectivekaktus.client.gui.DotcStatusBar;

public class DefenseOfTheCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudRenderCallback.EVENT.register(DotcStatusBar::drawStatusBar);
	}
}