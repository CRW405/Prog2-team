package com.example.teamproject1.filters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class BlueShift extends Filter{

    public Image applyBlueFilter(File inputFile) throws IOException {

        // this is a variable for 
        BufferedImage loadedImage = ImageIO.read(inputFile);

        // so we estentially have to loop through the x and y values so it can count every inch of the picture
        for (int y = 0; y < loadedImage.getHeight(); y++) {
            for (int x = 0; x < loadedImage.getWidth(); x++) {
                int pixel = loadedImage.getRGB(x, y);
                Color color = new Color(pixel);
                
                // we get color values :) 
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                red = 51; // red value
                green = 181; // green value
                blue = 255; // blue value

                int newPixel = (alpha<<24) | (red<<16) | (green<<8) | blue;
                loadedImage.setRGB(x, y, newPixel);
            }
        }

        Image outputImage = loadedImage; // rename image to outputImage and return
        return outputImage;
    }

    @Override
    public Image applyFilter(File inputFile) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyFilter'");
    } 
}

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
 * so the color shit makes sense but i dont know what's going on with the filter java area...
 * i hope i didn't break it. 
 * ik this is taking up a lot of lines of code but bare with me <3
 * 
 * sooooo todo list :
 * figure out if it WORKS 
 * find out how to improve it 
 * maybe work on the sepia?? (which i hear is hard)
 * research different techniques for building filters and working with colors of java for present and future projects
 * 
 */
