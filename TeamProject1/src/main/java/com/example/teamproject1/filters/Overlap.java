package com.example.teamproject1.filters;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.stage.FileChooser;

public class Overlap extends Filter{

    // this website helped a lot : https://www.tutorialspoint.com/javafx/javafx_images.htm
    // maybe not directly but it definitely help me get to this point 

    @Override
    public BufferedImage applyFilter(File inputFile) throws IOException {
        // This code will load the image already inputted 
        // hence the ImageIO read
        BufferedImage baseImage = ImageIO.read(inputFile); 

        // chooseOverlayImage() opens up a file chooser
        // it will also show an exception when the user doesn't actually input an image
        File overlayFile = chooseOverlayImage();
        if (overlayFile == null) {
            throw new IOException("No overlay image selected");
        }

        // This will load the image directly onto the first image
        // same image read  
        BufferedImage overlayImage = ImageIO.read(overlayFile);

        // The final image turns it into one final image
        // no image read this time, didn't work at first when i did that  
        BufferedImage outputImage = new BufferedImage(baseImage.getWidth(), baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // At first, the image was only on the top corner so i added this code
        // this scales the image to the base images dimensions 
        Image scaledOverlayImage = overlayImage.getScaledInstance(baseImage.getWidth(), baseImage.getHeight(), Image.SCALE_SMOOTH);

        // Graphics 2D get  the image to draw on the first image 
        // helpful for rendering 2D dimensional figures 
        Graphics2D g2d = outputImage.createGraphics(); 

        //the actual drawing of the base 
        g2d.drawImage(baseImage,0,0, null);

        // float alpha 0.5f is creating a variable for transparency
        // you can even change the transparency from 0.0-1.0 
        // reminder : alpha is transparency and the composite is just the composed amount thing
        float alpha = 0.5f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // making the second image transparent 
        // switch overlayImage to scaledOverlayImage to fix image size issues
        g2d.drawImage(scaledOverlayImage,0,0,null);

        // bye bye graphics2D because its not needed anymore
        // we dont want it to continue throughout the program
        g2d.dispose(); // similar to scan.close();

        return outputImage;
    }

    // NEW METHOD!!! 
    //this is a cool lil method that opens a file chooser similar to what we have in the filter code 
    private File chooseOverlayImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Overlay Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        return fileChooser.showOpenDialog(null);
    }
}

/*
 * hey its me again 
 * sooo the file chooser pops up but nothing happens?? 
 * idek 
 * maybe i need to just be smarter LOL, my brain hurts 
 * 
 * problems : 
 * doesn't work
 * no overlay imaage appear 
 * try sorting pixels through a loop or smt???
 * 
 * so i fixed the transparency (YAY)
 * fixing that was just one line of code and website away 
 * AHHHH IT WORKS 
 */