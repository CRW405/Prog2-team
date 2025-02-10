package com.example.teamproject1.filters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;

/* TODO:
 * 
 * IDEAS:
 * overload applyFilter to take in parameters
 */

public abstract class Filter {
    public abstract Image applyFilter(File inputFile) throws IOException;

    public static javafx.scene.image.Image convertBufferedToFx(BufferedImage bufferedImage) { // convert buffered image
                                                                                              // to fx image
        WritableImage fxImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight()); // create new
                                                                                                        // writable
                                                                                                        // image object

        PixelWriter writer = fxImage.getPixelWriter(); // get pixel writer object for new image

        for (int x = 0; x < bufferedImage.getWidth(); x++) { // loop through all the old image pixels and set pixel
                                                             // values for the new image
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                writer.setArgb(x, y, bufferedImage.getRGB(x, y));
            }
        }

        return new ImageView(fxImage).getImage(); // covert image view to javafx image
    }

}