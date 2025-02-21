package com.example.teamproject1.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Inverse extends Filter {

    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputFile);

        for (int y = 0; y < inputImage.getHeight(); y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                int pixel = inputImage.getRGB(x, y);
                Color color = new Color(pixel, true);

                // reverse colors by subtracting from max RGB value
                int alpha = color.getAlpha();
                int red = 255 - color.getRed();
                int green = 255 - color.getGreen();
                int blue = 255 - color.getBlue();

                int newColor = new Color(red, green, blue, alpha).getRGB();
                inputImage.setRGB(x, y, newColor);
            }
        }
        return inputImage;
    }
}
