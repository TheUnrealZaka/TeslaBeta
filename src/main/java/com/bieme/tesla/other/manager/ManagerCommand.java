package com.bieme.tesla.other.manager;

// import com.bieme.tesla.other.command.Commands; // Disabled
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public class ManagerCommand {

	// public static Commands command_list; // Disabled

	public ManagerCommand() {
	}

	public void init() {
		TextColor color = TextColor.fromRgb(0x0000FF);
		Style blueStyle = Style.EMPTY.withColor(color);
		// command_list = new Commands(); // Disabled
	}

	public static void set_prefix(String new_prefix) {
		// command_list.set_prefix(new_prefix); // Disabled
	}

	public static String get_prefix() {
		// return command_list.get_prefix(); // Disabled
		return "/";
	}
}
