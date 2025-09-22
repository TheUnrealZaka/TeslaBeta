package com.bieme.tesla;

import com.bieme.tesla.modules.utils.chat.MessageUtil;
import com.bieme.tesla.other.guiscreen.ClientGui;
import com.bieme.tesla.other.manager.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;

public class Client {

    // logger
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String CLIENT_NAME = "TeslaClient";
    public static final String CLIENT_VERSION = "0.1";
    public static final String CLIENT_AUTHOR = "BiEmE";

    public static final Minecraft mc = Minecraft.getInstance();

    public static final String g = "§7";
    public static final String r = "§c";

    private static ManagerHack hackManager;
    private static ManagerCommand commandManager;
    private static ManagerFriend friendManager;
    private static ManagerSetting settingManager;
    private static SimpleHudManager hudManager;

    public static ClientGui clickGui;

    private static ManagerConfig configManager = new ManagerConfig();
    private static MessageUtil messageUtil = new MessageUtil();

    public static ClientGui click_hud = new ClientGui();

    public static final int KEY_GUI_ESCAPE = 1;
    public static final int KEY_DELETE = 211;

    public static void init() {

        LOGGER.info("Starting Client.");

        settingManager = new ManagerSetting();
        hackManager = new ManagerHack();
        commandManager = new ManagerCommand();
        friendManager = new ManagerFriend();
        hudManager = new SimpleHudManager();
        clickGui = new ClientGui();
        click_hud = clickGui;

        hackManager.init();
        commandManager.init();

        LOGGER.info("Client started.");
    }

    public static ManagerConfig getConfigManager() {
        return configManager;
    }

    public static ManagerHack getHackManager() {
        return hackManager;
    }

    public static ManagerCommand getCommandManager() {
        return commandManager;
    }

    public static ManagerFriend getFriendManager() {
        return friendManager;
    }

    public static ClientGui getClickGui() {
        return clickGui;
    }

    public static MessageUtil getMessageUtil() {
        return messageUtil;
    }

    public static ManagerSetting getSettingManager() {
        return settingManager;
    }
    
    public static SimpleHudManager get_hud_manager() {
        return hudManager;
    }
    
    // Legacy compatibility methods
    public static ManagerConfig get_config_manager() { return getConfigManager(); }
    public static ManagerHack get_hack_manager() { return getHackManager(); }
    public static ManagerSetting get_setting_manager() { return getSettingManager(); }

    }