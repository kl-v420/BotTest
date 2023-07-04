package com.hub1616.bot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CvcBot {
	private Robot robot;

	public CvcBot() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CvcBot bot = new CvcBot();
		bot.run();
	}

	public void run() {
		// click into game
		robot.delay(3000);
		mouseclick();
		robot.delay(5);
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.delay(5);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		robot.delay(1000);
		headBox();
	}

	private void mouseclick() {
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(4);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(4);
	}

	private void headBox() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage image = robot.createScreenCapture(new Rectangle(d));

		System.out.println(d.width);
		System.out.println(d.height);

		int x = 0;
		boolean found = false;
		while (!found && x < d.width) {
			int y = 0;
			while (!found && y < d.height - 150) {
				Color color = new Color(image.getRGB(x, y), true);
				System.out.println(x + " " + y);

				if (color.getRed() < 25 && color.getGreen() > 200) {
					Point p = MouseInfo.getPointerInfo().getLocation();
					robot.mouseMove(p.x, p.y);
					robot.delay(100);
					saveImage(image, "found1");
					robot.mouseMove(p.x + 1, p.y);
					robot.delay(100);
					BufferedImage image2 = robot.createScreenCapture(new Rectangle(d));
					saveImage(image2, "found2");

//					for (int i = 0; i < (x - p.x); i++) {
//						robot.delay(200);
//						robot.mouseMove(p.x + i, p.y);
//					}
					robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
					robot.delay(40);
					robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
					robot.delay(40);
					found = true;
				}

				y++;
			}
			x++;
		}

		if (!found) {
			saveImage(image, "notfound");
		}
	}

	private void saveImage(BufferedImage image, String name) {
		try {
			File outputfile = new File("C:\\Users\\Kyle\\OneDrive\\Pictures\\Camera Roll\\" + name + ".jpg");
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
