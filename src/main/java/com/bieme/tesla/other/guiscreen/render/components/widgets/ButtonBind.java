package com.bieme.tesla.other.guiscreen.render.components.widgets;

import com.bieme.tesla.Client;
import com.bieme.tesla.other.guiscreen.render.ClientDraw;
import com.bieme.tesla.other.guiscreen.render.components.AbstractWidget;
import com.bieme.tesla.other.guiscreen.render.components.Frame;
import com.bieme.tesla.other.guiscreen.render.components.ModuleButton;

import java.awt.*;

public class ButtonBind extends AbstractWidget {

	private final Frame frame;
	private final ModuleButton master;

	private final String button_name;

	private String points = ".";

	private int x;
	private int y;

	private int width;
	private int height;

	private int save_y;

	private float tick;

	private boolean can = true;
	private boolean waiting;

	private final ClientDraw font = new ClientDraw(1);

	public ButtonBind(Frame frame, ModuleButton master, String tag, int update_position) {
		this.frame = frame;
		this.master = master;

		this.x = master.get_x();
		this.y = update_position;

		this.save_y = this.y;

		this.width = master.get_width();
		this.height = font.getStringHeight();

		this.button_name = tag;
	}

	@Override
	public void does_can(boolean value) {
		this.can = value;
	}

	@Override
	public void set_x(int x) {
		this.x = x;
	}

	@Override
	public void set_y(int y) {
		this.y = y;
	}

	@Override
	public void set_width(int width) {
		this.width = width;
	}

	@Override
	public void set_height(int height) {
		this.height = height;
	}

	@Override
	public int get_x() {
		return this.x;
	}

	@Override
	public int get_y() {
		return this.y;
	}

	@Override
	public int get_width() {
		return this.width;
	}

	@Override
	public int get_height() {
		return this.height;
	}

	public int get_save_y() {
		return this.save_y;
	}

	@Override
	public boolean motion_pass(int mx, int my) {
		return motion(mx, my);
	}

	public boolean motion(int mx, int my) {
		return mx >= get_x() && my >= get_save_y() && mx <= get_x() + get_width() && my <= get_save_y() + get_height();
	}

	public boolean can() {
		return this.can;
	}

	@Override
	public boolean is_binding() {
		return this.waiting;
	}

	@Override
	public void bind(char char_, int key) {
		if (this.waiting) {
			switch (key) {
				case 256 -> this.waiting = false; // ESCAPE key
				case 261 -> { // DELETE key
					this.master.get_module().setKey(0);
					this.waiting = false;
				}
				default -> {
					this.master.get_module().setKey(key);
					this.waiting = false;
				}
			}
		}
	}

	@Override
	public void mouse(int mx, int my, int mouse) {
		if (mouse == 0 && motion(mx, my) && this.master.is_open() && can()) {
			this.frame.does_can(false);
			this.waiting = true;
		}
	}

	@Override
	public void render(int master_y, int separe, int absolute_x, int absolute_y) {
		set_width(this.master.get_width() - separe);

		float hue = (System.currentTimeMillis() % (360 * 32)) / (360f * 32);
		int color = Color.HSBtoRGB(hue, 1, 1);

		this.save_y = this.y + master_y;

		int ns_r = Client.clickGui.theme_widget_name_r;
		int ns_g = Client.clickGui.theme_widget_name_g;
		int ns_b = Client.clickGui.theme_widget_name_b;
		int ns_a = Client.clickGui.theme_widget_name_a;

		int bg_r = Client.clickGui.theme_widget_background_r;
		int bg_g = Client.clickGui.theme_widget_background_g;
		int bg_b = Client.clickGui.theme_widget_background_b;
		int bg_a = Client.clickGui.theme_widget_background_a;

		if (this.waiting) {
			ClientDraw.drawRect(null, get_x(), this.save_y, get_x() + this.width, this.save_y + this.height, new java.awt.Color(bg_r, bg_g, bg_b, bg_a));
			this.tick += 0.5f;

			if (this.tick >= 15) this.points = "..";
			if (this.tick >= 30) this.points = "...";
			if (this.tick >= 45) {
				this.points = ".";
				this.tick = 0.0f;
			}

			ClientDraw.drawString(null, "Listening " + this.points, this.x + 2, this.save_y, new java.awt.Color(ns_r, ns_g, ns_b, ns_a));
		} else {
			String bindString = String.valueOf(this.master.get_module().getKey());
			ClientDraw.drawString(null, "Bind <" + bindString + ">", this.x + 2, this.save_y, new java.awt.Color(ns_r, ns_g, ns_b, ns_a));
		}
	}

	public void set_key(int key) {
		this.master.get_module().setKey(key);
	}

	public void set_binding(boolean value) {
		this.waiting = value;
	}
}