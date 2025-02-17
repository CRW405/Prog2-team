package com.example.teamproject1.filters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;

import javax.imageio.ImageIO;
import java.awt.Color;

public class Sort extends Filter {
    public BufferedImage applyFilter(File inputFile) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputFile);

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        int[] pixels = new int[width * height]; // create array with size of all pixels

        inputImage.getRGB(0, 0, width, height, pixels, 0, width); // get all pixels from image

        Arrays.sort(pixels);

        inputImage.setRGB(0, 0, width, height, pixels, 0, width); // set all pixels to sorted pixels
        return inputImage;
    }

}
