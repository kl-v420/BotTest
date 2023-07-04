package com.hub1616.bot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bot {
	private Robot robot;

	public Bot() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Bot bot = new Bot();
		bot.run();
	}

	public void run() {
		int i = 0;
		robot.mouseMove(750, 1050);
		robot.delay(5);
		mouseclick();
		robot.mouseMove(0, 0);
		robot.delay(5);
		mouseclick();
		robot.mouseMove(300, 500);

		while (i < 1000) {
			mouseclick();
			if (i % 100 == 0) {
				grandma();
				robot.mouseMove(300, 500);
			}
			i++;
		}

		System.out.print(false);
	}

	private void mouseclick() {
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(4);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(4);
	}

	private void grandma() {
		Rectangle area = new Rectangle(0, 0, 1920, 1080);
		BufferedImage bufferedImage = robot.createScreenCapture(area);
		File outputfile = new File("C:\\Users\\Kyle\\OneDrive\\Pictures\\Camera Roll\\image.jpg");
		try {
			ImageIO.write(bufferedImage, "jpg", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Color brown = new Color(214, 149, 107);
		for (int y = 0; y < 1080; y++) {
			Color color = new Color(bufferedImage.getRGB(1060, y), true);
			if (color.equals(brown)) {
				robot.mouseMove(1060, y);
				mouseclick();
			}
		}
	}

}
