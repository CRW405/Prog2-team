package com.example.teamproject1.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
public class Sepia extends Filter{
    
    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {

        // this is a variable for the new buffered image
        BufferedImage inputImage = ImageIO.read(inputFile);

        // so we estentially have to loop through the x and y values so it can count every inch of the picture
        for (int y = 0; y < inputImage.getHeight(); y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                int pixel = inputImage.getRGB(x, y);
                Color color = new Color(pixel);
                
                // we get color values :) 
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                var newColor = (int)(red + blue  + green ) /8;

                // Create an Integer for the new values
                int newPixel = (alpha<<24) | (newColor<<16) | (newColor<<8) | newColor;
                inputImage.setRGB(x, y, newPixel);
            }
        }
        return inputImage;
    }
}
