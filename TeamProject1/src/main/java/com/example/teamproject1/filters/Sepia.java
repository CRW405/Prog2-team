package com.example.teamproject1.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
public class Sepia extends Filter{
    
    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {

        BufferedImage inputImage = ImageIO.read(inputFile);

        for (int y = 0; y < inputImage.getHeight(); y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                int pixel = inputImage.getRGB(x, y);
                Color color = new Color(pixel);
                
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // values sourced from
                // https://stackoverflow.com/questions/1061093/how-is-a-sepia-tone-created 
                int filteredRed = (int) Math.min((red * 0.393) + (int) (green * 0.769) + (int) (blue * 0.189), 255);
                int filteredGreen = (int) Math.min((red * 0.349) + (int) (green * 0.686) + (int) (blue * 0.168), 255);
                int filteredBlue = (int) Math.min((red * 0.272) + (int) (green * 0.534) + (int) (blue * 0.131), 255);

                int newColor = new Color(filteredRed, filteredGreen, filteredBlue, alpha).getRGB();
                inputImage.setRGB(x, y, newColor);
            }
        }
        return inputImage;
    }
}
