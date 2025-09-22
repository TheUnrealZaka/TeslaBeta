package com.bieme.tesla.other.guiscreen.hud;

import com.bieme.tesla.Client;
import com.bieme.tesla.other.guiscreen.render.ClientDraw;
import com.bieme.tesla.other.guiscreen.render.pinnables.Pinnable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class ClientUser extends Pinnable {

	public ClientUser() {
		super("User", "User", 1, 0, 0);
	}

	@Override
	public void render(GuiGraphics guiGraphics) {
		int r = Client.getSettingManager().getSettingByTag("HUDStringsColorR").getSliderValueInt();
		int g = Client.getSettingManager().getSettingByTag("HUDStringsColorG").getSliderValueInt();
		int b = Client.getSettingManager().getSettingByTag("HUDStringsColorB").getSliderValueInt();
		int a = Client.getSettingManager().getSettingByTag("HUDStringsColorA").getSliderValueInt();

		String username = mc.player != null ? mc.player.getName().getString() : "Player";
		int hour = java.time.LocalTime.now().getHour();
		String greeting;

		if (hour < 12) {
			greeting = "Morning";
		} else if (hour < 16) {
			greeting = "Afternoon";
		} else if (hour < 24) {
			greeting = "Evening";
		} else {
			greeting = "Welcome";
		}

		String line = greeting + ", " + ChatFormatting.GOLD + ChatFormatting.BOLD + username + ChatFormatting.RESET + " you're looking fine today :)";

		int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
		int color = (a << 24) | (r << 16) | (g << 8) | b;
		guiGraphics.drawString(mc.font, line, (int) (screenWidth / 2f - mc.font.width(line) / 2f), 20, color, true);

		this.set_width(this.get(line, "width") + 2);
		this.set_height(this.get(line, "height") + 2);
	}
}
