package com.example.teamproject1.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.Random;


// Logic sourced from http://datamoshing.com/type/image/
// great resource for info on how to create image filters

public class ChannelShift extends Filter {

    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {
        // boilerplate, get img, width, height, random
        Random random = new Random();
        BufferedImage inputImage = ImageIO.read(inputFile);
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // parameters
        int iterations = 5;
        boolean recursiveIterations = true;
        boolean shiftVertically = true;
        boolean shiftHorizontally = true;

        for (int i = 0; i < iterations; i++) {
            // get random color channels to shift, RGB
            int sourceChannel = random.nextInt(3);
            int targetChannel = random.nextInt(3);

            // get random shift values if enabled, and wrap around if reached end of image
            int horizontalShift = shiftHorizontally ? random.nextInt(width) : 0;
            int verticalShift = shiftVertically ? random.nextInt(height) : 0;

            // create a copy of the image to get the source pixel values from
            BufferedImage sourceImage = new BufferedImage(width, height, inputImage.getType()); 
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    sourceImage.setRGB(x, y, inputImage.getRGB(x, y));
                }
            }

            for (int y = 0; y < height; y++) { // loop through the image
                int sourceY = (y + verticalShift) % height; // get the shifted y value, wrap around if needed
                for (int x = 0; x < width; x++) {
                    int sourceX = (x + horizontalShift) % width; // get the shifted x value, wrap around if needed

                    // Get the original pixel's color components
                    int origColor = inputImage.getRGB(x, y);
                    int origAlpha = (origColor >> 24) & 0xFF;
                    int origRed = (origColor >> 16) & 0xFF;
                    int origGreen = (origColor >> 8) & 0xFF;
                    int origBlue = origColor & 0xFF;

                    // Get the pixel color from the shifted position in the source image
                    int shiftedColor = sourceImage.getRGB(sourceX, sourceY);
                    int shiftedRed = (shiftedColor >> 16) & 0xFF;
                    int shiftedGreen = (shiftedColor >> 8) & 0xFF;
                    int shiftedBlue = shiftedColor & 0xFF;

                    // Get the channel value from the source channel
                    int channelValue;
                    switch (sourceChannel) {
                        case 0:
                            channelValue = shiftedRed;
                            break;
                        case 1:
                            channelValue = shiftedGreen;
                            break;
                        default:
                            channelValue = shiftedBlue;
                            break;
                    }

                    // Set the channel value to the target channel
                    switch (targetChannel) {
                        case 0:
                            origRed = channelValue;
                            break;
                        case 1:
                            origGreen = channelValue;
                            break;
                        default:
                            origBlue = channelValue;
                            break;
                    }

                    // Create the new color with the modified channel values
                    int newColor = (origAlpha << 24) | (origRed << 16) | (origGreen << 8) | origBlue;
                    inputImage.setRGB(x, y, newColor);
                }
            }
        }
        return inputImage;
    }
}