package com.example.teamproject1.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class RedShift extends Filter {

    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputFile);

        for (int y = 0; y < inputImage.getHeight(); y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                int pixel = inputImage.getRGB(x, y);
                Color color = new Color(pixel, true);

                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // red 140%, green 80%, blue 40%
                blue = (int) (blue * 0.4);
                green = (int) (green * 0.8);
                red = Math.min((int) (red * 1.4), 250);

                int newColor = new Color(red, green, blue, alpha).getRGB();
                inputImage.setRGB(x, y, newColor);
            }
        }
        return inputImage;
    }
}
