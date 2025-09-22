package com.bieme.tesla.other.guiscreen.hud;

import com.bieme.tesla.Client;
import com.bieme.tesla.other.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class WurstplusCompass extends Pinnable {

	public WurstplusCompass() {
		super("Compass", "Compass", 1, 0, 0);
	}

	@Override
	public void render(GuiGraphics guiGraphics) {
		if (mc.player == null) return;

		int nl_r = Client.getSettingManager().getSettingByTag("HUDStringsColorR").getSliderValueInt();
		int nl_g = Client.getSettingManager().getSettingByTag("HUDStringsColorG").getSliderValueInt();
		int nl_b = Client.getSettingManager().getSettingByTag("HUDStringsColorB").getSliderValueInt();
		int nl_a = Client.getSettingManager().getSettingByTag("HUDStringsColorA").getSliderValueInt();

		// Simple compass implementation
		float yaw = mc.player.getYRot();
		while (yaw < 0) yaw += 360;
		while (yaw >= 360) yaw -= 360;

		String direction = "Unknown";
		if (yaw >= 337.5 || yaw < 22.5) direction = "South";
		else if (yaw >= 22.5 && yaw < 67.5) direction = "South-West";
		else if (yaw >= 67.5 && yaw < 112.5) direction = "West";
		else if (yaw >= 112.5 && yaw < 157.5) direction = "North-West";
		else if (yaw >= 157.5 && yaw < 202.5) direction = "North";
		else if (yaw >= 202.5 && yaw < 247.5) direction = "North-East";
		else if (yaw >= 247.5 && yaw < 292.5) direction = "East";
		else if (yaw >= 292.5 && yaw < 337.5) direction = "South-East";

		String line = Client.g + "Direction: " + Client.r + direction + Client.g + " (" + String.format("%.1f", yaw) + "°)";

		create_line(line, this.docking(2, line), 2, nl_r, nl_g, nl_b, nl_a);

		this.set_width(this.get(line, "width") + 4);
		this.set_height(this.get(line, "height") + 4);
	}
}