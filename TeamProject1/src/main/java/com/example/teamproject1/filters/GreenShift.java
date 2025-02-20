package com.example.teamproject1.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class GreenShift extends Filter {

    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {

        BufferedImage inputImage = ImageIO.read(inputFile);

        for (int y = 0; y < inputImage.getHeight(); y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                int pixel = inputImage.getRGB(x, y);
                Color color = new Color(pixel);

                // we get color values :)
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // red 40%, green 140%, blue 80%
                red = (int) (red * 0.4);
                blue = (int) (blue * 0.8);
                green = Math.min((int) (green * 1.4), 250);

                int newColor = new Color(red, green, blue, alpha).getRGB();
                inputImage.setRGB(x, y, newColor);
            }
        }
        return inputImage;
    }
}