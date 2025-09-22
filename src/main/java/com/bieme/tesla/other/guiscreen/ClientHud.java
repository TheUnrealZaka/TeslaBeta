package com.bieme.tesla.other.guiscreen;

import com.bieme.tesla.Client;
import com.bieme.tesla.other.guiscreen.render.pinnables.PinnableFrame;
import com.bieme.tesla.other.guiscreen.render.pinnables.PinnableButton;
import net.minecraft.client.gui.screens.Screen;


public class ClientHud extends Screen {
	private final PinnableFrame frame;

	public boolean on_gui;
	public boolean back;

	public ClientHud() {
		this.frame = new PinnableFrame("Wurst+2 HUD", "WurstplusHUD", 40, 40);
		this.back = false;
		this.on_gui = false;
	}

	public PinnableFrame get_frame_hud() {
		return this.frame;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		this.on_gui = true;

		PinnableFrame.nc_r = Client.getSettingManager().getSettingByTag("GUI", "ClickGUINameFrameR").getValueInt();
		PinnableFrame.nc_g = Client.getSettingManager().getSettingByTag("GUI", "ClickGUINameFrameG").getValueInt();
		PinnableFrame.nc_b = Client.getSettingManager().getSettingByTag("GUI", "ClickGUINameFrameB").getValueInt();

		PinnableFrame.bg_r = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBackgroundFrameR").getValueInt();
		PinnableFrame.bg_g = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBackgroundFrameG").getValueInt();
		PinnableFrame.bg_b = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBackgroundFrameB").getValueInt();
		PinnableFrame.bg_a = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBackgroundFrameA").getValueInt();

		PinnableFrame.bd_r = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderFrameR").getValueInt();
		PinnableFrame.bd_g = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderFrameG").getValueInt();
		PinnableFrame.bd_b = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderFrameB").getValueInt();
		PinnableFrame.bd_a = 0;

		PinnableFrame.bdw_r = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderWidgetR").getValueInt();
		PinnableFrame.bdw_g = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderWidgetG").getValueInt();
		PinnableFrame.bdw_b = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderWidgetB").getValueInt();
		PinnableFrame.bdw_a = 255;

		PinnableButton.nc_r = Client.getSettingManager().getSettingByTag("GUI", "ClickGUINameWidgetR").getValueInt();
		PinnableButton.nc_g = Client.getSettingManager().getSettingByTag("GUI", "ClickGUINameWidgetG").getValueInt();
		PinnableButton.nc_b = Client.getSettingManager().getSettingByTag("GUI", "ClickGUINameWidgetB").getValueInt();

		PinnableButton.bg_r = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBackgroundWidgetR").getValueInt();
		PinnableButton.bg_g = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBackgroundWidgetG").getValueInt();
		PinnableButton.bg_b = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBackgroundWidgetB").getValueInt();
		PinnableButton.bg_a = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBackgroundWidgetA").getValueInt();

		PinnableButton.bd_r = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderWidgetR").getValueInt();
		PinnableButton.bd_g = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderWidgetG").getValueInt();
		PinnableButton.bd_b = Client.getSettingManager().getSettingByTag("GUI", "ClickGUIBorderWidgetB").getValueInt();
	}

	@Override
	public void onGuiClosed() {
		if (this.back) {
			Client.getHackManager().getModuleWithTag("GUI").setActive(true);
			Client.getHackManager().getModuleWithTag("HUD").setActive(false);
		} else {
			Client.getHackManager().getModuleWithTag("HUD").setActive(false);
			Client.getHackManager().getModuleWithTag("GUI").setActive(false);
		}

		this.on_gui = false;
		Client.getConfigManager().saveSettings();
	}

	@Override
	protected void mouseClicked(int mx, int my, int mouse) {
		this.frame.mouse(mx, my, mouse);

		if (mouse == 0) {
			if (this.frame.motion(mx, my) && this.frame.can()) {
				this.frame.set_move(true);
				this.frame.set_move_x(mx - this.frame.get_x());
				this.frame.set_move_y(my - this.frame.get_y());
			}
		}
	}

	@Override
	protected void mouseReleased(int mx, int my, int state) {
		this.frame.release(mx, my, state);
		this.frame.set_move(false);
	}

	@Override
	public void drawScreen(int mx, int my, float tick) {
		this.drawDefaultBackground();
		this.frame.render(mx, my, 2);
	}
}
