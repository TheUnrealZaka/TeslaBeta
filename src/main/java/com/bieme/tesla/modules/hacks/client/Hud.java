package com.bieme.tesla.modules.hacks.client;

import com.bieme.tesla.Client;
import com.bieme.tesla.modules.hacks.Category;
import com.bieme.tesla.other.guiscreen.render.components.Frame;
import com.bieme.tesla.other.guiscreen.render.pinnables.PinnableButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class Hud extends Screen {

    private final Frame frame;

    public boolean onGui = false;
    public boolean back = false;

    public Hud() {
        super(Component.literal("Tesla HUD"));
        this.frame = new Frame(Category.CLIENT, 40, 40);
    }

    public Frame getFrame() {
        return this.frame;
    }

    @Override
    protected void init() {
        this.onGui = true;

        var settings = Client.getSettingManager();

        Frame.nc_r = settings.getSettingByTag("GUI", "ClickGUINameFrameR").getValueInt();
        Frame.nc_g = settings.getSettingByTag("GUI", "ClickGUINameFrameG").getValueInt();
        Frame.nc_b = settings.getSettingByTag("GUI", "ClickGUINameFrameB").getValueInt();

        Frame.bg_r = settings.getSettingByTag("GUI", "ClickGUIBackgroundFrameR").getValueInt();
        Frame.bg_g = settings.getSettingByTag("GUI", "ClickGUIBackgroundFrameG").getValueInt();
        Frame.bg_b = settings.getSettingByTag("GUI", "ClickGUIBackgroundFrameB").getValueInt();
        Frame.bg_a = settings.getSettingByTag("GUI", "ClickGUIBackgroundFrameA").getValueInt();

        Frame.bd_r = settings.getSettingByTag("GUI", "ClickGUIBorderFrameR").getValueInt();
        Frame.bd_g = settings.getSettingByTag("GUI", "ClickGUIBorderFrameG").getValueInt();
        Frame.bd_b = settings.getSettingByTag("GUI", "ClickGUIBorderFrameB").getValueInt();
        Frame.bd_a = 0;

        Frame.bdw_r = settings.getSettingByTag("GUI", "ClickGUIBorderWidgetR").getValueInt();
        Frame.bdw_g = settings.getSettingByTag("GUI", "ClickGUIBorderWidgetG").getValueInt();
        Frame.bdw_b = settings.getSettingByTag("GUI", "ClickGUIBorderWidgetB").getValueInt();
        Frame.bdw_a = 255;

        PinnableButton.nc_r = settings.getSettingByTag("GUI", "ClickGUINameWidgetR").getValueInt();
        PinnableButton.nc_g = settings.getSettingByTag("GUI", "ClickGUINameWidgetG").getValueInt();
        PinnableButton.nc_b = settings.getSettingByTag("GUI", "ClickGUINameWidgetB").getValueInt();

        PinnableButton.bg_r = settings.getSettingByTag("GUI", "ClickGUIBackgroundWidgetR").getValueInt();
        PinnableButton.bg_g = settings.getSettingByTag("GUI", "ClickGUIBackgroundWidgetG").getValueInt();
        PinnableButton.bg_b = settings.getSettingByTag("GUI", "ClickGUIBackgroundWidgetB").getValueInt();
        PinnableButton.bg_a = settings.getSettingByTag("GUI", "ClickGUIBackgroundWidgetA").getValueInt();

        PinnableButton.bd_r = settings.getSettingByTag("GUI", "ClickGUIBorderWidgetR").getValueInt();
        PinnableButton.bd_g = settings.getSettingByTag("GUI", "ClickGUIBorderWidgetG").getValueInt();
        PinnableButton.bd_b = settings.getSettingByTag("GUI", "ClickGUIBorderWidgetB").getValueInt();
    }

    @Override
    public void onClose() {
        if (back) {
            Client.getHackManager().get_module_with_tag("GUI").set_active(true);
            Client.getHackManager().get_module_with_tag("HUD").set_active(false);
        } else {
            Client.getHackManager().get_module_with_tag("GUI").set_active(false);
            Client.getHackManager().get_module_with_tag("HUD").set_active(false);
        }

        this.onGui = false;
        Client.getConfigManager().saveSettings();
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        frame.mouse((int) mouseX, (int) mouseY, button);

        if (button == 0 && frame.motion((int) mouseX, (int) mouseY) && frame.can()) {
            frame.set_move(true);
            frame.set_move_x((int) mouseX - frame.get_x());
            frame.set_move_y((int) mouseY - frame.get_y());
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        frame.release((int) mouseX, (int) mouseY, button);
        frame.set_move(false);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        frame.render(context, mouseX, mouseY, 2);
        super.render(context, mouseX, mouseY, delta);
    }
}
