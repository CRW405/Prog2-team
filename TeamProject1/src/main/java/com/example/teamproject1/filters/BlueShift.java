package com.example.teamproject1.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class BlueShift extends Filter {

    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {

        BufferedImage inputImage = ImageIO.read(inputFile); // read input image

        // all the basic get image things for going through pixels 
        for (int y = 0; y < inputImage.getHeight(); y++) { // loop through all the pixels in the image
            for (int x = 0; x < inputImage.getWidth(); x++) {
                int pixel = inputImage.getRGB(x, y); // getting pixel value
                Color color = new Color(pixel, true); // create the color object from pixel value
                // true hasalpha makes the filter keep the transparency

                // J: we get color values :)
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // red 40%, green 80%, blue 140% or max
                red = (int) (red * 0.4);
                green = (int) (green * 0.8);
                blue = Math.min((int) (blue * 1.4), 250); // J: blue no go higher than 250

                int newColor = new Color(red, green, blue, alpha).getRGB(); // create new color object from new color
                                                                            // values
                inputImage.setRGB(x, y, newColor); // set pixel value
            }
        }
        return inputImage; // return new image
    }
}

// preserving Jay's comments for posterity and historical reasons
/*
 * sooooooo how do colors work???
 * uhhhh how does red or blue or green = grey??
 * boutta give up its 12 am
 * 
 * nvm i didnt.
 * so.. how does this work
 * 
 * dont even KNOW IF THIS WORKS
 * the UI is kinda weird cus it isnt connected??
 * i think im ust bullshitting rn.
 * AM I JUST MAKING UP CODE??? yes.
 * is it correct?? who knows
 * 
 * caleb if you see this, i actually did work
 * so no racist for you
 * 
 * so the color shit makes sense but i dont know what's going on with the filter
 * java area...
 * i hope i didn't break it.
 * ik this is taking up a lot of lines of code but bare with me <3
 * 
 * sooooo todo list :
 * figure out if it WORKS
 * find out how to improve it
 * maybe work on the sepia?? (which i hear is hard)
 * research different techniques for building filters and working with colors of
 * java for present and future projects
 */
