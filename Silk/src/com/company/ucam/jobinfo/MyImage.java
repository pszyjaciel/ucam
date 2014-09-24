package com.company.ucam.jobinfo;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyImage extends JPanel {

	BufferedImage image;

	public void ShowImage() {
		try {
			File input = new File("u:/ucamusers/java/ucam/home/Workspace/Silk2/7jframeH.gif");
			image = ImageIO.read(input);

		} catch (IOException ie) {
			System.out.println("Error:" + ie.getMessage());
		}
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

}
