package com.example.teamproject1.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.Random;

public class ShuffleTile extends Filter {

    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {
        Random random = new Random();

        BufferedImage inputImage = ImageIO.read(inputFile);
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        int brushSize = 100;

        for (int y = 0; y < height; y += brushSize) {
            for (int x = 0; x < width; x += brushSize) {
                int swapX = random.nextInt(width / brushSize) * brushSize;
                int swapY = random.nextInt(height / brushSize) * brushSize;

                for (int cursorX = 0; cursorX < brushSize; cursorX++) {
                    for (int cursorY = 0; cursorY < brushSize; cursorY++) {
                        if (x + cursorX < width && y + cursorY < height && swapX + cursorX < width
                                && swapY + cursorY < height) {
                            int pixel1 = inputImage.getRGB(x + cursorX, y + cursorY);
                            int pixel2 = inputImage.getRGB(swapX + cursorX, swapY + cursorY);
                            inputImage.setRGB(x + cursorX, y + cursorY, pixel2);
                            inputImage.setRGB(swapX + cursorX, swapY + cursorY, pixel1);
                        }
                    }
                }
            }
        }
        return inputImage;
    }
}