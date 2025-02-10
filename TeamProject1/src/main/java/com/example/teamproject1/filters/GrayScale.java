package com.example.teamproject1.filters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import java.awt.Color;

public class GrayScale extends Filter {
    public BufferedImage applyFilter(File inputFile) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputFile);

        // loop through y values
        for (int y = 0; y < inputImage.getHeight(); y++) {
            // loop through x values
            for (int x = 0; x < inputImage.getWidth(); x++) {
                // get pixel from x and y
                int pixel = inputImage.getRGB(x, y);
                Color color = new Color(pixel); // create color object from pixel

                // get color values
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // calculate average of red, green, and blue
                int avg = (red + green + blue) / 3;

                // create new color object with new greyscale value
                int newColor = new Color(avg, avg, avg, alpha).getRGB();
                inputImage.setRGB(x, y, newColor); // set new pixel value
            }
        }
        return inputImage;
    }

}
