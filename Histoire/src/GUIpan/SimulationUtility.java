package GUIpan;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * This utility class contains unit functions used by the train simulation.
 * 
 * @see SimuPara
 * @author Tianxiao.Liu@u-cergy.fr
 */
public class SimulationUtility {
	
	/**
	 * Reads a image from an image file.
	 * 
	 * @param filePath the path (from "src") of the image file
	 * @return the read file
	 */
	public static Image readImage(String filePath) {
		try {
			return ImageIO.read(new File(filePath));
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
			return null;
		}
	}

	/**
	 * Generates a new train with a random speed.
	 * 
	 * @param id the new train's id
	 * @return the generated train
	 */



}
